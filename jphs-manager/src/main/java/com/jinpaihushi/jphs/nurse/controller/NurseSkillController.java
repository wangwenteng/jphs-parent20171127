package com.jinpaihushi.jphs.nurse.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.nurse.model.NurseSkill;
import com.jinpaihushi.jphs.nurse.service.NurseSkillService;
import com.jinpaihushi.jphs.skill.model.Skill;
import com.jinpaihushi.jphs.skill.service.SkillService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author wangwt
 * @date 2017-06-28 08:54:37
 * @version 1.0
 */
@Controller
@RequestMapping(name = "护士技能管理", path = "/nurse/skill")
public class NurseSkillController extends BaseController<NurseSkill> {

	@Autowired
	private NurseSkillService nurseSkillService;
	@Autowired
	private SkillService skillService;
	@Override
	protected BaseService<NurseSkill> getService() {
		return nurseSkillService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			NurseSkill nurseSkill, Integer p, Integer n) {
		startPage(p, n);
		List<NurseSkill> list = nurseSkillService.queryDetail(nurseSkill);
		PageInfos<NurseSkill> pageInfo = new PageInfos<NurseSkill>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "nurse/nurse/skill/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			NurseSkill nurseSkill) {
		List<NurseSkill> list = nurseSkillService.queryDetail(nurseSkill);
		NurseSkill result = list.get(0);
		Skill query= new Skill();
		query.setStatus(0);
		List<Skill> skillList = skillService.list(query);
		modelMap.put("nurseSkill", result);
		modelMap.put("skill", skillList);
		return "nurse/nurse/skill/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "nurse/nurse/skill/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			NurseSkill nurseSkill) {
 		List<NurseSkill> list = nurseSkillService.queryDetail(nurseSkill);
		NurseSkill result = list.get(0);
		Skill query= new Skill();
		query.setStatus(0);
		List<Skill> skillList = skillService.list(query);
		modelMap.put("nurseSkill", result);
		modelMap.put("skill", skillList);
		return "nurse/nurse/skill/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, NurseSkill nurseSkill) {

		if (nurseSkill.getId() != null && !nurseSkill.getId().equals("")) {
			boolean b = nurseSkillService.updateNurseSkill(nurseSkill);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/nurse/skill/err.jhtml";
			}
		} else {
			nurseSkill.setId(UUID.randomUUID().toString());
			String result = nurseSkillService.insert(nurseSkill);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/nurse/skill/err.jhtml";
			}
		}
		return "redirect:/nurse/skill/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {
		NurseSkill nurseSkill =new NurseSkill();
		nurseSkill.setId(id);
		nurseSkill.setStatus(-1);
		boolean b = nurseSkillService.update(nurseSkill);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/nurse/skill/err.jhtml";
		}

		return "redirect:/nurse/skill/index.jhtml";
	}
	
	

}
