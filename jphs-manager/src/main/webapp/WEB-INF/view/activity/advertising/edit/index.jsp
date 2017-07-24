<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 10px 10px 10px;">
	<div class="zw_dqwz">
			您目前所在的位置是：<b>活动管理&nbsp;>>&nbsp;轮播图管理&nbsp;>>&nbsp;<label
			id="nav_current">${advertising.id == null?'轮播图添加':'轮播图编辑'}</label>
		</b>
		
	</div>
	<form class="add_body" id="advertisingForm" method="post" data-role="validate-form"
		action="/advertising/insert.jhtml">
		<div class="tool_form">
			<div class="wf_btn_left">
				<afocus:privilege url="/advertising/insert.jhtml">
					<button type="submit" class="btn" data-role="save-btn">保存</button>
				</afocus:privilege>
				<input type="button" class="btn" name="button1" id="button1"
				value="返回" onclick="history.go(-1)">
			</div>
		</div>
		
		<div class="con_side">
			<div class="con_side2">
				<div class="event_add_2" >
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
