package com.jinpaihushi.panel.upyun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import main.java.com.UpYun;
import main.java.com.upyun.FormUploader;
import main.java.com.upyun.Params;
import main.java.com.upyun.Result;

public class Upyun {
	
	 	private static final String BUCKET_NAME = "jinpai";
//		private static final String OPERATOR_NAME = "jinpai";
//		private static final String OPERATOR_PWD = "jinpai123";
		private static final String APIKEY = "giy0MIxZ40EYXsIWh6tF2wdIqrg=";
		private static final String UPYUN_IP="https://jinpai.b0.upaiyun.com";
	    
	    public static void main(String[] args) throws IOException {
	    	// https://jinpai.b0.upaiyun.com/upload/JP20170726160830-91590.png
	    	String url=writeFile("E://Desktop/金牌护师-微信二维码/官网/7/"
	    			+ ".jpg");
//	    	String url=writeFile("E://Desktop/金牌护师-微信二维码/微信图片_20170726185845.png");
	    	// e://logo.png
	    	System.out.println("url:"+url);
	    	  
	    	
	    /*	readfile("D://WPS/img");*/
	    	/*for(int a=0;a<100;a++){
	    		contentToTxt("D://WPS/url.java",""+a);
	    	}*/
	    	
		}
	    
	    public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
            try {

                    File file = new File(filepath);
                    if (!file.isDirectory()) {
                            System.out.println("文件");
                            System.out.println("path=" + file.getPath());
                            System.out.println("absolutepath=" + file.getAbsolutePath());
                            System.out.println("name=" + file.getName());

                    } else if (file.isDirectory()) {
                            System.out.println("文件夹");
                            String[] filelist = file.list();
                            for (int i = 0; i < filelist.length; i++) {
                                    File readfile = new File(filepath + "\\" + filelist[i]);
                                    if (!readfile.isDirectory()) {
                                            System.out.println("path=" + readfile.getPath());
                                            System.out.println("absolutepath=" + readfile.getAbsolutePath());
                                            System.out.println("name=" + readfile.getName());
                                           String imgurl =  writeFile(readfile.getPath());
                                            contentToTxt("D://WPS/imgurl.java","文件名："+readfile.getName()+
                                            		"\n"+"文件路径："+readfile.getPath()+"\n"+
                                            		"upyun图片地址："+imgurl+"\n\n");
                                            
                                            
                                    } else if (readfile.isDirectory()) {
                                            readfile(filepath + "\\" + filelist[i]);
                                    }
                            }

                    }

            } catch (FileNotFoundException e) {
                    System.out.println("readfile()   Exception:" + e.getMessage());
            }
            return true;
    }
	    
	    public static void contentToTxt(String filePath, String content) {  
	        String str = new String(); //原有txt内容  
	        String s1 = new String();//内容更新  
	        try {  
	            File f = new File(filePath);  
	            if (f.exists()) {  
	                System.out.print("文件存在");  
	            } else {  
	                System.out.print("文件不存在");  
	                f.createNewFile();// 不存在则创建  
	            }  
	            BufferedReader input = new BufferedReader(new FileReader(f));  
	  
	            while ((str = input.readLine()) != null) {  
	                s1 += str + "\n";  
	            }  
	            System.out.println(s1);  
	            input.close();  
	            s1 += content;  
	  
	            BufferedWriter output = new BufferedWriter(new FileWriter(f));  
	            output.write(s1);  
	            output.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	  
	        }  
	    }  
	  
	    
	    public static String writeFile(String path)
	    {
	   	 // 创建实例
	        /*UpYun upyun = new UpYun(BUCKET_NAME, OPERATOR_NAME,OPERATOR_PWD);

	        // 可选属性1，是否开启 debug 模式，默认不开启
	        upyun.setDebug(false);
	        // 可选属性2，超时时间，默认 30s
	        upyun.setTimeout(60);*/
	        
	        //String path="D://1.png";
	        String suffixName = path.substring(path.lastIndexOf("."),path.length());//获取文件后缀名
	        System.out.println("后缀名："+suffixName);
	        String saveFileName="/upload/"+getDateFormat()+suffixName;
	        // 采用数据流模式上传文件（节省内存）,自动创建父级目录
	    	File file = new File(path);
	    	Result result = new Result();
//	    	boolean result = true;
	    	try {
//				upyun.setContentMD5(UpYun.md5(file));
				FormUploader uploader = new FormUploader(BUCKET_NAME, APIKEY, null);
		        final Map<String, Object> paramsMap = new HashMap<String, Object>();
		        paramsMap.put(Params.SAVE_KEY,saveFileName);///opacity/90
		        paramsMap.put(Params.X_GMKERL_THUMB, "/watermark/url/L3VwbG9hZC9KUDIwMTcwNzI2MTg1OTQwLTY3MjY4LnBuZw==/align/southeast/percent/20/margin/10x10");
		        result= uploader.upload(paramsMap, file);
//				result = upyun.writeFile(saveFileName, file, true);
//				System.out.println(result);
			} catch (Exception e) {
			}
	    	
	    	if(result.isSucceed())
	    	{
	    		return UPYUN_IP+saveFileName;
	    	}else {
	    		return "上传失败！";
			}
	    	
	    }
	    /**
	     * 上传图片到又拍云，不加水印
	     */
	    public static boolean putUpyunNotWatermark(String SAVE_KEY,File fl){
	    	boolean result = true;
	    	try {
				UpYun up=new UpYun("jinpai","jinpaiwechat","jinpaiwechat");
				up.setContentMD5(UpYun.md5(fl));
				Map<String, String> params=new HashMap<String,String>();
				params.put("x-gmkerl-type", "fix_both");
//				params.put("x-gmkerl-value", "60x60");
				result= up.writeFile(SAVE_KEY, fl, true,params);
			} catch (IOException e) {
				result = false;
				
			}
	    	return result;
	    }
	    
	    /**
	     * 上传图片到又拍云，加水印
	     */
	    public static boolean putUpyunWatermark(String SAVE_KEY,File fl){
	    	// 加水印
			FormUploader uploader = new FormUploader("jinpai", APIKEY, null);
	        final Map<String, Object> paramsMap = new HashMap<String, Object>();
	        paramsMap.put(Params.SAVE_KEY,SAVE_KEY);///opacity/90
	        paramsMap.put(Params.X_GMKERL_THUMB, "/watermark/url/L2ppbnBhaWh1c2hpLzE1MDExMTg4OTE1MTIuanBn/align/southeast/percent/10/margin/10x10");
	        Result result= uploader.upload(paramsMap, fl);
	    	return result.isSucceed();
	    }
	    
	    public static String getDateFormat(){
	        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	        Random random = new Random();  
	        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数  
	        return "JP"+df.format(new Date())+"-"+rannum;       
	    }  
	    
	    
}
