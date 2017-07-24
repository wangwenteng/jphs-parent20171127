<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
<fieldset>
		<div class="form-group">
			<label class="control-label col-md-3">频道列表：</label>
			<div class="controls col-md-6">
				<!-- 多选选项 -->
				<c:forEach var="informationChannelOne" items="${informationChannel }" varStatus="sratus">
					<c:if test="${informationChannelOne.status == 1 }">
						${informationChannelOne.name }<br>
					</c:if>
				</c:forEach>
			</div>
		</div>

		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">标题：</label>
			<div class="controls col-md-6">
				${information.title }
			</div>
		</div>
		
		<c:forEach items="${fn:split(information.image,',')}" var="addr" begin="0" end="${fn:length(fn:split(information.image,','))}" varStatus="stat">
			<div class="form-group">
				<label class="control-label col-md-3">图${stat.index }</label>
				<!-- 文件上传 -->
				<div class="controls col-md-6">
					<img alt="" height="200" width="200" id="pcurls" src="${addr }"> 
				</div>
			</div>
		</c:forEach>
		<%-- 
		<div class="form-group">
			<label class="control-label col-md-3">图2</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
					<img alt="" height="200" width="200" id="pcurl2s" src="${fn:split(information.image,',')[1] }"> 
					<input class="input-file" type="file" name="myfiles" id="pcurl_s2" onchange="ajaxFileUpload('pcurl_s2','pcurl2');" /> 
					<input class="input-file" type="hidden" id="pcurl2" name="image" value="${fn:split(information.image,',')[1] }" />
			</div>
		</div> --%>
		<%-- 
		<div class="form-group">
			<label class="control-label col-md-3">图3</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
					<img alt="" height="200" width="200" id="pcurl3s" src="${fn:split(information.image,',')[2] }"> 
					<input class="input-file" type="file" name="myfiles" id="pcurl_s3" onchange="ajaxFileUpload('pcurl_s3','pcurl3');" /> 
					<input class="input-file" type="hidden" id="pcurl3" name="image" value="${fn:split(information.image,',')[2] }" />
			</div>
		</div> --%>

		<div class="form-group">
			<label class="control-label col-md-3">内容：</label>
			<div class="controls col-md-6">
				<textarea class="form-control" >${information.content}</textarea>
			</div>
		</div>

		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">预览数量：</label>
			<div class="controls col-md-6">
				${information.previewNumber }
			</div>
		</div>

		<div class="form-group">
			<!-- 下拉列表 -->
			<label class="control-label col-md-3">置顶：</label>
			<div class="controls col-md-6">
					<c:if test="${information.top == 0}">
						未置顶
					</c:if>
					<c:if test="${information.top == 1}">
						已置顶
					</c:if> 
			</div>
		</div>

		<div class="form-group">
			<!-- 下拉列表 -->
			<label class="control-label col-md-3">类型：</label>
			<div class="controls col-md-6">
				${information.type}
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">出处：</label>
			<div class="controls col-md-6">
				${information.source }
			</div>
		</div>

		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">作者：</label>
			<div class="controls col-md-6">
				${information.author }
			</div>
		</div>

	</fieldset>
</form>