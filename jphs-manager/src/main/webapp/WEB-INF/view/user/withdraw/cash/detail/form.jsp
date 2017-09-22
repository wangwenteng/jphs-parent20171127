<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt">提现申请记录
</div>
	<table id="withdrawCashTable" class="tableStyle">
		<tr>
			<td align="right"width="100">提现金额：</td>
			<td>
				<c:out value="${withdrawCash.amount}"/>
			</td>
		</tr>
		<tr>
			<td align="right">账户姓名：</td>
			<td>
				<c:out value="${withdrawCash.accountName}"/>
			</td>
		</tr>
		<tr>
			<td align="right">支付宝账号：</td>
			<td>
				<c:out value="${withdrawCash.alipayAccount}"/>
			</td>
		</tr>
		<tr>
			<td align="right">审核时间：</td>
			<td>
				<fmt:formatDate value="${withdrawCash.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td align="right">备注 驳回时间：</td>
			<td>
				<c:out value="${withdrawCash.remark}"/>
			</td>
		</tr>
	</table>
</div>