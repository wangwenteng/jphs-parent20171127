<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="businessTable" class="tableStyle">
		<tr>
			<td align="right"width="100">家商名称：</td>
			<td>
				<c:out value="${business.name}"/>
			</td>
		</tr>
		<tr>
			<td align="right">官网地址：</td>
			<td>
				<c:out value="${business.officialWebsiteUrl}"/>
			</td>
		</tr>
		<tr>
			<td align="right">商家地址：</td>
			<td>
				<c:out value="${business.address}"/>
			</td>
		</tr>
		<tr>
			<td align="right">联系人：</td>
			<td>
				<c:out value="${business.contactsName}"/>
			</td>
		</tr>
		<tr>
			<td align="right">联系电话：</td>
			<td>
				<c:out value="${business.contactsPhone}"/>
			</td>
		</tr>
		<tr>
			<td align="right">算结周期：</td>
			<td>
				<c:out value="${business.settlementCycle}"/>
			</td>
		</tr>
		<tr>
			<td align="right">银行：</td>
			<td>
				<c:out value="${business.bank}"/>
			</td>
		</tr>
		<tr>
			<td align="right">开户行：</td>
			<td>
				<c:out value="${business.openBankAddress}"/>
			</td>
		</tr>
		<tr>
			<td align="right">卡号：</td>
			<td>
				<c:out value="${business.cardNumber}"/>
			</td>
		</tr>
		<tr>
			<td align="right">开卡人：</td>
			<td>
				<c:out value="${business.cardName}"/>
			</td>
		</tr>
		<tr>
			<td align="right">备注：</td>
			<td>
				<c:out value="${business.remark}"/>
			</td>
		</tr>
	</table>
</div>