package com.jinpaihushi.jphs.family.service;

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

}