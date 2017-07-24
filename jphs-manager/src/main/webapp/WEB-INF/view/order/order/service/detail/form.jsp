<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="orderServiceTable" class="tableStyle">
		<tr>
			<td align="right"width="100">主订单id：</td>
			<td>
				<c:out value="${orderService.orderId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">单次价格：</td>
			<td>
				<c:out value="${orderService.price}"/>
			</td>
		</tr>
		<tr>
			<td align="right">执行人：</td>
			<td>
				<c:out value="${orderService.nurseId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">患者姓名：</td>
			<td>
				<c:out value="${orderService.patientName}"/>
			</td>
		</tr>
		<tr>
			<td align="right">患者电话：</td>
			<td>
				<c:out value="${orderService.patientPhone}"/>
			</td>
		</tr>
		<tr>
			<td align="right">指定医生：</td>
			<td>
				<c:out value="${orderService.expectorDoctor}"/>
			</td>
		</tr>
		<tr>
			<td align="right">进度：</td>
			<td>
				<c:out value="${orderService.schedule}"/>
			</td>
		</tr>
		<tr>
			<td align="right">预约时间：</td>
			<td>
				<fmt:formatDate value="${orderService.appointmentTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td align="right">出发时间：</td>
			<td>
				<fmt:formatDate value="${orderService.setoutTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td align="right">开始服务时间：</td>
			<td>
				<fmt:formatDate value="${orderService.startServiceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td align="right">结束服务时间：</td>
			<td>
				<fmt:formatDate value="${orderService.endServiceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td align="right">提醒时间：</td>
			<td>
				<fmt:formatDate value="${orderService.remindTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td align="right">完成订单时间：</td>
			<td>
				<fmt:formatDate value="${orderService.confirmTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
	</table>
</div>