package com.jinpaihushi.utils;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CycleTimeUtils {

	private static final int FIRST_DAY = Calendar.MONDAY;

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public String getMonthStart()// 获取月初日期
	{
		Date d = new Date();
		// 月初
		// System.out.println("月初" + sdf.format(getMonthStart(d)));
		return sdf.format(getMonthStart(d));
	}

	public static String getMonthStartStr(Date d)// 根据传入日期来获取一个月的开始时间
	{
		return sdf.format(getMonthStart(d));
	}

	public static String getMonthEndStr(Date d)// 根据传入时间获取一个月月末时间
	{

		return sdf.format(getMonthEnd(d));
	}

	public static List<String> getAllDaysMonthByDate(Date d)// 根据传入的日期获取所在月份所有日期
	{
		List<String> lst = new ArrayList<String>();
		Date date = getMonthStart(d);
		Date monthEnd = getMonthEnd(d);
		while (!date.after(monthEnd)) {
			// System.out.println(sdf.format(date));
			lst.add(sdf.format(date));
			date = getNext(date);
		}
		return lst;
	}

	public static Date paraseStringToDate(String timestr)// 将字符串转化为日期
	{
		Date date = null;

		Format f = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = (Date) f.parseObject(timestr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public String getMonthEnd()// 获取月末日期
	{
		Date d = new Date();
		return sdf.format(getMonthEnd(d));
	}

	public static List<String> getAllDaysMonth() {
		List<String> lst = new ArrayList<String>();
		Date d = new Date();
		Date date = getMonthStart(d);
		Date monthEnd = getMonthEnd(d);
		while (!date.after(monthEnd)) {
			lst.add(sdf.format(date));
			date = getNext(date);
		}
		return lst;
	}

	private static Date getMonthStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (1 - index));
		return calendar.getTime();
	}

	private static Date getMonthEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (-index));
		return calendar.getTime();
	}

	private static Date getNext(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * 根据日期来获取一周的第一天
	 * 
	 * @param d
	 * @return
	 */
	public static String getWeekStartDay(Date d) {// 根据日期来获取一周的第一天
		Calendar c = Calendar.getInstance();
		List<String> lst = new ArrayList<String>();
		c.setTime(d);
		setToFirstDay(c);
		for (int i = 0; i < 7; i++) {
			String day = printDay(c);
			lst.add(day);
			c.add(Calendar.DATE, 1);
		}
		return lst.get(0);
	}

	/**
	 * 两个日期相差的天数
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int daysOfTwo(Date fDate, Date oDate) {

		Calendar aCalendar = Calendar.getInstance();

		aCalendar.setTime(fDate);

		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

		aCalendar.setTime(oDate);

		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

		return day2 - day1;

	}

	public static String getWeekEndtDay(Date d) {// 根据日期来获取一周的最后一天
		Calendar c = Calendar.getInstance();
		List<String> lst = new ArrayList<String>();
		c.setTime(d);
		setToFirstDay(c);
		for (int i = 0; i < 7; i++) {
			String day = printDay(c);
			lst.add(day);
			c.add(Calendar.DATE, 1);
		}
		return lst.get(6);
	}

	public static String getNextWeekEndtDay(Date d) {// 根据日期来获取一周的最后一天
		Calendar c = Calendar.getInstance();
		List<String> lst = new ArrayList<String>();
		c.setTime(d);
		setToFirstDay(c);
		for (int i = 0; i < 14; i++) {
			String day = printDay(c);
			lst.add(day);
			c.add(Calendar.DATE, 1);
		}
		return lst.get(13);
	}

	public static List<String> getAllweekDays(Date d) {// 根据日期来获取其所在周的每一天
		Calendar c = Calendar.getInstance();
		List<String> lst = new ArrayList<String>();
		c.setTime(d);
		setToFirstDay(c);
		for (int i = 0; i < 7; i++) {
			String day = printDay(c);
			lst.add(day);
			c.add(Calendar.DATE, 1);
		}
		return lst;
	}

	public static List<String> getALlweekDays() {
		List<String> lst = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		setToFirstDay(calendar);
		for (int i = 0; i < 7; i++) {
			String day = printDay(calendar);
			lst.add(day);
			calendar.add(Calendar.DATE, 1);
		}
		return lst;
	}

	private static void setToFirstDay(Calendar calendar) {
		while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
			calendar.add(Calendar.DATE, -1);
		}
	}

	private static String printDay(Calendar calendar) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * @Title: getDatesBetweenTwoDate @Description: TODO(获取两个日期内的所有时间) @param
	 *         startTime 开始日期 @param endTime 结束日期 @return @throws Exception List
	 *         <String> @throws
	 */

	public static List<String> getDatesBetweenTwoDate(String startTime, String endTime) {
		Date beginDate =null;
		Date endDate =null;
		try {
			beginDate = sdf.parse(startTime);
			endDate = sdf.parse(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(beginDate);
		// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}
		// 把结束时间加入集合
		lDate.add(endDate);
		List<String> list = new ArrayList<>();
		for (Date date : lDate) {
			list.add(sdf.format(date));
		}
		return list;
	}

	/**
	 * 获取本周的开始时间和结束时间
	 * 
	 * @return 时间字符串 2017-06-19T2017-06-25
	 */
	public static String getWeekStartAndEnd() {
		String startDay = "";
		String endDay = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int d = 0;
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			d = -6;
		} else {
			d = 2 - cal.get(Calendar.DAY_OF_WEEK);
		}
		cal.add(Calendar.DAY_OF_WEEK, d);
		// 所在周开始日期
		startDay = sdf.format(cal.getTime());
		cal.add(Calendar.DAY_OF_WEEK, 6);
		// 所在周结束日期
		endDay = sdf.format(cal.getTime());
		return startDay + "T" + endDay;
	}

	private static List<String> getDatesBeforeToday(String endTime) throws Exception {
		Date endDate = sdf.parse(endTime);
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(endDate);
		// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(endDate);
		for (int i = 0; i < 6; i++) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
			lDate.add(cal.getTime());
		}
		List<String> list = new ArrayList<>();
		for (int i = lDate.size() - 1; i >= 0; i--) {
			list.add(sdf.format(lDate.get(i)));

		}
		return list;
	}

	private static List<String> getDatesBeforeToday(Date endTime) throws Exception {
		String endDate = sdf.format(endTime);
		return getDatesBeforeToday(endDate);
	}

	private static List<String> getDatesAfterToday(String startTime) throws Exception {
		Date endDate = sdf.parse(startTime);
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(endDate);
		// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(endDate);
		for (int i = 0; i < 6; i++) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(cal.getTime());
		}
		List<String> list = new ArrayList<>();
		for (Date date : lDate) {
			list.add(sdf.format(date));
		}
		return list;
	}

	public static List<String> getTimeList(String startTime, String endTime) throws Exception {
		List<String> list = new ArrayList<String>();
		if (startTime != null && endTime != null) {
			if (startTime.isEmpty() && endTime.isEmpty()) {
				Date date = new Date();
				list = getDatesBeforeToday(date);
			} else if (startTime.isEmpty()) {
				list = getDatesBeforeToday(endTime);
			} else if (endTime.isEmpty()) {
				list = getDatesAfterToday(startTime);
			} else {
				list = getDatesBetweenTwoDate(startTime, endTime);
			}
		}
		return list;
	}

	/**
	 * 根据日期获取 周
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(Date date) {
		String[] weekOfDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekOfDays[w];
	}

	/**
	 * 获取未来 第 past 天的日期
	 * 
	 * @param past
	 * @return
	 */
	public static String getFetureDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
		Date today = calendar.getTime();
		String result = sdf.format(today);
		return result;
	}

	/**
	 * 获取过去第几天的日期
	 * 
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		String result = sdf.format(today);
		return result;
	}

	public static void main(String[] args) {
		System.out.println(getFetureDate(7));
		System.out.println(getPastDate(1));
	}
}
