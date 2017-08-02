package com.jinpaihushi.jphs.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.activity.dao.VoucherUseDao;
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.jphs.activity.service.VoucherUseService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author zhangzd
 * @date 2017-06-26 14:48:27
 * @version 1.0
 */
@Service("voucherUseService")
public class VoucherUseServiceImpl extends BaseServiceImpl<VoucherUse> implements VoucherUseService{

	@Autowired
	private VoucherUseDao voucherUseDao;
	
	@Override
	protected BaseDao<VoucherUse> getDao(){
		return voucherUseDao;
	}

	@Override
	public Page<VoucherUse> getList(VoucherUse voucherUse) {
		// TODO Auto-generated method stub
		Page<VoucherUse> list = voucherUseDao.getList(voucherUse);
		
		return list;
	}

	@Override
	public Page<VoucherUse> getDetailtList(VoucherUse voucherUse) {
		// TODO Auto-generated method stub
		return voucherUseDao.getDetailtList(voucherUse);
	}
	
	@Override
	public VoucherUse getVoucherUse(String id) {
		// TODO Auto-generated method stub
		return voucherUseDao.getVoucherUse(id);
	}


}