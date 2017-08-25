package com.jinpaihushi.jphs.tijian.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.tijian.dao.TijianHospitalDao;
import com.jinpaihushi.jphs.tijian.model.TijianHospital;
import com.jinpaihushi.jphs.tijian.service.TijianHospitalService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-08-08 15:34:53
 * @version 1.0
 */
@Service("tijianHospitalService")
public class TijianHospitalServiceImpl extends BaseServiceImpl<TijianHospital> implements TijianHospitalService{

	@Autowired
	private TijianHospitalDao tijianHospitalDao;
	
	@Override
	protected BaseDao<TijianHospital> getDao(){
		return tijianHospitalDao;
	}
	
	public List<Map<String,Object>> getAllHospital(String platformId){
		return tijianHospitalDao.getAllHospital(platformId);
	}

}