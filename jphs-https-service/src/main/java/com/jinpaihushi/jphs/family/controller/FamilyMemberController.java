package com.jinpaihushi.jphs.family.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.family.dao.FamilyMemberDao;
import com.jinpaihushi.jphs.family.model.FamilyMember;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/familyMember")
public class FamilyMemberController {

	@Autowired
	private FamilyMemberDao familyMemberDao; 
	
	@RequestMapping(name="",path="/fmList.json")
	@ResponseBody
	public byte[] getFamilyMemberList(HttpServletRequest req, HttpServletResponse resp, String authCode,String userId){
		try {
			if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("wechat.getMenuShare.json,authCode="+authCode+"- userId="+userId);
            }
			if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
			FamilyMember familyMember = new FamilyMember();
			List<FamilyMember> familyMember_list = familyMemberDao.list(familyMember);
			return JSONUtil.toJSONResult(1, "操作成功！", familyMember_list);
	 		} catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("wechat.getMenuShare.json,authCode="+authCode+" userId="+userId, e);
        }
		return null;
	}
	
}
