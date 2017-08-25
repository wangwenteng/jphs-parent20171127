package com.jinpaihushi.jphs.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.information.dao.InformationChannelDao;
import com.jinpaihushi.jphs.information.model.InformationChannel;
import com.jinpaihushi.jphs.information.service.InformationChannelService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-07-19 15:01:20
 * @version 1.0
 */
@Service("informationChannelService")
public class InformationChannelServiceImpl extends BaseServiceImpl<InformationChannel> implements InformationChannelService{

	@Autowired
	private InformationChannelDao informationChannelDao;
	
	@Override
	protected BaseDao<InformationChannel> getDao(){
		return informationChannelDao;
	}

}