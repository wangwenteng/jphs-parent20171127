<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<form id="serach-form" method="get" action="/information/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<%-- <tr>
			<td>服务名称：</td>
			<td width="200"><input type="text" name="title" id="title"
				value="${goods.title}" /></td>
			<td>服务别名：</td>
			<td width="200"><input type="text" name="subTitle" id="subTitle"
				value="${goods.subTitle}" /></td>
		</tr> --%>
		
		<tr>
			<td>频道名称：</td>
			<td>
				<div class="controls col-md-6">
					<select class="form-control input-xlarge" style="width: 180px;" id="informationChannelId" name="informationChannelId">
						<option value="">全部</option>
						<c:forEach var="iarrone" items="${iarr }" varStatus="status">
							<c:if test="${iarrone.id == information.informationChannelId }">
								<option value="${iarrone.id }" selected="selected">${iarrone.name }</option>
							</c:if>
							<c:if test="${iarrone.id != information.informationChannelId }">
								<option value="${iarrone.id }">${iarrone.name }</option>
							</c:if>

						</c:forEach>
					</select>
				</div>
			</td>
			<td>资讯名称：</td>
			<td width="200"><input type="text" name="title" id="title"
				value="${information.title}" /></td>
		</tr>
		
		<%-- <tr>
			<td>置顶状态：</td>
			<td width="200">
				<select class="form-control input-xlarge" style="width: 180px;" id="top" name="top">
					<c:if test="${information.title == 1}">
						<option value="" >全部</option>
					</c:if>
					<c:if test="">
						<option value="0" >置顶</option>
					</c:if>
					<c:if test="">
						<option value="-1" >未置顶</option>
					</c:if>
				</select>
			</td>
			<td>服务别名：</td>
			<td width="200"><input type="text" name="subTitle" id="subTitle"
				value="${goods.subTitle}" /></td>
		</tr> --%>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
	</div>
</form>