package com.jinpaihushi.jphs.order.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.peizhen.model.PeizhenPharmacyRemind;
import com.jinpaihushi.jphs.peizhen.model.PeizhenRecord;
import com.jinpaihushi.jphs.peizhen.service.PeizhenPharmacyRemindService;
import com.jinpaihushi.jphs.peizhen.service.PeizhenRecordService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

/**
 * 陪诊记录
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/peizhen")
public class PeizhenRecordController {
	
	@Autowired
	PeizhenRecordService peizhenRecordService;
	@Autowired
	PeizhenPharmacyRemindService peizhenPharmacyRemindService;
	
	/*
	http://192.168.7.66:8060/peizhen/setPeizhen.json?id=20140
		&phone=13341181592&password=220ecdad95054356ba9461c84414b179
		&type=0&authCode=123
		
		PeizhenPharmacyRemind
		PeizhenRecord
		（向后台索要两个实体类）
		token存Header
	*/
	
	@ResponseBody
	@RequestMapping(name = "添加陪诊记录", path = "/setPeizhen")
	@Transactional
	public byte [] setPeizhenRecord(HttpServletRequest req, HttpServletResponse resp, String authCode,
			User user,List<PeizhenPharmacyRemind> ppr,PeizhenRecord pr){ 
		
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("peizhen.setPeizhen.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone()+" token="+token);
			}
			// 查空
			if (StringUtils.isEmpty(user.getId())
						||StringUtils.isEmpty(user.getPhone())
							||StringUtils.isEmpty(user.getPassword())
								||StringUtils.isEmpty(user.getType().toString())
									|| StringUtils.isEmpty(authCode)
									|| StringUtils.isEmpty(pr.getOrderId())
									|| StringUtils.isEmpty(String.valueOf(pr.getReturnExamine()))
									|| StringUtils.isEmpty(String.valueOf(pr.getRotateExamine()))
									|| StringUtils.isEmpty(pr.getExamineTime().toString())
									|| StringUtils.isEmpty(pr.getRotateTime().toString())
									|| StringUtils.isEmpty(pr.getRemarks())
									|| StringUtils.isEmpty(pr.getRecommendDepartment())
									|| StringUtils.isEmpty(pr.getUserId())/*
											|| StringUtils.isEmpty(token)*/) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			/*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}*/
			pr.setCreatorId(user.getId());
			pr.setCreatorName(user.getName());
			pr.setCreateTime(new Date());
			pr.setStatus(1);
			pr.setId(UUIDUtils.getId());
			String prId = peizhenRecordService.insert(pr);
			
			if(!StringUtils.isEmpty(ppr.toString())){
				for(int a= 0;a<ppr.size();a++){
					PeizhenPharmacyRemind ppr_one = ppr.get(a);
					ppr_one.setPeizhenRecordId(prId);
					ppr_one.setCreateTime(new Date());
					ppr_one.setCreatorId(user.getId());
					ppr_one.setCreatorName(user.getName());
					ppr_one.setId(UUIDUtils.getId());
					peizhenPharmacyRemindService.insert(ppr_one);
				}
			}
			
			return JSONUtil.toJSONResult(1, "添加成功",prId);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("peizhen.setPeizhen.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone(),e);
		}
		
		return null;
	}
	
}
