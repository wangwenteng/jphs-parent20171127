<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/order/other/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>订单id：</td>
			<td width="200">
				<input type="text" name="orderId" id="orderId" value="${orderOther.orderId}" />
			</td>
			<td>科室id：</td>
			<td width="200">
				<input type="text" name="departmentId" id="departmentId" value="${orderOther.departmentId}" />
			</td>
			<td>医院：</td>
			<td width="200">
				<input type="text" name="hospital" id="hospital" value="${orderOther.hospital}" />
			</td>
			<td>地址-省：</td>
			<td width="200">
				<input type="text" name="address" id="address" value="${orderOther.address}" />
			</td>
			<td>详细地址：</td>
			<td width="200">
				<input type="text" name="detailAddress" id="detailAddress" value="${orderOther.detailAddress}" />
			</td>
			<td>是否有药品：</td>
			<td width="200">
				<input type="text" name="drug" id="drug" value="${orderOther.drug}" />
			</td>
			<td>是否有工具：</td>
			<td width="200">
				<input type="text" name="tool" id="tool" value="${orderOther.tool}" />
			</td>
			<td>通用字段：</td>
			<td width="200">
				<input type="text" name="publicName" id="publicName" value="${orderOther.publicName}" />
			</td>
			<td>通用字段：</td>
			<td width="200">
				<input type="text" name="publicContent" id="publicContent" value="${orderOther.publicContent}" />
			</td>
			<td>：</td>
			<td width="200">
				<input type="text" name="remarks" id="remarks" value="${orderOther.remarks}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

