<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form method="get" action="/nurse/active/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>被封护士ID：</td>
			<td width="200">
				<input type="text" name="nurseId" id="nurseId" value="${nurseActive.nurseId}" />
			</td>
			<td>封号标识：</td>
			<td width="200">
				<input type="text" name="active" id="active" value="${nurseActive.active}" />
			</td>
			<td>备注：</td>
			<td width="200">
				<input type="text" name="remark" id="remark" value="${nurseActive.remark}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

