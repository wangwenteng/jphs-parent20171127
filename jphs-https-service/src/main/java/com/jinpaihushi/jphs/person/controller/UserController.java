package com.jinpaihushi.jphs.person.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.account.model.Account;
import com.jinpaihushi.jphs.account.service.AccountService;
import com.jinpaihushi.jphs.information.service.InformationService;
import com.jinpaihushi.jphs.nurse.model.Nurse;
import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.model.NurseJobtitle;
import com.jinpaihushi.jphs.nurse.service.NurseImagesService;
import com.jinpaihushi.jphs.nurse.service.NurseJobtitleService;
import com.jinpaihushi.jphs.nurse.service.NurseService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.user.model.UserAddress;
import com.jinpaihushi.jphs.user.service.UserAddressService;
import com.jinpaihushi.jphs.user.service.UserService;
import com.jinpaihushi.jphs.worktime.model.Worktime;
import com.jinpaihushi.jphs.worktime.service.WorktimeService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.DateUtils;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private NurseImagesService nurseImagesService;

    @Autowired
    private WorktimeService worktimeService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private NurseJobtitleService nurseJobtitleService;

    @Autowired
    private NurseService nurseService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Override
    protected BaseService<User> getService() {
        // TODO Auto-generated method stub
        return null;
    }

    @RequestMapping(path = "/getSessionUser.json", name = "获取session中用户的信息")
    @ResponseBody
    public byte[] getSessionUser(HttpSession hs, HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("user.getSessionUser.json");
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null) {
                return JSONUtil.toJSONResult(0, "您还没有登录！", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", user);
        }
        catch (Exception e) {
            Util.failLog.error("user.getSessionUser.json", e);
        }
        return null;
    }

    @SuppressWarnings("static-access")
    @RequestMapping(path = "/getUserDetail.json", name = "用户个人资料")
    @ResponseBody
    public byte[] getUserDetail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("user.getUserDetail.json userId=" + userId);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            // 验证token
            String token = req.getHeader("token");
            //            if (StringUtils.isEmpty(token)) {
            //                return JSONUtil.toJSONResult(3, "非法请求", null);
            //            }
            User user = userService.loadById(userId);
            if (null != user && StringUtils.isEmpty(user.getName()))
                user.setName(user.getPhone());
            if (user.getType() == 0) {
                Nurse nurse_s = new Nurse();
                nurse_s.setCreatorId(user.getId());

                Nurse n = nurseService.load(nurse_s);
                if (n != null) {
                    if (n.getWorkYears() != null) {
                        long wk = DateUtils.getDistanceDays(new Date(), n.getWorkYears());
                        n.setWorkYear(new String().valueOf(wk / 365));
                    }
                    if (StringUtils.isNotEmpty(n.getSfz()) && n.getSfz().length() > 14) {
                        long wk = DateUtils.getDistanceDays(new Date(), sdf.parse(n.getSfz().substring(6, 14)));
                        n.setAge(Integer.parseInt(new String().valueOf(wk / 365)));
                    }
                    else {
                        n.setAge(1);
                    }
                    user.setNurse(new JSONObject().fromObject(n));
                    NurseJobtitle jobtitle = new NurseJobtitle();
                    jobtitle.setCreatorId(userId);
                    jobtitle.setStatus(1);
                    String roleName = "";
                    List<NurseJobtitle> list = nurseJobtitleService.list(jobtitle);
                    for (NurseJobtitle nurseJobtitle : list) {
                        //                    .护士，2.康复师，3.母婴师
                        switch (nurseJobtitle.getType()) {
                        case 1:
                            roleName += "护士/";
                            break;
                        case 2:
                            roleName += "康复师/";
                            break;
                        case 3:
                            roleName += "母婴师/";
                            break;
                        default:
                            break;
                        }
                    }
                    if (roleName.length() > 0)
                        roleName = roleName.substring(0, roleName.length() - 1);
                    user.getNurse().put("roleName", roleName);
                }
            }
            token = Common.getToken(user.getPhone(), user.getPassword());// 生成token
            user.setToken(token);
            NurseImages img = new NurseImages();
            img.setSourceId(user.getId());
            img.setType(1);
            img.setStatus(1);
            img = nurseImagesService.load(img);
            if (img != null)
                user.setHeadPicture(img.getUrl());
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            //            if (!flag) {
            //                // 身份认证失败,返回错误信息
            //                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            //            }
            Account account = new Account();
            account.setCreatorId(userId);
            Account result = accountService.load(account);
            user.setBalance(result.getBalance());
            return JSONUtil.toJSONResult(1, "操作成功！", user);
        }
        catch (Exception e) {
            Util.failLog.error("user.getUserDetail.json userId=" + userId, e);
        }
        return null;
    }

    @RequestMapping(path = "/updateUserInfo.json", name = "用户修改个人资料", method = RequestMethod.POST)
    @ResponseBody
    public byte[] updateUserInfo(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, User user) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("user.updateUserInfo.json");
            }
            if (StringUtils.isEmpty(user.getId())) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            boolean flag = false;
            String token = req.getHeader("token");
            //            if (StringUtils.isEmpty(token)) {
            //                return JSONUtil.toJSONResult(3, "非法请求", null);
            //            }
            User seesion = userService.loadById(user.getId());
            //            boolean flag = Common.CheckPerson(seesion.getPhone(), seesion.getPassword(), token);
            //            if (!flag) {
            //                // 身份认证失败,返回错误信息
            //                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            //            }
            user.setType(seesion.getType());
            flag = userService.update(user);
            if (StringUtils.isNotEmpty(user.getHeadPicture())) {
                NurseImages nurseImages = new NurseImages();
                nurseImages.setSourceId(user.getId());
                nurseImages.setType(1);
                nurseImages.setStatus(1);
                nurseImages = nurseImagesService.load(nurseImages);
                if (nurseImages == null) {
                    nurseImages = new NurseImages();
                    nurseImages.setId(UUIDUtils.getId());
                    nurseImages.setUrl(user.getHeadPicture());
                    nurseImages.setSourceId(user.getId());
                    nurseImages.setType(1);
                    nurseImages.setStatus(1);
                    nurseImages.setCreatorId(user.getId());
                    nurseImages.setCreatorName(seesion.getName());
                    nurseImages.setCreateTime(new Date());
                    String s = nurseImagesService.insert(nurseImages);
                    if (s.length() > 0) {
                        flag = true;
                    }
                }
                else {
                    nurseImages.setUrl(user.getHeadPicture());
                    nurseImages.setCreatorId(user.getId());
                    nurseImages.setCreatorName(seesion.getName());
                    nurseImages.setCreateTime(new Date());
                    flag = nurseImagesService.update(nurseImages);
                }
                return JSONUtil.toJSONResult(1, "上传成功！", user);
            }
            if (!flag) {

                return JSONUtil.toJSONResult(0, "操作失败", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", user);
        }
        catch (Exception e) {
            Util.failLog.error("user.updateUserInfo.json", e);
        }
        return null;
    }

    @RequestMapping(path = "/getUserAccount.json", name = "个人账户", method = RequestMethod.POST)
    @ResponseBody
    public byte[] getUserAccount(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("user.updateUserInfo.json userId=" + userId);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            String token = req.getHeader("token");
            //            if (StringUtils.isEmpty(token)) {
            //                return JSONUtil.toJSONResult(3, "非法请求", null);
            //            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
                user = userService.loadById(userId);
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            //            if (!flag) {
            //                // 身份认证失败,返回错误信息
            //                return JSONUtil.toJSONResult(2, "身份认证失败", null);
            //            }
            Account account = new Account();
            account.setCreatorId(userId);
            Account result = accountService.load(account);
            if (result == null) {
                return JSONUtil.toJSONResult(0, "操作失败", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            Util.failLog.error("user.updateUserInfo.json userId=" + userId, e);
        }
        return null;
    }

    @RequestMapping(path = "/findWorkTime.json", name = "选择上门时间")
    @ResponseBody
    public byte[] findWorkTime(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            String productId) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("user.findWorkTime.json userId=" + userId + " productId=" + productId);
            }
            if (StringUtils.isEmpty(productId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            String token = req.getHeader("token");
            //			if (StringUtils.isEmpty(token)) {
            //				return JSONUtil.toJSONResult(3, "非法请求", null);
            //			}
            //			User user = (User) req.getSession().getAttribute("user");
            //			if (user == null)
            //				user = userService.loadById(userId);
            //			boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            //			if (!flag) {
            //				// 身份认证失败,返回错误信息
            //				return JSONUtil.toJSONResult(2, "身份认证失败", null);
            //			}
            List<Worktime> result = worktimeService.findUserWorkTime(productId);
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            Util.failLog.error("user.findWorkTime.json userId=" + userId + " productId=" + productId, e);
        }
        return null;
    }

    @RequestMapping(path = "/getCollection.json", name = "我的收藏")
    @ResponseBody
    public byte[] getCollection(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            @RequestParam(value = "p", defaultValue = "1", required = true) Integer p,
            @RequestParam(value = "n", defaultValue = "10", required = true) Integer n, String userId) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("user.getCollection.json userId=" + userId);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
            	return JSONUtil.toJSONResult(3, "非法请求", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
            	user = userService.loadById(userId);
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
            	// 身份认证失败,返回错误信息
            	return JSONUtil.toJSONResult(2, "身份认证失败", null);
            } */
            PageHelper.startPage(p, n);
            List<Map<String, Object>> informationList = informationService.getCollection(userId);
            PageInfo<Map<String, Object>> result = new PageInfo<Map<String, Object>>(informationList);
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            Util.failLog.error("user.getCollection.json userId=" + userId, e);
        }
        return null;
    }

    @RequestMapping(path = "/deleteCollection.json", name = "删除我的收藏")
    @ResponseBody
    public byte[] deleteCollection(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            String informationIds, String userId) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog
                        .debug("user.deleteCollection.json informationIds=" + informationIds + " userId=" + userId);
            }
            if (StringUtils.isEmpty(informationIds)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }

            // String token = req.getHeader("token");
            // if (StringUtils.isEmpty(token)) {
            // return JSONUtil.toJSONResult(3, "非法请求", null);
            // }
            // User user = (User) req.getSession().getAttribute("user");
            // if (user == null)
            // user = userService.loadById(userId);
            // boolean flag = Common.CheckPerson(user.getPhone(),
            // user.getPassword(), token);
            // if (!flag) { // 身份认证失败,返回错误信息
            // return JSONUtil.toJSONResult(2, "身份认证失败", null);
            // }

            int i = informationService.deleteCollection(informationIds, userId);
            if (i < 1) {
                return JSONUtil.toJSONResult(0, "操作失败！", i);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", i);
        }
        catch (Exception e) {
            Util.failLog.error("user.deleteCollection.json informationIds=" + informationIds + " userId=" + userId, e);
        }
        return null;
    }

    /**
     * goodsId 服务id 默认不传
     * type 排序规则 1、离我最近 2、服务次数最多 3、护龄最长 默认不传
     * lon 经度
     * lat 纬度
     * nurseName 护士姓名
     * @param query
     * @return
     */
    @RequestMapping(path = "/getNurseList.json", name = "护士列表")
    @ResponseBody
    public byte[] getNurseList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            @RequestParam(value = "lon", defaultValue = "116.403119", required = true) String lon,
            @RequestParam(value = "lat", defaultValue = "39.914492", required = true) String lat, String goodsId,
            /*@RequestParam(value = "type", defaultValue = "0", required = true) Integer type,*/ String nurseName,
            @RequestParam(value = "p", defaultValue = "1", required = true) Integer p,
            @RequestParam(value = "n", defaultValue = "10", required = true) Integer n) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("user.getNurseList.json lon=" + lon + " lat=" + lat + " goodsId=" + goodsId
                /* + " type=" + type*/ + " nurseName=" + nurseName);
            }
            if (StringUtils.isEmpty(lon) || StringUtils.isEmpty(lat)) {
                lon = "116.403119";
                lat = "39.914492";
            }
            // String token = req.getHeader("token");
            // if (StringUtils.isEmpty(token)) {
            // return JSONUtil.toJSONResult(3, "非法请求", null);
            // }
            // User user = (User) req.getSession().getAttribute("user");
            // if (user == null)
            // user = userService.loadById(userId);
            // boolean flag = Common.CheckPerson(user.getPhone(),
            // user.getPassword(), token);
            // if (!flag) { // 身份认证失败,返回错误信息
            // return JSONUtil.toJSONResult(2, "身份认证失败", null);
            // }
            Map<String, Object> query = new HashMap<>();
            query.put("lat", lat);
            query.put("lon", lon);
            query.put("goodsId", goodsId);
            /*query.put("type", type);*/
            query.put("nurseName", nurseName);
            if (StringUtils.isNotEmpty(nurseName)) {
                query.put("type", 1);
            }
            PageHelper.startPage(p, n);
            List<Map<String, Object>> list = nurseJobtitleService.getNurseList(query);
            PageInfo<Map<String, Object>> result = new PageInfo<Map<String, Object>>(list);
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            Util.failLog.error("user.getNurseList.json lon=" + lon + " lat=" + lat + " goodsId=" + goodsId /*+ " type="
                                                                                                           + type*/
                    + " nurseName=" + nurseName, e);
        }
        return null;
    }

    @RequestMapping(path = "/getReceiveAddress.json", name = "查询收货地址")
    @ResponseBody
    public byte[] getReceiveAddress(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId,
            Integer defaultAddress) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog
                        .debug("user.getReceiveAddress.json userId=" + userId + ",defaultAddress = " + defaultAddress);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            UserAddress userAddress = new UserAddress();

            userAddress.setDefaultAddress(defaultAddress);

            userAddress.setCreatorId(userId);
            List<UserAddress> UserAddressList = userAddressService.getReceiveAddress(userAddress);

            if (UserAddressList.size() < 0) {
                return JSONUtil.toJSONResult(0, "操作失败！", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", UserAddressList);
        }
        catch (Exception e) {
            Util.failLog.error("user.getReceiveAddress.json userId = " + userId + ",defaultAddress = " + defaultAddress,
                    e);
        }
        return null;
    }

    @RequestMapping(path = "/insertReceiveAddress.json", name = "添加收货地址")
    @ResponseBody
    public byte[] insertReceiveAddress(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            UserAddress userAddress) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("user.insertReceiveAddress.json CreatorId=" + userAddress.getCreatorId());
            }
            if (StringUtils.isEmpty(userAddress.getCreatorId())) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }

            if (userAddress.getDefaultAddress() == 0) {

                UserAddress userAddressModel = new UserAddress();
                userAddressModel.setStatus(0);

                userAddressModel.setCreatorId(userAddress.getCreatorId());
                List<UserAddress> list = userAddressService.getReceiveAddress(userAddressModel);
                if (list.size() == 0) {
                    userAddress.setDefaultAddress(1);
                }

            }
            else {
                UserAddress userAddressModel = new UserAddress();
                userAddressModel.setStatus(0);
                userAddressModel.setDefaultAddress(1);
                userAddressModel.setCreatorId(userAddress.getCreatorId());
                List<UserAddress> list = userAddressService.getReceiveAddress(userAddressModel);
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setDefaultAddress(0);
                        userAddressService.update(list.get(i));
                    }
                }
            }

            userAddress.setId(UUID.randomUUID().toString());

            userAddress.setStatus(0);
            String result = userAddressService.insert(userAddress);

            if (result == null) {
                return JSONUtil.toJSONResult(0, "操作失败！", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            Util.failLog.error("user.insertReceiveAddress.json CreatorId=" + userAddress.getCreatorId(), e);
        }
        return null;
    }

    @RequestMapping(path = "/deleteReceiveAddress.json", name = "删除收货地址")
    @ResponseBody
    public byte[] deleteReceiveAddress(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            UserAddress userAddress) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("user.deleteReceiveAddress.json id=" + userAddress.getId());
            }
            if (StringUtils.isEmpty(userAddress.getId())) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            userAddress.setDefaultAddress(0);
            userAddress.setStatus(-1);
            boolean b = userAddressService.update(userAddress);

            if (!b) {
                return JSONUtil.toJSONResult(0, "操作失败！", null);
            }
            else {
                userAddress.setStatus(0);
                userAddress.setDefaultAddress(1);
                List<UserAddress> list = userAddressService.getReceiveAddress(userAddress);

                if (list.size() == 0) {
                    userAddress.setDefaultAddress(0);
                    List<UserAddress> updatelist = userAddressService.getReceiveAddress(userAddress);
                    if (updatelist.size() > 0) {
                        updatelist.get(0).setDefaultAddress(1);
                        userAddressService.update(updatelist.get(1));
                    }

                }
            }

            return JSONUtil.toJSONResult(1, "操作成功！", b);
        }
        catch (Exception e) {
            Util.failLog.error("user.deleteReceiveAddress.json id = " + userAddress.getId(), e);
        }
        return null;
    }

    @RequestMapping(path = "/updateReceiveAddress.json", name = "编辑收货地址")
    @ResponseBody
    public byte[] updateReceiveAddress(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            UserAddress userAddress) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("user.updateReceiveAddress.json ");
            }
            if (StringUtils.isEmpty(userAddress.getId())) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            if (userAddress.getCreatorId() != null) {
                UserAddress userAddressModel = new UserAddress();
                userAddressModel.setStatus(0);
                userAddressModel.setDefaultAddress(1);
                userAddressModel.setCreatorId(userAddress.getCreatorId());
                List<UserAddress> list = userAddressService.getReceiveAddress(userAddressModel);

                if (userAddress.getDefaultAddress() == 1) {
                    if (list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setDefaultAddress(0);
                            System.out.println();
                            userAddressService.update(list.get(i));
                        }
                    }
                }

            }

            boolean b = userAddressService.update(userAddress);

            if (b == false) {
                return JSONUtil.toJSONResult(0, "操作失败！", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", b);
        }
        catch (Exception e) {
            Util.failLog.error("user.updateReceiveAddress.json userAddressId ", e);
        }
        return null;
    }

    @RequestMapping(path = "/getAddressDetail.json", name = "查询收货地址")
    @ResponseBody
    public byte[] getAddressDetail(String id) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("user.getAddressDetail.json Id=" + id);
            }
            if (StringUtils.isEmpty(id)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            UserAddress userAddress = userAddressService.loadById(id);

            if (userAddress == null) {
                return JSONUtil.toJSONResult(0, "操作失败！", null);
            }
            return JSONUtil.toJSONResult(1, "操作成功！", userAddress);
        }
        catch (Exception e) {
            Util.failLog.error("user.getAddressDetail.json id = " + id, e);
        }
        return null;
    }

}
