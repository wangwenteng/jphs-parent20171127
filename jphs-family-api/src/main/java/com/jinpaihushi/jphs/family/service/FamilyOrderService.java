package com.jinpaihushi.jphs.family.service;

import java.util.SortedMap;

import com.jinpaihushi.jphs.family.model.FamilyOrder;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @version 1.0
 */
public interface FamilyOrderService extends BaseService<FamilyOrder> {

	int userWechatFamilyOrder(String openId);
	int userIdFamilyOrder(String id);
	byte[] setOrderFamily(String userId,String Ip,String name,String promoCode,String wxNo,String fmId,String openId,int payType,String code,String card);
	
	// 商品支付回调
	boolean updateWechatfamilyOrderStutas(SortedMap<Object, Object> packageParams);
}