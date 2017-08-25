package com.jinpaihushi.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Common {
	//定义每页条目
	public static final int PAGE = 15;//每页 条数
	//定义每页条目
	public static final int PAGE_5 = 5;//每页 条数
	//定义金牌护士导航栏分页页码
	public static final int GN_PAGE = 20;
	//定义金牌护士数量
	public static final int GN_NUMBER = 50;//测试方便使用4,后期改成50
	//VIP-获得积分倍数
	public static final int VIP_TIMES = 2;
	//定义默认地点id
	public static final int DEFAULT_LOCATION_ID = 100103;
	//定义默认医院id
	public static final int DEFAULT_HOSPITAL_ID = 1;
	//定义默认等级id
	public static final int DEFAULT_GRADE_ID = 1;
	//定义默认部门id
	public static final int DEFAULT_DEPARTMENT_ID = 1;
	//定义默认职称id
	public static final int DEFAULT_JOBTITLE_ID = 1;
	//定义默认工作年限id
	public static final int DEFAULT_WORKYEARS_ID = 1;
	//定义资讯的条数
	public static final int DEFAULT_BULLETIN = 5;
	
	public static DateFormat fd =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static DecimalFormat df= new DecimalFormat("#.00");
	//判断输入String时间是否早于当前
	public static int beforeNow(String datetime){
		Date date = new Date();
		try {
			if(date.before(fd.parse(datetime))){
				return 1;//当前时间早于有效时间, 有效
			}else{
				return 0;//过期 无效
			}
		} catch (ParseException e) {
			return -1;//失败
		}
				
	}
	
	//密码加密方法
	public static String Encrypt(String clear){
		String cipher = "";
		try {
			cipher = MD5.md5crypt(MD5.md5crypt(clear));
		} catch (Exception e) {
			return null;
		}
		return cipher;
	}
	
	//登录认证
	public static boolean CheckPerson(String phone, String personPwd, String token){
		//生成token
		String check = "";
		check = Common.getToken(phone, personPwd);
		
		//判断
		if(check.equals(token)){
			return true;
		}
		
		return false;
	}
	
	//token生成方法
	public static String getToken(String username, String password){
		//生成方法
		String token = "";
		token = username + password;
		//返回值
		return token;
	}
	
	//生成orderNumber
	public static String getOrderNumber(){
		//生成方法
		 int max=99999;
		 int min=10000;
		 Random random = new Random();
		 int s = random.nextInt(max)%(max-min+1) + min;
		Date date=new Date();
		DateFormat df=new SimpleDateFormat("yyyyMMdd");
		String time=df.format(date);
		
		//int i = (int)(Math.random()*100000+100000);
		//String d = String.valueOf(i);
		//String t = String.valueOf(System.currentTimeMillis());
		//int d = Integer.valueOf(t);
		String no = "JP"+time+s;
		//返回值
		return no;
	}

	//生成会话No
	public static String getMessageNo(){
		//生成方法
		 int max=999;
		 int min=100;
		 Random random = new Random();
		 int s = random.nextInt(max)%(max-min+1) + min;
		Date date=new Date();
		DateFormat df=new SimpleDateFormat("yyyyMMddhhmmss");
		String time=df.format(date);
		
		//int i = (int)(Math.random()*100000+100000);
		//String d = String.valueOf(i);
		//String t = String.valueOf(System.currentTimeMillis());
		//int d = Integer.valueOf(t);
		String no = time+s;
		//返回值
		return no;
	}
	//生成文件名
		public static String getMessageFileName(){
			//生成方法
			 int max=999999999;
			 int min=100000000;
			 Random random = new Random();
			 int s = random.nextInt(max)%(max-min+1) + min;
			Date date=new Date();
			DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss_");
			String time=df.format(date);
			
			//int i = (int)(Math.random()*100000+100000);
			//String d = String.valueOf(i);
			//String t = String.valueOf(System.currentTimeMillis());
			//int d = Integer.valueOf(t);
			String no = time+s;
			//返回值
			return no;
		}
	/**
	 * 优惠券加天数
	 * @param d  yyyy-MM-dd HH:mm:ss.fff
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	
	public static Date addDate(Date d,long day) throws ParseException {
		  long time = d.getTime(); 
		  day = day*24*60*60*1000; 
		  time+=day; 
		  return new Date(time);
		  }
	
}
