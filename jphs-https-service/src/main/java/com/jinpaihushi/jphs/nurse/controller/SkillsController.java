package com.jinpaihushi.jphs.nurse.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.nurse.model.NurseJobtitle;
import com.jinpaihushi.jphs.nurse.model.NurseSkills;
import com.jinpaihushi.jphs.nurse.service.NurseJobtitleService;
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
    SkillsService skillsService;

    @Autowired
    NurseSkillsService nurseSkillsService;
    @Autowired
    NurseJobtitleService nurseJobtitleService;

    /**
     * my_skills=选择技能
    kf_skills=选择技能
    hs_skills=选择技能
     * @param req
     * @param resp
     * @param authCode
     * @param user
     * @param nurseType
     * @param skills
     * @return
     */
    @ResponseBody
    @RequestMapping(name = "护士设置技能", path = "/setNurseSkills",method = RequestMethod.POST)
    @Transactional
    public byte[] nurseSkills(HttpServletRequest req, HttpServletResponse resp, String authCode, User user,
            String my_skills, String kf_skills, String hs_skills) {
        try {
            String token = "";
            try {
                token = req.getHeader("token");
            }
            catch (Exception e) {
            }
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("skills.setNurseSkills.json,user=" + user.getPhone() + " authCode=" + authCode
                        + " token=" + token);
            }
            // 查空
            if (StringUtils.isEmpty(user.getId()) || StringUtils.isEmpty(user.getPassword())
                    || StringUtils.isEmpty(user.getPhone())/*
                                                           ||StringUtils.isEmpty(token)*/) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            if (!Common.CheckPerson(user.getPhone(), user.getPassword(), token)) {
                return JSONUtil.toJSONResult(0, "token验证失败", null);
            }
            NurseSkills nurseSkills = new NurseSkills();
            nurseSkills.setCreatorId(user.getId());
            nurseSkills.setStatus(1);
            List<NurseSkills> nurseSkills_list = nurseSkillsService.list(nurseSkills);
            if (nurseSkills_list != null) {
                for (int a = 0; a < nurseSkills_list.size(); a++) {
                    nurseSkillsService.deleteById(nurseSkills_list.get(a).getId());
                }
            }

            if (!StringUtils.isEmpty(my_skills)) {
                String[] skill_arr_my = my_skills.split(",");
                for (int a = 0; a < skill_arr_my.length; a++) {
                    if (skill_arr_my[a] != null && !skill_arr_my[a].equals("")) {

                        NurseSkills nurseNkill = new NurseSkills();
                        nurseNkill.setId(UUID.randomUUID().toString());
                        nurseNkill.setSkillsId(skill_arr_my[a]);
                        nurseNkill.setType(1);
                        nurseNkill.setCreateTime(new Date());
                        try {
                            nurseNkill.setCreatorName(user.getName());
                        }
                        catch (Exception e) {
                        }
                        nurseNkill.setCreatorId(user.getId());
                        nurseNkill.setStatus(1);
                        nurseSkillsService.insert(nurseNkill);
                    }
                }
            }

            if (!StringUtils.isEmpty(kf_skills)) {
                String[] skill_arr_kf = kf_skills.split(",");
                for (int a = 0; a < skill_arr_kf.length; a++) {
                    if (skill_arr_kf[a] != null && !skill_arr_kf[a].equals("")) {

                        NurseSkills nurseNkill = new NurseSkills();
                        nurseNkill.setId(UUID.randomUUID().toString());
                        nurseNkill.setSkillsId(skill_arr_kf[a]);
                        nurseNkill.setType(1);
                        nurseNkill.setCreateTime(new Date());
                        try {
                            nurseNkill.setCreatorName(user.getName());
                        }
                        catch (Exception e) {
                        }
                        nurseNkill.setCreatorId(user.getId());
                        nurseNkill.setStatus(1);
                        nurseSkillsService.insert(nurseNkill);
                    }
                }
            }

            if (!StringUtils.isEmpty(hs_skills)) {
                String[] skill_arr_hs = hs_skills.split(",");
                for (int a = 0; a < skill_arr_hs.length; a++) {
                    if (skill_arr_hs[a] != null && !skill_arr_hs[a].equals("")) {

                        NurseSkills nurseNkill = new NurseSkills();
                        nurseNkill.setId(UUID.randomUUID().toString());
                        nurseNkill.setSkillsId(skill_arr_hs[a]);
                        nurseNkill.setType(1);
                        nurseNkill.setCreateTime(new Date());
                        try {
                            nurseNkill.setCreatorName(user.getName());
                        }
                        catch (Exception e) {
                        }
                        nurseNkill.setCreatorId(user.getId());
                        nurseNkill.setStatus(1);
                        nurseSkillsService.insert(nurseNkill);
                    }
                }
            }
            return JSONUtil.toJSONResult(1, "修改成功！", null);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.debugLog.debug("skills.setNurseSkills.json", e);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(name = "获取技能列表", path = "/getskills.json",method = RequestMethod.POST)
    public byte[] getSkills(HttpServletRequest req, HttpServletResponse resp, String authCode, User user,
            Integer nurseType) {

        try {
            String token = "";
            try {
                token = req.getHeader("token");
            }
            catch (Exception e) {
                System.out.println(token);
            }
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("skills.getskills.json,authCode=" + authCode);
            }

            // 查空
            if (/*StringUtils.isEmpty(authCode) || */StringUtils.isEmpty(user.getId())
                    /*|| StringUtils.isEmpty(user.getPassword())
                    || StringUtils.isEmpty(user.getPhone())/*
                                                           ||StringUtils.isEmpty(token)*/) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
           /* if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
            	return JSONUtil.toJSONResult(0, "token验证失败", null);
            }*/
            List<Map<String, Object>> skills_list_re = new ArrayList<Map<String, Object>>();
            if(nurseType == null || nurseType.equals("")){
            	NurseJobtitle nurseJobtitle = new NurseJobtitle();
                nurseJobtitle.setStatus(1);
                nurseJobtitle.setCreatorId(user.getId());
                List<NurseJobtitle> nurseJobtitle_list = nurseJobtitleService.list(nurseJobtitle);
                if(nurseJobtitle_list != null && nurseJobtitle_list.size()>0){
    	            for(int a=0;a<nurseJobtitle_list.size();a++){
    	            	Map<String, Object> map = new HashMap<String, Object>();
    	            	map.put("nurseId", user.getId());
    	            	map.put("type", nurseJobtitle_list.get(a).getType());
    	            	List<Map<String, Object>> skills_list = skillsService.getNurseSkills(map);
    	            	skills_list_re.addAll(skills_list);
    	            }
                }
            }else{
            	Map<String, Object> map = new HashMap<String, Object>();
            	map.put("nurseId", user.getId());
            	map.put("type", nurseType);
            	List<Map<String, Object>> skills_list = skillsService.getNurseSkills(map);
            	skills_list_re.addAll(skills_list);
            }
            
            /**
             * ifnot	护士是否勾选了该技能	0：未勾选
             * 							1：已勾选
             * skills_type	是否擅长还是普通	1：普通
             * 							2擅长
             * 
             * type	该技能属于那种职称	
             * 1.护士，2.康复师，3.母婴师
             */
            return JSONUtil.toJSONResult(1, "查询成功", skills_list_re);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("skills.getskills.json,authCode=" + authCode, e);
        }

        return null;
    }

    @ResponseBody
    @RequestMapping(name = "获取技能列表", path = "/getSkillsDetail.json")
    public byte[] getSkillsDetail(HttpServletRequest req, HttpServletResponse resp, String authCode, String userId) {

        try {
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("skills.getskills.json,authCode=" + authCode + " userId=" + userId);
            }

            // 查空
            if (StringUtils.isEmpty(userId)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            /*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
                return JSONUtil.toJSONResult(0, "token验证失败", null);
            }*/
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("nurseId", userId);
            List<Map<String, Object>> skills_list = skillsService.getNurseSkills(map);
            return JSONUtil.toJSONResult(1, "查询成功", skills_list);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("skills.getskills.json,authCode=" + authCode + " userId=" + userId, e);
        }

        return null;
    }
}
