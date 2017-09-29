package com.jinpaihushi.jphs.order.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.order.model.OrderOther;

/**
 * 
 * @author yangsong
 * @date 2017-07-04 10:39:09
 * @version 1.0
 */
@Repository("orderOtherDao")
public interface OrderOtherDao extends BaseDao<OrderOther> {

    boolean updateDetailAddress(OrderOther orderOther);

    int deleteByOrderId(String orderId);
    int updateByOrderId(OrderOther orderOther);
}	
