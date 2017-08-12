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

	
	List<Map<String, Object>> getUserAllVocher(Map<String, Object> map);
	
	Voucher getVocherByUser(String voucherUseId);
}
