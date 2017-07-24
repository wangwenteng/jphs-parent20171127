package com.jinpaihushi.jphs.voucher.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.jinpaihushi.jphs.voucher.model.Voucher;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-07-14 14:01:47
 * @version 1.0
 */
public interface VoucherService extends BaseService<Voucher> {

	Page<Voucher> getList(Voucher voucher);

	/**
	 * 获取下单页用户可用的优惠券
	 * @param pricePartId 站点商品的价格id
	 * @param goodsId 商品id
	 * @param userId 用户id
	 * @return
	 */
	List<Voucher> getUservoucher(String pricePartId, String goodsId, String userId);

	/**
	 * 个人中心所有优惠券
	 * @param userId 用户id
	 * @param type 1.已使用 2.已过去 3.可用 
	 * @return
	 */
	List<Map<String,Object>> getUserAllvoucher(String userId, Integer type);

}