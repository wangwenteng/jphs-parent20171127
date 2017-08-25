package com.jinpaihushi.jphs.car.controller;

import java.util.List;
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
import com.jinpaihushi.jphs.car.model.Car;
import com.jinpaihushi.jphs.car.service.CarService;
import com.jinpaihushi.utils.PageInfos;
import com.github.pagehelper.Page;

import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 19:55:04
 * @version 1.0
 */
@Controller
@RequestMapping(name = "car", path = "/car")
public class CarController extends BaseController<Car> {

	@Autowired
	private CarService carService;

	@Override
	protected BaseService<Car> getService() {
		return carService;
	}


	@RequestMapping(name = "添加商品至购物车", path = "/insertCar.json")
	@ResponseBody
	public byte[] insertCar(Car car) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("car.insertCar.json,commodityId =" + car.getCommodityId() +",commodityPriceId =" + car.getCommodityPriceId()+",number = "+car.getNumber() + ",creatorId = "+car.getCreatorId());
			}
			if (StringUtils.isEmpty(car.getCommodityId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(car.getCommodityPriceId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (StringUtils.isEmpty(car.getCreatorId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if (car.getNumber()<=0) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			car.setStatus(1);
			Car model = carService.lookup(car);
			if(model!=null){
				Integer number = model.getNumber();
				 
				model.setNumber(number+1);
				boolean b = carService.updateNumber(model);
				
				if (!b) {
					return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
				}
			}else{
				car.setCreateTime(new Date());
				car.setStatus(1);
				car.setId(UUID.randomUUID().toString());
				String result = carService.insert(car);
				
				if (result.length()<0) {
					return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
				}
			}

			 
			
			return JSONUtil.toJSONResult(1, "操作成功！", 1);
		} catch (Exception e) {
			Util.failLog.error("car.insertCar.json,commodityId =" + car.getCommodityId() +",commodityPriceId =" + car.getCommodityPriceId()+",number = "+car.getNumber() + ",creatorId = "+car.getCreatorId(), e);
		}
		return null;
	}
	
	
	@RequestMapping(name = "删除购物车商品", path = "/deleteCar.json")
	@ResponseBody
	public byte[] deleteCar(Car car) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("car.deleteCar.json,carId =" + car.getId());
			}
			if (StringUtils.isEmpty(car.getId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			car.setStatus(-1);
			boolean result = carService.update(car);
			if (result == false) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", "添加成功");
		} catch (Exception e) {
			Util.failLog.error("car.deleteCar.json,carId =" + car.getId(), e);
		}
		return null;
	}

	@RequestMapping(name = "修改购物车商品", path = "/updateCar.json")
	@ResponseBody
	public byte[] updateCar(Car car) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("car.updateCar.json,carId =" + car.getId());
			}
			if (StringUtils.isEmpty(car.getId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			 
			boolean result = carService.update(car);
			if (result == false) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", "添加成功");
		} catch (Exception e) {
			Util.failLog.error("car.updateCar.json,carId =" + car.getId(), e);
		}
		return null;
	}

	@RequestMapping(name = "购物车列表", path = "/carList.json")
	@ResponseBody
	public byte[] carList(String creatorId) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("car.carList.json,creatorId =" + creatorId);
			}
			if (StringUtils.isEmpty(creatorId)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			 
			List<Car> list = carService.getList(creatorId);
			if (list.size()<0) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！",list);
		} catch (Exception e) {
			Util.failLog.error("car.carList.json,creatorId =" + creatorId, e);
		}
		return null;
	}


	@RequestMapping(name = "购物车列表", path = "/updateNumber.json")
	@ResponseBody
	public byte[] updateNumber(Car car) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("car.updateNumber.json,id =" + car.getId());
			}
			if (StringUtils.isEmpty(car.getId())) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if ( car.getNumber()<0) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			 
			 boolean b = carService.updateNumber(car);
				
			if (!b) {
				return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
			}
			return JSONUtil.toJSONResult(1, "操作成功！", b);
		} catch (Exception e) {
			Util.failLog.error("car.updateNumber.json,id =" + car.getId(), e);
		}
		return null;
	}


	@RequestMapping(name = "删除购物车商品", path = "/deleteIds.json")
	@ResponseBody
	public byte[] deleteIds(String ids) {
		try {
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("car.deleteIds.json,carId =" + ids);
			}
			if (StringUtils.isEmpty(ids)) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			Car car = new Car();
			car.setStatus(-1);
			String[] idArr = ids.split(",");
			System.out.println(idArr.length);
			boolean result = true;
			for(int i = 0;i<idArr.length;i++){
				car.setId(idArr[i]);
				result = carService.update(car); 
				if (result == false) {
					return JSONUtil.toJSONResult(0, "请核对参数后访问", null);
				}
			}
			
			return JSONUtil.toJSONResult(1, "操作成功！", "添加成功");
		} catch (Exception e) {
			Util.failLog.error("car.deleteIds.json,carId =" + ids, e);
		}
		return null;
	}
}
