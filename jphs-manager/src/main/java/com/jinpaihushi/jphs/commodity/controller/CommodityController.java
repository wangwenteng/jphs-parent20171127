package com.jinpaihushi.jphs.commodity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.business.model.Business;
import com.jinpaihushi.jphs.business.service.BusinessService;
import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.jphs.commodity.model.CommodityPrice;
import com.jinpaihushi.jphs.commodity.model.CommodityType;
import com.jinpaihushi.jphs.commodity.service.CommodityImagesService;
import com.jinpaihushi.jphs.commodity.service.CommodityService;
import com.jinpaihushi.jphs.commodity.service.CommodityTypeService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author yangsong
 * @date 2017-09-18 10:32:25
 * @version 1.0
 */
@Controller
@RequestMapping(name = "商品", path = "/commodity")
public class CommodityController extends BaseController<Commodity> {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private CommodityTypeService commodityTypeService;

    @Autowired
    private CommodityImagesService commodityImagesService;

    @Override
    protected BaseService<Commodity> getService() {
        return commodityService;
    }

    @RequestMapping(name = "列表页", path = "/index.jhtml")
    public String index(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Commodity commodity, Integer p, Integer n) {
        startPage(p, n);
        //Page<Commodity> list = commodityService.query(commodity);

        Page<Commodity> list = commodityService.getPageList(commodity);
        PageInfos<Commodity> pageInfo = new PageInfos<Commodity>(list, req);
        Business business = new Business();
        List<Business> businessList = businessService.list(business);
        CommodityType commodityType = new CommodityType();
        List<CommodityType> commodityTypeList = commodityTypeService.list(commodityType);
        modelMap.put("list", list);
        modelMap.put("businessList", businessList);
        modelMap.put("commodityTypeList", commodityTypeList);
        modelMap.put("pageInfo", pageInfo);
        return "shop/commodity/list";
    }

    @RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
    public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        Commodity commodity = commodityService.loadById(id);
        modelMap.put("commodity", commodity);
        return "shop/commodity/edit";
    }

    @RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
    public String redirectAddPage(ModelMap modelMap) {
        Business business = new Business();
        List<Business> businessList = businessService.list(business);
        CommodityType commodityType = new CommodityType();
        List<CommodityType> commodityTypeList = commodityTypeService.list(commodityType);
        modelMap.put("businessList", businessList);
        modelMap.put("commodityTypeList", commodityTypeList);
        return "shop/commodity/edit";
    }

    @RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
    public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {
        Commodity commodity = commodityService.loadById(id);
        modelMap.put("commodity", commodity);
        return "shop/commodity/detail";
    }

    @RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
    public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            Commodity commodity, String url, String content, List<CommodityPrice> cpList) {

        /*String result =  null;
        if (commodity.getId() != null && !commodity.getId().equals("")) {
        	boolean b = commodityService.update(commodity);
        	if (b == false) {
        		// 跳转到错误页
        		return "redirect:/commodity/err.jhtml";
        	}
        } else {
        	commodity.setId(UUID.randomUUID().toString());
        	 result = commodityService.insert(commodity);
        	if (result.length() <= 0) {
        		// 跳转到错误页
        		return "redirect:/commodity/err.jhtml";
        	}
        }
        if(result != null){
        	CommodityImages commodityImages = new CommodityImages();
        	commodityImages.setDeviceType(2);
        	commodityImages.setStatus(0);
        	commodityImages.setCreateTime(new Date());
        	commodityImages.setSourceId(result);
        	if(url != null){
        		commodityImages.setId(UUID.randomUUID().toString());
        		commodityImages.setUrl(url);
        		commodityImages.setType(1);
        		commodityImagesService.insert(commodityImages);
        	}
        	if(content != null){
        		commodityImages.setId(UUID.randomUUID().toString());
        		commodityImages.setUrl(content);
        		commodityImages.setType(2);
        		commodityImagesService.insert(commodityImages);
        	}
        }
        
        
        System.out.println(url);
        System.out.println(content);
        */
        for (int i = 0; i < cpList.size(); i++) {
            System.out.println(cpList.get(i).getName());
        }

        return "redirect:/commodity/index.jhtml";
    }

    @RequestMapping(name = "删除数据", path = "/delete.jhtml")
    public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
            String id) {

        boolean b = commodityService.deleteById(id);
        if (b == false) {
            // 跳转到错误页
            return "redirect:/commodity/err.jhtml";
        }

        return "redirect:/commodity/index.jhtml";
    }

}
