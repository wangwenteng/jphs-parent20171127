package com.jinpaihushi.jphs.family.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.family.dao.FamilyVoucherDao;
import com.jinpaihushi.jphs.family.model.FamilyVoucher;
import com.jinpaihushi.jphs.family.service.FamilyVoucherService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @version 1.0
 */
@Service("familyVoucherService")
public class FamilyVoucherServiceImpl extends BaseServiceImpl<FamilyVoucher> implements FamilyVoucherService{

	@Autowired
	private FamilyVoucherDao familyVoucherDao;
	
	@Override
	protected BaseDao<FamilyVoucher> getDao(){
		return familyVoucherDao;
	}

}