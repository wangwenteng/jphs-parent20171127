<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div id="pageBody" style="min-height: 400px;">
	<div id="pageBody_dateTable">
		<table id="dateTable" width="98%" cellspacing="0" class="bg_list"
			cellpadding="0">
			<tr class="bg_list_head">
				<th width="30"></th>
				<th></th>
				<th></th>
				<th>url类型id</th>
				<th width="125">操作</th>
			</tr>
			<c:choose>
				<c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td><c:out value="${e.path}"/></td>
							<td><c:out value="${e.name}"/></td>
							<td><c:out value="${e.productId}"/></td>
							<td>
								<div class="opertionStn">
									<afocus:privilege url="/goods/url/edit.jhtml">
										<a href="/goods/url/edit.jhtml?id=${e.id}" title="编辑">
											<span class="ui-icon ui-icon-tag"></span>
										</a>
									</afocus:privilege>
									<afocus:privilege url="/goods/url/detail.jhtml">
										<a href="/goods/url/detail.jhtml?id=${e.id}" title="详情">
											<span class="ui-icon ui-icon-comment"></span>
										</a>
									</afocus:privilege>
									<afocus:privilege url="/goods/url/delete.json">
										<a href="javascript:umodule.remove(${e.id});" title="删除">
											<span class="ui-icon ui-icon-close"></span>
										</a>
									</afocus:privilege>
								</div>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="20" align="center">没有可显示的记录。</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	<div class="dia_fenye">
		<afocus:page pageInfos="${pageInfo}"></afocus:page>
	</div>
</div>