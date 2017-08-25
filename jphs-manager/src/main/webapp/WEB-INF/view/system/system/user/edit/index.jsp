<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" />系统管理 <i
		class="public1-horn-45"></i> 用户管理 <i class="public1-horn-45"></i>${systemUser.id == null?'用户添加':'用户编辑'}
</div>
<form class="form-horizontal" id="systemUserForm" method="post"
	data-role="validate-form" action="/system/user/insert.jhtml">
	<div class="marage_right_content">
		<div class="clearfix">
			<button type="submit" id="save"
				class="public-info public_btn public_btn_left" data-role="save-btn">保存</button>
			<input type="button" class="public-info public_btn public_btn_left"
				name="button1" id="button1" value="返回" onclick="history.go(-1)">
			<button type="button" id="resetPassword"
				class="public-info public_btn public_btn_right" data-role="save-btn">重置密码</button>
			<button type="button" id="modifyPassword"
				class="public-info public_btn public_btn_right"  data-role="save-btn">修改密码</button>
		</div>
		<hr class="mt-5" />
		<jsp:include page="form.jsp"></jsp:include>
	</div>
</form>

