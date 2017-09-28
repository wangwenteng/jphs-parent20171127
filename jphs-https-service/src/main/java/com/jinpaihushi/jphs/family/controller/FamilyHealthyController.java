package com.jinpaihushi.jphs.family.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.family.model.FamilyHealthy;
import com.jinpaihushi.jphs.family.service.FamilyHealthyService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@RequestMapping("/familyHealthy")
@Controller
public class FamilyHealthyController {

	@Autowired
	private FamilyHealthyService familyHealthyService;
	
	@RequestMapping(name="获取健康列表",path = "/healthyList.json")
	@ResponseBody
	public byte[] getFamilyConsultationList(HttpServletRequest req, HttpServletResponse resp, String authCode,String userId,String id){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("familyHealthy.consultationList.json,userId=" + userId + " id="+id );
            }
            // 查空
            if (StringUtils.isEmpty(userId)|| StringUtils.isEmpty(id)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            FamilyHealthy familyHealthy = new FamilyHealthy();
            familyHealthy.setCreatorId(userId);
            familyHealthy.setFamilyMemberId(id);
            familyHealthy.setStatus(0);
            List<FamilyHealthy> familyHealthy_list = familyHealthyService.list(familyHealthy);
            
            return JSONUtil.toJSONResult(1, "操作成功！", familyHealthy_list);
		}catch(Exception e){
			 // 记录日志-fail
            Util.failLog.error("familyHealthy.consultationList.json,authCode=" + authCode+" userId="+userId , e);
		}
		
		return null;
	}
	
	@RequestMapping(name="获取问题详情",path = "/healthyDetails.json")
	@ResponseBody
	public byte[] getFamilyConsultationDetails(HttpServletRequest req, HttpServletResponse resp, String authCode,String userId,String id){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("familyHealthy.healthyDetails.json,userId=" + userId );
            }
            // 查空
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            FamilyHealthy familyHealthy = new FamilyHealthy();
            familyHealthy.setCreatorId(userId);
            familyHealthy.setId(id);
            familyHealthy.setStatus(0);
            List<FamilyHealthy> familyHealthy_list = familyHealthyService.list(familyHealthy);
            
            return JSONUtil.toJSONResult(1, "操作成功！", familyHealthy_list.get(0));
		}catch(Exception e){
			 // 记录日志-fail
            Util.failLog.error("familyHealthy.healthyDetails.json,authCode=" + authCode+" userId="+userId , e);
		}
		
		return null;
	}
	
}
