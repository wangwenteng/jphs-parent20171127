package com.jinpaihushi.jphs.family.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.family.model.FamilyMode;
import com.jinpaihushi.jphs.family.model.FamilyPackage;
import com.jinpaihushi.jphs.family.service.FamilyModeService;
import com.jinpaihushi.jphs.family.service.FamilyPackageService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

/**
 * 根据访问code判断购买流程
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/familyMode")
public class FamilyModeController {
	
	@Autowired
	private FamilyModeService familyModeService;
	@Autowired
	private FamilyPackageService familyPackageService;

	@RequestMapping(name="获取下单流程",path="/getMode.json")
	@ResponseBody
	public byte[] getfamilyMode(HttpServletRequest req, HttpServletResponse resp, String authCode,String promoCode){
		try{
			// 查空
            if (StringUtils.isEmpty(promoCode)) {
            	promoCode = "1";
            }
            FamilyMode familyMode = new  FamilyMode();
            familyMode.setValidateCode(promoCode);
            FamilyMode familyMode_m= familyModeService.load(familyMode);
            FamilyPackage familyPackage = new FamilyPackage();
            familyPackage.setId(familyMode_m.getFamilyPackageId());
            FamilyPackage familyPackageOne = familyPackageService.load(familyPackage);
            familyMode_m.setPrice(familyPackageOne.getPrice());
    		return JSONUtil.toJSONResult(1, "操作成功！", familyMode_m);
    	}catch (Exception e) {
    		  // 记录日志-fail
            Util.failLog.error("familyMode.getMode.json,authCode=" + authCode+" promoCode="+promoCode , e);
    	}
		return null;
	}
	
}
