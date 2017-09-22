<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/business/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>家商名称：</td>
			<td width="200">
				<input type="text" name="name" id="name" value="${business.name}" />
			</td>
			<td>联系人：</td>
			<td width="200">
				<input type="text" name="contactsName" id="contactsName" value="${business.contactsName}" />
			</td>
			<td>联系电话：</td>
			<td width="200">
				<input type="text" name="contactsPhone" id="contactsPhone" value="${business.contactsPhone}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

