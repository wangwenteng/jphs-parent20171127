package com.jinpaihushi.jphs.order.service;

import com.jinpaihushi.jphs.order.model.OrderGoods;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author yangsong
 * @date 2017-07-04 10:29:44
 * @version 1.0
 */
public interface OrderGoodsService extends BaseService<OrderGoods> {

	Integer getOrderCount(OrderGoods orderGoods);

}