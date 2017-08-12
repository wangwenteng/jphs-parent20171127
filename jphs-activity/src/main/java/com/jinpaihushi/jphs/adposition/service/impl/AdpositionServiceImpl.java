package com.jinpaihushi.jphs.adposition.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.adposition.dao.AdpositionDao;
import com.jinpaihushi.jphs.adposition.model.Adposition;
import com.jinpaihushi.jphs.adposition.service.AdpositionService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-08-07 11:47:31
 * @version 1.0
 */
@Service("adpositionService")
public class AdpositionServiceImpl extends BaseServiceImpl<Adposition> implements AdpositionService{

	@Autowired
	private AdpositionDao adpositionDao;
	
	@Override
	protected BaseDao<Adposition> getDao(){
		return adpositionDao;
	}

	public List<Map<String,Object>> getAppindex(Integer type){
		return adpositionDao.getAppindex(type);
	}
	
}