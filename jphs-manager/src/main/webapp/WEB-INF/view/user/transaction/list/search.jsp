<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form method="get" action="/transaction/index.jhtml" id="serach-form">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>昵称：</td>
			<td width="200">
				<input type="text" name="creatorName" id="creatorName" value="${transaction.creatorName}" />
			</td>
			<td>选择操作：</td>
			<td width="200">
				<select class="form-control input-xlarge" id="operate"
					name="operate">
					<option value="">请选择</option>
					<option value="2"
						<c:if test="${transaction.operate==2}">selected="selected"</c:if>>充值</option>
					<option value="3"
						<c:if test="${transaction.operate==3}">selected="selected"</c:if>>消费</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>交易号：</td>
			<td width="200">
				<input type="text" name="outTradeNo" id="outTradeNo" value="${transaction.outTradeNo}" />
			</td>
			<td>支付方式：</td>
			<td width="200">
				<select class="form-control input-xlarge" id="payType"
					name="payType">
					<option value="">请选择</option>
					<option value="1"
						<c:if test="${transaction.payType==1}">selected="selected"</c:if>>支付宝</option>
					<option value="2"
						<c:if test="${transaction.payType==2}">selected="selected"</c:if>>微信</option>
						<option value="3"
						<c:if test="${transaction.payType==3}">selected="selected"</c:if>>余额</option>
					<option value="4"
						<c:if test="${transaction.payType==4}">selected="selected"</c:if>>银联</option>
						<option value="5"
						<c:if test="${transaction.payType==5}">selected="selected"</c:if>>vip卡支付</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>订单编号：</td>
				<td width="200">
				<input type="text" name="orderId" id="orderId" value="${transaction.orderId}" />
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
								value="<fmt:formatDate value="${transaction.beginTime }" type="both" pattern="yyyy-MM-dd" />"
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
								value="<fmt:formatDate value="${transaction.stopTime }" type="both" pattern="yyyy-MM-dd" />"
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

