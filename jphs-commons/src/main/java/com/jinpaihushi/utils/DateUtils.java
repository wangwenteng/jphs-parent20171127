package com.jinpaihushi.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by IntelliJ IDEA. User: garmbrood Time: 2009-4-7 16:32:56 Company:
 * 天极传媒集团
 * Descripion:日期工具类,继承自apache的DateUtils类，继承的方法参见org.apache.commons.lang.time
 * .DateUtils的文档 内置了常见的日期格式，格式化时自动适配相应类型
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	protected static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

	public static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";

	public static final String FORMAT_DATE = "yyyy-MM-dd";

	public static final String FORMAT_DATE_STR = "yyyyMMdd";

	public static final String FORMAT_TIME = "HH:mm:ss";

	public static final String FORMAT_SHORT_DATE_TIME = "MM-dd HH:mm";

	public static final String FORMAT_DATE_TIME = FORMAT_DEFAULT;

	public static final String FORMAT_NO_SECOND = "yyyy-MM-dd HH:mm";

	public static final String FORMAT_JAPAN = "MM.dd(EEE) HH";

	public static final String FORMAT_CHINESE_NO_SECOND = "yyyy年MM月dd日 HH:mm";

	public static final String FORMAT_CHINESE_NO_SECOND_1 = "yyyy年MM月dd日HH:mm";

	public static final String FORMAT_CHINESE = "yyyy年MM月dd日 HH点mm分";

	public static final int TYPE_HTML_SPACE = 2;

	public static final int TYPE_DECREASE_SYMBOL = 3;

	public static final int TYPE_SPACE = 4;

	public static final int TYPE_NULL = 5;

	public static final String NORMAL_START_WORK_TIME = " 8:00:00";// 规定的上班时间

	public static final String NORMAL_END_WORK_TIME1 = " 17:30:00";// 夏季下班5点半（5.4-10.7）；

	public static final String NORMAL_END_WORK_TIME2 = " 17:00:00";// 冬季下班5点（10.8-5.3）

	public static final String SUMMER_START_TIME = "-05-04 00:00:00";// 夏季开始日期

	public static final String WINTER_START_TIME = "-10-08 00:00:00";// 冬季开始日期
	
	/**
	 * >= 前一个小时 HH:00:00 毫秒数
	 * 
	 * @return
	 */
	public static long beforeOneHourTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		// System.out.println("一个小时前的时间：" + df.format(cal.getTime()));
		// System.out.println("当前的时间：" + df.format(new Date()));
		Date date = parse(df.format(cal.getTime()), "yyyy-MM-dd HH:mm:ss");
		return date.getTime();
	}

	/**
	 * < 当前这个小时 HH:00:00 毫秒数
	 * 
	 * @return
	 */
	public static long currentHourTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
		Date date = parse(df.format(new Date()), "yyyy-MM-dd HH:mm:ss");
		return date.getTime();
	}

	/**
	 * 上一个小时的时间
	 * 
	 * @return
	 */
	public static Date beforeOneHourDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - 1);
		Date date = cal.getTime();
		return date;
	}

	// 得到昨天的日期
	public static Date getYesterdayDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(5, -1);
		return cal.getTime();
	}

	/**
	 * 获取某月最大天数
	 * @param yearMonth
	 * @return
	 */
	public static int calDayByYearAndMonth(String yearMonth) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMM");
		Calendar rightNow = Calendar.getInstance();
		try {
			rightNow.setTime(simpleDate.parse(yearMonth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);// 根据年月 获取月份天数
	}

	public static String dateStrToDateStr(String str) {
		Date date = parse(str, FORMAT_DATE_STR);

		return format(date, FORMAT_DATE);
	}

	public static String dateStrToWeekDay(String str) {
		Date date = parse(str, FORMAT_DATE_STR);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String weekDay = transformNumber(cal.get(Calendar.DAY_OF_WEEK));
		return weekDay;
	}

	public static String transformNumber(int i) {
		switch (i) {
		case 1:
			return "日";
		case 2:
			return "一";
		case 3:
			return "二";
		case 4:
			return "三";
		case 5:
			return "四";
		case 6:
			return "五";
		case 7:
			return "六";
		default:
			return "传入数字有误";
		}
	}

	// 得到昨日的 规定上班时间
	public static long getYesterdayStartTime() {

		// 一年 上班时间都为 8:00
		return yesterdayDateTime(NORMAL_START_WORK_TIME);
	}

	// 得到昨日的 规定下班时间
	public static long getYesterdayEndTime() {
		if (yesterdayDateTime(NORMAL_START_WORK_TIME) > thisYearSeasonStartTime(SUMMER_START_TIME)
				&& yesterdayDateTime(NORMAL_START_WORK_TIME) < thisYearSeasonStartTime(WINTER_START_TIME)) {
			// 条件成立，为夏季，下班时间5:30
			return yesterdayDateTime(NORMAL_END_WORK_TIME1);
		} else {
			// 为冬季，下班时间 5:00
			return yesterdayDateTime(NORMAL_END_WORK_TIME2);
		}

	}

	// 获取昨日的各个时间段的 时间
	public static long yesterdayDateTime(String timeScale) {
		String yesterdayDateStr = getYesterdayDateStr("yyyy-MM-dd") + timeScale;
		Date yesterdayDate = parse(yesterdayDateStr, FORMAT_DEFAULT);
		// System.out.println(yesterdayDateStr+"字符串|||"+yesterdayDate);
		return yesterdayDate.getTime();
	}

	// 昨日所属年的季节开始时间
	public static long thisYearSeasonStartTime(String seasonDate) {
		String startDateStr = getYesterdayDateStr("yyyy") + seasonDate;
		Date startDate = parse(startDateStr, FORMAT_DEFAULT);
		// System.out.println(startDateStr+"字符串|||"+startDate);
		return startDate.getTime();
	}

	// 判断日期是否是昨日
	public static boolean isYesterday(Date date) {
		String str1 = getYesterdayDateStr("yyyy-MM-dd");
		String str2 = format(date, "yyyy-MM-dd");
		if (str1.equals(str2)) {
			return true;
		} else {
			return false;
		}

	}

	// 得到昨天的格式化日期
	public static String getYesterdayDateStr(String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(5, -1);
		String str = new SimpleDateFormat(format).format(cal.getTime());

		return str;
	}

	// 根据输入的月份，得到当年的月份时间
	public static Date coverDate(int month) {

		String str = new SimpleDateFormat("yyyy").format(new Date()) + ""
				+ month;

		return parse(str, "yyyyMM");
	}

	// 查询月的第一天日期号>=
//	public static String getMonthFirstDay(Date date) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		// 暂时使用 上个月 取数据
//		// cal.add(2, -1);
//		int firstDay = cal.getActualMinimum(5);
//		Date firstDate = cal.getTime();
//		firstDate.setDate(firstDay);
//		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_STR);
//		String str = sdf.format(firstDate);
//		return str;
//	}

	// 查询月的最后一天日期号>=
//	public static String getMonthLastDay(Date date) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		int firstDay = cal.getActualMaximum(5);
//		Date firstDate = cal.getTime();
//		firstDate.setDate(firstDay);
//		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_STR);
//		String str = sdf.format(firstDate);
//		return str;
//	}

	// 查询月下一个月的第一天<
//	public static String getNextMonthFirstDay(Date date) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		cal.add(2, 1);
//		int firstDay = cal.getActualMinimum(5);
//		Date firstDate = cal.getTime();
//		firstDate.setDate(firstDay);
//		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_STR);
//		String str = sdf.format(firstDate);
//		return str;
//	}

	public static Map<String, SimpleDateFormat> getFormaters() {
		return formaters;
	}

	private static Map<String, SimpleDateFormat> formaters = new HashMap<String, SimpleDateFormat>();

	static {
		SimpleDateFormat defaultFormater = new SimpleDateFormat(FORMAT_DEFAULT,
				Locale.CHINA);
		formaters.put(FORMAT_DEFAULT, defaultFormater);
		formaters.put(FORMAT_DATE, new SimpleDateFormat(FORMAT_DATE,
				Locale.CHINA));
		formaters.put(FORMAT_TIME, new SimpleDateFormat(FORMAT_TIME,
				Locale.CHINA));
		formaters.put(FORMAT_SHORT_DATE_TIME, new SimpleDateFormat(
				FORMAT_SHORT_DATE_TIME, Locale.CHINA));
		formaters.put(FORMAT_CHINESE_NO_SECOND, new SimpleDateFormat(
				FORMAT_CHINESE_NO_SECOND, Locale.CHINA));
		formaters.put(FORMAT_CHINESE, new SimpleDateFormat(FORMAT_CHINESE,
				Locale.CHINA));
		formaters.put(FORMAT_DATE_TIME, defaultFormater);
		formaters.put(FORMAT_NO_SECOND, new SimpleDateFormat(FORMAT_NO_SECOND,
				Locale.CHINA));
		formaters.put(FORMAT_JAPAN, new SimpleDateFormat(FORMAT_JAPAN,
				Locale.JAPAN));
		formaters.put(FORMAT_CHINESE_NO_SECOND_1, new SimpleDateFormat(
				FORMAT_CHINESE_NO_SECOND_1, Locale.CHINA));

	}

	/**
	 * 使用给定的 pattern 对日期进格式化为字符串
	 * 
	 * @param date
	 *            待格式化的日期
	 * @param pattern
	 *            格式字符串
	 * @return 格式化后的日期字符串
	 */
	public static String format(Date date, String pattern) {
		SimpleDateFormat dateFormat;
		if (formaters.containsKey(pattern)) {
			dateFormat = formaters.get(pattern);
		} else {
			dateFormat = new SimpleDateFormat(pattern);
		}
		return dateFormat.format(date);
	}

	/**
	 * 以默认日期格式(yyyy-MM-dd HH:mm:ss)对日期进行格式化
	 * 
	 * @param date
	 *            待格式化的日期
	 * @return 格式化后的日期字符串
	 */
	public static String format(Date date) {
		return formaters.get(FORMAT_DEFAULT).format(date);
	}

	/**
	 * 格式化日期，
	 * --------------------------------------------------------------------
	 * ------<br>
	 * 创建者：杨思勇<br>
	 * 创建日期：2002-1-16<br>
	 * <br>
	 * 修改者：<br>
	 * 修改日期：<br>
	 * 修改原因：<br>
	 * ------------------------------------------------------------------------
	 * --
	 * 
	 * @param date
	 *            要格式化的日期对象
	 * @param format
	 *            格式
	 * @param type
	 *            如果日期为空，定义返回的类型
	 * @return 返回值，如果date为空，则type定义返回类型，如果格式化失败。则返回空串
	 */
	public static String format(Date date, String format, int type) {
		if (date != null) {
			// ---------------------------------
			// 日期不为空时才格式
			// ---------------------------------
			try {
				// ---------------------------------
				// 调用SimpleDateFormat来格式化
				// ---------------------------------
				return new SimpleDateFormat(format).format(date);
			} catch (Exception e) {
				// ---------------------------------
				// 格式化失败后，返回一个空串
				// ---------------------------------
				return "";
			}
		} else {
			// ---------------------------------
			// 如果传入日期为空，则根据类型返回结果
			// ---------------------------------
			switch (type) {
			case TYPE_HTML_SPACE: // '\002'
				return "&nbsp;";

			case TYPE_DECREASE_SYMBOL: // '\003'
				return "-";

			case TYPE_SPACE: // '\004'
				return " ";

			case TYPE_NULL:
				return null;

			default:
				// ---------------------------------
				// 默认为空串
				// ---------------------------------
				return "";
			}
		}
	}

	/**
	 * 将给定字符串解析为对应格式的日期,循环尝试使用预定义的日期格式进行解析
	 * 
	 * @param str
	 *            待解析的日期字符串
	 * @return 解析成功的日期，解析失败返回null
	 */
	public static Date parse(String str) {
		Date date = null;
		for (String _pattern : formaters.keySet()) {
			if (_pattern.getBytes().length == str.getBytes().length) {
				try {
					date = formaters.get(_pattern).parse(str);
					// 格式化成功则退出
					break;
				} catch (ParseException e) {
					// 格式化失败，继续尝试下一个
					logger.debug("尝试将日期:" + str + "以“" + _pattern
							+ "”格式化--失败=.=!");
				}
			} else if (_pattern.equals(FORMAT_JAPAN)) {
				try {
					date = formaters.get(_pattern).parse(str);
					// 格式化成功则退出
					break;
				} catch (ParseException e) {
				}
			}
		}
		return date;
	}

	public static Date parse(String str, String pattern) {
		SimpleDateFormat dateFormat;
		if (formaters.containsKey(pattern)) {
			dateFormat = formaters.get(pattern);
		} else {
			dateFormat = new SimpleDateFormat(pattern);
		}
		Date date = null;
		try {
			date = dateFormat.parse(str);
		} catch (ParseException e) {
			// 是格式失败
			logger.debug("尝试将日期:" + str + "以“" + pattern + "”格式化--失败=.=!");
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * date2 是否在date1之后
	 * 
	 * @param date1
	 *            参照日期
	 * @param date2
	 *            比较日期
	 * @return true:是 false:否
	 */
	public static boolean isAfter(Date date1, Date date2) {
		Calendar calendar2 = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();
		calendar2.setTime(date2);
		calendar1.setTime(date1);
		return calendar2.after(calendar1);
	}

	/**
	 * 当前时间+指定的分钟以后的时间
	 */
	public static Date getTimeByMinis(int minis) {
		Calendar calendar = Calendar.getInstance();
		int min = calendar.get(Calendar.MINUTE);
		calendar.set(Calendar.MINUTE, min + minis);
		Date cc = calendar.getTime();
		return cc;
	}

	/**
	 * 
	 * 获得当前时间的毫秒数
	 * 
	 * @return
	 */
	public static Long getMillisecond() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DEFAULT);
		Date beginTime = null;
		try {
			beginTime = sdf.parse(DateUtils.format(new Date(), FORMAT_DEFAULT));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return beginTime.getTime();
	}

	/**
	 * 获得当前时间的毫秒数
	 * 
	 * @param pattern
	 * @return
	 */
	public static Long getMillisecond(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date beginTime = null;
		try {
			beginTime = sdf.parse(DateUtils.format(new Date(), pattern));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return beginTime.getTime();
	}

	/**
	 * 获得指定时间的毫秒数
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Long getMillisecond(String date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date beginTime = null;
		try {
			beginTime = sdf.parse(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return beginTime.getTime();
	}

	/**
	 * 
	 * @param date
	 * @param pattern
	 * @param locale
	 * @return
	 */
	public static Long getMillisecond(String date, String pattern, Locale locale) {
		SimpleDateFormat sdf = null;
		if (locale == null) {
			sdf = new SimpleDateFormat(pattern, locale);
		} else {
			sdf = new SimpleDateFormat(pattern, locale);
		}
		Date beginTime = null;
		try {
			beginTime = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return beginTime.getTime();
	}

	public static void main(String[] args) throws Exception {
//		System.out.println(getYesterdayDateStr("yyyy-MM-dd 23:00:00"));
		// beforeOneHourTime();
		// System.out.println(dateStrToWeekDay("20141030"));
		// System.out.println(getYesterdayDateStr("yyyy-MM-dd"));
		// System.out.println(coverDate(10));
		//
		// System.out.println(getMonthFirstDay(coverDate(10))+"..."+getMonthLastDay(coverDate(10))
		// +"..."+getNextMonthFirstDay(coverDate(10)));
		/*
		 * System.out.println( Integer.parseInt("20141025")
		 * >Integer.parseInt("20141026")); for(int
		 * i=Integer.parseInt(DateUtils.getMonthFirstDay(new Date()));
		 * i<=Integer.parseInt(DateUtils.getMonthLastDay(new
		 * Date()));i++){//查询月的日期
		 * 
		 * System.out.println(i);
		 * 
		 * }
		 */
		// System.out.println(getMonthFirstDayNum(new
		// Date())+",,"+getMonthLastDayNum(new Date()));
		// System.out.println(dateStrToDateStr("20141028"));
		/*
		 * System.out.println(thisYearSummerStartTime());
		 * System.out.println(thisYearSummerEndTime());
		 * System.out.println(yesterdayDateTime());
		 */
		// System.out.println(getYesterdayDateStr());
		/*
		 * String str = "11/Sep/2013:12:00:01 +0800";
		 * System.out.println(DateUtils.parse(str));
		 * System.out.println(DateUtils.format(DateUtils.parse(str)));
		 */

		// logger.debug("------------------------------------------------------");
		// String sdate1 = DateUtils.format(new Date());
		// logger.debug("sdate1 = " + sdate1);
		// String sdate2 = DateUtils.format(new Date(),DateUtils.FORMAT_DATE);
		// logger.debug("sdate2 = " + sdate2);
		// String sdate3 = DateUtils.format(new
		// Date(),DateUtils.FORMAT_NO_SECOND);
		// logger.debug("sdate3 = " + sdate3);
		// String sdate4 = DateUtils.format(new Date(),DateUtils.FORMAT_JAPAN);
		// logger.debug("sdate4 = " + sdate4);
		// logger.debug("------------------------------------------------------");
		//
		// Date date1 = DateUtils.parse(sdate1);
		// Date date2 = DateUtils.parse(sdate2);
		// Date date3 = DateUtils.parse(sdate3);
		// Date date4 = DateUtils.parse(sdate4);
		// logger.debug(DateUtils.format(date3));
		// logger.debug(DateUtils.format(date4));
		// Date date5 = DateUtils.parse("2008-05-05",DateUtils.FORMAT_DATE);
		// logger.debug(DateUtils.format(date5));
		//
		// logger.debug(DateUtils.isAfter(date5,date1));

//		System.out.println(parseDateTime("2017-2-32 11:33:30", "y-M-d H:m:s"));
		
		String string = "2016-9-4 21:59:06";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.parse(string));
		
    	long s = DateUtils.getDistanceDays(sdf.parse(string),new Date());
    	
		System.out.println("ssss....."+s);
	}

	/**
	 * localDateTime 转成 date
	 * @param dateTime
	 * @return
	 * @author huoht
	 */
	public static Date dateTimeToDate(LocalDateTime dateTime){
		return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * Date 转成 LocalDateTime
	 * @param date
	 * @return
	 * @author huoht
	 */
	public static LocalDateTime dateToDateTime(Date date){
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
	
	public static String getNowTime() {
		return DateUtils.format(new Date());
	}
	
	/**
	 * LocalDateTime转字符串
	 * @param dateTime
	 * @param pattern
	 * @return
	 * @author huoht
	 */
	public static String format(LocalDateTime dateTime,String pattern){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return dateTime.format(formatter);
	}
	
	/**
	 * 字符串装LocalDateTime
	 * @param date
	 * @param pattern
	 * @return
	 * @author huoht
	 */
	public static LocalDateTime parseDateTime(String date,String pattern){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDateTime.parse(date, formatter);
	}
	
	/** 
     * 两个时间之间相差距离多少天 
     * @param one 时间参数 1： 
     * @param two 时间参数 2： 
     * @return 相差天数 
     */  
    public static long getDistanceDays(Date str1, Date str2) throws Exception{  
        long days=0;  
        try {  
            long time1 = str1.getTime();  
            long time2 = str2.getTime();  
            long diff ;  
            if(time1<time2) {  
                diff = time2 - time1;  
            } else {  
                diff = time1 - time2;  
            }  
            days = diff / (1000 * 60 * 60 * 24);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return days;  
    }  
    
}
