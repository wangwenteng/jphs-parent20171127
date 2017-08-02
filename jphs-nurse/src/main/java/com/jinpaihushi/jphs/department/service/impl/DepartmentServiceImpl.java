package com.jinpaihushi.jphs.department.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.department.dao.DepartmentDao;
import com.jinpaihushi.jphs.department.model.Department;
import com.jinpaihushi.jphs.department.service.DepartmentService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @version 1.0
 */
@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService{

	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	protected BaseDao<Department> getDao(){
		return departmentDao;
	}

}