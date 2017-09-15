package com.jinpaihushi.jphs.audit.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.audit.dao.AuditDao;
import com.jinpaihushi.jphs.audit.model.Audit;
import com.jinpaihushi.jphs.audit.service.AuditService;
import com.jinpaihushi.jphs.department.dao.DepartmentDao;
import com.jinpaihushi.jphs.department.model.Department;
import com.jinpaihushi.jphs.nurse.dao.NurseDao;
import com.jinpaihushi.jphs.nurse.dao.NurseImagesDao;
import com.jinpaihushi.jphs.nurse.dao.NurseJobtitleDao;
import com.jinpaihushi.jphs.nurse.model.Nurse;
import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.model.NurseJobtitle;
import com.jinpaihushi.jphs.person.dao.PersonGroupDao;
import com.jinpaihushi.jphs.person.model.PersonGroup;
import com.jinpaihushi.jphs.user.dao.UserDao;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.jphs.worktime.service.WorktimeService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.TransactionTemplateUtils;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @version 1.0
 */
@Service("auditService")
public class AuditServiceImpl extends BaseServiceImpl<Audit> implements AuditService {

    @Autowired
    private AuditDao auditDao;

    @Autowired
    private NurseDao nurseDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private NurseJobtitleDao nurseJobtitleDao;

    @Autowired
    private PersonGroupDao personGroupDao;

    @Autowired
    private WorktimeService worktimeService;

    @Autowired
    private NurseImagesDao nurseImagesDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private PlatformTransactionManager txManager;

    @Override
    protected BaseDao<Audit> getDao() {
        return auditDao;
    }

    @Override
    public List<Audit> getNurseAudit(Audit audit) {
        List<Audit> list = auditDao.getNurseAudit(audit);
        return list;
    }

    @Override
    public int insertAudit(Audit audit, String nurseJobtitleId) {
        //事务模板
        TransactionTemplate transactionTemplate = TransactionTemplateUtils.getDefaultTransactionTemplate(txManager);
        String rs = (String) transactionTemplate.execute(new TransactionCallback<Object>() {
            public String doInTransaction(final TransactionStatus status) {
                try {
                    //判断护士的状态
                    Nurse nurse = new Nurse();
                    User user = null;
                    nurse.setCreatorId(audit.getCreatorId());
                    Nurse load = nurseDao.load(nurse);
                    NurseJobtitle nurseJobtitle = null;
                    NurseJobtitle jobtitle = nurseJobtitleDao.loadById(nurseJobtitleId);
                    if (load.getStatus() != 1) {
                        nurse.setSfz(jobtitle.getSfz());

                        nurse.setWorkYears(jobtitle.getWorkYears());
                        NurseImages image = new NurseImages();
                        image.setSourceId(nurseJobtitleId);
                        image.setType(1);
                        image.setCreatorId(audit.getCreatorId());
                        image = nurseImagesDao.load(image);
                        NurseImages nurseImage = new NurseImages();
                        nurseImage.setSourceId(audit.getCreatorId());
                        nurseImage.setCreatorId(audit.getCreatorId());
                        nurseImage.setType(1);
                        nurseImage = nurseImagesDao.load(nurseImage);
                        if (nurseImage == null) {
                            nurseImage = new NurseImages();
                            nurseImage.setId(UUIDUtils.getId());
                            nurseImage.setSourceId(audit.getCreatorId());
                            nurseImage.setCreatorId(audit.getCreatorId());
                            nurseImage.setType(1);
                            nurseImage.setCreateTime(new Date());
                            nurseImage.setStatus(1);
                            nurseImage.setUrl(image.getUrl());
                            nurseImagesDao.insert(nurseImage);
                        }
                        else {
                            nurseImage.setUrl(image.getUrl());
                            nurseImagesDao.update(nurseImage);
                        }
                        //插入默認分組
                        PersonGroup personGroup = new PersonGroup();
                        personGroup.setId(UUIDUtils.getId());
                        personGroup.setName("我的护士");
                        personGroup.setIsDefault(1);
                        personGroup.setStatus(0);
                        personGroup.setCreateTime(new Date());
                        personGroup.setCreatorId(audit.getCreatorId());
                        personGroup.setCreatorName(audit.getCreatorName());
                        personGroupDao.insert(personGroup);
                        //插入日程安排
                        worktimeService.insertNurseWorkTime(audit.getCreatorId());
                    }
                    user = new User();
                    user.setId(audit.getCreatorId());
                    user.setName(jobtitle.getName());
                    user.setSex(jobtitle.getSex());
                    user.setAddress(jobtitle.getAddress());
                    userDao.update(user);
                    if (jobtitle.getType() == 1) {
                        nurse.setHospital(jobtitle.getHospital());
                        Department department = departmentDao.loadById(jobtitle.getDepartmentId());
                        nurse.setDepartmentId(department.getName());
                    }
                    //修改护士的状态
                    nurse.setStatus(1);
                    nurse.setActive(1);
                    nurse.setId(load.getId());
                    nurseDao.update(nurse);
                    //修改头像
                    //插入審核記錄
                    audit.setCreatorName(user.getName());
                    int i = auditDao.insert(audit);
                    //更新職稱的狀態
                    nurseJobtitle = new NurseJobtitle();
                    nurseJobtitle.setCreatorId(audit.getCreatorId());
                    nurseJobtitle.setStatus(1);
                    nurseJobtitle.setId(nurseJobtitleId);
                    i = nurseJobtitleDao.update(nurseJobtitle);
                    return "1";

                }
                catch (Exception e) {

                    e.printStackTrace();
                    //日志打印区
                    status.setRollbackOnly();//回滚
                    return "0";
                }
            }

        });

        return (int) Integer.parseInt(rs);
    }

}