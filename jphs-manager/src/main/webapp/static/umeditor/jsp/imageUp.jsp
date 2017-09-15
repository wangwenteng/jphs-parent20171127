    <%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8"%>
        <%@ page import="com.baidu.ueditor.um.Uploader" %>
       <%@ page import="com.jinpaihushi.panel.upyun.Upyun" %>
       <%@ page import="java.io.File" %>
            <%
    request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
    Uploader up = new Uploader(request);
    up.setSavePath("../../../static/upload");
    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"}; 
    
    //String[] fileType = {".png" };
    up.setAllowFiles(fileType);
    up.setMaxSize(1024); //单位KB
    up.upload();
    String callback = request.getParameter("callback");
    String nameJsp = "imageUp.jsp";
    String pathJsp = application.getRealPath(nameJsp);
    System.out.println("pathJsp---------1------------------"+pathJsp);
    
    String imgPath = pathJsp.replace(nameJsp, "");
    System.out.println("pathJsp----------2-----------------"+pathJsp);
    
    String imga = up.getUrl().replace("../", "").replace("/", "\\");
    imgPath = imgPath+imga;
    System.out.println("imgPath----------4-----------------"+imgPath);
    Upyun upyun = new Upyun();
    String ss =upyun.writeFile(imgPath.replace("\\", "/"));
    System.out.println("ss----------3-----------------"+ss);
    String result = "{\"name\":\""+ up.getFileName() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ss +"\"}";

    result = result.replaceAll( "\\\\", "\\\\" );

    if( callback == null ){
        response.getWriter().print( result );
    }else{
        response.getWriter().print("<script>"+ callback +"(" + result + ")</script>");
    }
    %>
