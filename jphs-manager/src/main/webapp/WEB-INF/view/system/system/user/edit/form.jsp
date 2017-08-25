<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">姓名：</label>
			<div class="controls col-md-6">
				<input type="hidden" value ="${systemUser.id}" name ="id" id="id">
				<input type="text" value="${systemUser.name}" maxlength="4" id="name" name="name" placeholder="请输入真实姓名" class="form-control">
			</div>
		</div>		
		<div class="form-group">			
			<label class="control-label  col-md-3">企业邮箱：</label>
			<div class="controls  col-md-6  ">
				<div class="input-append input-group">
					<input  value="${systemUser.email}" id="email" name="email" class="span2 form-control" placeholder="请输入公司邮箱" type="text">
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3" for="input01">手机号：</label>
			<div class="controls col-md-6">
				<input type="text" value="${systemUser.phone}" id="phone" maxlength="11" name="phone" placeholder="请输入手机号" class="form-control">
			</div>
		</div>
		<c:if test="${systemUser.id == null}">
			<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">密码：</label>
			<div class="controls col-md-6">
				<input type="password" style="width: 320px;" maxlength="16" placeholder="请输入密码"  value="${systemUser.password}" id="password"
					name="password" class="form-control">
			</div>
		</div>

		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">确认密码：</label>
			<div class="controls col-md-6">
				<input type="password" style="width: 320px;" placeholder="确认密码"  maxlength="16" value="${systemUser.password}" id="confirm_password"
					name="confirm_password" class="form-control">
			</div>
		</div>
		</c:if>
	</fieldset>
</form>
