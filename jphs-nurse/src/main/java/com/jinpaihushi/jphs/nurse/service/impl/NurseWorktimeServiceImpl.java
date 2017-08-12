package com.jinpaihushi.jphs.nurse.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.nurse.dao.NurseWorktimeDao;
import com.jinpaihushi.jphs.nurse.model.NurseWorktime;
import com.jinpaihushi.jphs.nurse.service.NurseWorktimeService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.CycleTimeUtils;
import com.jinpaihushi.utils.DateUtils;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 16:32:52
 * @version 1.0
 */
@Service("nurseWorktimeService")
public class NurseWorktimeServiceImpl extends BaseServiceImpl<NurseWorktime> implements NurseWorktimeService {

	@Autowired
	private NurseWorktimeDao nurseWorktimeDao;

	@Override
	protected BaseDao<NurseWorktime> getDao() {
		return nurseWorktimeDao;
	}

	@Override
	public List<NurseWorktime> findWorkTime(String userId, Boolean isNextWeek) {
		List<NurseWorktime> result = new ArrayList<NurseWorktime>();
		NurseWorktime query = new NurseWorktime();

		query.setUserid(userId);
		query.setOrderby("NW.calendar ASC");
		if (isNextWeek) {
			PageHelper.startPage(2, 7);
		} else {
			PageHelper.startPage(1, 7);
		}
		result = nurseWorktimeDao.list(query);
		return result;
	}

	@Override
	public int updateNurseWorkTime() {
		return 0;
	}

	@Override
	@Transactional
	public int insertNurseWorkTime(String userId) {
		Date today = new Date();
		// 得到周一的日期
		String startDays = CycleTimeUtils.getWeekStartDay(today);

		Date startDay = DateUtils.parse(startDays);
		// 计算当前日期跟周一的日期相差的天数
		int w = CycleTimeUtils.daysOfTwo(startDay, today);
		// 获取下一周的最后一天的日期
		String endDays = CycleTimeUtils.getFetureDate(13 - w);
		// 根据开始日期和结束日期得到之间的所有日期字符串
		List<String> list = CycleTimeUtils.getDatesBetweenTwoDate(startDays, endDays);
		NurseWorktime nurseWorktime = null;
		int i = 0;
		for (String string : list) {
			nurseWorktime = new NurseWorktime();
			nurseWorktime.setId(UUIDUtils.getId());
			nurseWorktime.setCalendar(string);
			nurseWorktime.setWeek(CycleTimeUtils.getWeekOfDate(DateUtils.parse(string)));
			nurseWorktime.setUserid(userId);
			nurseWorktime.setCreatetime(new Date());
			i = nurseWorktimeDao.insert(nurseWorktime);
		}
		return i;
	}

	@Override
	@Transactional
	public void updateAllNurseWorkTime() {

		List<String> list = nurseWorktimeDao.getAllNurse();
		String startDay = CycleTimeUtils.getFetureDate(7);
		String endDays = CycleTimeUtils.getFetureDate(13);
		// 新的一周的时间
		List<String> timeList = CycleTimeUtils.getDatesBetweenTwoDate(startDay, endDays);
		// 删除所有护士过期的数据
		int i = nurseWorktimeDao.deleteExpired();
		// 给所有的护士插入新的一周数据
		NurseWorktime nurseWorktime = null;
		for (String string : list) {
			nurseWorktime = new NurseWorktime();
			for (String time : timeList) {
				nurseWorktime = new NurseWorktime();
				nurseWorktime.setId(UUIDUtils.getId());
				nurseWorktime.setCalendar(time);
				nurseWorktime.setWeek(CycleTimeUtils.getWeekOfDate(DateUtils.parse(time)));
				nurseWorktime.setUserid(string);
				nurseWorktime.setCreatetime(new Date());
				i = nurseWorktimeDao.insert(nurseWorktime);
			}
		}
	}

}