package com.jinpaihushi.jphs.family.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.family.model.FamilyConsultation;
import com.jinpaihushi.jphs.family.service.FamilyConsultationService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@RequestMapping("/familyConsultation")
@Controller
public class FamilyConsultationController {

	@Autowired
	private FamilyConsultationService familyConsultationService;
	
	@RequestMapping(name="获取问题列表",path = "/consultationList.json")
	@ResponseBody
	public byte[] getFamilyConsultationList(HttpServletRequest req, HttpServletResponse resp, String authCode,String userId){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("familyConsultation.consultationList.json,userId=" + userId );
            }
            // 查空
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            FamilyConsultation familyConsultation = new FamilyConsultation();
            familyConsultation.setCreatorId(userId);
            familyConsultation.setStatus(0);
            List<FamilyConsultation> familyConsultation_list = familyConsultationService.list(familyConsultation);
            
            return JSONUtil.toJSONResult(1, "操作成功！", familyConsultation_list);
		}catch(Exception e){
			 // 记录日志-fail
            Util.failLog.error("familyConsultation.consultationList.json,authCode=" + authCode+" userId="+userId , e);
		}
		
		return null;
	}
	
	@RequestMapping(name="获取问题详情",path = "/consultationDetails.json")
	@ResponseBody
	public byte[] getFamilyConsultationDetails(HttpServletRequest req, HttpServletResponse resp, String authCode,String userId,String id){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("familyConsultation.consultationDetails.json,userId=" + userId );
            }
            // 查空
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            FamilyConsultation familyConsultation = new FamilyConsultation();
            familyConsultation.setCreatorId(userId);
            familyConsultation.setId(id);
            familyConsultation.setStatus(0);
            List<FamilyConsultation> familyConsultation_list = familyConsultationService.list(familyConsultation);
            
            return JSONUtil.toJSONResult(1, "操作成功！", familyConsultation_list.get(0));
		}catch(Exception e){
			 // 记录日志-fail
            Util.failLog.error("familyConsultation.consultationDetails.json,authCode=" + authCode+" userId="+userId , e);
		}
		
		return null;
	}
}
