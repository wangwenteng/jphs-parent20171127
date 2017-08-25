<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/jobtitle/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>职称名称：</td>
			<td width="200">
				<input type="text" placeholder="请输入职称名称"  name="name" id="name" value="${jobtitle.name}" />
			</td>
			<td>职称类型：</td>
			<td width="200">
				<select class="marage_select" id="jobtitleTypeId"
					name="jobtitleTypeId">
					<option value="">全部</option>
					<c:forEach items="${typeList}" var="e" varStatus="s">
						<option value="${e.id }"
							<c:if test="${jobtitle.jobtitleTypeId==e.id}">selected="selected"</c:if>>${e.name }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

