<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="evaluationTable" class="tableStyle">
		<tr>
			<td align="right"width="100">主订单id：</td>
			<td>
				<c:out value="${evaluation.orderId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">商品id：</td>
			<td>
				<c:out value="${evaluation.goodsId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">护士id：</td>
			<td>
				<c:out value="${evaluation.nurseId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">星级：</td>
			<td>
				<c:out value="${evaluation.level}"/>
			</td>
		</tr>
		<tr>
			<td align="right">内容：</td>
			<td>
				<c:out value="${evaluation.content}"/>
			</td>
		</tr>
	</table>
</div>