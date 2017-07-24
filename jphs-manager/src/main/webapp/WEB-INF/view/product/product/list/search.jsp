<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<form id="serach-form" method="get" action="/product/index.jhtml">
	<span class="marage_select_span">
		品类名称：
		<input type="text" name="title" id="title" value="${product.title}" />
	</span>
	<span class="marage_select_span">
		排序：
		<input type="text" name="sort" id="sort" value="${product.sort}" />
	</span>
	<%-- <span class="marage_select_span">
		备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：
		<input type="text" name="remark" id="remark" value="${product.remark}" />
	</span> --%>
	<div class="marage_search_btn">
		<button type="submit" class="public_btn2 public_color m-10 search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="public_btn2 m-10 search_btn">清空</button>
	</div>
</form>

