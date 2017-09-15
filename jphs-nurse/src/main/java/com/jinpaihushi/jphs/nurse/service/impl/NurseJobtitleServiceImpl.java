package com.jinpaihushi.jphs.nurse.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.dao.NurseImagesDao;
import com.jinpaihushi.jphs.nurse.dao.NurseJobtitleDao;
import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.model.NurseJobtitle;
import com.jinpaihushi.jphs.nurse.service.NurseJobtitleService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author scj
 * @date 2017-08-16 14:56:07
 * @version 1.0
 */
@Service("nurseJobtitleService")
public class NurseJobtitleServiceImpl extends BaseServiceImpl<NurseJobtitle> implements NurseJobtitleService {

    @Autowired
    private NurseJobtitleDao nurseJobtitleDao;

    @Autowired
    private NurseImagesDao nurseImagesDao;

    @Override
    protected BaseDao<NurseJobtitle> getDao() {
        return nurseJobtitleDao;
    }

    @Override
    public List<Map<String, Object>> getNurseList(Map<String, Object> query) {
        return nurseJobtitleDao.getNurseList(query);
    }

    public List<NurseJobtitle> getNurseAuditing(Map<String, Object> map) {
        return nurseJobtitleDao.getNurseAuditing(map);
    }

    @Override
    public List<NurseJobtitle> getNurseJobtitleDetail(NurseJobtitle nurseJobtitle) {
        return nurseJobtitleDao.getNurseJobtitleDetail(nurseJobtitle);
    }

    @Override
    public NurseJobtitle getNurseJobtitleDetails(NurseJobtitle nurseJobtitle) {
        NurseJobtitle jobtitle = nurseJobtitleDao.getNurseJobtitleDetail(nurseJobtitle).get(0);
        //获取护士的认证图片
        NurseImages nurseImages = new NurseImages();
        nurseImages.setSourceId(nurseJobtitle.getId());
        nurseImages.setCreatorId(nurseJobtitle.getCreatorId());
        nurseImages.setStatus(1);
        List<NurseImages> images = nurseImagesDao.list(nurseImages);
        /**
         * 图片类型,(如果source_id是护士1、护士头像，2、护士资质证正面，3、护士资质证反面，4、身份证正面，5、身份证反面 ,
         * 6.执业证，7.医院聘书，8.工牌，9.康复师资格证正面，10.康复师资格证反面，11.母婴师资格证)
         */
        for (NurseImages nurseImages2 : images) {
            if (nurseImages2.getType() == 1) {
                jobtitle.setSculpture(nurseImages2.getUrl());
            }
            if (nurseImages2.getType() == 2) {
                jobtitle.setSeniorityProve(nurseImages2.getUrl());
            }
            if (nurseImages2.getType() == 4) {
                jobtitle.setSfzz(nurseImages2.getUrl());
            }
            if (nurseImages2.getType() == 5) {
                jobtitle.setSfzf(nurseImages2.getUrl());
            }
            if (nurseImages2.getType() == 6) {
                jobtitle.setCharteredProve(nurseImages2.getUrl());
            }
            if (nurseImages2.getType() == 7) {
                jobtitle.setHospitalContract(nurseImages2.getUrl());
            }
            if (nurseImages2.getType() == 8) {
                jobtitle.setWorkCard(nurseImages2.getUrl());
            }
            if (nurseImages2.getType() == 9) {
                jobtitle.setTherapistZ(nurseImages2.getUrl());
            }
            if (nurseImages2.getType() == 10) {
                jobtitle.setTherapistF(nurseImages2.getUrl());
            }
            if (nurseImages2.getType() == 11) {
                jobtitle.setFransnanaCard(nurseImages2.getUrl());
            }
        }
        return jobtitle;
    }

}