<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<form method="get" action="/order/redirectUpdate.jhtml" id="serach-form">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<input type="hidden" name="id" id="id" value="${order.id }"/>
			<td>姓名：</td>
			<td width="200">
				<input type="text" placeholder="请输入搜索护士姓名" name="user.name" id="user.name" value="${nurse.user.name}" />
			</td>
			<td>护士手机号：</td>
			<td width="200">
				<input type="text" placeholder="请输入搜索护士手机号" name="user.phone" id="user.phone" value="${nurse.user.phone}" />
			</td>
		</tr>
		<tr>
			<td>性别：</td>
			<td width="200">
				<select class="form-control input-xlarge" id="user.sex" name="user.sex">
					<option value="">全部</option>
					<option value="0" <c:if test="${nurse.user.sex==0}">selected="selected"</c:if>>男</option>
					<option value="1" <c:if test="${nurse.user.sex==1}">selected="selected"</c:if>>女</option>
				</select>
			</td>
			<td>服务时间：</td>
			<td width="200">
				<div class="form-group">
					<div style="width: 130%; margin-top: -23px; margin-bottom: -40px;"
						class="input-group date form_date col-md-3" data-date=""
						data-date-format="yyyy-mm-dd hh" data-link-field="workYears"
						data-link-format="yyyy-mm-dd hh">
						<input class="form-control" value="${nurse.workYear}" size="14"
							placeholder="请选择服务时间" type="text" readonly> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-remove" title="清空"></span></span> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-calendar" title="选择日期"></span></span> <input
							type="hidden" name="workYear" id="workYear"  />
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>状态：</td>
			<td width="200">
					<select class="form-control input-xlarge" id="status"
					name="status">
					<option value="" >全部</option>
					<option value="0"
						<c:if test="${nurse.status==0}">selected="selected"</c:if>>待审核</option>
					<option value="1"
						<c:if test="${nurse.status==1}">selected="selected"</c:if>>已审核</option>
				</select>
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>
