package com.jinpaihushi.jphs.voucher.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.voucher.model.VoucherRepertory;

/**
 * 
 * @author yangsong
 * @date 2017-07-14 14:35:45
 * @version 1.0
 */
@Repository("voucherRepertoryDao")
public interface VoucherRepertoryDao extends BaseDao<VoucherRepertory> {
    /**  
     * 根据用户下单的商品获取用户可用的优惠券
     * @param map 用户id 品类id 商品的id
     * @return
     */
    List<Map<String, Object>> getUserVocher(Map<String, Object> map);

    /**
     * 根据主键修改优惠券的时间（将优惠券的使用时间置为null）
     * @param id
     * @return
     */
    int updataUseTime(String id);
}
