package com.jinpaihushi.jphs.order.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.peizhen.model.PeizhenPharmacyRemind;
import com.jinpaihushi.jphs.peizhen.model.PeizhenRecord;
import com.jinpaihushi.jphs.peizhen.model.PeizhenRecords;
import com.jinpaihushi.jphs.peizhen.service.PeizhenPharmacyRemindService;
import com.jinpaihushi.jphs.peizhen.service.PeizhenRecordService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

import net.sf.json.JSONArray;

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
	
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(name = "添加陪诊记录", path = "/setPeizhen",method = RequestMethod.POST)
	@Transactional
	public byte [] setPeizhenRecord(HttpServletRequest req, HttpServletResponse resp, String authCode,
			User user,String ppr,PeizhenRecords pr){ 
		
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			PeizhenRecord peizhenRecords = new PeizhenRecord();
	        BeanUtils.copyProperties(peizhenRecords, pr);
	        if(!StringUtils.isEmpty(pr.getExamineTimes())){
	        	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        	   Date examineTimes =sdf.parse(pr.getExamineTimes());
	        	   peizhenRecords.setExamineTime(examineTimes);
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
									|| StringUtils.isEmpty(peizhenRecords.getOrderId())
									|| StringUtils.isEmpty(peizhenRecords.getUserId())/*
											|| StringUtils.isEmpty(token)*/) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			/*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}*/
			peizhenRecords.setCreatorId(user.getId());
			peizhenRecords.setCreatorName(user.getName());
			peizhenRecords.setCreateTime(new Date());
			peizhenRecords.setStatus(1);
			peizhenRecords.setId(UUIDUtils.getId());
			String prId = peizhenRecordService.insert(peizhenRecords);
			
			if(!StringUtils.isEmpty(ppr)){
				JSONArray ppr_arr = new JSONArray().fromObject(ppr);
				for(int a= 0;a<ppr_arr.size();a++){
					PeizhenPharmacyRemind ppr_one = new PeizhenPharmacyRemind();
					if(ppr_arr.getJSONObject(a).containsKey("content"))
						ppr_one.setContent(ppr_arr.getJSONObject(a).getString("content"));
					if(ppr_arr.getJSONObject(a).containsKey("drug"))
						ppr_one.setDrug(ppr_arr.getJSONObject(a).getString("drug"));
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
