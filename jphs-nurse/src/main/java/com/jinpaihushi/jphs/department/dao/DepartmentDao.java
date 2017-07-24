package com.jinpaihushi.jphs.department.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.department.model.Department;

/**
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @version 1.0
 */
@Repository("departmentDao")
public interface DepartmentDao extends BaseDao<Department> {
	
	
	
}
