package com.jinpaihushi.jphs.voucher.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.voucher.model.Voucher;

/**
 * 
 * @author yangsong
 * @date 2017-07-14 14:01:47
 * @version 1.0
 */
@Repository("voucherDao")
public interface VoucherDao extends BaseDao<Voucher> {

	Page<Voucher> getList(Voucher voucher);

	/**  
	 * 根据用户下单的商品获取用户可用的优惠券
	 * @param map 用户id 品类id 商品的id
	 * @return
	 */
	List<Voucher> getUserVocher(Map<String, Object> map);
	
	List<Map<String, Object>> getUserAllVocher(Map<String, Object> map);
	
	Voucher getVocherByUser(String voucherUseId);
}
