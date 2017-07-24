package com.jinpaihushi.jphs.nurse.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.department.model.Department;
import com.jinpaihushi.jphs.department.service.DepartmentService;
import com.jinpaihushi.jphs.jobtitle.model.JobtitleType;
import com.jinpaihushi.jphs.location.model.Location;
import com.jinpaihushi.jphs.location.service.LocationService;
import com.jinpaihushi.jphs.nurse.model.Nurse;
import com.jinpaihushi.jphs.nurse.model.NurseSkill;
import com.jinpaihushi.jphs.nurse.service.NurseService;
import com.jinpaihushi.jphs.nurse.service.NurseSkillService;
import com.jinpaihushi.jphs.system.model.SystemUser;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @version 1.0
 */
@Controller
@RequestMapping(name = "护士管理", path = "/nurse")
public class NurseController extends BaseController<Nurse> {

	@Autowired
	private NurseService nurseService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private NurseSkillService nurseSkillService;
	@Autowired
	private LocationService locationService;
	@Override
	protected BaseService<Nurse> getService() {
		return nurseService;
	}

	@RequestMapping(name = "已审核护士列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			Nurse nurse, Integer p, Integer n) {
		startPage(p, n);
		nurse.setStatus(1);
		List<Nurse> list = nurseService.getNurseDetail(nurse);
		PageInfos<Nurse> pageInfo = new PageInfos<Nurse>(list, req);
		//科室列表
		List<Department> department = departmentService.query(null);
		modelMap.put("department", department);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "nurse/nurse/list";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			Nurse nurse) {
		JSONObject message = new JSONObject();
		List<Department> department = departmentService.query(null);
		modelMap.put("department", department);
		Nurse result = nurseService.getNurseDetail(nurse).get(0);
		//获取护士的职称
		List<JobtitleType> list=nurseService.getNurseJobtitle(nurse);
		List<Location> location=(List<Location>)req.getSession().getAttribute("location");
		List<Location> locationList = locationService.getEasyTreeData(location,nurse.getId());
		message.put("treeData", locationList);
		modelMap.put("data", message);
		modelMap.put("nurse", result);
		modelMap.put("list", list);
		modelMap.put("addressName", result.getAddress().split("-")[3]);
		return "nurse/nurse/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {
		List<Department> department = departmentService.query(null);
		modelMap.put("department", department);
		return "nurse/nurse/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			Nurse nurse) {
		List<Department> department = departmentService.query(null);
		modelMap.put("department", department);
		Nurse result = nurseService.getNurseDetail(nurse).get(0);
		NurseSkill query =new NurseSkill();
		query.setCreatorId(result.getId());
		List<NurseSkill> skillList = nurseSkillService.queryDetail(query);
		modelMap.put("nurse", result);
		modelMap.put("skillList", skillList);
		modelMap.put("addressName", result.getAddress().split("-")[3]);
		return "nurse/nurse/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Nurse nurse,User user) {
		SystemUser systemUser =(SystemUser) req.getSession().getAttribute("session_user");
		user.setId(nurse.getUser().getId());
		nurse.setUser(user);
		if (nurse.getId() != null && !nurse.getId().equals("")) {
			String result = nurseService.updateNurse(nurse,null);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/nurse/err.jhtml";
			}
		} else {
			nurse.setCreatorId(systemUser.getId());
			nurse.setCreatorName(systemUser.getName());
			String result = nurseService.insertNurse(nurse);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/nurse/err.jhtml";
			}
		}
		return "redirect:/nurse/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {
		Nurse nurse =new Nurse();
		nurse.setId(id);
		nurse.setStatus(-1);
		boolean b = nurseService.update(nurse);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/nurse/err.jhtml";
		}

		return "redirect:/nurse/index.jhtml";
	}
	
	@RequestMapping(name = "护士统计", path = "/statistics.jhtml")
	public String statistics(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			Nurse nurse, @RequestParam(value="page",defaultValue="1",required = false)Integer p, @RequestParam(value="rows",defaultValue="10",required = false)Integer n) {
		List<Map<String, Object>> ordeList=nurseService.queryOrder(nurse);
		List<Map<String, Object>> incomeList=nurseService.queryIncome(nurse);
		modelMap.put("ordeList", ordeList);
		modelMap.put("incomeList", incomeList);
		return "nurse/nurse/statistics";
	}

}
