<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<form id="serach-form" method="get" action="/goods/index.jhtml">
	<table class="text-right ">
		<tr>
			<td>服务名称：</td>
			<td width="200"><input type="text" name="title" id="title"
				value="${goods.title}" /></td>
			<td>服务别名：</td>
			<td width="200"><input type="text" name="subTitle" id="subTitle"
				value="${goods.subTitle}" /></td>
		</tr>
		
		<tr>
			<td>服务品类：</td>
			<td>
				<div class="controls col-md-6">
					<select class="form-control input-xlarge" style="width: 180px;" id="productId" name="productId">
						<option value="">全部</option>
						<c:forEach var="productone" items="${product_list }"
							varStatus="status">
							<c:if test="${productone.id == goods.productId }">
								<option value="${productone.id }" selected="selected">${productone.title }</option>
							</c:if>
							<c:if test="${productone.id != goods.productId }">
								<option value="${productone.id }">${productone.title }</option>
							</c:if>

						</c:forEach>
					</select>
				</div>
			</td>
			<%-- <td>状态：</td>
			<td width="200"><input type="text" name="status" id="status"
				value="${goods.status}" /></td> --%>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</form>

