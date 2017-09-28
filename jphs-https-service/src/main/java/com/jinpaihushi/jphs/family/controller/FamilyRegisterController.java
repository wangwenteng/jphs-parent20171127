package com.jinpaihushi.jphs.family.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.family.model.FamilyRegister;
import com.jinpaihushi.jphs.family.service.FamilyRegisterService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/familyRegister")
public class FamilyRegisterController {
	
	@Autowired
	private FamilyRegisterService familyRegisterService;

	@RequestMapping(name="获取就医列表",path = "/getRegister.json")
	@ResponseBody
	public byte[] getfamilyRegister(HttpServletRequest req, HttpServletResponse resp, String authCode,String userId,int type){
		try{
			// 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("familyRegister.getRegister.json,userId=" + userId );
            }
            // 查空
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            FamilyRegister familyRegister = new FamilyRegister();
            familyRegister.setCreatorId(userId);
            familyRegister.setType(type);
            familyRegister.setStatus(1);
            List<FamilyRegister> familyRegister_list = familyRegisterService.list(familyRegister);
            
            return JSONUtil.toJSONResult(1, "操作成功！", familyRegister_list);
		}catch (Exception e){
			// 记录日志-fail
            Util.failLog.error("familyRegister.getRegister.json,authCode=" + authCode+" userId="+userId , e);
		}
		
		return null;
	}
	
}
