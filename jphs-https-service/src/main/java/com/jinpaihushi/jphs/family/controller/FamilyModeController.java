package com.jinpaihushi.jphs.family.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
			// 查空sss
            if (StringUtils.isEmpty(promoCode)) {
            	promoCode = "1";
            }
            FamilyMode familyMode = new  FamilyMode();
            familyMode.setValidateCode(promoCode);
            FamilyMode familyMode_m= familyModeService.load(familyMode);
            if(familyMode_m == null || familyMode_m.equals("")){
            	return JSONUtil.toJSONResult(0, "请通过正确途径兑换！", null);
            }
            FamilyPackage familyPackage = new FamilyPackage();
            familyPackage.setId(familyMode_m.getFamilyPackageId());
            FamilyPackage familyPackageOne = familyPackageService.load(familyPackage);
            if(familyMode_m == null || familyMode_m.equals("")){
            	return JSONUtil.toJSONResult(0, "请通过正确途径兑换!", null);
            }
            familyMode_m.setFamilyPackage(familyPackageOne);
            Date d = new Date();
            String msg = "操作成功！";
            int ib = d.compareTo(familyMode_m.getBeginTime());
            if(ib < 0){
            	msg = "该活动没到开启时间,敬请期待！";
            	familyMode_m.setStatus(1);
            }
            int ie = d.compareTo(familyMode_m.getEndTime());
            if(ie > 0){
            	msg = "该活动已结束,敬请期待其他活动！";
            	familyMode_m.setStatus(1);
            }
    		return JSONUtil.toJSONResult(1, msg, familyMode_m);
    	}catch (Exception e) {
    		  // 记录日志-fail
            Util.failLog.error("familyMode.getMode.json,authCode=" + authCode+" promoCode="+promoCode , e);
    	}
		return null;
	}
	public static void main(String[] args) throws ParseException {
		String DateStr1 = "2014-08-21 10:20:16";
		String DateStr2 = "2014-08-27 15:50:35";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateTime1 = dateFormat.parse(DateStr1);
		Date dateTime2 = dateFormat.parse(DateStr2);
		int i = dateTime1.compareTo(dateTime2); 
		System.out.println(i < 0); 	
	}
}
