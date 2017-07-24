<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal">
	<fieldset>
		<!-- <div id="legend" class="">
					<legend class="">表单名</legend>
				</div> -->
		<input type="hidden" id="id" name="id" value="${product.id}" />
		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">品类名称：</label>
			<div class="controls col-md-6">
				<input type="text" id="title" name="title" placeholder="请输入品类名"
					value="${product.title}"  readonly="readonly" class="form-control">
			</div>
		</div>



		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">序号：</label>
			<div class="controls col-md-6">
				<input type="text" id="sort" name="sort" placeholder="顺序"
					value="${product.sort}" readonly="readonly" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-3">移动图标：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
					<img alt="" height="200" width="200" id="urls" src="${web_image.url}">
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-3">PC图标：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
					<img alt="" height="200" width="200" id="urls" src="${pc_image.url}">
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-3">是否开启：</label>
			<div class="controls  col-md-6">
				<!-- 单行单选项目 -->
				<label class="radio radio-inline">
					<c:if test="${product.status == 0}">使用中</c:if>
					<c:if test="${product.status == -1}">已停用</c:if>
				</label>
				
			</div>
		</div>

		<div class="form-group">

			<!-- 文本区域 -->
	          <label class="control-label col-md-3">备注：</label>
	          <div class="controls col-md-6">
	            <div class="textarea">
	                  <textarea  id="remark" name="remark" placeholder="说明" readonly="readonly" class="form-control">${product.remark}</textarea>
	            </div>
	          </div>
		</div>

	</fieldset>
</form>