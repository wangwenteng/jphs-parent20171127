package com.jinpaihushi.jphs.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.transaction.model.Transaction;
import com.jinpaihushi.jphs.user.model.User;

/**
 * 
 * @author yangsong
 * @date 2017-06-27 17:15:32
 * @version 1.0
 */
@Repository("userDao")
public interface UserDao extends BaseDao<User> {

	User getUserDetail(@Param("id") String id);

	Page<User> userList(User user);

	User findUser(User user);

	User queryUser(User user);

}
