<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<form id="serach-form" method="get" action="/site/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>站点名称：</td>
			<td width="200"><input type="text" id="name" name="name" value="${site.name}" placeholder="请输入搜索名称" /></td>
			<td>站点域名：</td>
			<td width="200"><input type="text" id="url" name="url" value="${site.url}"  placeholder="请输入搜索域名" /></td>
			<!--<td><button class="input-group-addon btn btn-primary search_btn">搜索</button></td>-->
		</tr>
		<!-- <tr>
			<td>服务区域：</td>
			<td width="200"><input type="text" placeholder="请输入搜索品类" /></td>
			<td>备注：</td>
			<td width="200"><input type="text" placeholder="请输入搜索品类" /></td>
			<td><button class="input-group-addon btn btn-primary search_btn">搜索</button></td>
		</tr> -->
		<!-- <tr>
			<td>名称：</td>
			<td width="200"><input type="text" placeholder="请输入搜索品类" /></td>
			<td>名称称：</td>
			<td width="200"><input type="text" placeholder="请输入搜索品类" /></td>
			<td><button class="input-group-addon btn btn-primary search_btn">搜索</button></td>
		</tr> -->
	</table>
	<div class="marage_search_btn">
		<button id="search_btn" type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>

</div>
</form>