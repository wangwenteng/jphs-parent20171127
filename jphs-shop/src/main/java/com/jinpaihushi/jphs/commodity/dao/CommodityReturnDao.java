package com.jinpaihushi.jphs.commodity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.commodity.model.CommodityReturn;

/**
 * 
 * @author yangsong
 * @date 2017-08-15 13:57:49
 * @version 1.0
 */
@Repository("commodityReturnDao")
public interface CommodityReturnDao extends BaseDao<CommodityReturn> {

	CommodityReturn getNotStatus(CommodityReturn commodityReturn);

	boolean updateReason(CommodityReturn commodityReturn);

	List<CommodityReturn> getListByCoId(String coId);
	
	
	
}
