<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>

<input type="hidden" id="id" name="id" value="${user.id}" />
	<fieldset>
		<div id="legend" class="">
			<legend class="">编辑会员信息</legend>
		</div>
		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">昵称</label>
			<div class="controls col-md-6">
				<input type="text" value="${user.name }" id="name" name="name"
					class="form-control">
			</div>
		</div>

		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">密码</label>
			<div class="controls col-md-6">
				<input type="text" value="${user.password }" id="password"
					name="password" class="form-control">
			</div>
		</div>


		<div class="form-group">
			<label class="control-label col-md-3">性别</label>
			<div class="controls  col-md-6">
				<!-- 单行单选项目 -->
				<label class="radio radio-inline"> <input type="radio"
					value="0" name="sex" id="sex"
					<c:if test="${user.sex==0}">checked="checked"</c:if>> 男
				</label> <label class="radio radio-inline"> <input type="radio"
					value="1" name="sex" id="sex"
					<c:if test="${user.sex==1}">checked="checked"</c:if>> 女
				</label>
			</div>
		</div>

		<div class="form-group">

			<!-- 文本区域 -->
			<label class="control-label col-md-3">个人简介</label>
			<div class="controls col-md-6">
				<div class="textarea">
					<textarea id="brief" name="brief" class="form-control">${user.brief } </textarea>
				</div>
			</div>
		</div>

	</fieldset>
