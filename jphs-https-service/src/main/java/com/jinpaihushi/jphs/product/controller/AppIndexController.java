package com.jinpaihushi.jphs.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.activity.model.Advertising;
import com.jinpaihushi.jphs.activity.service.AdvertisingService;
import com.jinpaihushi.jphs.adposition.service.AdpositionService;
import com.jinpaihushi.jphs.goods.service.GoodsService;
import com.jinpaihushi.jphs.information.service.InformationService;
import com.jinpaihushi.jphs.platform.service.PlatformService;
import com.jinpaihushi.jphs.sysversion.model.Sysversion;
import com.jinpaihushi.jphs.sysversion.service.SysversionService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/app")
public class AppIndexController {

    @Autowired
    private PlatformService platformService;

    @Autowired
    private AdvertisingService advertisingService;

    @Autowired
    private AdpositionService adpositionService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SysversionService sysversionService;

    /**
     * 
     * @param hs				session
     * @param req				
     * @param resp				
     * @param platformId		平台ID
     * @param authCode			验证code
     * @param deviceType		端
     * @param columnId			热门服务栏目ID
     * @return
     */

    @ResponseBody
    @RequestMapping(name = "app首页展示", path = "/index.json")
    public byte[] appindex(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String platformId,
            String authCode, Integer deviceType, String columnId, String siteId, String channelId) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("app.index.json,platformId=" + platformId + " deviceType=" + deviceType
                        + " authCode=" + authCode + " channelId=" + channelId);
            }
            // 查空
            if (StringUtils.isEmpty(columnId) || StringUtils.isEmpty(siteId) || StringUtils.isEmpty(platformId)
                    || deviceType == null || StringUtils.isEmpty(channelId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            //	轮播图集合
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("atype", 2);
            map.put("stype", 1);
            List<Advertising> advertisingList = advertisingService.getCarouselFigure(map);

            //	品类集合
            List<Map<String, Object>> productlist = platformService.getProductList(platformId, deviceType);

            //	广告位集合
            List<Map<String, Object>> adpositionList = adpositionService.getAppindex(deviceType);

            //	资讯集合
            Map<String, Object> query = new HashMap<>();
            query.put("channelId", channelId);
            query.put("top", 1);
            query.put("num", 5);
            List<Map<String, Object>> informationList = informationService.listapp(query);

            //	定制服务	新品推荐

            //	热门服务
            Map<String, Object> map_columnId = new HashMap<String, Object>();
            map_columnId.put("columnId", columnId);
            map_columnId.put("siteId", siteId);
            map_columnId.put("deviceType", deviceType);
            List<Map<String, Object>> columnGoodsList = goodsService.getColumnGoods(map_columnId);

            // 1.根据 name，password,type查询完整信息
            // 2.错误N种情况判断及返回前端
            // 3.信息无误，封装信息以及生成token，返回前端

            Map<String, Object> re_map = new HashMap<String, Object>();
            re_map.put("advertising", advertisingList); //	轮播图
            re_map.put("product", productlist); //	品类
            re_map.put("adposition", adpositionList); //	广告
            re_map.put("information", informationList); //	资讯
            re_map.put("columnGoods", columnGoodsList); //	热门服务

            return JSONUtil.toJSONResult(1, "操作成功！", re_map);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("app.index.json,platformId=" + platformId + " deviceType=" + deviceType + " authCode="
                    + authCode + " channelId=" + channelId, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "app版本号", path = "/SysVersion.json")
    public byte[] appSysVersion(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String authCode,
            String versionNumber, Integer device) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("app.SysVersion.json,device=" + device + " authCode=" + authCode);
            }
            // 查空
            if (device == null) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            Map<String, Object> re_map = new HashMap<String, Object>();

            try {
                Sysversion sysversion = sysversionService.appVersion(device);
                if (sysversion != null) {
                    if (StringUtils.isNotEmpty(versionNumber) && versionNumber.equals(sysversion.getVersion())) {
                        re_map.put("version", false);
                    }
                    else {
                        re_map.put("version", true);
                        re_map.put("content", sysversion);
                    }
                }
                else {
                    return JSONUtil.toJSONResult(0, "操作失败,参数错误！", "");
                }
            }
            catch (Exception e) {
                return JSONUtil.toJSONResult(0, "操作失败！", "");
            }
            return JSONUtil.toJSONResult(1, "操作成功！", re_map);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("app.index.json,device=" + device + " authCode=" + authCode, e);
        }
        return null;

    }

}
