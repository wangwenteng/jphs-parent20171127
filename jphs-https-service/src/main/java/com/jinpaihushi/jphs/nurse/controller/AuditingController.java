package com.jinpaihushi.jphs.nurse.controller;

import java.util.Date;
<<<<<<< HEAD
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

import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.model.NurseJobtitle;
import com.jinpaihushi.jphs.nurse.model.NurseSkills;
import com.jinpaihushi.jphs.nurse.service.NurseImagesService;
import com.jinpaihushi.jphs.nurse.service.NurseJobtitleService;
import com.jinpaihushi.jphs.nurse.service.NurseSkillsService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/nurseAudting")
public class AuditingController {

    @Autowired
    private NurseImagesService nurseImagesService;

    @Autowired
    private NurseJobtitleService nurseJobtitleService;

    @Autowired
    private NurseSkillsService nurseSkillsService;

    @ResponseBody
    @RequestMapping(name = "查看认证信息", path = "/getAudting.json")
    public byte[] lookAudting(HttpServletRequest req, HttpServletResponse resp, String authCode, User user,
            String nurseType) {
        try {
            String token = "";
            try {
                token = req.getHeader("token");
            }
            catch (Exception e) {
            }
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseAudting.pageone.json,authCode=" + authCode + " user=" + user.getId()
                        + " user=" + user.getPhone() + " token=" + token);
            }
            /*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
            	return JSONUtil.toJSONResult(0, "token验证失败", null);
            }*/
            // 查空
            if (StringUtils.isEmpty(user.getId()) || StringUtils.isEmpty(user.getPhone())
                    || StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getType().toString())
                    || StringUtils.isEmpty(authCode)/*
                                                    || StringUtils.isEmpty(token)*/) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("njType", nurseType);
            map.put("id", user.getId());
            List<NurseJobtitle> nj = nurseJobtitleService.getNurseAuditing(map);

            return JSONUtil.toJSONResult(1, "成功", nj);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseAudting.pageone.json,authCode=" + authCode + " user=" + user.getId() + " user="
                    + user.getPhone(), e);
        }

        return null;
    }

    @ResponseBody
    @RequestMapping(name = "护士认证", path = "/pageone.json")
    @Transactional
    public byte[] audting(HttpServletRequest req, HttpServletResponse resp, String authCode, User user,
            String goodSkills, String skillsArr, NurseJobtitle nurseJobtitle) {
        try {
            String token = "";
            try {
                token = req.getHeader("token");
            }
            catch (Exception e) {
            }
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseAudting.pageone.json,authCode=" + authCode + " user=" + user.getId()
                        + " user=" + user.getPhone() + " token=" + token);
            }
            /*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
            	return JSONUtil.toJSONResult(0, "token验证失败", null);
            }*/
            // 查空
            if (StringUtils.isEmpty(String.valueOf(nurseJobtitle.getType())) || StringUtils.isEmpty(user.getId())
                    || StringUtils.isEmpty(user.getPhone()) || StringUtils.isEmpty(user.getPassword())
                    || StringUtils.isEmpty(user.getType().toString())
                    || StringUtils.isEmpty(authCode)/*
                                                    || StringUtils.isEmpty(token)*/) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }

            // 通用字段验证是否为空
            if (StringUtils.isEmpty(nurseJobtitle.getName()) || StringUtils.isEmpty(goodSkills)
                    || StringUtils.isEmpty(skillsArr) || StringUtils.isEmpty(nurseJobtitle.getAddress())
                    || StringUtils.isEmpty(nurseJobtitle.getHospital())
                    || StringUtils.isEmpty(nurseJobtitle.getSex().toString())
                    || StringUtils.isEmpty(nurseJobtitle.getType().toString())
                    || StringUtils.isEmpty(nurseJobtitle.getJobtitleId()) || StringUtils.isEmpty(nurseJobtitle.getSfz())
                    || StringUtils.isEmpty(nurseJobtitle.getSfzz()) || StringUtils.isEmpty(nurseJobtitle.getSfzf())
                    || StringUtils.isEmpty(nurseJobtitle.getWorkYears().toString())) {

                return JSONUtil.toJSONResult(0, "认证参数不能为空", null);
            }
            //	护士-验证护士证
            /**	执业证	charteredProve*/
            /**	资格证	seniorityProve*/
            /**	医院聘书	hospitalContract*/
            /**	工牌	workCard*/
            if (nurseJobtitle.getType() == 1) {

                if (StringUtils.isEmpty(nurseJobtitle.getCharteredProve())
                        || StringUtils.isEmpty(nurseJobtitle.getSeniorityProve())
                        || StringUtils.isEmpty(nurseJobtitle.getHospitalContract())
                        || StringUtils.isEmpty(nurseJobtitle.getWorkCard())) {
                    return JSONUtil.toJSONResult(0, "护士资格证参数不能为空", null);
                }

            }
            //	康复师-验证康复师证件
            /**	康复师资格证正面	therapistZ*/
            /**	康复师资格证反面	therapistF*/
            if (nurseJobtitle.getType() == 2) {
                if (StringUtils.isEmpty(nurseJobtitle.getTherapistZ())
                        || StringUtils.isEmpty(nurseJobtitle.getTherapistF())) {

                    return JSONUtil.toJSONResult(0, "康复师资格证参数不能为空", null);
                }
            }
            //	母婴师-验证母婴师证件
            /**	母婴师	fransnanaCard*/
            if (nurseJobtitle.getType() == 3) {
                if (StringUtils.isEmpty(nurseJobtitle.getFransnanaCard())) {

                    return JSONUtil.toJSONResult(0, "母婴师资格证参数不能为空", null);
                }
            }
            String nj_id = UUID.randomUUID().toString();
            nurseJobtitle.setId(nj_id);
            nurseJobtitle.setStatus(0);
            nurseJobtitle.setCreateTime(new Date());
            nurseJobtitle.setCreatorId(user.getId());
            nurseJobtitle.setCreatorName(user.getName());
            String i_n = nurseJobtitleService.insert(nurseJobtitle);
            if (i_n.length() < 1) {
                return JSONUtil.toJSONResult(0, "提交认证失败", null);
            }

            /**
             * 擅长技能-ID数组
             * goodSkills
             */
            try {
                String[] goodSkills_arr = goodSkills.split(",");
                for (int a = 0; a < goodSkills_arr.length; a++) {
                    if (!StringUtils.isEmpty(goodSkills_arr[a])) {
                        NurseSkills ns = new NurseSkills();
                        ns.setId(UUIDUtils.getId());
                        ns.setSkillsId(goodSkills_arr[a]);
                        ns.setType(2);
                        ns.setCreatorName(user.getName());
                        ns.setCreatorId(user.getId());
                        ns.setCreateTime(new Date());
                        ns.setStatus(1);
                        nurseSkillsService.insert(ns);
                    }
                }
            }
            catch (Exception e) {
            }

            /**
             * 技能-ID数组
             * skillsArr
             */
            try {
                String[] skillsArr_arr = skillsArr.split(",");
                for (int a = 0; a < skillsArr_arr.length; a++) {
                    if (!StringUtils.isEmpty(skillsArr_arr[a])) {
                        NurseSkills ns = new NurseSkills();
                        ns.setId(UUIDUtils.getId());
                        ns.setSkillsId(skillsArr_arr[a]);
                        ns.setType(1);
                        ns.setCreatorName(user.getName());
                        ns.setCreatorId(user.getId());
                        ns.setCreateTime(new Date());
                        ns.setStatus(1);
                        nurseSkillsService.insert(ns);
                    }
                }
            }
            catch (Exception e) {
            }

            /**
             * 图片类型,(如果source_id是护士1、护士头像，2、护士资质证正面，3、护士资质证反面，4、身份证正面，5、身份证反面 ,
             * 6.执业证，7.医院聘书，8.工牌，9.康复师资格证正面，10.康复师资格证反面，11.母婴师资格证)
             */
            NurseImages nurseImages = new NurseImages();
            nurseImages.setCreateTime(new Date());
            nurseImages.setId(UUID.randomUUID().toString());
            nurseImages.setStatus(1);
            nurseImages.setSourceId(nj_id);
            nurseImages.setCreatorId(user.getId());
            nurseImages.setCreatorName(user.getName());
            //			护士-验证护士证
            /**	执业证	charteredProve*/
            /**	资格证	seniorityProve*/
            /**	医院聘书	hospitalContract*/
            /**	工牌	workCard*/
            if (nurseJobtitle.getType() == 1) {

                // 执业证
                nurseImages.setType(6);
                nurseImages.setUrl(nurseJobtitle.getCharteredProve());
                nurseImagesService.insert(nurseImages);

                // 资格证
                nurseImages.setType(2);
                nurseImages.setUrl(nurseJobtitle.getSeniorityProve());
                nurseImagesService.insert(nurseImages);
                // 医院聘书
                nurseImages.setType(7);
                nurseImages.setUrl(nurseJobtitle.getHospitalContract());
                nurseImagesService.insert(nurseImages);
                // 工牌
                nurseImages.setType(8);
                nurseImages.setUrl(nurseJobtitle.getWorkCard());
                nurseImagesService.insert(nurseImages);

            }
            //	康复师-验证康复师证件
            /**	康复师资格证正面	therapistZ*/
            /**	康复师资格证反面	therapistF*/
            if (nurseJobtitle.getType() == 2) {

                // 康复师资格证正面
                nurseImages.setType(9);
                nurseImages.setUrl(nurseJobtitle.getTherapistZ());
                nurseImagesService.insert(nurseImages);

                // 康复师资格证正面
                nurseImages.setType(10);
                nurseImages.setUrl(nurseJobtitle.getTherapistF());
                nurseImagesService.insert(nurseImages);

            }
            //	母婴师-验证母婴师证件
            /**	母婴师	fransnanaCard*/
            if (nurseJobtitle.getType() == 3) {
                // 母婴师-验证母婴师证件
                nurseImages.setType(11);
                nurseImages.setUrl(nurseJobtitle.getFransnanaCard());
                nurseImagesService.insert(nurseImages);
            }

            return JSONUtil.toJSONResult(1, "提交成功", null);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseAudting.pageone.json,authCode=" + authCode + " user=" + user.getId() + " user="
                    + user.getPhone(), e);
        }

        return null;
    }
=======
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.nurse.dao.NurseImagesDao;
import com.jinpaihushi.jphs.nurse.dao.NurseJobtitleDao;
import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.model.NurseJobtitle;
import com.jinpaihushi.jphs.nurse.service.NurseService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.Util;

@Controller
@RequestMapping("/nurseAudting")
public class AuditingController {
	
	@Autowired
	NurseImagesDao nureseImagesDao;
	@Autowired
	NurseService nurseService;
	@Autowired
	NurseJobtitleDao nurseJobtitleDao;
	
	@ResponseBody
	@RequestMapping(name="护士认证",path = "/pageone.json")
	@Transactional
	public byte[] audting(HttpServletRequest req, HttpServletResponse resp,String authCode,User user,NurseJobtitle nurseJobtitle ){
		try{
			String token = "";
			try {
				token = req.getHeader("token");
			} catch (Exception e) {
			}
			// 记录日志-debug
			if (Util.debugLog.isDebugEnabled()) {
				Util.debugLog.debug("nurseAudting.pageone.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone()+" token="+token);
			}
			/*if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
				return JSONUtil.toJSONResult(0, "token验证失败", null);
			}*/
			// 查空
			if (StringUtils.isEmpty(String.valueOf(nurseJobtitle.getType()))
					||StringUtils.isEmpty(user.getId())
						||StringUtils.isEmpty(user.getPhone())
							||StringUtils.isEmpty(user.getPassword())
								||StringUtils.isEmpty(user.getType().toString())
									|| StringUtils.isEmpty(authCode)/*
											|| StringUtils.isEmpty(token)*/) {
				return JSONUtil.toJSONResult(0, "参数不能为空", null);
			}
			
			// 通用字段验证是否为空
			if (StringUtils.isEmpty(nurseJobtitle.getName())
					|| StringUtils.isEmpty(nurseJobtitle.getAddress())
						|| StringUtils.isEmpty(nurseJobtitle.getHospital())
							|| StringUtils.isEmpty(nurseJobtitle.getSex().toString())
								|| StringUtils.isEmpty(nurseJobtitle.getType().toString())
									|| StringUtils.isEmpty(nurseJobtitle.getJobtitleId())
										|| StringUtils.isEmpty(nurseJobtitle.getSfz())
											|| StringUtils.isEmpty(nurseJobtitle.getSfzz())
												|| StringUtils.isEmpty(nurseJobtitle.getSfzf())
													|| StringUtils.isEmpty(nurseJobtitle.getWorkYears().toString()) ) {
				
				return JSONUtil.toJSONResult(0, "认证参数不能为空", null);
			}
			//	护士-验证护士证
			/**	执业证	charteredProve*/
			/**	资格证	seniorityProve*/
			/**	医院聘书	hospitalContract*/
			/**	工牌	workCard*/
			if(nurseJobtitle.getType() == 1){
				
				if (StringUtils.isEmpty(nurseJobtitle.getCharteredProve()) 
						|| StringUtils.isEmpty(nurseJobtitle.getSeniorityProve())
						|| StringUtils.isEmpty(nurseJobtitle.getHospitalContract())
						|| StringUtils.isEmpty(nurseJobtitle.getWorkCard())) {
					return JSONUtil.toJSONResult(0, "护士资格证参数不能为空", null);
				}
				
			}
			//	康复师-验证康复师证件
			/**	康复师资格证正面	therapistZ*/
			/**	康复师资格证反面	therapistF*/
			if(nurseJobtitle.getType() == 2){
				if (StringUtils.isEmpty(nurseJobtitle.getTherapistZ()) 
						|| StringUtils.isEmpty(nurseJobtitle.getTherapistF()) ) {
					
					return JSONUtil.toJSONResult(0, "康复师资格证参数不能为空", null);
				}		
			}
			//	母婴师-验证母婴师证件
			/**	母婴师	fransnanaCard*/
			if(nurseJobtitle.getType() == 3){
				if (StringUtils.isEmpty(nurseJobtitle.getFransnanaCard()) ) {
					
					return JSONUtil.toJSONResult(0, "母婴师资格证参数不能为空", null);
				}
			}
			nurseJobtitle.setStatus(0);
			nurseJobtitle.setCreateTime(new Date());
			nurseJobtitle.setCreatorId(user.getId());
			nurseJobtitle.setCreatorName(user.getName());
			int i_n = nurseJobtitleDao.insert(nurseJobtitle);
			if(i_n != 1){
				return JSONUtil.toJSONResult(0, "提交认证失败", null);
			}
			/**
			 * 图片类型,(如果source_id是护士1、护士头像，2、护士资质证正面，3、护士资质证反面，4、身份证正面，5、身份证反面 ,
			 * 6.执业证，7.医院聘书，8.工牌，9.康复师资格证正面，10.康复师资格证反面，11.母婴师资格证)
			 */
			NurseImages nurseImages = new NurseImages();
			nurseImages.setCreateTime(new Date());
			nurseImages.setId(UUID.randomUUID().toString());
			nurseImages.setStatus(1);
			nurseImages.setSourceId(user.getId());
			nurseImages.setCreatorId(user.getId());
			nurseImages.setCreatorName(user.getName());
			//			护士-验证护士证
			/**	执业证	charteredProve*/
			/**	资格证	seniorityProve*/
			/**	医院聘书	hospitalContract*/
			/**	工牌	workCard*/
			if(nurseJobtitle.getType() == 1){
				
				// 执业证
				nurseImages.setType(6);
				nurseImages.setUrl(nurseJobtitle.getCharteredProve());
				nureseImagesDao.insert(nurseImages);
				
				// 资格证
				nurseImages.setType(2);
				nurseImages.setUrl(nurseJobtitle.getSeniorityProve());
				nureseImagesDao.insert(nurseImages);
				// 医院聘书
				nurseImages.setType(7);
				nurseImages.setUrl(nurseJobtitle.getHospitalContract());
				nureseImagesDao.insert(nurseImages);
				// 工牌
				nurseImages.setType(8);
				nurseImages.setUrl(nurseJobtitle.getWorkCard());
				nureseImagesDao.insert(nurseImages);
				
			}
			//	康复师-验证康复师证件
			/**	康复师资格证正面	therapistZ*/
			/**	康复师资格证反面	therapistF*/
			if(nurseJobtitle.getType() == 2){
				
				// 康复师资格证正面
				nurseImages.setType(9);
				nurseImages.setUrl(nurseJobtitle.getTherapistZ());
				nureseImagesDao.insert(nurseImages);
				
				// 康复师资格证正面
				nurseImages.setType(10);
				nurseImages.setUrl(nurseJobtitle.getTherapistF());
				nureseImagesDao.insert(nurseImages);
				
			}
			//	母婴师-验证母婴师证件
			/**	母婴师	fransnanaCard*/
			if(nurseJobtitle.getType() == 3){
				// 母婴师-验证母婴师证件
				nurseImages.setType(11);
				nurseImages.setUrl(nurseJobtitle.getFransnanaCard());
				nureseImagesDao.insert(nurseImages);
			}
			
			return JSONUtil.toJSONResult(1, "提交成功", null);
		} catch (Exception e) {
			// 记录日志-fail
			Util.failLog.error("nurseAudting.pageone.json,authCode=" + authCode + " user=" + user.getId() + " user=" + user.getPhone(),e);
		}
		
		return null;
	}
>>>>>>> branch 'master1' of https://github.com/120591516/jphs-parent.git

}
