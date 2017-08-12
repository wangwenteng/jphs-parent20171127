<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form method="get" action="/evaluation/index.jhtml" id="serach-form">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>订单编号：</td>
			<td width="200">
				<input type="text" name="orderId" id="orderId" value="${evaluation.orderId}" />
			</td>
				<td>用户昵称：</td>
			<td width="200">
				<input type="text" name="userName" id="userName" value="${evaluation.userName}" />
			</td>
		</tr>
		<tr>
		<td>用户编号：</td>
			<td width="200">
				<input type="text" name="creatorId" id="creatorId" value="${evaluation.creatorId}" />
			</td>
			<td>护士编号：</td>
			<td width="200">
				<input type="text" name="nurseId" id="nurseId" value="${evaluation.nurseId}" />
			</td>
		</tr>
		<%-- <tr>
			<td>创建时间：</td>
				<td width="200"><div class="form-group">
						<div style="width: 120%;"
							class="input-group date form_date1 col-md-3" data-date=""
							data-date-format="yyyy-mm-dd" data-link-field="workYears"
							data-link-format="yyyy-mm-dd">
							<input class="form-control" id="beginTime"
								name="beginTime"
								value="<fmt:formatDate value="${evaluation.beginTime }" type="both" pattern="yyyy-MM-dd" />"
								size="14" placeholder="请选择注册时间" type="text" value="" readonly>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span> 
						</div>
						</td><td>-
							</td><td>
						<div style="width: 100%;"
							class="input-group date form_date col-md-3" data-date=""
							data-date-format="yyyy-mm-dd" data-link-field="workYears"
							data-link-format="yyyy-mm-dd">
							<input class="form-control" id="stopTime"
								name="stopTime"
								value="<fmt:formatDate value="${evaluation.stopTime }" type="both" pattern="yyyy-MM-dd" />"
								size="14" placeholder="请选择注册时间" type="text" value="" readonly>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span> 
						</div>
					</div></td> 
		</tr> --%>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

