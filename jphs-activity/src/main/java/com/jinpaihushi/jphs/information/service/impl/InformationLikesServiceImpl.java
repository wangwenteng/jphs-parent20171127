package com.jinpaihushi.jphs.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.information.dao.InformationLikesDao;
import com.jinpaihushi.jphs.information.model.InformationLikes;
import com.jinpaihushi.jphs.information.service.InformationLikesService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-07-19 15:01:20
 * @version 1.0
 */
@Service("informationLikesService")
public class InformationLikesServiceImpl extends BaseServiceImpl<InformationLikes> implements InformationLikesService{

	@Autowired
	private InformationLikesDao informationLikesDao;
	
	@Override
	protected BaseDao<InformationLikes> getDao(){
		return informationLikesDao;
	}

}