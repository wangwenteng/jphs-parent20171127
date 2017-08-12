package com.jinpaihushi.jphs.column.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.column.dao.ColumnServiceDao;
import com.jinpaihushi.jphs.column.model.ColumnService;
import com.jinpaihushi.jphs.column.service.ColumnServiceService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-08-07 14:05:57
 * @version 1.0
 */
@Service("columnServiceService")
public class ColumnServiceServiceImpl extends BaseServiceImpl<ColumnService> implements ColumnServiceService{

	@Autowired
	private ColumnServiceDao columnServiceDao;
	
	@Override
	protected BaseDao<ColumnService> getDao(){
		return columnServiceDao;
	}

}