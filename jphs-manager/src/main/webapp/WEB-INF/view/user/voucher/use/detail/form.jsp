<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="voucherUseTable" class="tableStyle">
		<tr>
			<td align="right"width="100">优惠券仓库id：</td>
			<td>
				<c:out value="${voucherUse.voucherRepertoryId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">领取人手机号：</td>
			<td>
				<c:out value="${voucherUse.phone}"/>
			</td>
		</tr>
		<tr>
			<td align="right">优惠券金额：</td>
			<td>
				<c:out value="${voucherUse.amount}"/>
			</td>
		</tr>
		<tr>
			<td align="right">有效期开始时间：</td>
			<td>
				<fmt:formatDate value="${voucherUse.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td align="right">有效期结束时间：</td>
			<td>
				<fmt:formatDate value="${voucherUse.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td align="right">使用时间：</td>
			<td>
				<fmt:formatDate value="${voucherUse.useTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
	</table>
</div>