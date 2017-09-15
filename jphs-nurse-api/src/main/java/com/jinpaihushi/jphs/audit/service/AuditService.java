package com.jinpaihushi.jphs.audit.service;

import java.util.List;

import com.jinpaihushi.jphs.audit.model.Audit;
import com.jinpaihushi.service.BaseService;

/**
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @version 1.0
 */
public interface AuditService extends BaseService<Audit> {

    List<Audit> getNurseAudit(Audit audit);

    int insertAudit(Audit audit, String nurseJobtitleId);

}