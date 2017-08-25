<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="insuranceTable" class="tableStyle">
		<tr>
			<td align="right"width="100">主订单id：</td>
			<td>
				<c:out value="${insurance.orderId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">受保人：</td>
			<td>
				<c:out value="${insurance.name}"/>
			</td>
		</tr>
		<tr>
			<td align="right">受保人身份证：</td>
			<td>
				<c:out value="${insurance.sfz}"/>
			</td>
		</tr>
		<tr>
			<td align="right">备注：</td>
			<td>
				<c:out value="${insurance.remark}"/>
			</td>
		</tr>
	</table>
</div>