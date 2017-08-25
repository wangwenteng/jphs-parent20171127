<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form method="get" action="/skill/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>技能名称：</td>
			<td width="200">
				<input type="text" name="name" id="name" value="${skill.name}" />
			</td>
			<td>平台此商品最低价格：</td>
			<td width="200">
				<input type="text" name="amount" id="amount" value="${skill.amount}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

