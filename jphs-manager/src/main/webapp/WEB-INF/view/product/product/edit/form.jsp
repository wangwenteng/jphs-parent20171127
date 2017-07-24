<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<input type="hidden" id="id" name="id" value="${product.id}" />
		<input type="hidden" id="moveid" name="moveid" value="${wap_image.id}" />
		<input type="hidden" id="pcid" name="pcid" value="${pc_image.id}" />
		<input type="hidden" value="0" name="status" checked="checked"/>
		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">品类名称：</label>
			<div class="controls col-md-6">
				<input type="text" id="title" name="title" placeholder="请输入品类名"
					value="${product.title}" class="form-control">
			</div>
		</div>

		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">序号：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" id="sort" name="sort"
					value="${product.sort}">
					<c:forEach var="temps" begin="1" step="1" end="30">
						<c:if test="${temps == product.sort }">
							<option value="${product.sort}" selected="selected">${product.sort}</option>
						</c:if>
						<c:if test="${temps != product.sort }">
							<option value="${temps}">${temps}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-3">手机端图标：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
				<img alt="" height="200" width="200" id="moveurls"
					src="${wap_image.url}"> <input class="input-file" type="file"
					name="myfiles" id="moveurl_s"
					onchange="ajaxFileUpload('moveurl_s','moveurl');" /> <input
					class="input-file" type="hidden" id="moveurl" name="moveurl"
					value="${wap_image.url}" />
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-3">pc图标：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
					<img alt="" height="200" width="200" id="pcurls" src="${pc_image.url}"> 
					<input class="input-file" type="file" name="myfiles" id="pcurl_s" onchange="ajaxFileUpload('pcurl_s','pcurl');" /> 
					<input class="input-file" type="hidden" id="pcurl" name="pcurl" value="${pc_image.url}" />
			</div>
		</div>

		<!-- <div class="form-group">
			<label class="control-label col-md-3">是否开启</label>
			<div class="controls  col-md-6">
				单行单选项目
				<label class="radio radio-inline">
					<input type="radio" value="0" name="status" checked="checked"> 开启
				</label>
				<label class="radio radio-inline">
					<input type="radio" value="-1" name="status"> 关闭
				</label>
			</div>
		</div>
 -->
		<div class="form-group">
			<!-- 文本区域 -->
			<label class="control-label col-md-3">备注：</label>
			<div class="controls col-md-6">
				<div class="textarea">
					<textarea id="remark" name="remark" placeholder="说明"
						class="form-control">${product.remark}</textarea>
				</div>
			</div>
		</div>

	</fieldset>
</form>