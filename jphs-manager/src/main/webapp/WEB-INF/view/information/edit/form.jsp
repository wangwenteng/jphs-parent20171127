<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<input type="hidden" id="id" name="id" value="${information.id}" />
		<div class="form-group">
			<label class="control-label col-md-3">频道列表：</label>
			<div class="controls col-md-6">
				<!-- 多选选项 -->
				<c:forEach var="informationChannelOne" items="${informationChannel }" varStatus="sratus">
					<c:if test="${informationChannelOne.status == 1 }">
						<label class="checkbox" > 
							<input type="checkbox" checked="checked" name="informationChannelId"  value="${informationChannelOne.id }">${informationChannelOne.name }
						</label>
					</c:if>
					<c:if test="${informationChannelOne.status != 1 }">
						<label class="checkbox" > 
							<input type="checkbox" name="informationChannelId"  value="${informationChannelOne.id }">${informationChannelOne.name }
						</label>
					</c:if>
				</c:forEach>
			</div>
		</div>

		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">标题：</label>
			<div class="controls col-md-6">
				<input type="text" placeholder="标题" name="title" value="${information.title }" class="form-control">
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-3">图1：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
					<img alt="" height="200" width="200" id="pcurls" src="${fn:split(information.image,',')[0] }"> 
					<input class="input-file" type="file" name="myfiles" id="pcurl_s" onchange="ajaxFileUpload('pcurl_s','pcurl');" /> 
					<input class="input-file" type="hidden" id="pcurl" name="image" value="${fn:split(information.image,',')[0] }" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-3">图2：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
					<img alt="" height="200" width="200" id="pcurl2s" src="${fn:split(information.image,',')[1] }"> 
					<input class="input-file" type="file" name="myfiles" id="pcurl_s2" onchange="ajaxFileUpload('pcurl_s2','pcurl2');" /> 
					<input class="input-file" type="hidden" id="pcurl2" name="image" value="${fn:split(information.image,',')[1] }" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-3">图3：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
					<img alt="" height="200" width="200" id="pcurl3s" src="${fn:split(information.image,',')[2] }"> 
					<input class="input-file" type="file" name="myfiles" id="pcurl_s3" onchange="ajaxFileUpload('pcurl_s3','pcurl3');" /> 
					<input class="input-file" type="hidden" id="pcurl3" name="image" value="${fn:split(information.image,',')[2] }" />
			</div>
		</div>
		
		<div class="form-group">

			<!-- 下拉列表 -->
			<label class="control-label col-md-3">置顶：</label>
			<div class="controls col-md-6">
				<!-- 单行单选项目 -->
				<label class="radio radio-inline">
					<c:if test="${information.top == 0}">
						<input type="radio" value="0" name="top" checked="checked">
					</c:if>
					<c:if test="${information.top != 0}">
						<input type="radio" value="0" name="top" checked="checked">
					</c:if> 取消置顶
				</label> <label class="radio radio-inline">
					<c:if test="${information.top == 1}">
						<input type="radio" value="1" name="top" checked="checked">
					</c:if>
					<c:if test="${information.top != 1}">
						<input type="radio" value="1" name="top">
					</c:if>置顶
				</label>
			</div>
		</div>

		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">置顶时间：</label>
			<div class="controls col-md-6">
				<fmt:formatDate value="${information.topTime}" pattern="yy-MM-dd HH:mm:ss" />
			</div>
		</div>

		<div class="form-group">

			<!-- 下拉列表 -->
			<label class="control-label col-md-3">类型：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" name="type">
					<c:forEach var="temps" begin="1" step="1" end="30">
						<c:if test="${temps == information.type }">
							<option value="${information.type}" selected="selected">${information.type}</option>
						</c:if>
						<c:if test="${temps != information.type }">
							<option value="${temps}">${temps}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>

		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">出处：</label>
			<div class="controls col-md-6">
				<input type="text" placeholder="出处" name="source" value="${information.source }" class="form-control">
			</div>
		</div>

		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">作者：</label>
			<div class="controls col-md-6">
				<input type="text" placeholder="作者" name="author" value="${information.author }" class="form-control">
			</div>
		</div>
		
		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">创建时间：</label>
			<div class="controls col-md-6">
				<fmt:formatDate value="${information.createTime}" pattern="yy-MM-dd HH:mm:ss" />
			</div>
		</div>

		<div class="public_editor">
			<h5>内容</h5>
			<p>
				<textarea name="content" id="myEditor"  class="form-control" style="width:805px;height:500px;">${information.content}</textarea>
			</p>
		</div>
<%-- 
		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">预览数量</label>
			<div class="controls col-md-6">
				<input type="text" placeholder="预览数量" name="previewNumber" value="${information.previewNumber }" class="form-control"/>
			</div>
		</div> --%>

		

	</fieldset>
</form>