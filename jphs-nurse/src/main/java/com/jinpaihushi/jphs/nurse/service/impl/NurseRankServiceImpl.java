package com.jinpaihushi.jphs.nurse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.dao.NurseRankDao;
import com.jinpaihushi.jphs.nurse.model.NurseRank;
import com.jinpaihushi.jphs.nurse.service.NurseRankService;
import com.jinpaihushi.service.impl.BaseServiceImpl;

/**
 * 
 * @author wangwenteng
 * @date 2017-09-20 09:27:07
 * @version 1.0
 */
@ComponentScan
@Service(value = "nurseRankService")
public class NurseRankServiceImpl extends BaseServiceImpl<NurseRank> implements NurseRankService {

    @Autowired
    private NurseRankDao nurseRankDao;

    @Override
    protected BaseDao<NurseRank> getDao() {
        return nurseRankDao;
    }

}