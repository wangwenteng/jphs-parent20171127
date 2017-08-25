package com.jinpaihushi.jphs.column.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.column.dao.ColumnDao;
import com.jinpaihushi.jphs.column.model.Column;
import com.jinpaihushi.jphs.column.service.ColumnService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-08-07 14:05:57
 * @version 1.0
 */
@Service("columnService")
public class ColumnServiceImpl extends BaseServiceImpl<Column> implements ColumnService{

	@Autowired
	private ColumnDao columnDao;
	
	@Override
	protected BaseDao<Column> getDao(){
		return columnDao;
	}

}