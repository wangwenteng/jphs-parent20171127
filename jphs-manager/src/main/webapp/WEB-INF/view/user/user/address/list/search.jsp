<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/user/address/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>标题地址：</td>
			<td width="200">
				<input type="text" name="title" id="title" value="${userAddress.title}" />
			</td>
			<td>省：</td>
			<td width="200">
				<input type="text" name="province" id="province" value="${userAddress.province}" />
			</td>
			<td>市：</td>
			<td width="200">
				<input type="text" name="city" id="city" value="${userAddress.city}" />
			</td>
			<td>区（县）：</td>
			<td width="200">
				<input type="text" name="area" id="area" value="${userAddress.area}" />
			</td>
			<td>详细地址：</td>
			<td width="200">
				<input type="text" name="detailaddress" id="detailaddress" value="${userAddress.detailaddress}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

