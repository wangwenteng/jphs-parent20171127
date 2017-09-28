package com.jinpaihushi.jphs.wechat.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.wechat.dao.WechatDao;
import com.jinpaihushi.jphs.wechat.model.Wechat;
import com.jinpaihushi.jphs.wechat.model.WechatUser;
import com.jinpaihushi.jphs.wechat.service.WechatService;
import com.jinpaihushi.jphs.wechat.service.WechatUserService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.util.PropertiesUtil;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;
import com.jinpaihushi.wechat.utils.HttpRequest;
import com.jinpaihushi.wechat.utils.WechatHttp;
import com.jinpaihushi.wechat.utils.WechatJStokenUtils;

import net.sf.json.JSONObject;

/**
 * 
 * @author scj
 * @date 2017-09-03 09:50:11
 * @version 1.0
 */
@Service("wecthService")
@Configurable
@EnableScheduling
public class WechatServiceImpl extends BaseServiceImpl<Wechat> implements WechatService {

	@Autowired
	private WechatDao wecthDao;

	@Autowired
	private WechatUserService wechatUserService;

	@Override
	protected BaseDao<Wechat> getDao() {
		return wecthDao;
	}

	//@Scheduled(cron = "${GET_WECTH_TOKEN}")
	public void getTokens() {
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("我被线程池调用执行啦~！");
		}
		boolean falg_token = true;
		boolean token_falg = true;
		int ft = 1;
		while (falg_token) {
			boolean f_token = getToken();
			token_falg = f_token;
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("获取token第-" + ft + "-次(" + f_token + ")");
			}
			if (f_token || ft == 3) {
				falg_token = false;
			}
			ft++;
		}
		if (token_falg) {
			boolean falg_ticket = true;
			int tt = 1;
			while (falg_ticket) {
				
				boolean ti_token = getTicket();
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("获取ticket第-" + tt + "-次(" + ti_token + ")");
				}
				if (ti_token || tt == 3) {
					falg_ticket = false;
				}
				tt++;
			}
		}
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("获取微信token完成");
		}
	}

	public Wechat getUnusedAccessToken(Integer type) {
		return wecthDao.getUnusedAccessToken(type);
	}

	/**
	 * 获取到微信用户的信息
	 * 
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String getWXUserInfo(String code) {
		// 读取配置文件类
		Properties pps = new Properties();
		InputStream in = PropertiesUtil.class.getResourceAsStream("/config/wechatConfig.properties");
		String APPID = "";
		String secret = "";

		try {
			pps.load(in);

			APPID = pps.getProperty("APP_ID_TEST");
			secret = pps.getProperty("APP_SECRET_WCHAT_TEST");
		} catch (IOException e) {
		}
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("wecthService.getWXUserInfo,APPID=" + APPID + "-secret=" + secret);
		}

		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID + "&secret=" + secret + "&code="
				+ code + "&grant_type=authorization_code";
		// 返回参数
		// String ret="无"; //access_token + refresh_token + openid
		String jsonResult = WechatHttp.get(url, "UTF-8");// 得到JSON字符串
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("wecthService.getWXUserInfo,jsonResult=" + jsonResult);
		}
		JSONObject object = JSONObject.fromObject(jsonResult);
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("wecthService.getWXUserInfo,object=" + object);
		}
		if (object.containsKey("errmsg")) {
			return object.toString();
		}

		String access_token = object.getString("access_token");
		String refresh_token = object.getString("refresh_token");
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug(
					"wecthService.getWXUserInfo,access_token=" + access_token + "--refresh_token" + refresh_token);
		}
		String openid = object.getString("openid");
		// 获取用户信息
		String userUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid
				+ "&lang=zh_CN";
		String userResult = WechatHttp.get(userUrl, "UTF-8");
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("wecthService.getWXUserInfo,1-userResult=" + userResult);
		}
		JSONObject userObject = JSONObject.fromObject(userResult);

		if (userObject.containsKey("errmsg")) {
			// 刷新token
			String update = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + APPID
					+ "&grant_type=refresh_token&refresh_token=" + refresh_token;
			String updateResult = WechatHttp.get(update, "UTF-8"); // 执行刷新
			JSONObject updateObject = JSONObject.fromObject(updateResult);

			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("wecthService.getWXUserInfo,2-updateObject=" + updateObject);
			}
			// 刷新后重新赋值
			access_token = updateObject.getString("access_token");
			refresh_token = updateObject.getString("refresh_token");
			openid = updateObject.getString("openid");

			// 重新提交一次
			userUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid
					+ "&lang=zh_CN";
			userResult = WechatHttp.get(userUrl, "UTF-8");
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("wecthService.getWXUserInfo,2-userResult=" + userResult);
			}
		}
		WechatUser wechatUser = new WechatUser();
		JSONObject json_user = new JSONObject().fromObject(userResult);
		wechatUser.setId(UUIDUtils.getId());
		wechatUser.setCreateTime(new Date());
		wechatUser.setStatus(1);
		wechatUser.setAppid(APPID);
		if (json_user.containsKey("errmsg")) {
			wechatUser.setStatus(0);
			wechatUser.setErrmsg(json_user.getString("errmsg"));
			if (json_user.containsKey("errcode"))
				wechatUser.setErrcode(json_user.getString("errcode"));
		} else {
			wechatUser.setErrmsg("1");
			wechatUser.setErrcode("1");
			if (json_user.containsKey("openid"))
				wechatUser.setOpenid(json_user.getString("openid"));
			if (json_user.containsKey("nickname"))
				wechatUser.setNickname(json_user.getString("nickname"));
			if (json_user.containsKey("sex"))
				wechatUser.setSex(json_user.getString("sex"));
			if (json_user.containsKey("province"))
				wechatUser.setProvince(json_user.getString("province"));
			if (json_user.containsKey("city"))
				wechatUser.setCity(json_user.getString("city"));
			if (json_user.containsKey("country"))
				wechatUser.setCountry(json_user.getString("country"));
			if (json_user.containsKey("headimgurl"))
				wechatUser.setHeadimgurl(json_user.getString("headimgurl"));
			if (json_user.containsKey("privilege"))
				wechatUser.setPrivilege(json_user.getString("privilege"));
			if (json_user.containsKey("unionid"))
				wechatUser.setUnionid(json_user.getString("unionid"));
		}
		boolean flag = true;
		if(!StringUtils.isEmpty(wechatUser.getOpenid())){
			WechatUser wechatUser_ifnull = new WechatUser();
			wechatUser_ifnull.setOpenid(wechatUser.getOpenid());
			wechatUser_ifnull.setStatus(1);
			List<WechatUser> wechatUser_list = wechatUserService.list(wechatUser_ifnull);
			if(wechatUser_list!=null && wechatUser_list.size() > 0){
				flag = false;
				wechatUser.setId(wechatUser_list.get(0).getId());
				if(wechatUser_list.size() > 1){
					for(int i=1;i < wechatUser_list.size();i++){
						wechatUserService.deleteById(wechatUser_list.get(i).getId());
					}
				}
			}
		}
		if(flag){
			wechatUserService.insert(wechatUser);
		} else{
			wechatUserService.update(wechatUser);
		}
		return userResult;
	}

	/**
	 * 获取微信 accrss_token
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public boolean getToken() {
		boolean falg = true;
		// 读取配置文件类
		Properties pps = new Properties();
		InputStream in = PropertiesUtil.class.getResourceAsStream("/config/wechatConfig.properties");

		String APPID = "";
		String APPSECRET = "";
		try {
			pps.load(in);
			APPID = pps.getProperty("APP_ID_TEST");
			APPSECRET = pps.getProperty("APP_SECRET_WCHAT_TEST");
		} catch (IOException e) {
		}
		String token_str = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token",
				"grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET, "");
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("token_str==" + token_str);
		}
		try {
			JSONObject json_token = new JSONObject().fromObject(token_str);
			Wechat wechat = new Wechat();
			wechat.setAppid(APPID);
			wechat.setCreateTime(new Date());
			wechat.setId(UUIDUtils.getId());
			wechat.setType(1);

			if (json_token.containsKey("access_token")) {
				String token = json_token.getString("access_token");
				String expires_in = json_token.getString("expires_in");
				wechat.setAccessToken(token);
				wechat.setExpiresIn(expires_in);
				wechat.setTicket("0");
				wechat.setErrcode("0");
				wechat.setErrmsg("正常");
				if("wxc1662397992295bf".equals(APPID)){
					wechat.setStatus(1);
				}else{
					wechat.setStatus(0);
				}
			} else if (json_token.containsKey("errcode")) {
				// {"errcode":40013,"errmsg":"invalid appid"}
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("errcode:" + json_token.getString("errcode"));
					Util.debugLog.debug("errmsg:" + json_token.getString("errmsg"));
				}
				wechat.setTicket("0");
				wechat.setAccessToken("0");
				wechat.setExpiresIn("0");
				wechat.setStatus(0);
				wechat.setErrcode(json_token.getString("errcode"));
				wechat.setErrmsg(json_token.getString("errmsg"));
				falg = false;
			}
			wecthDao.insert(wechat);
		} catch (Exception e1) {
			falg = false;
			Util.failLog.error("获取微信公众号token加密 ", e1);
		}
		return falg;
	}

	/**
	 * 获取微信公众号JS加密tikei_token
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public boolean getTicket() {
		boolean falg = true;
		try {
			Wechat w = wecthDao.getUnusedAccessToken(1);
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("获取微信公众号JS加密tikei_tokenw==" + w);
			}
			if (w != null) {
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("获取微信公众号JS加密tikei_tokenw==" + new JSONObject().fromObject(w));
				}
			}
			String ticket_str = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket",
					"access_token=" + w.getAccessToken() + "&type=jsapi", "");
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("获取微信公众号JS加密    ticket_str==" + ticket_str);
			}
			JSONObject json_ticket = new JSONObject().fromObject(ticket_str);
			Wechat wechat = new Wechat();
			wechat.setAppid(w.getAppid());
			wechat.setAccessToken(w.getAccessToken());
			wechat.setCreateTime(new Date());
			wechat.setId(UUIDUtils.getId());
			wechat.setType(2);
			if (json_ticket.containsKey("ticket")) {
				if("wxc1662397992295bf".equals(w.getAppid())){
					wechat.setStatus(1);
				}else{
					wechat.setStatus(0);
				}
				wechat.setExpiresIn(json_ticket.getString("expires_in"));
				wechat.setTicket(json_ticket.getString("ticket"));
				wechat.setErrcode("0");
				wechat.setErrmsg("正常");
			} else if (json_ticket.containsKey("errcode")) {
				// {"errcode":40013,"errmsg":"invalid appid"}
				if (Util.debugLog.isDebugEnabled()) {
					Util.debugLog.debug("errcode:" + json_ticket.getString("errcode"));
					Util.debugLog.debug("errmsg:" + json_ticket.getString("errmsg"));
				}
				wechat.setStatus(0);
				wechat.setExpiresIn("0");
				wechat.setTicket("0");
				wechat.setErrcode(json_ticket.getString("errcode"));
				wechat.setErrmsg(json_ticket.getString("errmsg"));
				falg = false;
			}
			wecthDao.insert(wechat);
		} catch (Exception e1) {
			falg = false;
			Util.failLog.error("获取微信公众号JS加密 ", e1);
		}
		return falg;
	}

	/**
	 * 获取分享签名
	 */
	@SuppressWarnings("static-access")
	public String getMenuShare(String url_p) {
		Wechat w = wecthDao.getUnusedAccessToken(2);
		Map<String, String> ret = WechatJStokenUtils.sign(w.getTicket(), url_p);
		JSONObject json_s = new JSONObject().fromObject(ret);
		json_s.put("appid", w.getAppid());
		json_s.put("resultcode", 1);
		return json_s.toString();
	}
	
	/**
	 * 判断用户是否关注公众号
	 * @param openId
	 * @return
	 */
	@SuppressWarnings("static-access")
	public int getUserWecthIfFollow(String openId){
		Wechat w = wecthDao.getUnusedAccessToken(1);
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("获取微信公众    getUserWecthIfFollow==>>>>>>AccessToken==" +  w.getAccessToken());
		}
		String user_str = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/user/info","access_token=" + w.getAccessToken() + "&openid="+openId+"&lang=zh_CN", "");
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("获取微信公众    getUserWecthIfFollow==>>>>>>user_str==" +  user_str);
		}
		if(StringUtils.isEmpty(user_str)){
			return 0;
		}
		JSONObject user_str_json = new JSONObject().fromObject(user_str);
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("获取微信公众    getUserWecthIfFollow==>>>>>>user_str_json==" +  user_str_json);
		}
		if(!user_str_json.containsKey("subscribe")){
			return 0;
		}
		int subscribe = user_str_json.getInt("subscribe");
		if (Util.debugLog.isDebugEnabled()) {
			Util.debugLog.debug("获取微信公众    getUserWecthIfFollow==>>>>>>subscribe==" +  subscribe);
		}
		return subscribe;
	}

}