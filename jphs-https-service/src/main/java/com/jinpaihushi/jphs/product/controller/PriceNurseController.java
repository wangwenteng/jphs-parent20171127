package com.jinpaihushi.jphs.product.controller;

import java.util.Map;
import java.util.UUID;
import java.util.Date;

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

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.jphs.price.model.PriceNurse;
import com.jinpaihushi.jphs.price.service.PriceNurseService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;

import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;
/**
 * 
 * @author yangsong
 * @date 2017-08-16 15:07:51
 * @version 1.0
 */
@Controller
@RequestMapping(name = "PriceNurse", path = "/price/nurse")
public class PriceNurseController extends BaseController<PriceNurse> {

	@Autowired
	private PriceNurseService priceNurseService;

	@Override
	protected BaseService<PriceNurse> getService() {
		return priceNurseService;
	}

 

	 
	@ResponseBody
	@RequestMapping(name = "添加发布服务", path = "/insertPriceNurse.json")
	public byte[] insertPriceNurse(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,String pricePartIds,String prices,String creatorId,String goodsId) {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("price.nurse.insertPriceNurse.json");
			}
			if (StringUtils.isEmpty(pricePartIds)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if ( StringUtils.isEmpty(prices)){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(creatorId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			String[] pricePartIdArr = pricePartIds.split(",");
			String[] priceArr = prices.split(",");

			if(pricePartIdArr.length != priceArr.length){
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			 PriceNurse priceNurse = new PriceNurse();
			 String result = null;
			 priceNurse.setGoodsId(goodsId);
			for (int i = 0; i<pricePartIdArr.length;i++ ){
				priceNurse.setId(UUID.randomUUID().toString());
				priceNurse.setPricePartId(pricePartIdArr[i]);
				priceNurse.setPrice( Double.parseDouble(priceArr[i]));
				priceNurse.setStatus(0);
				result = priceNurseService.insert(priceNurse);
				if (result.length() <= 0) {
					return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
				}
			}
			return JSONUtil.toJSONResult(1, "操作成功！", "1");
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("price.nurse.insertPriceNurse.json " , e);
		}
		return null;
	}
	 
	
	@ResponseBody
	@RequestMapping(name = "修改发布服务", path = "/updatePriceNurse.json")
	public byte[] updatePriceNurse(HttpSession hs, HttpServletRequest req, HttpServletResponse resp,String ids,String pricePartIds,String prices,String creatorId,String goodsId) {
		try {
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("price.nurse.updatePriceNurse.json");
			}
			if (StringUtils.isEmpty(creatorId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(ids)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			String[] idArr = ids.split(",");
			String[] pricePartIdArr = pricePartIds.split(",");
			String[] priceArr = prices.split(",");
 
			if(pricePartIdArr.length != priceArr.length && pricePartIdArr.length != idArr.length){
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			 
			 PriceNurse priceNurse = new PriceNurse();
			 String result = null;
			 priceNurse.setGoodsId(goodsId);
			for (int i = 0; i<pricePartIdArr.length;i++ ){
				
				if(idArr[i]==null){
					priceNurse.setId(UUID.randomUUID().toString());
					priceNurse.setPricePartId(pricePartIdArr[i]);
					priceNurse.setPrice( Double.parseDouble(priceArr[i]));
					priceNurse.setStatus(0);
					result = priceNurseService.insert(priceNurse);

					if (result.length() <= 0) {
						return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
					}
				}else{
					priceNurse.setId(idArr[i]);
					priceNurse.setPricePartId(pricePartIdArr[i]);
					priceNurse.setPrice( Double.parseDouble(priceArr[i]));
					priceNurse.setStatus(0);
					boolean b = priceNurseService.update(priceNurse);

					if (b == false) {
						return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
					}
				}
			}
			return JSONUtil.toJSONResult(1, "操作成功！", "1");
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("price.nurse.updatePriceNurse.json " , e);
		}
		return null;
	}
	 
	

}
