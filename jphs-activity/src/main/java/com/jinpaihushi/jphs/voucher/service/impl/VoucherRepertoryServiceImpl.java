package com.jinpaihushi.jphs.voucher.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.voucher.dao.VoucherRepertoryDao;
import com.jinpaihushi.jphs.voucher.model.VoucherRepertory;
import com.jinpaihushi.jphs.voucher.service.VoucherRepertoryService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author yangsong
 * @date 2017-07-14 14:35:45
 * @version 1.0
 */
@Service("voucherRepertoryService")
public class VoucherRepertoryServiceImpl extends BaseServiceImpl<VoucherRepertory> implements VoucherRepertoryService{

	@Autowired
	private VoucherRepertoryDao voucherRepertoryDao;
	
	@Override
	protected BaseDao<VoucherRepertory> getDao(){
		return voucherRepertoryDao;
	}

}