<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div
	style="width: 465px; float: left; margin-left: 40px; border-right: 1px solid #e0e0e0; margin-right: 44px;">
	<table style="width: 400px" class="data_table text-center" style="width:100%;">
		<caption align="left">护士订单统计</caption>
		<thead>
			<tr>
				<th></th>
				<th>护士姓名</th>
				<th>地区</th>
				<th>订单数量</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(ordeList) >0}">
					<c:forEach items="${ordeList }" varStatus="s" var="e">
						<tr>
							<td width="30">${s.index+1}</td>
							<td><c:out value="${e.name }"></c:out></td>
							<td><c:out value="${e.address }"></c:out></td>
							<td><c:out value="${e.count }"></c:out></td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
			<tr>
				<td width="30">1</td>
				<td><c:out value="开始测试"></c:out></td>
				<td><c:out value="开始测试"></c:out></td>
				<td><c:out value="开始测试"></c:out></td>
			</tr>
			<tr>
				<td width="30">1</td>
				<td><c:out value="开始测试"></c:out></td>
				<td><c:out value="开始测试"></c:out></td>
				<td><c:out value="开始测试"></c:out></td>
			</tr>
			<tr>
				<td width="30">1</td>
				<td><c:out value="开始测试"></c:out></td>
				<td><c:out value="开始测试"></c:out></td>
				<td><c:out value="开始测试"></c:out></td>
			</tr>
		</tbody>
	</table>
</div>
<div style="width: 400; margin-left: 20px;">
	<table style="width: 400px" class="data_table text-center" style="width:100%;">
		<caption align="left">护士收入统计</caption>
		<thead>
			<tr>
				<th></th>
				<th>护士id</th>
				<th>姓名</th>
				<th>订单收入</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(incomeList) >0}">
					<c:forEach items="${incomeList }" varStatus="s" var="e">
						<tr>
							<td width="30">${s.index+1}</td>
							<td><c:out value="${e.creator_id }"></c:out></td>
							<td><c:out value="${e.creator_name }"></c:out></td>
							<td><c:out value="${e.income }"></c:out></td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
			<tr>
				<td width="30">1</td>
				<td><c:out value="开始测试"></c:out></td>
				<td><c:out value="开始测试"></c:out></td>
				<td><c:out value="开始测试"></c:out></td>
			</tr>
			<tr>
				<td width="30">1</td>
				<td><c:out value="开始测试"></c:out></td>
				<td><c:out value="开始测试"></c:out></td>
				<td><c:out value="开始测试"></c:out></td>
			</tr>
			<tr>
				<td width="30">1</td>
				<td><c:out value="开始测试"></c:out></td>
				<td><c:out value="开始测试"></c:out></td>
				<td><c:out value="开始测试"></c:out></td>
			</tr>
		</tbody>
	</table>
</div>
<%-- <table id="dateTable" cellpadding="0" cellspacing="0"
	class="data_table text-center" style="width:100%;">
	<thead>
		<tr>
			<th width="30"></th>
			<th>姓名</th>
			<th>联系电话</th>
			<th>年龄</th>
			<th>工龄</th>
			<th>在职医院</th>
			<th>所属科室</th>
			<th>注册时间</th>
			<th>状态</th>
			<th width="125">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td><c:out value="${e.user.name}" /></td>
						<td><c:out value="${e.user.phone}" /></td>
						<td><c:set var="idcard" value="${fn:substring(e.sfz,6,10) }" />
							<jsp:useBean id="nowDate" class="java.util.Date" /> <fmt:formatDate
								var="nowStr" value="${nowDate}" pattern="yyyy" /> <c:if
								test="${nowStr-idcard==0 }">1</c:if> <c:if
								test="${nowStr-idcard!=0 }">${nowStr-idcard}</c:if></td>
						<td><fmt:formatDate var="str" value="${e.workYears}"
								pattern="yyyy" /> <fmt:formatDate var="nowStr"
								value="${nowDate}" pattern="yyyy" /> <c:if
								test="${nowStr-str==0 }">1</c:if> <c:if test="${nowStr-str!=0 }">${nowStr-str}</c:if>
						</td>
						<td><c:out value="${e.hospital}" /></td>
						<td><c:forEach items="${department}" var="d">
								<c:if test="${e.departmentId==d.id}">${d.name }</c:if>
							</c:forEach></td>
						<td><fmt:formatDate value="${e.createTime}"
								pattern="yyyy-MM-dd" /></td>
						<td><c:if test="${e.status==1 }">已审核</c:if></td>
						<td><a onclick="redirectDetailPage('${e.id}')" title="详情">
								<img src="/static/images/chakan.png">
						</a> <a onclick="redirectUpdatePage('${e.id}')" title="编辑"> <img
								src="/static/images/xiugai.png">
						</a> <a onclick="deleteById('${e.id}')" title="删除"> <img
								src="/static/images/shanchu.png">
						</a></td>
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
</table> --%>
