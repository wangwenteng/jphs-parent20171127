package com.jinpaihushi.jphs.audit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.audit.model.Audit;

/**
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @version 1.0
 */
@Repository("auditDao")
public interface AuditDao extends BaseDao<Audit> {

	List<Audit> getNurseAudit(Audit audit);
	
	
	
}
