package com.jinpaihushi.jphs.commons.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jinpaihushi.jphs.nurse.model.NurseImages;
import com.jinpaihushi.jphs.nurse.service.NurseImagesService;
import com.jinpaihushi.utils.JSONUtil;
import com.jinpaihushi.utils.UUIDUtils;

import main.java.com.upyun.FormUploader;
import main.java.com.upyun.Params;
import main.java.com.upyun.Result;

@Controller
@RequestMapping(name = "文件上传", path = "/upload")
public class FileUploadController {
    private static final String URL = "https://jinpai.b0.upaiyun.com";

    @Autowired
    private NurseImagesService nurseImagesService;

    /**
     * 这里这里用的是MultipartFile[] myfiles参数,所以前台就要用
     * <input type="file" name="myfiles"/> 上传文件完毕后返回给前台[0`filepath],0表示上传成功(
     * 后跟上传后的文件路径),1表示失败(后跟失败描述)
     */
    @RequestMapping(value = "/index.jhtml", method = RequestMethod.POST)
    @ResponseBody
    public byte[] upload(@RequestParam("uname") String uname, @RequestParam MultipartFile[] file,
            HttpServletRequest request, HttpServletResponse response, String userId) throws IOException {
        JSONObject message = new JSONObject();

        // 可以在上传文件的同时接收其它参数
        System.out.println("收到用户[" + uname + "]的文件上传请求");
        System.out.println("文件myfiles=" + file);
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
        for (MultipartFile myfile : file) {
            if (myfile.isEmpty()) {
                message.put("msg", "上传失败");
                message.put("resultcode", "0");
                return JSONUtil.toJSONResult(0, "上传失败", null);
                //				return message;
            }
            else {
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

                    //	--上传图片到又拍云	不加水印	
                    File fl = new File(realPath + "/" + originalFilename);
                    /*UpYun up=new UpYun("jinpai","jinpaiwechat","jinpaiwechat");
                    String SAVE_KEY = "/jinpaihushi/" + getDateFormat() + ".jpg";
                    up.setContentMD5(UpYun.md5(fl));
                    Map<String, String> params=new HashMap<String,String>();
                    params.put("x-gmkerl-type", "fix_both");
                    params.put("x-gmkerl-value", "60x60");
                    boolean result= up.writeFile(SAVE_KEY, fl, true,params);*/

                    // 加水印
                    String SAVE_KEY = "/jinpaihushi/" + getDateFormat() + ".png";
                    FormUploader uploader = new FormUploader("jinpai", "giy0MIxZ40EYXsIWh6tF2wdIqrg=", null);
                    final Map<String, Object> paramsMap = new HashMap<String, Object>();
                    paramsMap.put(Params.SAVE_KEY, SAVE_KEY);///opacity/90
                    paramsMap.put(Params.X_GMKERL_THUMB,
                            "/watermark/url/L2ppbnBhaWh1c2hpLzE1MDExMTg4OTE1MTIuanBn/align/southeast/percent/10/margin/10x10");
                    Result result = uploader.upload(paramsMap, fl);

                    if (result.isSucceed()) {
                        message.put("msg", URL + SAVE_KEY);
                        message.put("resultcode", "1");
                        //return JSONUtil.toJSONResult(0, "上传成功", URL+SAVE_KEY);
                        if (StringUtils.isNotEmpty(userId)) {
                            NurseImages nurseImage = new NurseImages();
                            nurseImage.setSourceId(userId);
                            nurseImage.setType(1);
                            nurseImage = nurseImagesService.load(nurseImage);
                            if (nurseImage != null) {
                                nurseImage.setUrl(URL + SAVE_KEY);
                                boolean b = nurseImagesService.update(nurseImage);
                                if (b) {
                                    return JSONUtil.toJSONResult(1, "上传成功", URL + SAVE_KEY);
                                }
                            }
                            else {
                                nurseImage = new NurseImages();
                                nurseImage.setUrl(URL + SAVE_KEY);
                                nurseImage.setSourceId(userId);
                                nurseImage.setCreatorId(userId);
                                nurseImage.setId(UUIDUtils.getId());
                                nurseImage.setCreateTime(new Date());
                                nurseImage.setStatus(1);
                                nurseImage.setType(1);
                                String insert = nurseImagesService.insert(nurseImage);
                                if (insert.length() > 0) {
                                    return JSONUtil.toJSONResult(1, "上传成功", URL + SAVE_KEY);
                                }
                            }
                        }
                        return JSONUtil.toJSONResult(1, "上传成功", URL + SAVE_KEY);

                    }
                    else {
                        message.put("msg", "上传失败");
                        message.put("resultcode", "0");
                    }

                    //					return message;
                    return JSONUtil.toJSONResult(0, "上传失败", null);
                    /*return new ModelAndView(customJsonView);*/

                }
                catch (IOException e) {
                    System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");
                    message.put("msg", "上传失败");
                    message.put("resultcode", "0");

                    //					return message;
                    return JSONUtil.toJSONResult(0, "上传失败", null);
                }
            }
        }
        // 此时在Windows下输出的是[D:\Develop\apache-tomcat-6.0.36\webapps\AjaxFileUpload\\upload\愤怒的小鸟.jpg]
        // System.out.println(realPath + "\\" + originalFilename);
        // 此时在Windows下输出的是[/AjaxFileUpload/upload/愤怒的小鸟.jpg]
        // System.out.println(request.getContextPath() + "/upload/" +
        // originalFilename);
        // 不推荐返回[realPath + "\\" + originalFilename]的值
        // 因为在Windows下<img src="file:///D:/aa.jpg">能被firefox显示,而<img
        // src="D:/aa.jpg">firefox是不认的
        System.out.println("0`" + request.getContextPath() + "/upload/" + originalFilename);
        message.put("msg", "上传失败");
        message.put("resultcode", "0");
        return JSONUtil.toJSONResult(0, "上传失败", null);
        //		return message;
    }

    public static String getDateFormat() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数  
        return "JP" + df.format(new Date()) + "-" + rannum;
    }
}