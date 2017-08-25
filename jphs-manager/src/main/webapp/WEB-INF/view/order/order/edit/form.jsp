<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${order.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<form action="/order/redirectUpdate.jhtml?id=${order.id }"  method="post">
			<div  class="text-center">
				<div>
					<divtyle="margin: 20px;">护士姓名： 
					 <input type="text" id="user.name" name="user.name" value="${nurse.user.name }"></div>
				</div>
				<div  class="text-center">
					<div style="margin:20px 0 20px 0">手机号码： <input type="text" id="user.phone" name="user.phone" value=${nurse.user.phone }></div>
				</div>
				<div  class="text-center">
					<div >护士类型：
						<select  style="height: 30px;width: 180px" id="type" name="type">
							<option value="1"
								<c:if test="${nurse.type==1}">selected="selected"</c:if>>护士</option>
							<option value="2"
								<c:if test="${nurse.type==2}">selected="selected"</c:if>>康复师</option>
								<option value="3"
								<c:if test="${nurse.type==3}">selected="selected"</c:if>>母婴护理师</option>
						</select>
				</div>
				</div>
					<div class="text-center" style="margin: 5px;"><input type="submit"  type="button" class="public-info public_btn" data-role="search-btn" value="搜索"></div>
		</form>
		<form action="/order/insert.jhtml" method="post">
			<input type="hidden" id="id" name="id" value="${order.id }">
			<input type="hidden" id="schedule" name="schedule" value="1">
			<table id="orderTable" style="border: none" class="tableStyle">
				<tr>
					<td style="width: 10%;"></td>
					<td style="width: 20%;">姓名</td>
					<td style="width: 20%;">手机号</td>
					<td style="width: 50%;">类型</td>
				</tr>
				<c:choose>
				<c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">
							<input type="radio" id="acceptUserId" name="acceptUserId" value="${e.creatorId }"></td>
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
			</table>
					<div class="text-center"><button type="submit"  class="public-info public_btn" data-role="search-btn">确定</button></div>
		</form>
	</div>
</div>