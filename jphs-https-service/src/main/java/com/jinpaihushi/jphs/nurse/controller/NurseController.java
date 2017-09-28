package com.jinpaihushi.jphs.nurse.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinpaihushi.jphs.goods.model.Grade;
import com.jinpaihushi.jphs.nurse.model.Nurse;
import com.jinpaihushi.jphs.nurse.service.NurseService;
import com.jinpaihushi.jphs.price.service.PriceNurseService;
import com.jinpaihushi.jphs.user.model.UserAddress;
import com.jinpaihushi.jphs.user.service.UserAddressService;
import com.jinpaihushi.jphs.worktime.model.Worktime;
import com.jinpaihushi.jphs.worktime.service.WorktimeService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/nurse")
public class NurseController {
    @Autowired
    private WorktimeService worktimeService;

    @Autowired
    private PriceNurseService priceNurseService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private NurseService nurseService;

    @RequestMapping(path = "/getHomepage.json", name = "用户端护士主页")
    @ResponseBody
    public byte[] getNurseList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            @RequestParam(value = "lon", defaultValue = "116.403119", required = true) String lon,
            @RequestParam(value = "lat", defaultValue = "39.914492", required = true) String lat, String userId) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurse.getHomepage.json userId=" + userId);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            if (StringUtils.isEmpty(lon) || StringUtils.isEmpty(lat)) {
                lon = "116.403119";
                lat = "39.914492";
            }
            // 判断护士的状态是否是已经审核通过
            Nurse nurse_s = new Nurse();
            nurse_s.setCreatorId(userId);
            Nurse n = nurseService.load(nurse_s);
            if (n == null) {
                return JSONUtil.toJSONResult(0, "非法操作！", null);
            }
            if (n.getStatus() == 0) {
                return JSONUtil.toJSONResult(0, "您还为通过审核！", null);
            }
            // 护士基本信息
            Map<String, Object> query = new HashMap<>();
            query.put("userId", userId);
            query.put("lon", lon);
            query.put("lat", lat);
            List<Map<String, Object>> basicInfo = nurseService.getBasicInfo(query);
            // 服务项目
            List<Map<String, Object>> serviceItems = priceNurseService.getServiceItems(userId);
            // 工作时间
            Worktime time = new Worktime();
            time.setUserid(userId);
            int i = worktimeService.count(time);
            if (i == 0) {
                worktimeService.insertNurseWorkTime(userId);
            }
            List<Worktime> worktime = worktimeService.getNurseWorktime(userId, null);
            Map<String, Object> result = new HashMap<>();
            result.put("basicInfo", basicInfo);
            result.put("serviceItems", serviceItems);
            result.put("worktime", worktime);
            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            Util.failLog.error("nurse.getHomepage.json userId=" + userId, e);
        }
        return null;
    }

    @RequestMapping(path = "/getRecommendNurse.json", name = "推荐的护士")
    @ResponseBody
    public byte[] getRecommendNurse(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            @RequestParam(value = "lon", defaultValue = "116.403119", required = true) String lon,
            @RequestParam(value = "lat", defaultValue = "39.914492", required = true) String lat, String goodsId,
            String city, String priceId, String time,
            @RequestParam(value = "p", defaultValue = "1", required = true) Integer p,
            @RequestParam(value = "n", defaultValue = "10", required = true) Integer n) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurse.getRecommendNurse.json goodsId=" + goodsId + " lon=" + lon + " lat=" + lat
                        + " priceId=" + priceId + " time=" + time + " p=" + p + " n=" + n);
            }
            if (StringUtils.isEmpty(goodsId) || StringUtils.isEmpty(time) || StringUtils.isEmpty(priceId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            if (StringUtils.isEmpty(lon) || StringUtils.isEmpty(lat)) {
                lon = "116.403119";
                lat = "39.914492";
            }
            if (StringUtils.isEmpty(city)) {
                city = "北京";
            }
            //护士基本信息
            Map<String, Object> query = new HashMap<>();
            query.put("goodsId", goodsId);
            query.put("lon", lon);
            query.put("lat", lat);
            query.put("time", time);
            query.put("priceId", priceId);
            query.put("city", city);
            PageHelper.startPage(p, n);
            List<Map<String, Object>> recommendNurse = nurseService.getRecommendNurse(query);
            PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(recommendNurse);
            return JSONUtil.toJSONResult(1, "操作成功！", page);
        }
        catch (Exception e) {
            Util.failLog.error("nurse.getRecommendNurse.json goodsId=" + goodsId + " lon=" + lon + " lat=" + lat
                    + " priceId=" + priceId + " time=" + time + " p=" + p + " n=" + n, e);
        }
        return null;
    }

    /*@RequestMapping(path = "/getRecommendNurse.json", name = "推荐的护士")
    @ResponseBody
    public byte[] getRecommendNurse(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String goodsId,
            String priceId, String time, @RequestParam(value = "p", defaultValue = "1", required = true) Integer p,
            @RequestParam(value = "n", defaultValue = "10", required = true) Integer n, String address) {
        String lon = "";
        String lat = "";
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurse.getRecommendNurse.json goodsId=" + goodsId + " address=" + address
                        + " priceId=" + priceId + " time=" + time + " p=" + p + " n=" + n);
            }
            if (StringUtils.isEmpty(address) || StringUtils.isEmpty(goodsId) || StringUtils.isEmpty(time)
                    || StringUtils.isEmpty(priceId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
    
            Map<String, String> map = GetLatAndLngByBaidu.getGeocoderLatitude(address);
            lon = map.get("lng");
            lat = map.get("lat");
            //护士基本信息
            Map<String, Object> query = new HashMap<>();
            query.put("goodsId", goodsId);
            query.put("lon", lon);
            query.put("lat", lat);
            query.put("time", time);
            query.put("priceId", priceId);
            PageHelper.startPage(p, n);
            List<Map<String, Object>> recommendNurse = nurseService.getRecommendNurse(query);
            PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(recommendNurse);
            return JSONUtil.toJSONResult(1, "操作成功！", page);
        }
        catch (Exception e) {
            Util.failLog.error("nurse.getRecommendNurse.json goodsId=" + goodsId + " address=" + address + " lon=" + lon
                    + " lat=" + lat + " priceId=" + priceId + " time=" + time + " p=" + p + " n=" + n, e);
        }
        return null;
    }*/

    @RequestMapping(path = "/getNurseServicePrice.json", name = "获取护士的发布的服务价格列表")
    @ResponseBody
    public byte[] getNurseServicePrice(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String goodsId,
            String userId) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurse.getNurseServicePrice.json goodsId=" + goodsId + " userId=" + userId);
            }
            if (StringUtils.isEmpty(goodsId) || StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            //护士基本信息
            Map<String, Object> query = new HashMap<>();
            query.put("goodsId", goodsId);
            query.put("userId", userId);
            List<Grade> servicePrice = nurseService.getNurseServicePrice(query);
            return JSONUtil.toJSONResult(1, "操作成功！", servicePrice);
        }
        catch (Exception e) {
            Util.failLog.error("nurse.getNurseServicePrice.json goodsId=" + goodsId + " userId=" + userId, e);
        }
        return null;
    }

    @RequestMapping(path = "/addExitAddress.json", name = "添加出门地址", method = RequestMethod.POST)
    @ResponseBody
    public byte[] addExitAddress(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            UserAddress userAddress) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurse.addExitAddress.json detailAddress=" + userAddress.getDetailaddress()
                        + " Name=" + userAddress.getName() + " Phone=" + userAddress.getPhone() + " Province="
                        + userAddress.getProvince() + " City=" + userAddress.getCity() + " Area="
                        + userAddress.getArea());
            }
            if (StringUtils.isEmpty(userAddress.getCreatorId()) || userAddress.getLon() == null
                    || userAddress.getLat() == null || StringUtils.isEmpty(userAddress.getPhone())
                    || StringUtils.isEmpty(userAddress.getProvince()) || StringUtils.isEmpty(userAddress.getArea())
                    || StringUtils.isEmpty(userAddress.getCity()) || StringUtils.isEmpty(userAddress.getDetailaddress())
                    || StringUtils.isEmpty(userAddress.getName())) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            //先判断添加的地址数量
            UserAddress query = new UserAddress();
            query.setCreatorId(userAddress.getCreatorId());
            query.setStatus(0);
            List<UserAddress> list = userAddressService.list(query);
            if (!(list.size() < 2)) {
                return JSONUtil.toJSONResult(0, "添加数量超过上限！您只能添加两个出门地址", null);
            }
            userAddress.setId(UUIDUtils.getId());
            userAddress.setCreatorName(userAddress.getName());
            userAddress.setStatus(0);
            userAddress.setCreateTime(new Date());
            String insert = userAddressService.insert(userAddress);
            if (insert.length() <= 0) {

                return JSONUtil.toJSONResult(0, "添加失败！", null);
            }
            return JSONUtil.toJSONResult(1, "添加成功！", null);
        }
        catch (Exception e) {
            Util.failLog.error("nurse.addExitAddress.json detailAddress=" + userAddress.getDetailaddress() + " Name="
                    + userAddress.getName() + " Phone=" + userAddress.getPhone() + " Province="
                    + userAddress.getProvince() + " City=" + userAddress.getCity() + " Area=" + userAddress.getArea(),
                    e);
        }
        return null;
    }

    @RequestMapping(path = "/exitAddressList.json", name = "出门地址列表")
    @ResponseBody
    public byte[] exitAddressList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String userId) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurse.exitAddressList.json userId=" + userId);
            }
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            UserAddress query = new UserAddress();
            query.setCreatorId(userId);
            query.setStatus(0);
            query.setOrderby("create_time DESC");
            List<UserAddress> list = userAddressService.list(query);
            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            Util.failLog.error("nurse.exitAddressList.json userId=" + userId, e);
        }
        return null;
    }

    @RequestMapping(path = "/editExitAddress.json", name = "编辑出门地址", method = RequestMethod.POST)
    @ResponseBody
    public byte[] editExitAddress(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            UserAddress userAddress) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurse.editExitAddress.json detailAddress=" + userAddress.getDetailaddress()
                        + " id=" + userAddress.getId() + " Name=" + userAddress.getName() + " Phone="
                        + userAddress.getPhone() + " Province=" + userAddress.getProvince() + " City="
                        + userAddress.getCity() + " Area=" + userAddress.getArea());
            }
            if (StringUtils.isEmpty(userAddress.getId()) || userAddress.getLon() == null || userAddress.getLat() == null
                    || StringUtils.isEmpty(userAddress.getPhone()) || StringUtils.isEmpty(userAddress.getProvince())
                    || StringUtils.isEmpty(userAddress.getArea()) || StringUtils.isEmpty(userAddress.getCity())
                    || StringUtils.isEmpty(userAddress.getDetailaddress())
                    || StringUtils.isEmpty(userAddress.getName())) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            boolean update = userAddressService.update(userAddress);
            if (!update) {

                return JSONUtil.toJSONResult(0, "编辑失败", null);
            }
            return JSONUtil.toJSONResult(1, "编辑成功！", userAddress);
        }
        catch (Exception e) {
            Util.failLog.error("nurse.editExitAddress.json detailAddress=" + userAddress.getDetailaddress() + " id="
                    + userAddress.getId() + " Name=" + userAddress.getName() + " Phone=" + userAddress.getPhone()
                    + " Province=" + userAddress.getProvince() + " City=" + userAddress.getCity() + " Area="
                    + userAddress.getArea(), e);
        }
        return null;
    }

    @RequestMapping(path = "/deleteExitAddress.json", name = "删除出门地址")
    @ResponseBody
    public byte[] deleteExitAddress(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String id) {
        try {
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurse.deleteExitAddress.json id=" + id);
            }
            if (StringUtils.isEmpty(id)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            userAddressService.disableById(id);
            return JSONUtil.toJSONResult(1, "删除成功！", null);
        }
        catch (Exception e) {
            Util.failLog.error("nurse.deleteExitAddress.json id=" + id, e);
        }
        return null;
    }
}
