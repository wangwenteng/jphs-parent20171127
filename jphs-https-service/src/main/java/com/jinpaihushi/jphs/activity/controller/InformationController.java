package com.jinpaihushi.jphs.activity.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.information.model.Information;
import com.jinpaihushi.jphs.information.model.InformationCollection;
import com.jinpaihushi.jphs.information.model.InformationEvaluate;
import com.jinpaihushi.jphs.information.service.InformationCollectionService;
import com.jinpaihushi.jphs.information.service.InformationEvaluateService;
import com.jinpaihushi.jphs.information.service.InformationService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.ObjectVerification;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/information")
public class InformationController extends BaseController<Information> {
    @Autowired
    private InformationService informationService;

    @Autowired
    private InformationEvaluateService informationEvaluateService;

    @Autowired
    private InformationCollectionService informationCollectionService;
    //	@Autowired
    //	private UserService userService;

    @ResponseBody
    @RequestMapping(name = "最新资讯", path = "/getLatestformation.json")
    public byte[] getLatestformation(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String channelId,
            Integer num) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("information.getLatestformation.json,channelId=" + channelId);
            }
            // 查空
            if (StringUtils.isEmpty(channelId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }

            List<Map<String, Object>> list = informationService.getLatestformation(channelId, num);
            // 1.根据 name，password,type查询完整信息
            // 2.错误N种情况判断及返回前端
            // 3.信息无误，封装信息以及生成token，返回前端

            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("information.getLatestformation.json,channelId=" + channelId, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "资讯列表", path = "/getList.json")
    public byte[] getList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String channelId, Integer p,
            Integer r) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("information.getList.json,channelId=" + channelId);
            }
            // 查空
            if (StringUtils.isEmpty(channelId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            startPage(p, r);
            Map<String, Object> query = new HashMap<>();
            query.put("channelId", channelId);
            List<Map<String, Object>> informationList = informationService.listapp(query);
            PageInfo<Map<String, Object>> page = new PageInfo<>(informationList);
            // 1.根据 name，password,type查询完整信息
            // 2.错误N种情况判断及返回前端
            // 3.信息无误，封装信息以及生成token，返回前端

            return JSONUtil.toJSONResult(1, "操作成功！", page);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("information.getList.json,channelId=" + channelId, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "首页资讯列表", path = "/getHomeInformation.json")
    public byte[] getHomeInformation(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            String channelId) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("information.getHomeInformation.json,channelId=" + channelId);
            }
            // 查空
            if (StringUtils.isEmpty(channelId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }

            Map<String, Object> list = informationService.getHomeInformation(channelId);
            // 1.根据 name，password,type查询完整信息
            // 2.错误N种情况判断及返回前端
            // 3.信息无误，封装信息以及生成token，返回前端

            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("information.getHomeInformation.json,channelId=" + channelId, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "资讯列表", path = "/getInformationList.json")
    public byte[] getInformationList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String channelId,
            Integer page) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("information.getInformationList.json,channelId=" + channelId + " page=" + page);
            }
            // 查空
            if (StringUtils.isEmpty(channelId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            Map<String, Object> list = informationService.getInformationList(channelId, page);
            if (list.size() <= 0) {
                return JSONUtil.toJSONResult(1, "暂无数据", null);
            }
            // 1.根据 name，password,type查询完整信息
            // 2.错误N种情况判断及返回前端
            // 3.信息无误，封装信息以及生成token，返回前端

            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("information.getInformationList.json,channelId=" + channelId + " page=" + page, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "置顶资讯", path = "/getTopList.json")
    public byte[] getTopList(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String channelId,
            Integer num) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("information.getTopList.json,channelId=" + channelId + " page=" + num);
            }
            // 查空
            if (StringUtils.isEmpty(channelId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            List<Map<String, Object>> list = informationService.getTopList(channelId, num);
            if (list.size() <= 0) {
                return JSONUtil.toJSONResult(1, "暂无数据", null);
            }
            // 1.根据 name，password,type查询完整信息
            // 2.错误N种情况判断及返回前端
            // 3.信息无误，封装信息以及生成token，返回前端

            return JSONUtil.toJSONResult(1, "操作成功！", list);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("information.getTopList.json,channelId=" + channelId + " page=" + num, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "资讯详情", path = "/getInformationDetail.json")
    public byte[] getInformationDetail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String id,
            String userId) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("information.getInformationDetail.json,id=" + id + " userId=" + userId);
            }
            // 查空
            if (StringUtils.isEmpty(id)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            Information result = informationService.getInformationDetail(id);
            // 判断是否有用户登录
            if (StringUtils.isNotEmpty(userId)) {
                InformationCollection collection = new InformationCollection();
                collection.setCreatorId(userId);
                collection.setInformationId(id);
                collection.setStatus(0);
                InformationCollection load = informationCollectionService.load(collection);
                if (load != null) {
                    result.setCollection(true);
                }
            }
            // 1.根据 name，password,type查询完整信息
            // 2.错误N种情况判断及返回前端
            // 3.信息无误，封装信息以及生成token，返回前端

            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("information.getInformationDetail.json,id=" + id + " userId=" + userId, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "资讯评价", path = "/getInformationEvaluate.json")
    public byte[] getInformationEvaluate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, String id,
            Integer p, String userId, Integer r) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog
                        .debug("information.getInformationEvaluate.json,id=" + id + " p=" + p + " userId=" + userId);
            }
            // 查空
            if (StringUtils.isEmpty(id)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            /*if (StringUtils.isNotEmpty(userId)) {
            	String token = req.getHeader("token");
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
            	}
            }*/
            InformationEvaluate query = new InformationEvaluate();
            query.setInformationId(id);
            query.setStatus(1);
            query.setOrderby("create_time DESC");
            startPage(p, r);
            List<InformationEvaluate> list = informationEvaluateService.listInfo(query, userId);
            PageInfo<InformationEvaluate> page = new PageInfo<>(list);
            // 1.根据 name，password,type查询完整信息
            // 2.错误N种情况判断及返回前端
            // 3.信息无误，封装信息以及生成token，返回前端

            return JSONUtil.toJSONResult(1, "操作成功！", page);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("information.getInformationEvaluate.json,id=" + id + " p=" + p + " userId=" + userId, e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "发表评论", path = "/sendEvaluate.json", method = RequestMethod.POST)
    public byte[] sendEvaluate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            InformationEvaluate evaluate) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("information.sendEvaluate.json");
            }
            if (ObjectVerification.verification(evaluate) || StringUtils.isEmpty(evaluate.getContent())) {
                return JSONUtil.toJSONResult(0, "请核对参数！！", null);
            }
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
            	return JSONUtil.toJSONResult(0, "token不能为空", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
            	user = userService.loadById(evaluate.getCreatorId());
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
            	// 身份认证失败,返回错误信息
            	return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            evaluate.setId(UUIDUtils.getId());
            evaluate.setCreateTime(new Date());
            evaluate.setStatus(0);
            String result = informationEvaluateService.insert(evaluate);

            return JSONUtil.toJSONResult(1, "操作成功！", result);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("information.sendEvaluate.json", e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "收藏资讯", path = "/collectionInformation.json", method = RequestMethod.POST)
    public byte[] collectionInformation(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            InformationCollection collection) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("information.collectionInformation.json");
            }
            if (!ObjectVerification.verification(collection)) {
                return JSONUtil.toJSONResult(0, "请核对参数！！", null);
            }
            boolean flag = false;
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
            	return JSONUtil.toJSONResult(0, "token不能为空", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
            	user = userService.loadById(collection.getCreatorId());
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
            	// 身份认证失败,返回错误信息
            	return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            //判断该用户是否收藏
            collection.setDevice(null);
            InformationCollection load = informationCollectionService.load(collection);
            if (load != null) {
                if (load.getStatus() == 0) {
                    load.setStatus(-1);
                    flag = informationCollectionService.update(load);
                    return JSONUtil.toJSONResult(1, "取消收藏！", null);
                }
                if (load.getStatus() == -1) {
                    load.setStatus(0);
                    flag = informationCollectionService.update(load);
                    return JSONUtil.toJSONResult(1, "收藏成功！", load);
                }
            }
            collection.setId(UUIDUtils.getId());
            collection.setCreateTime(new Date());
            collection.setStatus(0);
            String result = informationCollectionService.insert(collection);

            return JSONUtil.toJSONResult(1, "收藏成功！", result);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("information.collectionInformation.json", e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "更新浏览量", path = "/updatePreviewNumber.json")
    public byte[] updatePreviewNumber(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,
            String informationId, String userId) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug(
                        "information.updatePreviewNumber.json informationId=" + informationId + " userId=" + userId);
            }
            if (StringUtils.isEmpty(informationId)) {
                return JSONUtil.toJSONResult(0, "请核对参数！！", null);
            }
            /*String token = req.getHeader("token");
            if (StringUtils.isEmpty(token)) {
            	return JSONUtil.toJSONResult(0, "token不能为空", null);
            }
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
            	user = userService.loadById(userId);
            boolean flag = Common.CheckPerson(user.getPhone(), user.getPassword(), token);
            if (!flag) {
            	// 身份认证失败,返回错误信息
            	return JSONUtil.toJSONResult(2, "身份认证失败", null);
            }*/
            Information query = informationService.loadById(informationId);
            query.setPreviewNumber(query.getPreviewNumber() + 1);
            informationService.update(query);
            return JSONUtil.toJSONResult(1, "操作成功！", query.getPreviewNumber());
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error(
                    "information.updatePreviewNumber.json informationId=" + informationId + " userId=" + userId, e);
        }
        return null;
    }

    @Override
    protected BaseService<Information> getService() {
        // TODO Auto-generated method stub
        return null;
    }
}
