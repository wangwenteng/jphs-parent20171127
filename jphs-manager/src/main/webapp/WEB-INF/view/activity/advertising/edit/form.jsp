<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<div id="legend" class="">
			<legend class="">${advertising.id == null?'添加轮播图':'编辑轮播图'}</legend>
			<input type="hidden" id="id" name="id" value="${advertising.id}" />
		</div>
		<%-- <div class="title_defalt">${advertising.id == null?'添加轮播图':'编辑轮播图'}</div> --%>
		
		<div class="form-group">
			<label class="control-label col-md-5">图片：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
				<img alt="" height="200" width="200"  id="images" src="${advertising.image}">
				<input class="input-file" type="file" name="myfiles" id="image_s" onchange="ajaxFileUpload('image_s','image');" />
				<input class="input-file" type="hidden" value="${advertising.image}"  id="image" name="image" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-5">背景图片：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
				<img alt="" height="200" width="200"  id="bgImages" src="${advertising.bgImage}">
				<input class="input-file" type="file" name="myfiles" id="bgImage_s" onchange="ajaxFileUpload('bgImage_s','bgImage');" />
				<input class="input-file" type="hidden" value="${advertising.bgImage}"  id="bgImage" name="bgImage" />
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-5" for="input01">链接地址：</label>
			<div class="controls col-md-6">
				<%-- <input type="text" value="${advertising.link}" id="link"
					name="link" placeholder="链接地址" class="form-control"> --%>
				<textarea type="" class="form-control" d="link"
					name="link" placeholder="链接地址">${advertising.link}</textarea> 
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-5" for="input01">渠道：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" id="type"
					name="type">
					<option value="">请选择</option>
					<option value="1"
							<c:if test="${advertising.type==1}">selected="selected"</c:if>>app护士端</option>
					<option value="2"
							<c:if test="${advertising.type==2}">selected="selected"</c:if>>app用户端</option>
					<option value="3"
							<c:if test="${advertising.type==3}">selected="selected"</c:if>>官网首页</option>
					<option value="4"
							<c:if test="${advertising.type==4}">selected="selected"</c:if>>app资讯</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-5">状态：</label>
			<div class="controls  col-md-6">
				<!-- 单行单选项目 -->
				<label class="radio radio-inline"> <input type="radio"
					value="0" name="status" id="status" <c:if test="${advertising.status==0}">checked="checked"</c:if>> 启用
				</label> <label class="radio radio-inline"> <input type="radio"
					value="-1" name="status" id="status" <c:if test="${advertising.status==-1}">checked="checked"</c:if>> 禁用
				</label>
			</div>
		</div>
	</fieldset>
</form>
