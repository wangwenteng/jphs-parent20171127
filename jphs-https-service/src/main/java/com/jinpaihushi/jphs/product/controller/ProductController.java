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

import com.jinpaihushi.jphs.goods.service.GoodsService;
import com.jinpaihushi.jphs.platform.service.PlatformService;
import com.jinpaihushi.jphs.product.model.Product;
import com.jinpaihushi.jphs.tijian.service.TijianHospitalService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping(path = "/product")
public class ProductController {
    @Autowired
    private PlatformService platformService;

    @Autowired
    private TijianHospitalService tijianHospitalService;

    @Autowired
    private GoodsService goodsService;

    @ResponseBody
    @RequestMapping(name = "品类列表", path = "/getProductList.json")
    public byte[] getProductList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String platformId,
            String authCode, Integer deviceType) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("product.getProductList.json,platformId" + platformId + " deviceType=" + deviceType
                        + " authCode=" + authCode);
            }
            // 查空
            if (StringUtils.isEmpty(platformId) || deviceType == null) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            List<Map<String, Object>> list = platformService.getProductList(platformId, deviceType);
            // 1.根据 name，password,type查询完整信息
            // 2.错误N种情况判断及返回前端
            // 3.信息无误，封装信息以及生成token，返回前端

            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("product.getProductList.json,platformId" + platformId + " deviceType=" + deviceType
                    + " authCode=" + authCode, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "服务列表", path = "/getGoodsList.json")
    public byte[] getGoodsList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String platformId,
            String productId, String authCode, Integer deviceType, String siteId) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("product.getGoodsList.json,platformId=" + platformId + " productId=" + productId
                        + " deviceType=" + deviceType + " siteId=" + siteId + " authCode=" + authCode);
            }
            // 查空
            if (StringUtils.isEmpty(platformId) || StringUtils.isEmpty(productId) || deviceType == null
                    || StringUtils.isEmpty(siteId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            Map<String, Object> query = new HashMap<>();
            query.put("platformId", platformId);
            query.put("productId", productId);
            query.put("deviceType", deviceType);
            query.put("siteId", siteId);
            List<Map<String, Object>> list = platformService.getAllGoodsList(query);
            // 1.根据 name，password,type查询完整信息
            // 2.错误N种情况判断及返回前端
            // 3.信息无误，封装信息以及生成token，返回前端

            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("product.getGoodsList.json,platformId=" + platformId + " productId=" + productId
                    + " deviceType=" + deviceType + " siteId=" + siteId + " authCode=" + authCode, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "二级导航列表", path = "/getNavigation.json")
    public byte[] getNavigation(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String platformId,
            String authCode, Integer deviceType) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("product.getNavigation.json,platformId=" + platformId + " deviceType=" + deviceType
                        + " authCode=" + authCode);
            }
            // 查空
            if (StringUtils.isEmpty(platformId) || deviceType == null) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            List<Product> list = platformService.getNavigation(platformId, deviceType);
            // 1.根据 name，password,type查询完整信息
            // 2.错误N种情况判断及返回前端
            // 3.信息无误，封装信息以及生成token，返回前端

            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("product.getNavigation.json,platformId=" + platformId + " deviceType=" + deviceType
                    + " authCode=" + authCode, e);
        }
        return null;
    }

    /**
     * 查询体检医院列表
     * @param hs
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody
    @RequestMapping(name = "体检医院", path = "/hospital.json")
    public byte[] getHospital(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String platformId,
            String authCode, Integer deviceType) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("goods.hospital.json ,platformId=" + platformId + " deviceType=" + deviceType
                        + " authCode=" + authCode);
            }
            // 查空
            if (StringUtils.isEmpty(platformId) || deviceType == null) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            List<Map<String, Object>> tijianHospital_list = tijianHospitalService.getAllHospital(platformId);

            return JSONUtil.toJSONResult(1, "操作成功！", tijianHospital_list);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("goods.hospital.json e=" + e);
        }
        return null;
    }

    /**
     * 查询体检医院服务列表
     * @param hs
     * @param req
     * @param resp
     * @param siteId		//	站点ID
     * @param hospitalId	//	医院ID
     * @param platformId	//	平台ID
     * @param authCode
     * @param deviceType	//	端id
     * @return
     */
    @ResponseBody
    @RequestMapping(name = "体检医院服务", path = "/hospitalGoods.json")
    public byte[] getHospitalGoods(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String hospitalId,
            String siteId, String platformId, String authCode, Integer deviceType) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("goods.hospital.json ,platformId=" + platformId + " deviceType=" + deviceType
                        + " authCode=" + authCode + " hospitalId=" + hospitalId + " siteId" + siteId);
            }
            // 查空
            if (StringUtils.isEmpty(hospitalId) || StringUtils.isEmpty(siteId) || StringUtils.isEmpty(platformId)
                    || deviceType == null) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("hospitalId", hospitalId);
            map.put("deviceType", deviceType);
            map.put("siteId", siteId);
            map.put("platformId", platformId);

            List<Map<String, Object>> tijianHospital_goods_list = goodsService.getHospitalGoods(map);

            return JSONUtil.toJSONResult(1, "操作成功！", tijianHospital_goods_list);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("goods.hospital.json e=" + e);
        }
        return null;
    }

}
