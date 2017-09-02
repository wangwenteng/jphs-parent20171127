package com.jinpaihushi.jphs.platform.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.platform.model.Platform;
import com.jinpaihushi.jphs.platform.model.TreeNode;
import com.jinpaihushi.jphs.platform.service.PlatformService;
import com.jinpaihushi.jphs.site.model.Site;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.MD5;
import com.jinpaihushi.utils.PageInfos;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwt
 * @date 2017-06-27 09:29:09
 * @version 1.0
 */
@Controller
@RequestMapping(name = "平台管理", path = "/platform")
public class PlatformController extends BaseController<Platform> {

    @Autowired
    private PlatformService platformService;

    @Override
    protected BaseService<Platform> getService() {
        return platformService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Platform platform, Integer p, Integer n) {
        startPage(p, n);
        platform.setOrderby("create_time DESC");
        Page<Platform> list = platformService.query(platform);
        PageInfos<Platform> pageInfo = new PageInfos<Platform>(list, req);
        modelMap.put("list", list);
        modelMap.put("pageInfo", pageInfo);
        return "product/platform/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        Platform platform = platformService.loadById(id);
        modelMap.put("platform", platform);
        JSONObject message = new JSONObject();
        List<TreeNode> list = platformService.getGoodsList(platform);
        List<Site> site = platformService.getSelectSite(platform);
        message.put("treeData", list);
        modelMap.put("data", message);
        modelMap.put("site", site);
        return "product/platform/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {
        JSONObject message = new JSONObject();
        List<TreeNode> list = platformService.getGoodsList(null);
        List<Site> site = platformService.getSelectSite(null);
        message.put("treeData", list);
        modelMap.put("data", message);
        modelMap.put("site", site);
        return "product/platform/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        Platform platform = platformService.loadById(id);
        List<TreeNode> list = platformService.getGoodsList(platform);
        List<Site> site = platformService.getSelectSite(platform);
        modelMap.put("platform", platform);
        modelMap.put("list", list);
        modelMap.put("site", site);
        return "product/platform/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Platform platform) {
        SystemUser systemUser = (SystemUser) req.getSession().getAttribute("session_user");
        if (platform.getId() != null && !platform.getId().equals("")) {
            boolean b = platformService.updatePlatform(platform);
            if (b == false) {
                // 跳转到错误页
                return "redirect:/platform/err.jhtml";
            }
        }
        else {
            platform.setId(UUIDUtils.getId());
            platform.setCreatorId(systemUser.getId());
            platform.setCreatorName(systemUser.getName());
            platform.setAuthCode(MD5.md5crypt(MD5.md5crypt(platform.getId())));
            String result = platformService.insertPlatform(platform);
            if (result.length() <= 0) {
                // 跳转到错误页
                return "redirect:/platform/err.jhtml";
            }
        }
        return "redirect:/platform/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Platform platform) {
        boolean b = platformService.update(platform);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/platform/err.jhtml";
        }

        return "redirect:/platform/index.jhtml";
    }

    @RequestMapping(name = "获取商品树", path = "/getGoodsTree.jhtml")
    public String getGoodsTree(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Platform platform) {
        JSONObject message = new JSONObject();
        List<TreeNode> list = platformService.getGoodsList(platform);
        message.put("treeData", list);
        modelMap.put("data", message);
        return "redirect:/platform/index.jhtml";
    }

}
