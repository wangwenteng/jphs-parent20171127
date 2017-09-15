package com.jinpaihushi.jphs.order.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.recovered.model.RecoveredRecord;
import com.jinpaihushi.jphs.recovered.service.RecoveredRecordService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

/**
 * 康复记录
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/recovered")
public class RecoveredRecordController {

	@Autowired
	RecoveredRecordService recoveredRecordService;
	/**
	 * 
	 * @param req
	 * @param resp
	 * @param authCode
	 * @param user				登录护士信息
	 * @param patientName		患者姓名
	 * @param patientSex		患者性别
	 * @param patientAge		患者年龄
	 * @param estimate			评估
	 * @param propose			建议
	 * @param orderId			订单ID
	 * @param userId			订单下单人ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(name = "添加康复记录", path = "/setRecovered.json",method = RequestMethod.POST)
	public byte[] setRecovered(HttpServletRequest req, HttpServletResponse resp, String authCode,User user,
			String patientName,Integer patientSex,Integer patientAge,String estimate,String propose,String orderId,String userId){
		
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("recovered.setRecovered.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone()+" token="+token);
			}
			// 查空
			if (StringUtils.isEmpty(user.getId())
						||StringUtils.isEmpty(user.getPhone())
							||StringUtils.isEmpty(user.getPassword())
								||StringUtils.isEmpty(user.getType().toString())
									|| StringUtils.isEmpty(authCode)
									|| StringUtils.isEmpty(orderId)
									|| StringUtils.isEmpty(userId)/*
											|| StringUtils.isEmpty(token)*/) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			/*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}*/
			RecoveredRecord recoveredRecord = new RecoveredRecord();
			recoveredRecord.setAge(patientAge);
			recoveredRecord.setCreateTime(new Date());
			recoveredRecord.setCreatorId(user.getId());
			recoveredRecord.setId(UUIDUtils.getId());
			recoveredRecord.setName(patientName);
			recoveredRecord.setUserId(userId);
			recoveredRecord.setStatus(1);
			recoveredRecord.setEstimate(estimate);
			recoveredRecord.setOrderId(orderId);
			recoveredRecord.setPropose(propose);
			String rrId = recoveredRecordService.insert(recoveredRecord);
			
			return JSONUtil.toJSONResult(1, "添加成功",rrId);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("recovered.setRecovered.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone(),e);
		}
		
		return null;
	}
	
}
