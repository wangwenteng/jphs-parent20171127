<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0"
	class="text-center">
	<form id="serach-form" action="/voucher/addUser.jhtml">
		
		<div class="form-group">
			<label class="control-label col-md-4" for="input01">请输入昵称 ：</label>
			<div class="controls col-md-6">
				<input type="text" class="form-control" name="name" id="name" value="${user.name }">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-4" for="input01">请输入电话 ：</label>
			<div class="controls col-md-6">
				<input type="text" class="form-control" name="phone" id="phone" value="${user.phone }">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-4" for="input01"></label>
			<div class="controls col-md-6">
				<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
			</div>
		</div>
	</form>
	
	<input id="voucherRepertoryId" name="voucherRepertoryId" value="${voucherRepertoryId}" style="display: none" >
	<input id="voucherId" name="voucherId" value="${voucherId}"  style="display: none" >
	<thead>
		<tr>
			<th>编号</th>
			<th>昵称</th>
			<th>电话</th>
			<th>选择</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(userList) >0}">
				<c:forEach items="${userList}" var="e" varStatus="s">
					<tr class="bg_list_body">
					 	<td>${s.index+1}</td>
					 	<td>${e.name}</td>
					 	<td>${e.phone}</td>
					 	<td><a onclick="choice(${e.id})">选择此人</a></td></td>
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