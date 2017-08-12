package com.jinpaihushi.jphs.user.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jinpaihushi.dao.BaseDao;
import com.jinpaihushi.jphs.user.dao.UserWorktimeDao;
import com.jinpaihushi.jphs.user.model.UserWorktime;
import com.jinpaihushi.jphs.user.service.UserWorktimeService;
import com.jinpaihushi.service.impl.BaseServiceImpl;
import com.jinpaihushi.utils.CycleTimeUtils;
import com.jinpaihushi.utils.UUIDUtils;

/**
 * 
 * @author yangsong
 * @date 2017-08-08 16:35:43
 * @version 1.0
 */
@Service("userWorktimeService")
public class UserWorktimeServiceImpl extends BaseServiceImpl<UserWorktime> implements UserWorktimeService {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	SimpleDateFormat formatDay = new SimpleDateFormat("yyyy-MM-dd ");
	@Autowired
	private UserWorktimeDao userWorktimeDao;

	@Override
	protected BaseDao<UserWorktime> getDao() {
		return userWorktimeDao;
	}

	// 天下畅通
	@Value("${url}")
	public String url;
	@Value("${userid}")
	public String userid;
	@Value("${account}")
	public String account;
	@Value("${password}")
	public String password;

	// 阿里大于
	@Value("${url_}")
	public String url_;
	@Value("${appkey_}")
	public String appkey_;
	@Value("${secret_}")
	public String secret_;
	@Value("${message}")
	private String message;
	@Value("${WorkTimeBy0}")
	private String WorkTimeBy0;
	@Value("${WorkTimeBy1}")
	private String WorkTimeBy1;
	@Value("${WorkTimeBy2}")
	private String WorkTimeBy2;

	/**
	 * 更新用户选择的上门时间
	 * 
	 * @return 1 成功 0 失败
	 */
	@Override
	public int updateUserWorkTime() {
		int i = 0;
		try {
			// 将当前日期的数据删除（如果是每天凌晨执行）
			String yesterday = CycleTimeUtils.getPastDate(1);
			i = userWorktimeDao.deleteByCalendar(yesterday);
			// 插入7天后的数据
			String newDays = CycleTimeUtils.getFetureDate(6);

			String week = CycleTimeUtils.getWeekOfDate(sdf.parse(newDays));
			UserWorktime insert = new UserWorktime();
			insert.setId(UUIDUtils.getId());
			insert.setCalendar(newDays);
			insert.setWeek(week);
			insert.setCreatetime(new Date());
			i = userWorktimeDao.insert(insert);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 
	 * @return 一周的时间集合
	 */
	@Override
	public List<UserWorktime> findWorkTime(String productId) {
		UserWorktime userWorktime = null;
		List<UserWorktime> result = new ArrayList<>();
		// 默认提前一个小时预约
		int hour = 1;
		if (productId.equals("135")) {
			hour = Integer.valueOf(WorkTimeBy0);
		}
		if (productId.equals("133")) {
			hour = Integer.valueOf(WorkTimeBy1);
		}
		if (productId.equals("137")) {
			hour = Integer.valueOf(WorkTimeBy2);
		}
		// 当前时间
		Date date = new Date();
		try {
			System.out.println("date当前时间:" + format.format(date));
			Calendar ca = Calendar.getInstance();
			ca.setTime(date);
			ca.add(Calendar.HOUR_OF_DAY, hour);
			System.out.println("date当前时间+" + hour + "小时后:" + format.format(ca.getTime()));
			userWorktime = new UserWorktime();
			userWorktime.setCalendar(sdf.format(date));
			userWorktime = userWorktimeDao.load(userWorktime);
			int hour24 = ca.get(Calendar.HOUR_OF_DAY);
			switch (hour24) {
			case 21:
				userWorktime.setH21(1);
			case 20:
				userWorktime.setH20(1);
			case 19:
				userWorktime.setH19(1);
			case 18:
				userWorktime.setH18(1);
			case 17:
				userWorktime.setH17(1);
			case 16:
				userWorktime.setH16(1);
			case 15:
				userWorktime.setH15(1);
			case 14:
				userWorktime.setH14(1);
			case 13:
				userWorktime.setH13(1);
			case 12:
				userWorktime.setH12(1);
			case 11:
				userWorktime.setH11(1);
			case 10:
				userWorktime.setH10(1);
			case 9:
				userWorktime.setH9(1);
			default:
				break;
			}
			int i = userWorktimeDao.update(userWorktime);
			if (i > 0) {
				result = userWorktimeDao.list(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}