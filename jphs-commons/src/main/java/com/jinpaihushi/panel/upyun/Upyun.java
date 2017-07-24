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
import java.util.Random;

import main.java.com.UpYun;

public class Upyun {
	
	 	private static final String BUCKET_NAME = "jinpai";
		private static final String OPERATOR_NAME = "jinpai";
		private static final String OPERATOR_PWD = "jinpai123";
		private static final String APIKEY = "giy0MIxZ40EYXsIWh6tF2wdIqrg=";
		private static final String UPYUN_IP="https://jinpai.b0.upaiyun.com";
	    
	    public static void main(String[] args) throws IOException {
	    	
	    	/*String url=writeFile("e://logo.png");
	    	System.out.println("url:"+url);*/
	    	
	    	readfile("D://WPS/img");
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
	        UpYun upyun = new UpYun(BUCKET_NAME, OPERATOR_NAME,OPERATOR_PWD);

	        // 可选属性1，是否开启 debug 模式，默认不开启
	        upyun.setDebug(false);
	        // 可选属性2，超时时间，默认 30s
	        upyun.setTimeout(60);
	        
	        //String path="D://1.png";
	        String suffixName = path.substring(path.lastIndexOf("."),path.length());//获取文件后缀名
	        System.out.println("后缀名："+suffixName);
	        String saveFileName="/upload/"+getDateFormat()+suffixName;
	        // 采用数据流模式上传文件（节省内存）,自动创建父级目录
	    	File file = new File(path);
	    	boolean result;
	    	try {
				upyun.setContentMD5(UpYun.md5(file));
				result = upyun.writeFile(saveFileName, file, true);
				System.out.println(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	if(result=true)
	    	{
	    		return UPYUN_IP+saveFileName;
	    	}else {
	    		return "上传失败！";
			}
	    	
	    }
	    
	    public static String getDateFormat(){
	        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	        Random random = new Random();  
	        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数  
	        return "JP"+df.format(new Date())+"-"+rannum;       
	    }  
	    

}
