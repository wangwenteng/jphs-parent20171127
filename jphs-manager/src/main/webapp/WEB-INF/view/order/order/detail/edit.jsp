<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="/order/toEdit.jhtml?id=${order.id }" id="toEdit" >
			<table id="orderTable" class="tableStyle">
				<tr>
					<td align="right" width="100">护士姓名：</td>
					<td><input type="text" id="status" name="status" value=""></td>
				</tr>
				<tr>
					<td align="right">手机号码：</td>
					<td><input type="text" id="user.phone" name="user.phone" value=${nurse.user.phone }></td>
				</tr>
				<tr>
					<td align="right">护士类型：</td>
					<td>
						<select class="form-control input-xlarge" id="type"
							name="type">
							<option value="1"
								<c:if test="${type==1}">selected="selected"</c:if>>护士</option>
							<option value="2"
								<c:if test="${type==2}">selected="selected"</c:if>>康复师</option>
								<option value="3"
								<c:if test="${type==3}">selected="selected"</c:if>>母婴护理师</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><input type="button" onclick="toSearch()" value="搜索"></td>
				</tr>
			</table>
		</form>
		<form action="/order/insert.jhtml" method="post">
			<input type="" id="id" name="id" value=" ">
			<input type="text" id="schedule" name="schedule" value="1">
			<table id="orderTable" class="tableStyle">
				<tr>
					<td></td>
					<td>姓名</td>
					<td>手机号</td>
					<td>类型</td>
				</tr>
				<c:choose>
				 
				 <c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30"><input type="radio" id="acceptUserId" name="acceptUserId" value="${e.id }"></td>
							<td>${e.user.name }</td>
							<td>${e.user.phone }</td>
							<td>
								<c:if test="${e.type==1 }">护士</c:if>
								<c:if test="${e.type==2 }">康复师</c:if>
								<c:if test="${e.type==3 }">母婴护理师</c:if>
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
				<tr>
					<td><button type="submit">确定</button></td>
				</tr>
			</table>
		</form>
 
</body>
</html>