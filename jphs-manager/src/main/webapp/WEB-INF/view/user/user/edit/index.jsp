<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" />会员管理<i class="public1-horn-45"></i>
	会员管理 <i class="public1-horn-45"></i>${user.id == null?'添加会员':'编辑会员信息'}
</div>
<div class="marage_right_content">
	<form class="form-horizontal add_body" id="platformForm" method="post"
		data-role="validate-form" action="/user/insert.jhtml">
		<div class="tool_form">
			<div class="wf_btn_left">
				<afocus:privilege url="/user/save.jhtml">
					<button type="submit" id="save"
						class="public-info public_btn public_btn_left"
						data-role="save-btn">保存</button>
				</afocus:privilege>
				<input type="button" class="public-info public_btn public_btn_left"
					name="button1" id="button1" value="返回" onclick="history.go(-1)">
			</div>
		</div>

		<div class="con_side">
			<div class="con_side2">
				<div class="event_add_2">
					<div class="event_handle_body">
						<div style="width: 630px; margin: 0 auto; padding: 10px 0px;">
							<jsp:include page="form.jsp"></jsp:include>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
