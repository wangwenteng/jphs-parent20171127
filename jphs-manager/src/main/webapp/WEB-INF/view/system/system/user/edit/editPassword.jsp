<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>

<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" />系统管理 <i
		class="public1-horn-45"></i> 用户管理 <i class="public1-horn-45"></i>${type == 2?'修改密码':'重置密码'}
</div>
<form class="form-horizontal" id="systemUserEdit" method="post"
	data-role="validate-form" action="/system/user/editPassword.jhtml">
	<div class="marage_right_content">
		<div class="clearfix">
			<button type="submit" id="save"
				class="public-info public_btn public_btn_left" data-role="save-btn">保存</button>
			<input type="button" class="public-info public_btn public_btn_left"
				name="button1" id="button1" value="返回" onclick="history.go(-1)">
		</div>
		<hr class="mt-5" />
		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">账号名称：</label>
			<div class="controls col-md-6">
				<input type="text" style="width: 320px;" maxlength="16" readonly="readonly"
					  value="${name }" class="form-control">
			</div>
		</div>
		<input type="hidden" id ="id" value="${id }" name ="id">
		<c:if test="${type==2}">
			<div class="form-group">
				<label class="control-label col-md-3" for="input01">原密码：</label>
				<div class="controls col-md-6">
					<input type="password" id="old_password" style="width: 320px;" required="required"
						maxlength="16" name="old_password" placeholder="请输入原密码"
						class="form-control">
				</div>
			</div>
		</c:if>
		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">新密码：</label>
			<div class="controls col-md-6">
				<input type="password" style="width: 320px;"  maxlength="16"
					placeholder="请输入新密码"  id="password" required="required"
					name="password" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">确认新密码：</label>
			<div class="controls col-md-6">
				<input type="password" style="width: 320px;" placeholder="请确认新密码"
					maxlength="16" id="confirm_password" required="required"
					name="confirm_password" class="form-control">
			</div>
		</div>

	</div>
</form>
