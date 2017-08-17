package com.jinpaihushi.jphs.nurse.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.area.dao.AreaDao;
import com.jinpaihushi.jphs.area.model.Area;
import com.jinpaihushi.jphs.jobtitle.dao.JobtitleTypeDao;
import com.jinpaihushi.jphs.jobtitle.model.Jobtitle;
import com.jinpaihushi.jphs.jobtitle.model.JobtitleType;
import com.jinpaihushi.jphs.nurse.dao.NurseDao;
import com.jinpaihushi.jphs.nurse.dao.NurseImagesDao;
import com.jinpaihushi.jphs.nurse.dao.NurseJobtitleDao;
import com.jinpaihushi.jphs.nurse.model.Nurse;
import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.model.NurseJobtitle;
import com.jinpaihushi.jphs.nurse.service.NurseService;
import com.jinpaihushi.jphs.sequence.dao.SequenceDao;
import com.jinpaihushi.jphs.user.dao.UserDao;
import com.jinpaihushi.jphs.user.model.User;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author fengrz
 * @date 2017-06-09 08:51:43
 * @version 1.0
 */
@Service("nurseService")
public class NurseServiceImpl extends BaseServiceImpl<Nurse> implements NurseService {

	@Autowired
	private NurseDao nurseDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private NurseImagesDao nurseImagesDao;
	@Autowired
	private AreaDao areaDao;
	@Autowired
	private SequenceDao sequenceDao;
	@Autowired
	private JobtitleTypeDao jobtitleTypeDao;
	@Autowired
	private NurseJobtitleDao nurseJobtitleDao;
	@Override
	protected BaseDao<Nurse> getDao() {
		return nurseDao;
	}

	@Override
	public Page<Nurse> getNurseDetail(Nurse nurse) {
		Page<Nurse> nurseList = (Page<Nurse>) nurseDao.getNurseDetail(nurse);
		return nurseList;
	}

	@Override
	@Transactional
	public String insertNurse(Nurse nurse) {
		if (nurse.getUser() != null) {

			NurseImages nurseImages = null;
			// 获取护士的基本信息
			String userId = sequenceDao.getCurrentVal("user");
			User user = nurse.getUser();
			user.setId(userId);
			user.setType(0);
			user.setStatus(0);
			user.setCreateTime(new Date());
			int i = userDao.insert(user);
			if (i > 0) {
				nurse.setId(UUIDUtils.getId());
				nurse.setCreatorId(user.getId());
				nurse.setCreatorName(user.getName());
				nurse.setStatus(0);
				nurse.setCreateTime(new Date());
				int j = nurseDao.insert(nurse);
				if (j > 0) {
					nurseImages = new NurseImages();
					nurseImages.setCreatorId(user.getId());
					nurseImages.setCreatorName(user.getName());
					nurseImages.setSourceId(nurse.getUser().getId());
					nurseImages.setCreateTime(new Date());
					nurseImages.setStatus(0);
					// 头像
					nurseImages.setType(1);
					nurseImages.setId(UUIDUtils.getId());
					nurseImages.setUrl(nurse.getHead_portrait());
					nurseImagesDao.insert(nurseImages);
					// 护士资格证正面
					nurseImages.setId(UUIDUtils.getId());
					nurseImages.setType(2);
					nurseImages.setUrl(nurse.getAptitude_positive());
					nurseImagesDao.insert(nurseImages);
					// 护士资格证反面
					nurseImages.setId(UUIDUtils.getId());
					nurseImages.setType(3);
					nurseImages.setUrl(nurse.getAptitude_negative());
					nurseImagesDao.insert(nurseImages);
					// 身份证正面
					nurseImages.setId(UUIDUtils.getId());
					nurseImages.setType(4);
					nurseImages.setUrl(nurse.getId_positive());
					nurseImagesDao.insert(nurseImages);
					// 身份证反面
					nurseImages.setId(UUIDUtils.getId());
					nurseImages.setType(5);
					nurseImages.setUrl(nurse.getId_negative());
					nurseImagesDao.insert(nurseImages);
					// 插入服务区域
					Area area = null;
					String areas[] = nurse.getAreas().split(",");
					for (int k = 0; k < areas.length; k++) {
						area = new Area();
						area.setId(UUIDUtils.getId());
						area.setCreatorId(user.getId());
						area.setCreatorName(user.getName());
						area.setCreateTime(new Date());
						area.setType(1);
						area.setSourceId(nurse.getUser().getId());
						area.setLocation(areas[k]);
						area.setStatus(0);
						areaDao.insert(area);
					}
				}
			}
			return nurse.getId();
		}
		return "";
	}

	@Override
	@Transactional
	public String updateNurse(Nurse nurse,List<JobtitleType> jobtitleType) {
		if (nurse.getUser() != null) {
			NurseImages nurseImages = null;
			// 获取护士的基本信息
			User user = nurse.getUser();
			user.setType(0);
			int i = userDao.update(user);
			if (i > 0) {
				int j = nurseDao.update(nurse);
				if (j > 0) {
					nurseImages = new NurseImages();
					nurseImages.setSourceId(nurse.getUser().getId());
					// 头像
					nurseImages.setType(1);
					nurseImages = nurseImagesDao.load(nurseImages);
					if (nurseImages != null) {
						nurseImages.setUrl(nurse.getHead_portrait());
						j = nurseImagesDao.update(nurseImages);

					} else {
						nurseImages = new NurseImages();
						nurseImages.setSourceId(nurse.getUser().getId());
						// 头像
						nurseImages.setType(1);
						nurseImages.setUrl(nurse.getHead_portrait());
						nurseImages.setCreatorId(user.getId());
						nurseImages.setCreatorName(user.getName());
						nurseImages.setCreateTime(new Date());
						nurseImages.setStatus(0);
						nurseImages.setId(UUIDUtils.getId());
						j = nurseImagesDao.insert(nurseImages);
					}
					// 护士资格证正面
					nurseImages = new NurseImages();
					nurseImages.setSourceId(nurse.getUser().getId());
					nurseImages.setType(2);
					nurseImages = nurseImagesDao.load(nurseImages);
					if (nurseImages != null) {
						nurseImages.setUrl(nurse.getAptitude_positive());
						j = nurseImagesDao.update(nurseImages);
					} else {
						nurseImages = new NurseImages();
						nurseImages.setSourceId(nurse.getUser().getId());
						// 护士资格证正面
						nurseImages.setType(2);
						nurseImages.setUrl(nurse.getAptitude_positive());
						nurseImages.setCreatorId(user.getId());
						nurseImages.setCreatorName(user.getName());
						nurseImages.setCreateTime(new Date());
						nurseImages.setStatus(0);
						nurseImages.setId(UUIDUtils.getId());
						j = nurseImagesDao.insert(nurseImages);
					}
					// 护士资格证反面
					nurseImages = new NurseImages();
					nurseImages.setSourceId(nurse.getUser().getId());
					nurseImages.setType(3);
					nurseImages = nurseImagesDao.load(nurseImages);
					if (nurseImages != null) {
						nurseImages.setUrl(nurse.getAptitude_negative());
						j = nurseImagesDao.update(nurseImages);
					} else {
						nurseImages = new NurseImages();
						nurseImages.setSourceId(nurse.getUser().getId());
						// 护士资格证正面
						nurseImages.setType(3);
						nurseImages.setUrl(nurse.getAptitude_negative());
						nurseImages.setCreatorId(user.getId());
						nurseImages.setCreatorName(user.getName());
						nurseImages.setCreateTime(new Date());
						nurseImages.setStatus(0);
						nurseImages.setId(UUIDUtils.getId());
						j = nurseImagesDao.insert(nurseImages);
					}
					// 身份证正面
					nurseImages = new NurseImages();
					nurseImages.setSourceId(nurse.getUser().getId());
					nurseImages.setType(4);
					nurseImages = nurseImagesDao.load(nurseImages);
					if (nurseImages != null) {
						nurseImages.setUrl(nurse.getId_positive());
						j = nurseImagesDao.update(nurseImages);
					} else {
						nurseImages = new NurseImages();
						nurseImages.setSourceId(nurse.getUser().getId());
						// 护士资格证正面
						nurseImages.setType(4);
						nurseImages.setUrl(nurse.getId_positive());
						nurseImages.setCreatorId(user.getId());
						nurseImages.setCreatorName(user.getName());
						nurseImages.setCreateTime(new Date());
						nurseImages.setStatus(0);
						nurseImages.setId(UUIDUtils.getId());
						j = nurseImagesDao.insert(nurseImages);
					}
					// 身份证反面
					nurseImages = new NurseImages();
					nurseImages.setSourceId(nurse.getUser().getId());
					nurseImages.setType(5);
					nurseImages = nurseImagesDao.load(nurseImages);
					if (nurseImages != null) {
						nurseImages.setUrl(nurse.getId_negative());
						j = nurseImagesDao.update(nurseImages);
					} else {
						nurseImages = new NurseImages();
						nurseImages.setSourceId(nurse.getUser().getId());
						// 护士资格证正面
						nurseImages.setType(4);
						nurseImages.setUrl(nurse.getId_negative());
						nurseImages.setCreatorId(user.getId());
						nurseImages.setCreatorName(user.getName());
						nurseImages.setCreateTime(new Date());
						nurseImages.setStatus(0);
						nurseImages.setId(UUIDUtils.getId());
						j = nurseImagesDao.insert(nurseImages);
					}
					// 先删除原有的服务区域信息
					Area area = null;
					area = new Area();
					area.setSourceId(nurse.getUser().getId());
					List<Area> list = areaDao.list(area);
					for (Area area2 : list) {
						areaDao.deleteById(area2.getId());
					}
					// 插入服务区域
					String areas[] = nurse.getAreas().split(",");
					for (int k = 0; k < areas.length; k++) {
						area = new Area();
						area.setId(UUIDUtils.getId());
						area.setCreatorId(user.getId());
						area.setCreatorName(user.getName());
						area.setCreateTime(new Date());
						area.setType(1);
						area.setSourceId(nurse.getUser().getId());
						area.setLocation(areas[k]);
						area.setStatus(0);
						areaDao.insert(area);
					}
				}
			}
			return nurse.getId();
		}
		return "";
	}

	@Override
	public List<Map<String, Object>> queryOrder(Nurse nurse) {
		List<Map<String,Object>> list = nurseDao.queryNurseOrder(nurse);
		return list;
	}

	@Override
	public List<Map<String, Object>> queryIncome(Nurse nurse) {
		List<Map<String, Object>> list = nurseDao.queryNurseIncome(nurse);
		return list;
	}

	@Override
	public List<Nurse> getSomeNurse(Nurse nurse) {
		// TODO Auto-generated method stub
		List<Nurse> nurseList = (List<Nurse>) nurseDao.getSomeNurse(nurse);
		return nurseList;
	}

	/**
	 * 获取护士的职称
	 * @param nurse
	 * @return
	 */
	@Override
	public List<JobtitleType> getNurseJobtitle(Nurse nurse) {
		//获取所有的职称
		List<JobtitleType> result = jobtitleTypeDao.getJobtitleDetail(null);
		//获取护士的职称
		NurseJobtitle query = new NurseJobtitle();
		query.setCreatorId(nurse.getId());
		List<NurseJobtitle> nurseList = nurseJobtitleDao.list(query);
		for (JobtitleType jobtitleType : result) {
			for (Jobtitle jobtitle : jobtitleType.getJobtitle()) {
				for (NurseJobtitle nurseJobtitle : nurseList) {
					if(jobtitle.getId().equals(nurseJobtitle.getJobtitleId())){
						jobtitle.setChecked(true);
					}
				}
			}
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getBasicInfo(Map<String, Object> map) {
		
		return nurseDao.getBasicInfo(map);
	}
	
}