<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div id="pageBody" style="min-height: 400px;">
	<div id="pageBody_dateTable">
		<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
			<thead>
				<tr >
					<th width="5%">序号</th>
					<th width="20%">图片</th>
					<th width="20%">链接</th>
					<th width="20%">创建时间</th>
					<th width="12%">渠道</th>
					<th width="8%">状态</th>
					<th width="15%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td><img alt="" src="${e.image}" width="150" height="100"></td>
							<td><c:out value="${e.link}"/></td>
							<td><fmt:formatDate value="${e.createTime}" pattern="yy-MM-dd HH:mm" /></td>
							<td>
								<c:if test="${e.type==1}">
									<c:out value="app护士端"/>
								</c:if>
								<c:if test="${e.type==2}">app用户端</c:if>
								<c:if test="${e.type==3}">官网首页</c:if>
								<c:if test="${e.type==4}">app咨询</c:if>
							</td>
							<td>
									<c:if test="${e.status==0}">启用</c:if>
									<c:if test="${e.status==-1}">禁用</c:if>
							</td>
							<td>
							<%-- <a href="/advertising/insert.jhtml?id=${e.id}">修改</a>&nbsp;|&nbsp;								
							<a href="/advertising/detail.jhtml?id=${e.id}">查看</a>&nbsp;|&nbsp;								
							<a href="/advertising/delete.jhtml?id=${e.id}">删除</a> --%>
							<jphs:hasPermission url="/advertising/detail.jhtml">
							<a onclick="redirectDetailPage('${e.id}')">
								<img src="/static/images/chakan.png">
							</a>								
							</jphs:hasPermission>
							<jphs:hasPermission url="/advertising/redirectUpdate.jhtml">
							<a onclick="redirectUpdatePage('${e.id}')">
								<img  src="/static/images/xiugai.png">
							</a>									
							</jphs:hasPermission>
							<%-- <a onclick="deleteById('${e.id}')">
								<img src="/static/images/shanchu.png">
							</a> --%>
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
			</tbody>
		</table>
		<div class="page">
			<jphs:page pageInfos="${pageInfo}"></jphs:page>
		</div>
	</div>
	<%-- <div class="dia_fenye">
		<afocus:page pageInfos="${pageInfo}">
			  <div class="pager">
							<form action="/advertising/index.jhtml" method="post">
							共 ${pageInfo.total } 条记录 每页<input class="page" value="${pageInfo.pageSize }" size="2" disabled="disabled" />条
							第<input class="page" value="${pageInfo.pageNum }" size="2" disabled="disabled"/>页/共${pageInfo.pages }页
							<a <c:if test="${pageInfo.pageNum>1 }">href="/advertising/index.jhtml"</c:if> >第一页</a>
							<a <c:if test="${pageInfo.pageNum>1 }">href="/advertising/index.jhtml?p=${pageInfo.pageNum-1 }"</c:if> >上一页</a>
							<a <c:if test="${pageInfo.pageNum<pageInfo.pages }">href="/advertising/index.jhtml?p=${pageInfo.pageNum+1 }"</c:if> >下一页</a>
							<a <c:if test="${pageInfo.pageNum<pageInfo.pages }">href="/advertising/index.jhtml?p=${pageInfo.pages }"</c:if> >最后一页</a>
							转到<input class="page" type="number" max="${pageInfo.pages }" min="1" id="page" value="${pageInfo.pageNum }"  name="p"/>页
								<button class="btn" width="20" type="submit">GO</button>
							</form>
			</div>
		</afocus:page>
	</div> --%>
</div>