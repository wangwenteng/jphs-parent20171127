package com.jinpaihushi.jphs.nurse.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinpaihushi.jphs.department.model.Department;
import com.jinpaihushi.jphs.department.service.DepartmentService;
import com.jinpaihushi.jphs.jobtitle.model.Jobtitle;
import com.jinpaihushi.jphs.jobtitle.service.JobtitleService;
import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.model.NurseInstitutions;
import com.jinpaihushi.jphs.nurse.model.NurseJobtitle;
import com.jinpaihushi.jphs.nurse.model.NurseJobtitles;
import com.jinpaihushi.jphs.nurse.model.NurseSkills;
import com.jinpaihushi.jphs.nurse.service.NurseImagesService;
import com.jinpaihushi.jphs.nurse.service.NurseInstitutionsService;
import com.jinpaihushi.jphs.nurse.service.NurseJobtitleService;
import com.jinpaihushi.jphs.nurse.service.NurseSkillsService;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.utils.Common;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.UUIDUtils;
import com.jinpaihushi.utils.Util;
import com.jinpaihushi.utils.Validate;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/nurseAudting")
public class AuditingController {

    @Autowired
    private NurseImagesService nurseImagesService;

    @Autowired
    private NurseJobtitleService nurseJobtitleService;

    @Autowired
    private NurseSkillsService nurseSkillsService;
    
    @Autowired
	private DepartmentService departmentService;
    @Autowired
    private NurseInstitutionsService nurseInstitutionsService;
    @Autowired
    private  JobtitleService jobtitleService;/*
    @Autowired
    private  GoodsService goodsService;
    @Autowired
    private  JobtitleGoods jobtitleGoods;
    
    @ResponseBody
    @RequestMapping(name = "服务和职称关联", path = "/setNj.json")
    public byte[] setNj(HttpServletRequest req, HttpServletResponse resp){
    	try{
    		Jobtitle jobtitle = new Jobtitle();
    		jobtitle.setStatus(1);
    		List<Jobtitle> jobtitle_list = jobtitleService.list(jobtitle);
    		Goods goods = new Goods();
    		goods.setStatus(1);
    		List<Goods> goods_list = goodsService.list(goods);
    		for(int a=0;a<jobtitle_list.size();a++){
    			for(int b=0;b<goods_list.size();b++){
    				Jobtitle jobtitle_one = jobtitle_list.get(a);
    				Goods goods_one = goods_list.get(a);
    				
        		}
    		}
    		
    		return JSONUtil.toJSONResult(1, "操作成功！", null);
    	}catch (Exception e) {
    		  // 记录日志-fail
            Util.failLog.error("nurseAudting.setNj.json,", e);
    	}
    	return null; 
    }*/
    
    @ResponseBody
    @RequestMapping(name = "获取机构列表", path = "/getNurseInstitutions.json")
    public byte[] getNurseInstitutions(HttpServletRequest req, HttpServletResponse resp, String authCode){
    	try{
    		NurseInstitutions ni = new NurseInstitutions();
    		ni.setStatus(1);
    		List<NurseInstitutions> ni_list = nurseInstitutionsService.list(ni);
    		
    		return JSONUtil.toJSONResult(1, "操作成功！", ni_list);
    	}catch (Exception e) {
    		  // 记录日志-fail
            Util.failLog.error("nurseAudting.getNurseInstitutions.json,authCode=" + authCode , e);
    	}
    	return null;
    }
    
    @ResponseBody
    @RequestMapping(name = "获取职称和科室", path = "/getJobtitleAndDepartment.json")
    public byte[] getJobtitleAndDepartment(HttpServletRequest req, HttpServletResponse resp, String authCode,Integer type){
    	try {
    		
    		// 查空
            if (StringUtils.isEmpty(type.toString())) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
    		
    		JSONObject json_re = new JSONObject();	
    		Department dept = new Department();
			dept.setStatus(1);
			List<Department> list = departmentService.list(dept);
			json_re.put("department", list);
			String j_type = "c6c9f0ba0e8e464b807d8d2bd5deb8b7";
			if(type == 1){
				j_type = "c6c9f0ba0e8e464b807d8d2bd5deb8b7";
			}else if (type == 2){
				j_type = "ad58e9c2c78f44258054617570b6afc4";
			}else if (type == 3){
				j_type = "2b1e6ec74e3c40a880c8d3cd8708a6ca";
			}
			
			List<Jobtitle> jobtitle =jobtitleService.jobtitleSelectList(j_type);
			json_re.put("jobtitle", jobtitle);
			return JSONUtil.toJSONResult(1, "操作成功！", json_re);
    			
    	}catch(Exception e){
    		  // 记录日志-fail
            Util.failLog.error("nurseAudting.getJobtitleAndDepartment.json,authCode=" + authCode , e);
    	}
    	return null;
    }
    
    @ResponseBody
    @RequestMapping(name = "查看认证信息", path = "/getAudting.json",method = RequestMethod.POST)
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
            }/*
            if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
            	return JSONUtil.toJSONResult(0, "token验证失败", null);
            }*/
            // 查空
            if (StringUtils.isEmpty(user.getId()) /*|| StringUtils.isEmpty(user.getPhone())
                    || StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getType().toString())
                    || StringUtils.isEmpty(authCode)*//*
                                                    || StringUtils.isEmpty(token)*/) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("njType", nurseType);
            map.put("id", user.getId());
            List<NurseJobtitle> nj = nurseJobtitleService.getNurseAuditing(map);
            for(int a=0;a<nj.size();a++){
            	if(!StringUtils.isEmpty(nj.get(a).getJobtitleId())){
            		Jobtitle j_one = jobtitleService.loadById(nj.get(a).getJobtitleId());
            		nj.get(a).setJobtitleId(j_one.getName());
            	}
            	if(!StringUtils.isEmpty(nj.get(a).getDepartmentId())){
            		Department d_one = departmentService.loadById(nj.get(a).getDepartmentId());
            		nj.get(a).setDepartmentId(d_one.getName());
            	}
            	if(!StringUtils.isEmpty(nj.get(a).getNurseInstitutionsId())){
            		NurseInstitutions ni_one = nurseInstitutionsService.loadById(nj.get(a).getNurseInstitutionsId());
            		nj.get(a).setNurseInstitutionsId(ni_one.getName());
            	}
            }
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
    @RequestMapping(name = "护士认证通用页面信息", path = "/setWapAudtingtwo.json")
    public byte[] setWapAudtingtwo(HttpServletRequest req, HttpServletResponse resp, String authCode,String userId,
    		String goodSkills, String skillsArr,String hospital,String departmentId,String jobtitleId,String workY,Integer nurseType,
    		String studyInstitution,String charteredProve, String seniorityProve,String hospitalContract,
    		String therapistZ,String fransnanaCard){
    	try {
	   		 // 记录日志-debug
	        if (Util.debugLog.isDebugEnabled()) {
	             Util.debugLog.debug("nurseAudting.setWapAudtingtwo.json,authCode=" + authCode + " userId=" +userId );
	        }
  			if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(workY)
  					 || StringUtils.isEmpty(workY)
  					 || StringUtils.isEmpty(String.valueOf(nurseType))
  					 || StringUtils.isEmpty(jobtitleId)
  					 || StringUtils.isEmpty(skillsArr)
  					 || StringUtils.isEmpty(goodSkills)){
  				return JSONUtil.toJSONResult(0, "参数不完整", null);
  			} 
  			NurseJobtitle nurseJobtitle = new NurseJobtitle();
   			nurseJobtitle.setCreatorId(userId);
   			nurseJobtitle.setType(4);
   			nurseJobtitle.setStatus(-1);
   			List<NurseJobtitle> nurseJobtitle_list = nurseJobtitleService.list(nurseJobtitle);
   			if(nurseJobtitle_list.size()<1){
   				return JSONUtil.toJSONResult(0, "失败。未填写基本信息", null);
   			}
   			
   			NurseJobtitle nurseJobtitle_up = new NurseJobtitle();
   			if(!StringUtils.isEmpty(workY)){
          	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
          	  Date workYs =sdf.parse(workY);
          	  nurseJobtitle_up.setWorkYears(workYs);
            } 
   			nurseJobtitle_up.setId(UUIDUtils.getId());
   			nurseJobtitle_up.setType(nurseType);
   			nurseJobtitle_up.setName(nurseJobtitle_list.get(0).getName());
   			nurseJobtitle_up.setSfz(nurseJobtitle_list.get(0).getSfz());
   			nurseJobtitle_up.setSex(nurseJobtitle_list.get(0).getSex());
   			nurseJobtitle_up.setStatus(0);
   			nurseJobtitle_up.setCreateTime(new Date());
   			nurseJobtitle_up.setCreatorName(nurseJobtitle_list.get(0).getCreatorId());
   			nurseJobtitle_up.setCreatorId(userId);
   			nurseJobtitle_up.setAddress(nurseJobtitle_list.get(0).getAddress());
   			nurseJobtitle_up.setJobtitleId(jobtitleId);
   			if(nurseType == 1){
   				nurseJobtitle_up.setHospital(hospital);
   				nurseJobtitle_up.setDepartmentId(departmentId);
   			}else if(nurseType == 2){
   				nurseJobtitle_up.setHospital(hospital);
   				nurseJobtitle_up.setStudyInstitution(studyInstitution);
   			}else if(nurseType == 3){
//   				nurseJobtitle_up.sets
   			}

   			nurseJobtitleService.insert(nurseJobtitle_up);
   			/**
             * 擅长技能-ID数组
             * goodSkills
             */
            try {
            	if(goodSkills != null && !goodSkills.equals("")){
            		
            		NurseSkills nurseSkills = new NurseSkills();
        			nurseSkills.setCreatorId(userId);
        			nurseSkills.setNurseType(nurseType);
        			nurseSkills.setType(2);
        			nurseSkills.setStatus(1);
        			List<NurseSkills> nurseSkills_list = nurseSkillsService.getNurseSkillsAc(nurseSkills);
        			if(nurseSkills_list != null){
        				for(int a =0;a<nurseSkills_list.size();a++){
        					nurseSkillsService.deleteById(nurseSkills_list.get(a).getId());
        				}
        			}
            		
            		 String[] goodSkills_arr = goodSkills.split(",");
                     for (int a = 0; a < goodSkills_arr.length; a++) {
                         if (!StringUtils.isEmpty(goodSkills_arr[a])) {
                             NurseSkills ns = new NurseSkills();
                             ns.setId(UUIDUtils.getId());
                             ns.setSkillsId(goodSkills_arr[a]);
                             ns.setType(2);
                             ns.setCreatorName(nurseJobtitle_up.getName());
                             ns.setCreatorId(userId);
                             ns.setCreateTime(new Date());
                             ns.setStatus(1);
                             nurseSkillsService.insert(ns);
                         }
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
            	if(skillsArr != null && !skillsArr.equals("")){
            		
            		NurseSkills nurseSkills = new NurseSkills();
        			nurseSkills.setCreatorId(userId);
        			nurseSkills.setNurseType(nurseType);
        			nurseSkills.setType(1);
        			nurseSkills.setStatus(1);
        			List<NurseSkills> nurseSkills_list = nurseSkillsService.getNurseSkillsAc(nurseSkills);
        			if(nurseSkills_list != null){
        				for(int a =0;a<nurseSkills_list.size();a++){
        					nurseSkillsService.deleteById(nurseSkills_list.get(a).getId());
        				}
        			}
            		
            		String[] skillsArr_arr = skillsArr.split(",");
                    for (int a = 0; a < skillsArr_arr.length; a++) {
                        if (!StringUtils.isEmpty(skillsArr_arr[a])) {
                            NurseSkills ns = new NurseSkills();
                            ns.setId(UUIDUtils.getId());
                            ns.setSkillsId(skillsArr_arr[a]);
                            ns.setType(1);
                            ns.setCreatorName(nurseJobtitle_up.getName());
                            ns.setCreatorId(userId);
                            ns.setCreateTime(new Date());
                            ns.setStatus(1);
                            nurseSkillsService.insert(ns);
                        }
                    }
            	}
            }
            catch (Exception e) {
            }
            
            NurseImages nurseImages_lo = new NurseImages();
            nurseImages_lo.setCreatorId(userId);
            nurseImages_lo.setSourceId(nurseJobtitle_list.get(0).getId());
   			List<NurseImages> nurseImages_list_lo = nurseImagesService.list(nurseImages_lo);
            if(nurseImages_list_lo!= null && nurseImages_list_lo.size()>0){
            	String s1 = "";
            	String s4 = "";
            	String s5 = "";
            	
            	for(int i = 0;i<nurseImages_list_lo.size();i++){
            		if(nurseImages_list_lo.get(i).getType() == 1){
            			s1 = nurseImages_list_lo.get(i).getUrl();
            		}
            		if(nurseImages_list_lo.get(i).getType() == 4){
            			s4 = nurseImages_list_lo.get(i).getUrl();
            		}
            		if(nurseImages_list_lo.get(i).getType() == 5){
            			s5 = nurseImages_list_lo.get(i).getUrl();
            		}
            	}
            	
                /**
                 * 图片类型,(如果source_id是护士1、护士头像，2、护士资质证正面，3、护士资质证反面，4、身份证正面，5、身份证反面 ,
                 * 6.执业证，7.医院聘书，8.工牌，9.康复师资格证正面，10.康复师资格证反面，11.母婴师资格证)
                 */
                NurseImages nurseImages = new NurseImages();
                nurseImages.setCreateTime(new Date());
                nurseImages.setStatus(1);
                nurseImages.setSourceId(nurseJobtitle_up.getId());
                nurseImages.setCreatorId(userId);
                nurseImages.setCreatorName(nurseJobtitle_up.getName());
                
                if(!StringUtils.isEmpty(s1)){
                	NurseImages nurseImages_del = new NurseImages();
            		nurseImages_del.setCreatorId(nurseJobtitle_up.getCreatorId());
            		nurseImages_del.setSourceId(nurseJobtitle_up.getId());
            		nurseImages_del.setType(1);
            		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
            		for(int a =0;a<list_img.size();a++){
            			nurseImagesService.deleteById(list_img.get(a).getId());
            		}
            		// 身份证正面
            		nurseImages.setId(UUID.randomUUID().toString());
            		nurseImages.setType(1);
            		nurseImages.setUrl(s1);
            		nurseImagesService.insert(nurseImages);
                }
                
                if(!StringUtils.isEmpty(s4)){
                	NurseImages nurseImages_del = new NurseImages();
            		nurseImages_del.setCreatorId(userId);
            		nurseImages_del.setSourceId(nurseJobtitle_up.getId());
            		nurseImages_del.setType(4);
            		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
            		for(int a =0;a<list_img.size();a++){
            			nurseImagesService.deleteById(list_img.get(a).getId());
            		}
            		
            		// 身份证正面
            		nurseImages.setType(4);
            		nurseImages.setId(UUID.randomUUID().toString());
            		nurseImages.setUrl(s4);
            		nurseImagesService.insert(nurseImages);
            		
                }
                if(!StringUtils.isEmpty(s5)){
                	NurseImages nurseImages_del = new NurseImages();
            		nurseImages_del.setCreatorId(userId);
            		nurseImages_del.setSourceId(nurseJobtitle_up.getId());
            		nurseImages_del.setType(5);
            		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
            		for(int a =0;a<list_img.size();a++){
            			nurseImagesService.deleteById(list_img.get(a).getId());
            		}
            		// 身份证正面
            		nurseImages.setType(5);
            		nurseImages.setId(UUID.randomUUID().toString());
            		nurseImages.setUrl(s5);
            		nurseImagesService.insert(nurseImages);
                }
                
                //			护士-验证护士证
                /**	执业证	charteredProve*/
                /**	资格证	seniorityProve*/
                /**	医院聘书	hospitalContract*/
                /**	工牌	workCard*/
                if (nurseType == 1) {

                	if(charteredProve != null && !charteredProve.equals("")){
                		NurseImages nurseImages_del = new NurseImages();
                		nurseImages_del.setCreatorId(userId);
                		nurseImages_del.setSourceId(nurseJobtitle_up.getId());
                		nurseImages_del.setType(6);
                		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
                		for(int a =0;a<list_img.size();a++){
                			nurseImagesService.deleteById(list_img.get(a).getId());
                		}
                		
                		// 执业证
                		nurseImages.setType(6);
                		 nurseImages.setId(UUID.randomUUID().toString());
                		nurseImages.setUrl(charteredProve);
                		nurseImagesService.insert(nurseImages);
                	}
                	if(seniorityProve != null && !seniorityProve.equals("")){
                		NurseImages nurseImages_del = new NurseImages();
                		nurseImages_del.setCreatorId(userId);
                		nurseImages_del.setSourceId(nurseJobtitle_up.getId());
                		nurseImages_del.setType(2);
                		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
                		for(int a =0;a<list_img.size();a++){
                			nurseImagesService.deleteById(list_img.get(a).getId());
                		}
    	                // 资格证
    	                nurseImages.setType(2);
    	                nurseImages.setId(UUID.randomUUID().toString());
    	                nurseImages.setUrl(seniorityProve);
    	                nurseImagesService.insert(nurseImages);
                	}
                	if(hospitalContract != null && !hospitalContract.equals("")){
                		NurseImages nurseImages_del = new NurseImages();
                		nurseImages_del.setCreatorId(userId);
                		nurseImages_del.setSourceId(nurseJobtitle_up.getId());
                		nurseImages_del.setType(7);
                		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
                		for(int a =0;a<list_img.size();a++){
                			nurseImagesService.deleteById(list_img.get(a).getId());
                		}
    	                // 医院聘书
    	                nurseImages.setType(7);
    	                nurseImages.setId(UUID.randomUUID().toString());
    	                nurseImages.setUrl(hospitalContract);
    	                nurseImagesService.insert(nurseImages);
                	}
                	if(nurseJobtitle.getWorkCard() != null && !nurseJobtitle.getWorkCard().equals("")){
                		NurseImages nurseImages_del = new NurseImages();
                		nurseImages_del.setCreatorId(userId);
                		nurseImages_del.setSourceId(nurseJobtitle_up.getId());
                		nurseImages_del.setType(8);
                		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
                		for(int a =0;a<list_img.size();a++){
                			nurseImagesService.deleteById(list_img.get(a).getId());
                		}
    	                // 工牌
    	                nurseImages.setType(8);
    	                nurseImages.setId(UUID.randomUUID().toString());
    	                nurseImages.setUrl(nurseJobtitle.getWorkCard());
    	                nurseImagesService.insert(nurseImages);
                	}

                }
                //	康复师-验证康复师证件
                /**	康复师资格证正面	therapistZ*/
                /**	康复师资格证反面	therapistF*/
                if (nurseType == 2) {
                	if(therapistZ != null && !therapistZ.equals("")){
                		NurseImages nurseImages_del = new NurseImages();
                		nurseImages_del.setCreatorId(userId);
                		nurseImages_del.setSourceId(nurseJobtitle_up.getId());
                		nurseImages_del.setType(9);
                		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
                		for(int a =0;a<list_img.size();a++){
                			nurseImagesService.deleteById(list_img.get(a).getId());
                		}
    	                // 康复师资格证正面
    	                nurseImages.setType(9);
    	                nurseImages.setId(UUID.randomUUID().toString());
    	                nurseImages.setUrl(therapistZ);
    	                nurseImagesService.insert(nurseImages);
                	}
                	if(nurseJobtitle.getTherapistF() != null && !nurseJobtitle.getTherapistF().equals("")){
                		NurseImages nurseImages_del = new NurseImages();
                		nurseImages_del.setCreatorId(userId);
                		nurseImages_del.setSourceId(nurseJobtitle_up.getId());
                		nurseImages_del.setType(10);
                		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
                		for(int a =0;a<list_img.size();a++){
                			nurseImagesService.deleteById(list_img.get(a).getId());
                		}
    	                // 康复师资格证正面
    	                nurseImages.setType(10);
    	                nurseImages.setId(UUID.randomUUID().toString());
    	                nurseImages.setUrl(nurseJobtitle.getTherapistF());
    	                nurseImagesService.insert(nurseImages);
                	}
                }
                //	母婴师-验证母婴师证件
                /**	母婴师	fransnanaCard*/
                if (nurseType == 3) {
                	if(fransnanaCard != null && !fransnanaCard.equals("")){
                		NurseImages nurseImages_del = new NurseImages();
                		nurseImages_del.setCreatorId(userId);
                		nurseImages_del.setSourceId(nurseJobtitle_up.getId());
                		nurseImages_del.setType(11);
                		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
                		for(int a =0;a<list_img.size();a++){
                			nurseImagesService.deleteById(list_img.get(a).getId());
                		}
    	                // 母婴师-验证母婴师证件
    	                nurseImages.setType(11);
    	                nurseImages.setId(UUID.randomUUID().toString());
    	                nurseImages.setUrl(fransnanaCard);
    	                nurseImagesService.insert(nurseImages);
                	}
                }
            }
   			
   			return JSONUtil.toJSONResult(1, "添加完成", null);
    	} catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseAudting.setWapAudtingtwo.json,authCode=" + authCode + " user=" + userId , e);
        }
    	return null;
    }
    
    @ResponseBody
    @RequestMapping(name = "护士认证通用页面信息", path = "/getWapIndex.json")
    public byte[] getWapIndex(HttpServletRequest req, HttpServletResponse resp, String authCode,String userId){
    	try {
	   		 // 记录日志-debug
	        if (Util.debugLog.isDebugEnabled()) {
	             Util.debugLog.debug("nurseAudting.getWapIndex.json,authCode=" + authCode + " userId=" +userId );
	        }
   			if(StringUtils.isEmpty(userId)){
   				return JSONUtil.toJSONResult(0, "参数不完整", null);
   			} 
   			NurseJobtitle nurseJobtitle = new NurseJobtitle();
   			nurseJobtitle.setCreatorId(userId);
   			nurseJobtitle.setType(4);
   			nurseJobtitle.setStatus(-1);
   			List<NurseJobtitle> nurseJobtitle_list = nurseJobtitleService.list(nurseJobtitle);
   			
   			
   			if(nurseJobtitle_list.size()>0){
   				NurseImages nurseImages = new NurseImages();
   	   			nurseImages.setCreatorId(userId);
   	   			nurseImages.setSourceId(nurseJobtitle_list.get(0).getId());
   	   			List<NurseImages> nurseImages_list = nurseImagesService.list(nurseImages);
   	   			nurseJobtitle_list.get(0).setNurseImages(nurseImages_list);
   				return JSONUtil.toJSONResult(1, "成功", nurseJobtitle_list.get(0));
   			}
   			return JSONUtil.toJSONResult(0, "未填写信息", null);
    	} catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseAudting.getWapIndex.json,authCode=" + authCode + " user=" + userId , e);
        }
    	return null;
    }
    
    @ResponseBody
    @RequestMapping(name = "护士认证", path = "/wapPageone.json",method = RequestMethod.POST)
    public byte[] wapAudting(HttpServletRequest req, HttpServletResponse resp, String authCode,String id,String userId,String name,int sex,
    		String addess,String sfz,String sfzz,String sfzf,String sculpture){
    	try {
    		 // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseAudting.wapPageone.json,authCode=" + authCode + " userId=" +userId
                        + " name=" + name + " sex=" + sex
                        + " addess=" + addess + " sfzz=" + sfzz
                        + " sfzf=" + sfzf );
            }
    		if(StringUtils.isEmpty(userId) || 
    				StringUtils.isEmpty(name) ||
    				StringUtils.isEmpty(String.valueOf(sex)) ||
    				StringUtils.isEmpty(addess) ||
    				StringUtils.isEmpty(sfzz) ||
    				StringUtils.isEmpty(sfzf)||
    				StringUtils.isEmpty(sfz)){
    			return JSONUtil.toJSONResult(0, "参数不完整", null);
            } 
    		/**
        	* 验证输入身份证号
        	* 
        	* @param 待验证的字符串
        	* @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
        	*/
        	if (!Validate.IsIDcard(sfz)){
        		 return JSONUtil.toJSONResult(0, "身份证格式不正确", null);
        	}
    		 NurseJobtitle nurseJobtitle = new NurseJobtitle();
    		 nurseJobtitle.setId(UUIDUtils.getId());
    		 nurseJobtitle.setName(name);
    		 nurseJobtitle.setSex(sex);
    		 nurseJobtitle.setAddress(addess);
    		 nurseJobtitle.setSfz(sfz);
    		 nurseJobtitle.setStatus(-1);
    		 nurseJobtitle.setType(4);
    		 nurseJobtitle.setCreateTime(new Date());
    		 nurseJobtitle.setCreatorId(userId);
    		 nurseJobtitle.setCreatorName(name);
    		 
    		 if(StringUtils.isEmpty(id)){
    			 nurseJobtitleService.insert(nurseJobtitle);
    		 }else{
    			 nurseJobtitle.setId(id);
    			 nurseJobtitleService.update(nurseJobtitle);
    		 }
    		 
    		 /**
              * 图片类型,(如果source_id是护士1、护士头像，2、护士资质证正面，3、护士资质证反面，4、身份证正面，5、身份证反面 ,
              * 6.执业证，7.医院聘书，8.工牌，9.康复师资格证正面，10.康复师资格证反面，11.母婴师资格证)
              */
             NurseImages nurseImages = new NurseImages();
             nurseImages.setCreateTime(new Date());
             nurseImages.setStatus(1);
             nurseImages.setSourceId(nurseJobtitle.getId());
             nurseImages.setCreatorId(userId);
             nurseImages.setCreatorName(name);
             
             if(!StringUtils.isEmpty(sculpture)){
              	NurseImages nurseImages_del = new NurseImages();
          		nurseImages_del.setCreatorId(userId);
          		nurseImages_del.setSourceId(nurseJobtitle.getId());
          		nurseImages_del.setType(1);
          		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
          		for(int a =0;a<list_img.size();a++){
          			nurseImagesService.deleteById(list_img.get(a).getId());
          		}
          		// 身份证正面
          		nurseImages.setType(1);
          		nurseImages.setId(UUID.randomUUID().toString());
          		nurseImages.setUrl(sculpture);
          		nurseImagesService.insert(nurseImages);
              }
             
    		 if(!StringUtils.isEmpty(sfzz)){
             	NurseImages nurseImages_del = new NurseImages();
         		nurseImages_del.setCreatorId(userId);
         		nurseImages_del.setSourceId(nurseJobtitle.getId());
         		nurseImages_del.setType(4);
         		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
         		for(int a =0;a<list_img.size();a++){
         			nurseImagesService.deleteById(list_img.get(a).getId());
         		}
         		// 身份证正面
         		nurseImages.setType(4);
         		nurseImages.setId(UUID.randomUUID().toString());
         		nurseImages.setUrl(sfzz);
         		nurseImagesService.insert(nurseImages);
             }
             if(!StringUtils.isEmpty(sfzf)){
             	NurseImages nurseImages_del = new NurseImages();
         		nurseImages_del.setCreatorId(userId);
         		nurseImages_del.setSourceId(nurseJobtitle.getId());
         		nurseImages_del.setType(5);
         		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
         		for(int a =0;a<list_img.size();a++){
         			nurseImagesService.deleteById(list_img.get(a).getId());
         		}
         		// 身份证正面
         		nurseImages.setType(5);
         		nurseImages.setId(UUID.randomUUID().toString());
         		nurseImages.setUrl(sfzf);
         		nurseImagesService.insert(nurseImages);
             }
    		 return JSONUtil.toJSONResult(1, "提交成功", nurseJobtitle.getId());
    	} catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseAudting.wappageone.json,authCode=" + authCode + " user=" + userId , e);
        }
    	return null;
    }

    @ResponseBody
    @RequestMapping(name = "护士认证", path = "/pageone.json",method = RequestMethod.POST)
    public byte[] audting(HttpServletRequest req, HttpServletResponse resp, String authCode, User user,
            String goodSkills, String skillsArr, NurseJobtitles nurseJobtitles,String workY ,String njId,String nurseName,Integer nurseType) {
        try {
        	
            String token = "";
            try {
                token = req.getHeader("token");
            }
            catch (Exception e) {
            }
            
            NurseJobtitle nurseJobtitle = new NurseJobtitle();
            BeanUtils.copyProperties(nurseJobtitle, nurseJobtitles);
           if(!StringUtils.isEmpty(workY)){
        	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        	   Date workYs =sdf.parse(workY);
        	   nurseJobtitle.setWorkYears(workYs);
           } 
            nurseJobtitle.setStatus(user.getStatus());
            nurseJobtitle.setId(njId);
            nurseJobtitle.setName(nurseName);
            nurseJobtitle.setType(nurseType);
            // 记录日志-debug
            if (Util.debugLog.isDebugEnabled()) {
                Util.debugLog.debug("nurseAudting.pageone.json,authCode=" + authCode + " user=" + user.getId()
                        + " user=" + user.getPhone() + " token=" + token);
            }
            if(!Common.CheckPerson(user.getPhone(), user.getPassword(), token)){
            	return JSONUtil.toJSONResult(0, "token验证失败", null);
            }
            // 查空
           /* if (StringUtils.isEmpty(String.valueOf(nurseJobtitle.getType())) || StringUtils.isEmpty(user.getId())
                    || StringUtils.isEmpty(user.getPhone()) || StringUtils.isEmpty(user.getPassword())
                    || StringUtils.isEmpty(user.getType().toString())
                    || StringUtils.isEmpty(authCode)
                                                    || StringUtils.isEmpty(token)) {
                return JSONUtil.toJSONResult(0, "参数不能为空", null);
            }*/

            // 通用字段验证是否为空
           /* if (StringUtils.isEmpty(nurseJobtitles.getName()) || StringUtils.isEmpty(goodSkills)
                    || StringUtils.isEmpty(skillsArr) || StringUtils.isEmpty(nurseJobtitles.getAddress())
                    || StringUtils.isEmpty(nurseJobtitles.getHospital())
                    || StringUtils.isEmpty(nurseJobtitles.getSex().toString())
                    || StringUtils.isEmpty(nurseJobtitle.getType().toString())
                    || StringUtils.isEmpty(nurseJobtitle.getJobtitleId()) || StringUtils.isEmpty(nurseJobtitle.getSfz())
                    || StringUtils.isEmpty(nurseJobtitle.getSfzz()) || StringUtils.isEmpty(nurseJobtitle.getSfzf())
                    || StringUtils.isEmpty(nurseJobtitle.getWorkYears().toString())) {

                return JSONUtil.toJSONResult(0, "认证参数不能为空", null);
            }*/
            //	护士-验证护士证
            /**	执业证	charteredProve*/
            /**	资格证	seniorityProve*/
            /**	医院聘书	hospitalContract*/
            /**	工牌	workCard*/
            /*if (nurseJobtitle.getType() == 1) {

                if (StringUtils.isEmpty(nurseJobtitle.getCharteredProve())
                        || StringUtils.isEmpty(nurseJobtitle.getSeniorityProve())
                        || StringUtils.isEmpty(nurseJobtitle.getHospitalContract())
                        || StringUtils.isEmpty(nurseJobtitle.getWorkCard())) {
                    return JSONUtil.toJSONResult(0, "护士资格证参数不能为空", null);
                }

            }*/
            //	康复师-验证康复师证件
            /**	康复师资格证正面	therapistZ*/
            /**	康复师资格证反面	therapistF*/
           /* if (nurseJobtitle.getType() == 2) {
                if (StringUtils.isEmpty(nurseJobtitle.getTherapistZ())
                        || StringUtils.isEmpty(nurseJobtitle.getTherapistF())) {

                    return JSONUtil.toJSONResult(0, "康复师资格证参数不能为空", null);
                }
            }*/
            //	母婴师-验证母婴师证件
            /**	母婴师	fransnanaCard*/
          /*  if (nurseJobtitle.getType() == 3) {
                if (StringUtils.isEmpty(nurseJobtitle.getFransnanaCard())) {

                    return JSONUtil.toJSONResult(0, "母婴师资格证参数不能为空", null);
                }
            }*/
            
            if(!StringUtils.isEmpty(nurseJobtitle.getSfz())){
            	/**
            	* 验证输入身份证号
            	* 
            	* @param 待验证的字符串
            	* @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
            	*/
            	if (!Validate.IsIDcard(nurseJobtitle.getSfz())){
            		 return JSONUtil.toJSONResult(0, "身份证格式不正确", null);
            	}
            }
            
            String nj_id = UUID.randomUUID().toString();
            nurseJobtitle.setCreateTime(new Date());
            nurseJobtitle.setCreatorId(user.getId());
            nurseJobtitle.setCreatorName(user.getName());
            
            String i_n = "";
            if(nurseJobtitle.getId() != null && !nurseJobtitle.getId().equals("")){
            	
            	nj_id = nurseJobtitle.getId();
            	
            	boolean i_ns = nurseJobtitleService.update(nurseJobtitle);
            	if(i_ns){
            		i_n = "true";
            	}
            }else{
            	nurseJobtitle.setId(nj_id);
            	i_n = nurseJobtitleService.insert(nurseJobtitle);
            }
            if (i_n.length() < 1) {
                return JSONUtil.toJSONResult(0, "提交认证失败", null);
            }

            /**
             * 擅长技能-ID数组
             * goodSkills
             */
            try {
            	if(goodSkills != null && !goodSkills.equals("")){
            		
            		NurseSkills nurseSkills = new NurseSkills();
        			nurseSkills.setCreatorId(user.getId());
        			nurseSkills.setNurseType(nurseType);
        			nurseSkills.setType(2);
        			nurseSkills.setStatus(1);
        			List<NurseSkills> nurseSkills_list = nurseSkillsService.getNurseSkillsAc(nurseSkills);
        			if(nurseSkills_list != null){
        				for(int a =0;a<nurseSkills_list.size();a++){
        					nurseSkillsService.deleteById(nurseSkills_list.get(a).getId());
        				}
        			}
            		
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
            }
            catch (Exception e) {
            }

            /**
             * 技能-ID数组
             * skillsArr
             */
            try {
            	if(skillsArr != null && !skillsArr.equals("")){
            		
            		NurseSkills nurseSkills = new NurseSkills();
        			nurseSkills.setCreatorId(user.getId());
        			nurseSkills.setNurseType(nurseType);
        			nurseSkills.setType(1);
        			nurseSkills.setStatus(1);
        			List<NurseSkills> nurseSkills_list = nurseSkillsService.getNurseSkillsAc(nurseSkills);
        			if(nurseSkills_list != null){
        				for(int a =0;a<nurseSkills_list.size();a++){
        					nurseSkillsService.deleteById(nurseSkills_list.get(a).getId());
        				}
        			}
            		
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
            }
            catch (Exception e) {
            }

            /**
             * 图片类型,(如果source_id是护士1、护士头像，2、护士资质证正面，3、护士资质证反面，4、身份证正面，5、身份证反面 ,
             * 6.执业证，7.医院聘书，8.工牌，9.康复师资格证正面，10.康复师资格证反面，11.母婴师资格证)
             */
            NurseImages nurseImages = new NurseImages();
            nurseImages.setCreateTime(new Date());
            nurseImages.setStatus(1);
            nurseImages.setSourceId(nj_id);
            nurseImages.setCreatorId(user.getId());
            nurseImages.setCreatorName(user.getName());
            
            if(!StringUtils.isEmpty(nurseJobtitle.getSculpture())){
            	NurseImages nurseImages_del = new NurseImages();
        		nurseImages_del.setCreatorId(user.getId());
        		nurseImages_del.setSourceId(nj_id);
        		nurseImages_del.setType(1);
        		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
        		for(int a =0;a<list_img.size();a++){
        			nurseImagesService.deleteById(list_img.get(a).getId());
        		}
        		
        		// 身份证正面
        		nurseImages.setId(UUID.randomUUID().toString());
        		nurseImages.setType(1);
        		nurseImages.setUrl(nurseJobtitle.getSculpture());
        		nurseImagesService.insert(nurseImages);
        		
            }
            
            if(!StringUtils.isEmpty(nurseJobtitle.getSfzz())){
            	NurseImages nurseImages_del = new NurseImages();
        		nurseImages_del.setCreatorId(user.getId());
        		nurseImages_del.setSourceId(nj_id);
        		nurseImages_del.setType(4);
        		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
        		for(int a =0;a<list_img.size();a++){
        			nurseImagesService.deleteById(list_img.get(a).getId());
        		}
        		
        		// 身份证正面
        		nurseImages.setType(4);
        		nurseImages.setId(UUID.randomUUID().toString());
        		nurseImages.setUrl(nurseJobtitle.getSfzz());
        		nurseImagesService.insert(nurseImages);
        		
            }
            if(!StringUtils.isEmpty(nurseJobtitle.getSfzf())){
            	NurseImages nurseImages_del = new NurseImages();
        		nurseImages_del.setCreatorId(user.getId());
        		nurseImages_del.setSourceId(nj_id);
        		nurseImages_del.setType(5);
        		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
        		for(int a =0;a<list_img.size();a++){
        			nurseImagesService.deleteById(list_img.get(a).getId());
        		}
        		// 身份证正面
        		nurseImages.setType(5);
        		 nurseImages.setId(UUID.randomUUID().toString());
        		nurseImages.setUrl(nurseJobtitle.getSfzf());
        		nurseImagesService.insert(nurseImages);
            }
            
            //			护士-验证护士证
            /**	执业证	charteredProve*/
            /**	资格证	seniorityProve*/
            /**	医院聘书	hospitalContract*/
            /**	工牌	workCard*/
            if (nurseJobtitle.getType() == 1) {

            	if(nurseJobtitle.getCharteredProve() != null && !nurseJobtitle.getCharteredProve().equals("")){
            		NurseImages nurseImages_del = new NurseImages();
            		nurseImages_del.setCreatorId(user.getId());
            		nurseImages_del.setSourceId(nj_id);
            		nurseImages_del.setType(6);
            		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
            		for(int a =0;a<list_img.size();a++){
            			nurseImagesService.deleteById(list_img.get(a).getId());
            		}
            		
            		// 执业证
            		nurseImages.setType(6);
            		 nurseImages.setId(UUID.randomUUID().toString());
            		nurseImages.setUrl(nurseJobtitle.getCharteredProve());
            		nurseImagesService.insert(nurseImages);
            	}
            	if(nurseJobtitle.getSeniorityProve() != null && !nurseJobtitle.getSeniorityProve().equals("")){
            		NurseImages nurseImages_del = new NurseImages();
            		nurseImages_del.setCreatorId(user.getId());
            		nurseImages_del.setSourceId(nj_id);
            		nurseImages_del.setType(2);
            		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
            		for(int a =0;a<list_img.size();a++){
            			nurseImagesService.deleteById(list_img.get(a).getId());
            		}
	                // 资格证
	                nurseImages.setType(2);
	                nurseImages.setId(UUID.randomUUID().toString());
	                nurseImages.setUrl(nurseJobtitle.getSeniorityProve());
	                nurseImagesService.insert(nurseImages);
            	}
            	if(nurseJobtitle.getHospitalContract() != null && !nurseJobtitle.getHospitalContract().equals("")){
            		NurseImages nurseImages_del = new NurseImages();
            		nurseImages_del.setCreatorId(user.getId());
            		nurseImages_del.setSourceId(nj_id);
            		nurseImages_del.setType(7);
            		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
            		for(int a =0;a<list_img.size();a++){
            			nurseImagesService.deleteById(list_img.get(a).getId());
            		}
	                // 医院聘书
	                nurseImages.setType(7);
	                nurseImages.setId(UUID.randomUUID().toString());
	                nurseImages.setUrl(nurseJobtitle.getHospitalContract());
	                nurseImagesService.insert(nurseImages);
            	}
            	if(nurseJobtitle.getWorkCard() != null && !nurseJobtitle.getWorkCard().equals("")){
            		NurseImages nurseImages_del = new NurseImages();
            		nurseImages_del.setCreatorId(user.getId());
            		nurseImages_del.setSourceId(nj_id);
            		nurseImages_del.setType(8);
            		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
            		for(int a =0;a<list_img.size();a++){
            			nurseImagesService.deleteById(list_img.get(a).getId());
            		}
	                // 工牌
	                nurseImages.setType(8);
	                nurseImages.setId(UUID.randomUUID().toString());
	                nurseImages.setUrl(nurseJobtitle.getWorkCard());
	                nurseImagesService.insert(nurseImages);
            	}

            }
            //	康复师-验证康复师证件
            /**	康复师资格证正面	therapistZ*/
            /**	康复师资格证反面	therapistF*/
            if (nurseJobtitle.getType() == 2) {
            	if(nurseJobtitle.getTherapistZ() != null && !nurseJobtitle.getTherapistZ().equals("")){
            		NurseImages nurseImages_del = new NurseImages();
            		nurseImages_del.setCreatorId(user.getId());
            		nurseImages_del.setSourceId(nj_id);
            		nurseImages_del.setType(9);
            		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
            		for(int a =0;a<list_img.size();a++){
            			nurseImagesService.deleteById(list_img.get(a).getId());
            		}
	                // 康复师资格证正面
	                nurseImages.setType(9);
	                nurseImages.setId(UUID.randomUUID().toString());
	                nurseImages.setUrl(nurseJobtitle.getTherapistZ());
	                nurseImagesService.insert(nurseImages);
            	}
            	if(nurseJobtitle.getTherapistF() != null && !nurseJobtitle.getTherapistF().equals("")){
            		NurseImages nurseImages_del = new NurseImages();
            		nurseImages_del.setCreatorId(user.getId());
            		nurseImages_del.setSourceId(nj_id);
            		nurseImages_del.setType(10);
            		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
            		for(int a =0;a<list_img.size();a++){
            			nurseImagesService.deleteById(list_img.get(a).getId());
            		}
	                // 康复师资格证正面
	                nurseImages.setType(10);
	                nurseImages.setId(UUID.randomUUID().toString());
	                nurseImages.setUrl(nurseJobtitle.getTherapistF());
	                nurseImagesService.insert(nurseImages);
            	}
            }
            //	母婴师-验证母婴师证件
            /**	母婴师	fransnanaCard*/
            if (nurseJobtitle.getType() == 3) {
            	if(nurseJobtitle.getFransnanaCard() != null && !nurseJobtitle.getFransnanaCard().equals("")){
            		NurseImages nurseImages_del = new NurseImages();
            		nurseImages_del.setCreatorId(user.getId());
            		nurseImages_del.setSourceId(nj_id);
            		nurseImages_del.setType(11);
            		List<NurseImages> list_img = nurseImagesService.list(nurseImages_del);
            		for(int a =0;a<list_img.size();a++){
            			nurseImagesService.deleteById(list_img.get(a).getId());
            		}
	                // 母婴师-验证母婴师证件
	                nurseImages.setType(11);
	                nurseImages.setId(UUID.randomUUID().toString());
	                nurseImages.setUrl(nurseJobtitle.getFransnanaCard());
	                nurseImagesService.insert(nurseImages);
            	}
            }

            return JSONUtil.toJSONResult(1, "提交成功", nj_id);
        }
        catch (Exception e) {
            // 记录日志-fail
            Util.failLog.error("nurseAudting.pageone.json,authCode=" + authCode + " user=" + user.getId() + " user="
                    + user.getPhone(), e);
        }

        return null;
    }

}
