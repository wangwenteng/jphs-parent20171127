<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form method="get" action="/insurance/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>主订单id：</td>
			<td width="200">
				<input type="text" name="orderId" id="orderId" value="${insurance.orderId}" />
			</td>
			<td>受保人：</td>
			<td width="200">
				<input type="text" name="name" id="name" value="${insurance.name}" />
			</td>
			<td>受保人身份证：</td>
			<td width="200">
				<input type="text" name="sfz" id="sfz" value="${insurance.sfz}" />
			</td>
			<td>备注：</td>
			<td width="200">
				<input type="text" name="remark" id="remark" value="${insurance.remark}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

