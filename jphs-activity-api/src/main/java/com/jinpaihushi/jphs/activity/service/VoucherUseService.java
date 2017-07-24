package com.jinpaihushi.jphs.activity.service;

import com.github.pagehelper.Page;
import com.jinpaihushi.jphs.activity.model.VoucherUse;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author zhangzd
 * @date 2017-06-26 14:48:27
 * @version 1.0
 */
public interface VoucherUseService extends BaseService<VoucherUse> {

	Page<VoucherUse> getList(VoucherUse voucherUse);

	Page<VoucherUse> getDetailtList(VoucherUse voucherUse);

}