package com.jinpaihushi.jphs.commoms.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jinpaihushi.panel.upyun.Upyun;

@Controller
@RequestMapping(name = "文件上传", path = "/upload")
public class FileUploadController {
	private static final String URL = "https://jinpai.b0.upaiyun.com";
	
	/**
	 * 这里这里用的是MultipartFile[] myfiles参数,所以前台就要用
	 * <input type="file" name="myfiles"/> 上传文件完毕后返回给前台[0`filepath],0表示上传成功(
	 * 后跟上传后的文件路径),1表示失败(后跟失败描述)
	 */
	@RequestMapping(value = "/index.jhtml")
	@ResponseBody
	public JSONObject upload(@RequestParam("uname") String uname,@RequestParam("type") String type, @RequestParam MultipartFile[] myfiles,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject message =new JSONObject();
		// 可以在上传文件的同时接收其它参数
		System.out.println("收到用户[" + uname + "]的文件上传请求");
		// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
		// 这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建
		String realPath = "E:/Desktop/update";
		// 设置响应给前台内容的数据格式
		response.setContentType("text/plain; charset=UTF-8");
		// 设置响应给前台内容的PrintWriter对象
//		PrintWriter out = response.getWriter();
		// 上传文件的原名(即上传前的文件名字)
		String originalFilename = null;
		// 如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解
		// 如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解
		// 上传多个文件时,前台表单中的所有<input
		// type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件
		
		//type=1加水印
		int type_s = 0;
		try {
			if(type == null || type.equals("")){
				type_s = 0;
			}else{
				type_s = Integer.parseInt(type);
			}
		} catch (Exception e1) {
			type_s = 0;
		}

		String sb_img = "";
		for (MultipartFile myfile : myfiles) {
			if (myfile.isEmpty()) {
				message.put("msg", "上传失败");
				message.put("resultcode", "0");
				return message;
			} else {
				originalFilename = myfile.getOriginalFilename();
				System.out.println("文件原名: " + originalFilename);
				System.out.println("文件名称: " + myfile.getName());
				System.out.println("文件长度: " + myfile.getSize());
				System.out.println("文件类型: " + myfile.getContentType());
				System.out.println("========================================");
				try {
					// 这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
					// 此处也可以使用Spring提供的MultipartFile.transferTo(File
					// dest)方法实现文件的上传
					FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, originalFilename));
					
					File fl=new File(realPath+"/"+ originalFilename);
					String SAVE_KEY = "/jinpaihushi/" + getDateFormat() + ".png";
					boolean result = true;
					// 加水印
					if(type_s == 1){
						result = Upyun.putUpyunWatermark(SAVE_KEY, fl);
					}else{
						// 不加水印
						result = Upyun.putUpyunNotWatermark(SAVE_KEY, fl);
					}
					
			        if(result){
			        	if(sb_img.length()>2)
			        		sb_img+= ","+ URL+SAVE_KEY;
			        	else
			        		sb_img+= URL+SAVE_KEY;
			        }else{
			        	if(sb_img.length()>2)
			        	sb_img+= ",";
			        }
					
				} catch (IOException e) {
					System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");
					message.put("msg", "上传失败");
					message.put("resultcode", "0");
					
					return message;
				}
			}
		}
		if(sb_img.length()>2){
			message.put("msg", sb_img);
			message.put("resultcode", "1");
			return message;
		}else{
			message.put("msg", "上传失败");
			message.put("resultcode", "0");
			return message;
		}
	}
	
	public static void main(String[] args) {
		String [] str = new String[30];
		str[0] = "23wer";
		str[1] = "asd";
		str[2] = "asd";
		
		System.out.println(str.length);
	}
	
	public static String getDateFormat(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Random random = new Random();  
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数  
        return "JP"+df.format(new Date())+"-"+rannum;       
    }  
}