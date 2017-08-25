package com.jinpaihushi.jphs.workyears.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.workyears.dao.WorkyearsDao;
import com.jinpaihushi.jphs.workyears.model.Workyears;
import com.jinpaihushi.jphs.workyears.service.WorkyearsService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:31
 * @version 1.0
 */
@Service("workyearsService")
public class WorkyearsServiceImpl extends BaseServiceImpl<Workyears> implements WorkyearsService{

	@Autowired
	private WorkyearsDao workyearsDao;
	
	@Override
	protected BaseDao<Workyears> getDao(){
		return workyearsDao;
	}

}