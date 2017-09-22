package com.jinpaihushi.jphs.family.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.family.dao.FamilyOrderDao;
import com.jinpaihushi.jphs.family.model.FamilyOrder;
import com.jinpaihushi.jphs.family.service.FamilyOrderService;
import com.jinpaihushi.jphs.user.dao.UserDao;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @version 1.0
 */
@Service("familyOrderService")
public class FamilyOrderServiceImpl extends BaseServiceImpl<FamilyOrder> implements FamilyOrderService{

	@Autowired
	private FamilyOrderDao familyOrderDao;
	@Autowired
	private UserDao userDao;
	@Override
	protected BaseDao<FamilyOrder> getDao(){
		return familyOrderDao;
	}

	public int userWechatFamilyOrder(String openId){
		 User user = new User();
		 user.setOpenId(openId);
		 user.setStatus(1);
		 List<User> user_list = userDao.list(user);
		 if(user_list.size() < 1){
			 return 0;
		 }
         FamilyOrder familyOrder = new FamilyOrder();
         familyOrder.setCreatorId(user_list.get(0).getId());
         familyOrder.setStatus(1);
         List<FamilyOrder> familyOrder_list = familyOrderDao.list(familyOrder);
         if(familyOrder_list.size() < 1){
        	 return 0;
         }
         return 1;
	}
	
}