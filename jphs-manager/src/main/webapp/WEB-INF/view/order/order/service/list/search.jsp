<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/order/service/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>主订单id：</td>
			<td width="200">
				<input type="text" name="orderId" id="orderId" value="${orderService.orderId}" />
			</td>
			<td>单次价格：</td>
			<td width="200">
				<input type="text" name="price" id="price" value="${orderService.price}" />
			</td>
			<td>执行人：</td>
			<td width="200">
				<input type="text" name="nurseId" id="nurseId" value="${orderService.nurseId}" />
			</td>
			<td>患者姓名：</td>
			<td width="200">
				<input type="text" name="patientName" id="patientName" value="${orderService.patientName}" />
			</td>
			<td>患者电话：</td>
			<td width="200">
				<input type="text" name="patientPhone" id="patientPhone" value="${orderService.patientPhone}" />
			</td>
			<td>指定医生：</td>
			<td width="200">
				<input type="text" name="expectorDoctor" id="expectorDoctor" value="${orderService.expectorDoctor}" />
			</td>
			<td>进度：</td>
			<td width="200">
				<input type="text" name="schedule" id="schedule" value="${orderService.schedule}" />
			</td>
			<td>预约时间：</td>
			<td width="200">
				<input type="text" name="appointmentTime" id="appointmentTime" value="${orderService.appointmentTime}" />
			</td>
			<td>出发时间：</td>
			<td width="200">
				<input type="text" name="setoutTime" id="setoutTime" value="${orderService.setoutTime}" />
			</td>
			<td>开始服务时间：</td>
			<td width="200">
				<input type="text" name="startServiceTime" id="startServiceTime" value="${orderService.startServiceTime}" />
			</td>
			<td>结束服务时间：</td>
			<td width="200">
				<input type="text" name="endServiceTime" id="endServiceTime" value="${orderService.endServiceTime}" />
			</td>
			<td>提醒时间：</td>
			<td width="200">
				<input type="text" name="remindTime" id="remindTime" value="${orderService.remindTime}" />
			</td>
			<td>完成订单时间：</td>
			<td width="200">
				<input type="text" name="confirmTime" id="confirmTime" value="${orderService.confirmTime}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

