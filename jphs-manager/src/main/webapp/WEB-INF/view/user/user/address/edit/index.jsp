<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 10px 10px 10px;">
	<div class="zw_dqwz">
			您目前所在的位置是：<b>xxxx&nbsp;>>&nbsp;xxxx&nbsp;>>&nbsp;xxxx&nbsp;>>&nbsp;<label
			id="nav_current">xxxx</label>
		</b>
		
	</div>
	<form class="add_body" id="userAddressForm" method="post" data-role="validate-form"
		action="/user/address/insert.jhtml">
		<div class="tool_form">
			<div class="wf_btn_left">
				<afocus:privilege url="/user/address/save.jhtml">
					<button type="submit" data-role="save-btn">保存</button>
				</afocus:privilege>
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
