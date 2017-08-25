package com.jinpaihushi.jphs.peizhen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.peizhen.dao.PeizhenPharmacyRemindDao;
import com.jinpaihushi.jphs.peizhen.model.PeizhenPharmacyRemind;
import com.jinpaihushi.jphs.peizhen.service.PeizhenPharmacyRemindService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-08-18 14:06:14
 * @version 1.0
 */
@Service("peizhenPharmacyRemindService")
public class PeizhenPharmacyRemindServiceImpl extends BaseServiceImpl<PeizhenPharmacyRemind> implements PeizhenPharmacyRemindService{

	@Autowired
	private PeizhenPharmacyRemindDao peizhenPharmacyRemindDao;
	
	@Override
	protected BaseDao<PeizhenPharmacyRemind> getDao(){
		return peizhenPharmacyRemindDao;
	}

}