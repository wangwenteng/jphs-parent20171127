package com.jinpaihushi.jphs.nurse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.dao.NurseImagesDao;
import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.service.NurseImagesService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author fengrz
 * @date 2017-06-09 14:36:57
 * @version 1.0
 */
@Service("nurseImagesService")
public class NurseImagesServiceImpl extends BaseServiceImpl<NurseImages> implements NurseImagesService{

	@Autowired
	private NurseImagesDao nurseImagesDao;
	
	@Override
	protected BaseDao<NurseImages> getDao(){
		return nurseImagesDao;
	}

}