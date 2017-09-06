package com.jinpaihushi.jphs.nurse.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.nurse.model.NurseSkills;
import com.jinpaihushi.jphs.nurse.service.NurseSkillsService;
import com.jinpaihushi.jphs.skills.service.SkillsService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/skills")
public class SkillsController {

	@Autowired
	SkillsService skillService;
	@Autowired
	NurseSkillsService nurseSkillService;
	
	@ResponseBody
	@RequestMapping(name = "护士设置技能" ,path = "/setNurseSkills")
	@Transactional
	public byte[] nurseSkills(HttpServletRequest req, HttpServletResponse resp,String authCode,User user,Integer nurseType,String skills){
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("skills.setNurseSkills.json,user="+user.getPhone()+" authCode="+authCode+" token="+token);
			}
			// 查空
			if(StringUtils.isEmpty(skills)
					||StringUtils.isEmpty(nurseType.toString())
						||StringUtils.isEmpty(user.getId())
							||StringUtils.isEmpty(user.getPassword())
								||StringUtils.isEmpty(user.getPhone())/*
									||StringUtils.isEmpty(token)*/){
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}
			NurseSkills nurseSkills = new NurseSkills();
			nurseSkills.setCreatorId(user.getId());
			nurseSkills.setStatus(1);
			List<NurseSkills> nurseSkills_list = nurseSkillService.list(nurseSkills);
			if(nurseSkills_list != null){
				for(int a =0;a<nurseSkills_list.size();a++){
					nurseSkills.setId(nurseSkills_list.get(a).getId());
					nurseSkills.setStatus(0);
					nurseSkillService.update(nurseSkills);
				}
			}
			
			String [] skill_arr = skills.split(",");
			for(int a=0;a<skill_arr.length;a++){
				if(skill_arr[a] != null && !skill_arr[a].equals("")){

					NurseSkills nurseNkill = new NurseSkills();
					nurseNkill.setId(UUID.randomUUID().toString());
					nurseNkill.setSkillsId(skill_arr[a]);
					nurseNkill.setType(nurseType);
					nurseNkill.setCreateTime(new Date());
					try {
						nurseNkill.setCreatorName(user.getName());
					} catch (Exception e) {
					}
					nurseNkill.setCreatorId(user.getId());
					nurseNkill.setStatus(1);
					nurseSkillService.insert(nurseNkill);
				}
			}
			
			return JSONUtil.toJSONResult(1, "成功", null);
		} catch (Exception e) {
			// 记录日志-fail
			Util.debugLog.debug("skills.setNurseSkills.json",e);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(name = "获取技能列表", path = "/getskills.json")
	public byte[] getSkills(HttpServletRequest req, HttpServletResponse resp,String authCode,User user,Integer nurseType){
		
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
				System.out.println(token);
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("skills.getskills.json,authCode=" + authCode);
			}
			
			// 查空
			if ( StringUtils.isEmpty(authCode)	
					||StringUtils.isEmpty(user.getId())
						||StringUtils.isEmpty(user.getPassword())
							||StringUtils.isEmpty(user.getPhone())/*
								||StringUtils.isEmpty(token)*/) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			/*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}*/
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("nurseId", user.getId());
			map.put("type", nurseType);
			List<Map<String,Object>> skills_list = skillService.getNurseSkills(map);
			/**
			 * ifnot	护士是否勾选了该技能	0：未勾选
			 * 							1：已勾选
			 * nstype	是否擅长还是普通		1：普通
			 * 							2擅长
			 * 
			 * type	该技能属于那种职称	
			 * 1.护士，2.康复师，3.母婴师
			 */
			return JSONUtil.toJSONResult(1, "查询成功", skills_list);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("skills.getskills.json,authCode=" + authCode,e);
		}
		
		return null;
	}
	
}
