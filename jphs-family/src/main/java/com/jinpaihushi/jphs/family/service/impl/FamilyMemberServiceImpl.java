package com.jinpaihushi.jphs.family.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.family.dao.FamilyMemberDao;
import com.jinpaihushi.jphs.family.model.FamilyMember;
import com.jinpaihushi.jphs.family.service.FamilyMemberService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-09-22 15:56:54
 * @version 1.0
 */
@Service("familyMemberService")
public class FamilyMemberServiceImpl extends BaseServiceImpl<FamilyMember> implements FamilyMemberService{

	@Autowired
	private FamilyMemberDao familyMemberDao;
	
	@Override
	protected BaseDao<FamilyMember> getDao(){
		return familyMemberDao;
	}

}