package com.jinpaihushi.jphs.price.dao;

 
import java.util.List;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.price.model.PriceNurse;

/**
 * 
 * @author yangsong
 * @date 2017-08-16 15:07:51
 * @version 1.0
 */
@Repository("priceNurseDao")
public interface PriceNurseDao extends BaseDao<PriceNurse> {
 

	boolean deleteByUserAndGoods(PriceNurse pn);

	List<PriceNurse> getList(PriceNurse pn);
	
	
    /**
     * 护士的服务项目
     * @param userId 护士id
     * @return
     */
    List<Map<String, Object>> getServiceItems(@Param("userId") String userId);

	boolean updatePriceNurse(PriceNurse pn);

	PriceNurse getModel(PriceNurse priceNurse);

	boolean updateModel(PriceNurse pn);
 
}
