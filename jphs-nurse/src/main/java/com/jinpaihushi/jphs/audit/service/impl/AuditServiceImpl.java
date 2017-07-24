package com.jinpaihushi.jphs.audit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.audit.dao.AuditDao;
import com.jinpaihushi.jphs.audit.model.Audit;
import com.jinpaihushi.jphs.audit.service.AuditService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @version 1.0
 */
@Service("auditService")
public class AuditServiceImpl extends BaseServiceImpl<Audit> implements AuditService{

	@Autowired
	private AuditDao auditDao;
	
	@Override
	protected BaseDao<Audit> getDao(){
		return auditDao;
	}

	@Override
	public List<Audit> getNurseAudit(Audit audit) {
		List<Audit> list=auditDao.getNurseAudit(audit);
		return list;
	}
	
}