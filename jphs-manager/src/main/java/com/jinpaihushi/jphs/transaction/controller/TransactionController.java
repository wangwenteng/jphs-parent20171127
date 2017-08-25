package com.jinpaihushi.jphs.transaction.controller;

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
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.transaction.service.TransactionService;
import com.jinpaihushi.service.BaseService;
import com.jinpaihushi.utils.PageInfos;

/**
 * 
 * @author yangsong
 * @date 2017-06-29 18:40:45
 * @version 1.0
 */
@Controller
@RequestMapping(name = "交易记录", path = "/transaction")
public class TransactionController extends BaseController<Transaction> {

	@Autowired
	private TransactionService transactionService;

	@Override
	protected BaseService<Transaction> getService() {
		return transactionService;
	}

	@RequestMapping(name = "列表页", path = "/index.jhtml")
	public String index(HttpSession hs, HttpServletRequest req,
			HttpServletResponse resp, ModelMap modelMap,
			Transaction transaction, Integer p, Integer n) {
		startPage(p, n);
		transaction.setOrderby("t.create_time DESC");
		Page<Transaction> list = transactionService.getUserInfo(transaction);
		PageInfos<Transaction> pageInfo = new PageInfos<Transaction>(list, req);
		modelMap.put("list", list);
		modelMap.put("pageInfo", pageInfo);
		return "user/transaction/list";
	}

	@RequestMapping(name = "跳转到修改页", path = "/redirectUpdate.jhtml")
	public String toUpdate(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Transaction transaction = transactionService.loadById(id);
		modelMap.put("transaction", transaction);
		return "user/transaction/edit";
	}
	
	@RequestMapping(name = "跳转到添加页", path = "/redirectAddPage.jhtml")
	public String redirectAddPage(ModelMap modelMap) {

		return "user/transaction/edit";
	}
	
	@RequestMapping(name = "详情页", path = "/detail.jhtml", method = RequestMethod.GET)
	public String detail(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap,
			String id) {
		Transaction transaction = transactionService.loadById(id);
		modelMap.put("transaction", transaction);
		return "user/transaction/detail";
	}

	@RequestMapping(name = "添加或修改数据", path = "/insert.jhtml")
	public String insert(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, Transaction transaction) {

		if (transaction.getId() != null && !transaction.getId().equals("")) {
			boolean b = transactionService.update(transaction);
			if (b == false) {
				// 跳转到错误页
				return "redirect:/transaction/err.jhtml";
			}
		} else {
			transaction.setId(UUID.randomUUID().toString());
			String result = transactionService.insert(transaction);
			if (result.length() <= 0) {
				// 跳转到错误页
				return "redirect:/transaction/err.jhtml";
			}
		}
		return "redirect:/transaction/index.jhtml";
	}

	@RequestMapping(name = "删除数据", path = "/delete.jhtml")
	public String delete(HttpSession hs, HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap, String id) {

		boolean b = transactionService.deleteById(id);
		if (b == false) {
			// 跳转到错误页
			return "redirect:/transaction/err.jhtml";
		}

		return "redirect:/transaction/index.jhtml";
	}
	
	

}
