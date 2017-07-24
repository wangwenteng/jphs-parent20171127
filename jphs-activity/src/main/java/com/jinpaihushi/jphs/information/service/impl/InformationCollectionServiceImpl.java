package com.jinpaihushi.jphs.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.information.dao.InformationCollectionDao;
import com.jinpaihushi.jphs.information.model.InformationCollection;
import com.jinpaihushi.jphs.information.service.InformationCollectionService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-07-19 15:01:20
 * @version 1.0
 */
@Service("informationCollectionService")
public class InformationCollectionServiceImpl extends BaseServiceImpl<InformationCollection> implements InformationCollectionService{

	@Autowired
	private InformationCollectionDao informationCollectionDao;
	
	@Override
	protected BaseDao<InformationCollection> getDao(){
		return informationCollectionDao;
	}

}