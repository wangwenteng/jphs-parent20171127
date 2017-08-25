package com.jinpaihushi.jphs.tijian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.tijian.dao.TijianTypeDao;
import com.jinpaihushi.jphs.tijian.model.TijianType;
import com.jinpaihushi.jphs.tijian.service.TijianTypeService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-08-08 15:34:54
 * @version 1.0
 */
@Service("tijianTypeService")
public class TijianTypeServiceImpl extends BaseServiceImpl<TijianType> implements TijianTypeService{

	@Autowired
	private TijianTypeDao tijianTypeDao;
	
	@Override
	protected BaseDao<TijianType> getDao(){
		return tijianTypeDao;
	}

}