﻿<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form method="get" action="/nurse/images/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>图片地址：</td>
			<td width="200">
				<input type="text" name="url" id="url" value="${nurseImages.url}" />
			</td>
			<td>服务器物理地址：</td>
			<td width="200">
				<input type="text" name="path" id="path" value="${nurseImages.path}" />
			</td>
			<td>来源：</td>
			<td width="200">
				<input type="text" name="sourceId" id="sourceId" value="${nurseImages.sourceId}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

