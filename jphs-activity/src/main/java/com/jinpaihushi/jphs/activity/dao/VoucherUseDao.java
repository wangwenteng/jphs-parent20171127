package com.jinpaihushi.jphs.activity.dao;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.activity.model.VoucherUse;

/**
 * 
 * @author zhangzd
 * @date 2017-06-26 14:48:27
 * @version 1.0
 */
@Repository("voucherUseDao")
public interface VoucherUseDao extends BaseDao<VoucherUse> {

	Page<VoucherUse> getList(VoucherUse voucherUse);

	Page<VoucherUse> getDetailList(String id);

	VoucherUse getVoucherUse(String id);

	
}
