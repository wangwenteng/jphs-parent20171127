package com.jinpaihushi.jphs.sequence.dao;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.sequence.model.Sequence;

/**
 * 
 * @author wangwt
 * @date 2017-06-30 13:56:20
 * @version 1.0
 */
@Repository("sequenceDao")
public interface SequenceDao extends BaseDao<Sequence> {
	
	/**
	 * {根据表名获取主键的序列值}
	 * 
	 * @param tableName 表名
	 * @return 序列值
	 * @author: wangwt
	 */
	String getCurrentVal(String tableName);
	
}
