package com.jinpaihushi.jphs.nurse.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.dao.NurseImagesDao;
import com.jinpaihushi.jphs.nurse.dao.NurseSkillDao;
import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.model.NurseSkill;
import com.jinpaihushi.jphs.nurse.service.NurseSkillService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author wangwt
 * @date 2017-06-27 11:24:30
 * @version 1.0
 */
@Service("nurseSkillService")
public class NurseSkillServiceImpl extends BaseServiceImpl<NurseSkill> implements NurseSkillService{

	@Autowired
	private NurseSkillDao nurseSkillDao;
	@Autowired
	private NurseImagesDao nurseImagesDao;
	@Override
	protected BaseDao<NurseSkill> getDao(){
		return nurseSkillDao;
	}

	@Override
	public List<NurseSkill> queryDetail(NurseSkill nurseSkill) {
		List<NurseSkill> list = nurseSkillDao.queryDetail(nurseSkill);
		return list;
	}

	@Override
	@Transactional
	public boolean updateNurseSkill(NurseSkill nurseSkill) {
		int i = nurseSkillDao.update(nurseSkill);
		if(i>0){
			//获取发布技能时添加的图片
			NurseImages query =new NurseImages();
			query.setSourceId(nurseSkill.getId());
			List<NurseImages> queryList = nurseImagesDao.list(query);
			for (NurseImages nurseImages : queryList) {
				nurseImagesDao.deleteById(nurseImages.getId());
			}
			//插入修改后的图片
			List<NurseImages> insertList = nurseSkill.getNurseImage();
			
			for (NurseImages nurseImages : insertList) {
				if(StringUtils.isNotEmpty(nurseImages.getUrl())){
					nurseImages.setId(UUIDUtils.getId());
					nurseImages.setCreateTime(new Date());
					nurseImages.setStatus(0);
					nurseImages.setSourceId(nurseSkill.getId());
					nurseImagesDao.insert(nurseImages);
				}
			}
			return true;
		}
		return false;
	}
		
}