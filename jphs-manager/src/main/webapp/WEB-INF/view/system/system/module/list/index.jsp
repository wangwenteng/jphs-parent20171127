<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" />系统管理<i
		class="public1-horn-45"></i>模块管理 <i class="public1-horn-45"></i>模块列表
</div>

<div class="marage_right_content">
	<!-- 左对齐按钮 -->
	<!-- <button id="redirectAddPage" type="button" class="public-info public_btn">新建**</button>
	<button type="button" class="btn btn-info public_btn">导出</button>	 -->
	<!-- 右对齐按钮 -->
	<!-- <button type="button" class="btn btn-info public_btn public_btn_right">功能按钮</button> -->
	<form class="form-horizontal edit_form" id="systemRoleModuleForm" method="post"
		data-role="validate-form" >
		<div class="event_handle_body">
			<div style="width: 630px; margin: 0 auto; padding: 10px 0px;">
				<jsp:include page="list.jsp"></jsp:include>
			</div>
		</div>
	</form>
</div>