package com.jinpaihushi.jphs.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.user.model.UserWorktime;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 16:35:43
 * @version 1.0
 */
@Repository("userWorktimeDao")
public interface UserWorktimeDao extends BaseDao<UserWorktime> {

	int deleteByCalendar(@Param("calendar")String calendar);
	
}
