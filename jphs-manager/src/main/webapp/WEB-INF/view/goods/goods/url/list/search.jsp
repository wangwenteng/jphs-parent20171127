<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<form id="stand_search" class="stand_search" method="post" action="/goods/url/index.jhtml">
	<table id="searchTable" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>：</td>
			<td>
				<input type="text" name="path" id="path" value="${goodsUrl.path}" />
			</td>
			<td>：</td>
			<td>
				<input type="text" name="name" id="name" value="${goodsUrl.name}" />
			</td>
			<td>url类型id：</td>
			<td>
				<input type="text" name="productId" id="productId" value="${goodsUrl.productId}" />
			</td>
			<td colspan="2">
				<button type="submit" data-role="search-btn">查询</button>
			</td>
		</tr>
	</table>
</form>

