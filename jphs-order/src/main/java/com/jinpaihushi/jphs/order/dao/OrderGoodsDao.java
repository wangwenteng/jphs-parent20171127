package com.jinpaihushi.jphs.order.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.order.model.OrderGoods;

/**
 * 
 * @author yangsong
 * @date 2017-07-04 10:29:44
 * @version 1.0
 */
@Repository("orderGoodsDao")
public interface OrderGoodsDao extends BaseDao<OrderGoods> {

    Integer getOrderCount(OrderGoods orderGoods);

    int deleteByOrderId(String orderId);
    /**
     * 修改接单人
     * @param orderId 订单id  接单人id
     * @return
     */
    int updateByOrderId(OrderGoods orderGoods);

}
