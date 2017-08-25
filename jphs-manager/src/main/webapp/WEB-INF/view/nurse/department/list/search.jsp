<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form method="get" action="/department/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>科室名：</td>
			<td width="200">
				<input type="text" name="name" id="name" value="${department.name}" />
			</td>
			<td>排序：</td>
			<td width="200">
				<input type="text" name="sort" id="sort" value="${department.sort}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

