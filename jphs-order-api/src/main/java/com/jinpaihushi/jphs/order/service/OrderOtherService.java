package com.jinpaihushi.jphs.order.service;

import com.jinpaihushi.jphs.order.model.OrderOther;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-07-04 10:39:09
 * @version 1.0
 */
public interface OrderOtherService extends BaseService<OrderOther> {

	boolean updateDetailAddress(OrderOther orderOther);


}