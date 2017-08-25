<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="orderOtherTable" class="tableStyle">
		<tr>
			<td align="right"width="100">订单id：</td>
			<td>
				<c:out value="${orderOther.orderId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">科室id：</td>
			<td>
				<c:out value="${orderOther.departmentId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">医院：</td>
			<td>
				<c:out value="${orderOther.hospital}"/>
			</td>
		</tr>
		<tr>
			<td align="right">地址-省：</td>
			<td>
				<c:out value="${orderOther.address}"/>
			</td>
		</tr>
		<tr>
			<td align="right">详细地址：</td>
			<td>
				<c:out value="${orderOther.detailAddress}"/>
			</td>
		</tr>
		<tr>
			<td align="right">是否有药品：</td>
			<td>
				<c:out value="${orderOther.drug}"/>
			</td>
		</tr>
		<tr>
			<td align="right">是否有工具：</td>
			<td>
				<c:out value="${orderOther.tool}"/>
			</td>
		</tr>
		<tr>
			<td align="right">通用字段：</td>
			<td>
				<c:out value="${orderOther.publicName}"/>
			</td>
		</tr>
		<tr>
			<td align="right">通用字段：</td>
			<td>
				<c:out value="${orderOther.publicContent}"/>
			</td>
		</tr>
		<tr>
			<td align="right">：</td>
			<td>
				<c:out value="${orderOther.remarks}"/>
			</td>
		</tr>
	</table>
</div>