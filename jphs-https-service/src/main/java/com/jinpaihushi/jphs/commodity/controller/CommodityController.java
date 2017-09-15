package com.jinpaihushi.jphs.commodity.controller;

import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.jphs.commodity.model.Commodity;
import com.jinpaihushi.jphs.commodity.model.CommodityMap;
import com.jinpaihushi.jphs.nurse.model.NurseCommodity;
import com.jinpaihushi.jphs.nurse.service.NurseCommodityService;
import com.jinpaihushi.jphs.commodity.service.CommodityService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;

import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;
/**
 * 
 * @author yangsong
 * @date 2017-08-08 20:04:04
 * @version 1.0
 */
@Controller
@RequestMapping(name = "Commodity", path = "/commodity")
public class CommodityController extends BaseController<Commodity> {

	@Autowired
	private CommodityService commodityService;
	@Autowired
	private NurseCommodityService nurseCommodityService;
	

	@Override
	protected BaseService<Commodity> getService() {
		return commodityService;
	}

	@RequestMapping(name = "获取商品列表", path = "/getShopList.json")
	@ResponseBody
	public byte[] getShopList(
			String columnId,String nurseId, Integer p,String sort) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(p);
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.getShopList.json,columnId =" + columnId +", nurseId = " +nurseId);
			}
			if (StringUtils.isEmpty(columnId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (p == null) {
				p = 1;
			}
			PageHelper.startPage(p, 10);
			List<CommodityMap> list = commodityService.getCommodityList(columnId,nurseId,sort);
			

			PageInfo<CommodityMap> page = new PageInfo<CommodityMap>(list);
			if (list == null) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", page);
		} catch (Exception e) {
			Util.failLog.error("commodity.getShopList.json,columnId =" + columnId +", nurseId = " +nurseId, e);
		}
		return null;
	}

	
	@RequestMapping(name = "获取商品详细", path = "/getShopDetail.json")
	@ResponseBody
	public byte[] getShopDetail(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			String commodityId,String columnId, Integer p,String re) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.getShopDetail.json,commodityId =" + commodityId +",columnId =" + columnId);
			}
			if (StringUtils.isEmpty(commodityId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			Commodity commodity = commodityService.getCommodityDetail(columnId,commodityId);
			
			commodityService.updateBrowser(commodityId);
			
			if(!"0".equals(re)){

				NurseCommodity nurseCommodity = new NurseCommodity();
				nurseCommodity.setCommodityId(commodityId);
				nurseCommodity.setCreatorId(re);
										System.out.println(re);
						 
				NurseCommodity nc = nurseCommodityService.load(nurseCommodity);

				if(nc != null){
					boolean result =  nurseCommodityService.updateBrowser(nurseCommodity);
				
					if (!result) {
						return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
					}
				}else{
					nurseCommodity.setId(UUID.randomUUID().toString());
					nurseCommodity.setStatus(0);
					nurseCommodity.setCreateTime(new Date());
					nurseCommodity.setSharenumber(0);
					nurseCommodity.setBrowser(1);
					nurseCommodity.setCount(0);
					String result =  nurseCommodityService.insert(nurseCommodity);
				
					if (result.length()<0) {
						return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
					}
				}

			}


			if (commodity == null) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", commodity);
		} catch (Exception e) {
			Util.failLog.error("commodity.getShopDetail.json,commodityId =" + commodityId +",columnId = "+columnId, e);
		}
		return null;
	}


	@RequestMapping(name = "护士销售列表", path = "/getSaleByNurse.json")
	@ResponseBody
	public byte[] getSaleByNurse(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			String nurseId, Integer p) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.getSaleByNurse.json,nurseId =" + nurseId);
			}
			if (StringUtils.isEmpty(nurseId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			List<Commodity> list = commodityService.getSaleByNurse(nurseId);
			if (list == null) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			Util.failLog.error("commodity.getSaleByNurse.json,nurseId =" + nurseId, e);
		}
		return null;
	}


	
	@RequestMapping(name = "护士销售信息列表", path = "/getNurseSale.json")
	@ResponseBody
	public byte[] getNurseSale(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			String nurseId, String commodityId,String schedule, Integer p) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.getNurseSale.json,nurseId =" + nurseId +",commodityId="+commodityId +",schedule =" +schedule);
			}
			if (StringUtils.isEmpty(nurseId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(commodityId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			List<Commodity> list = commodityService.getNurseSale(nurseId,commodityId,schedule);
			if (list == null) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			Util.failLog.error("commodity.getNurseSale.json,nurseId =" + nurseId +",commodityId="+commodityId +",schedule="+schedule, e);
		}
		return null;
	}


	@RequestMapping(name = "获取商品列表", path = "/getListByCar.json")
	@ResponseBody
	public byte[] getListByCar(
			String ids) {
			 
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.getListByCar.json,ids =" + ids );
			}
			if (StringUtils.isEmpty(ids)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			 
		 
			List<Commodity> list = commodityService.getListByCar(ids);
			
			
			if (list == null) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", list);
		} catch (Exception e) {
			Util.failLog.error("commodity.getListByCar.json,ids =" + ids , e);
		}
		return null;
	}


	@RequestMapping(name = "获取商品列表", path = "/getOneDetail.json")
	@ResponseBody
	public byte[] getOneDetail(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			String commodityId,String cpId, Integer p) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("commodity.getOneDetail.json,commodityId =" + commodityId +",cpId =" + cpId);
			}
			if (StringUtils.isEmpty(commodityId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(cpId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			Commodity commodity = commodityService.getOneDetail(commodityId,cpId);
			if (commodity == null) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", commodity);
		} catch (Exception e) {
			Util.failLog.error("commodity.getOneDetail.json,commodityId =" + commodityId +",cpId = "+cpId, e);
		}
		return null;
	}


}
