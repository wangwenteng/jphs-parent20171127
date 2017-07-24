package com.jinpaihushi.jphs.evaluation.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.Page;
import com.jinpaihushi.controller.BaseController;
import com.jinpaihushi.jphs.evaluation.model.Evaluation;
import com.jinpaihushi.jphs.evaluation.service.EvaluationService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author yangsong
 * @date 2017-06-30 12:02:09
 * @version 1.0
 */
@Controller
@RequestMapping(name = "Evaluation", path = "/evaluation")
public class EvaluationController extends BaseController<Evaluation> {

	@Autowired
	private EvaluationService evaluationService;

	@Override
	protected BaseService<Evaluation> getService() {
		return evaluationService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			Evaluation evaluation, Integer p, Integer n) {
		startPage(p, n);
		//Page<Evaluation> list = evaluationService.query(evaluation);
		Page<Evaluation> list = evaluationService.getInfo(evaluation);
		PageInfos<Evaluation> pageInfo = new PageInfos<Evaluation>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "user/evaluation/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Evaluation evaluation = evaluationService.loadById(id);
		modelMap.put("evaluation", evaluation);
		return "user/evaluation/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "user/evaluation/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		
		Evaluation eva = new Evaluation();
		eva.setId(id);
		
		  Page<Evaluation> info = evaluationService.getInfo(eva);
		modelMap.put("evaluation", info.get(0));
		return "user/evaluation/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Evaluation evaluation) {

		if (evaluation.getId() != null && !evaluation.getId().equals("")) {
			boolean b = evaluationService.update(evaluation);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/evaluation/err.jhtml";
			}
		} else {
			evaluation.setId(UUID.randomUUID().toString());
			String result = evaluationService.insert(evaluation);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/evaluation/err.jhtml";
			}
		}
		return "redirect:/evaluation/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = evaluationService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/evaluation/err.jhtml";
		}

		return "redirect:/evaluation/index.jhtml";
	}
	
	

}
