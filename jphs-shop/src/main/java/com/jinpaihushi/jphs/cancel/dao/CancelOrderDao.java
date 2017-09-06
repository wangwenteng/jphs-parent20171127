package com.jinpaihushi.jphs.cancel.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.cancel.model.CancelOrder;

/**
 * 
 * @author yangsong
 * @date 2017-08-25 16:52:03
 * @version 1.0
 */
@Repository("cancelOrderDao")
public interface CancelOrderDao extends BaseDao<CancelOrder> {
	
	
	
}
