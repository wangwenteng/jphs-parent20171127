<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="advertisingTable" class="tableStyle">
		<tr>
			<td align="right">id：</td>
			<td>
				<c:out value="${advertising.id}"/>
			</td>
		</tr>
		<tr>
			<td align="right"width="100">图片：</td>
			<td>
				<img alt="" src="${advertising.image}" width="150" height="150">
			</td>
		</tr>
		<tr>
			<td align="right"width="100">图片：</td>
			<td>
				<img alt="" src="${advertising.bgImage}" width="150" height="150">
			</td>
		</tr>
		<tr>
			<td align="right">链接地址：</td>
			<td>
				<c:out value="${advertising.link}"/>
			</td>
		</tr>
		<tr>
			<td align="right">创建人id：</td>
			<td>
				<c:out value="${advertising.creatorId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">创建人：</td>
			<td>
				<c:out value="${advertising.creatorName}"/>
			</td>
		</tr>
		<tr>
			<td align="right">创建时间：</td>
			
			<td>
				<fmt:formatDate value="${advertising.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
			</td>
		</tr>
		<tr>
			<td align="right">显示位置：</td>
			<td>
				<c:if test="${advertising.type==1}">app护士端</c:if>
				<c:if test="${advertising.type==2}">app用户端</c:if>
				<c:if test="${advertising.type==3}">官网首页</c:if>
				<c:if test="${advertising.type==4}">app资讯</c:if>
			</td>
		</tr>
	</table>
</div>