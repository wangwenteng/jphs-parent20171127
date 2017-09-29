<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get"
	action="/commodity/order/index.jhtml">
	<div class="clearfix">
		<table class="text-right ">
			<tr>
				
				<td>订单号：</td>
				<td width="200"><input type="text" name="orderNo" id="orderNo"
					value="${commodityOrder.orderNo}" />
				<input type="hidden" id="flag" name="flag" value="${commodityOrder.flag}">
					</td>
				<td>下单人：</td>
				<td width="200"><input type="text" name="creatorName" id="creatorName"
					value="${commodityOrder.creatorName}" /></td>
				<td>状态：</td>
				<td width="200"><c:if test="${commodityOrder.flag ==1}"><select class="marage_select" id="schedule"
					name="schedule">
					
						<option value="" <c:if test="">selected="selected"</c:if>>选择状态</option>
						
						<option value="0"
							<c:if test="${commodityOrder.schedule == 0}">selected="selected"</c:if>>待支付</option>
						<option value="1"
							<c:if test="${commodityOrder.schedule == 1}">selected="selected"</c:if>>待发货</option>
						<option value="2"
							<c:if test="${commodityOrder.schedule == 2}">selected="selected"</c:if>>待收货</option>
						<%-- <option value="7"
					<c:if test="${commodityOrder.schedule == 3}">selected="selected"</c:if>>7</option> --%>
						<option value="4"
							<c:if test="${commodityOrder.schedule == 4}">selected="selected"</c:if>>已完成</option>
						<option value="5"
							<c:if test="${commodityOrder.schedule == 5}">selected="selected"</c:if>>已删除</option>
						<option value="-1"
							<c:if test="${commodityOrder.schedule == -1}">selected="selected"</c:if>>已取消</option>
				</select></c:if>
					
					<c:if test="${commodityOrder.flag ==2}">
						<select class="marage_select" id="crStatus"
					name="crStatus">
						<option value="1"
							<c:if test="${commodityOrder.crStatus == 1}">selected="selected"</c:if>>退款中</option>
						<option value="2"
							<c:if test="${commodityOrder.crStatus == 2}">selected="selected"</c:if>>已退款</option>
						<option value="-2"
							<c:if test="${commodityOrder.crStatus == -2}">selected="selected"</c:if>>已拒绝</option>
						 </select>
					</c:if>
					
				</td>
			</tr>
			<tr>
				<td>运单号：</td>
				<td width="200"><input type="text" name="no"
					id="no" value="${commodityOrder.no}" /></td>
				<td>商品名称：</td>
				<td width="200"><input type="text" name="title"
					id="title" value="${commodityOrder.title}" /></td>
			</tr>
			<tr>
				<td>下单时间：</td>
				<td width="200"><div class="form-group">
						<div style="width: 120%;"
							class="input-group date form_date col-md-3" data-date=""
							data-date-format="yyyy-mm-dd" data-link-field="workYears"
							data-link-format="yyyy-mm-dd">
							<input class="form-control" id="beginTime"
								name="beginTime"
								value="<fmt:formatDate value="${commodityOrder.beginTime }" type="both" pattern="yyyy-MM-dd" />"
								size="14" placeholder="请选择下单时间" type="text" value="" readonly>
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
								value="<fmt:formatDate value="${commodityOrder.stopTime }" type="both" pattern="yyyy-MM-dd" />"
								size="14" placeholder="请选择下单时间" type="text" value="" readonly>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span> 
						</div>
					</div></td> 
			</tr>
		</table>
		<div class="marage_search_btn">
			<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
			<button id="clear" class="search_btn">清空</button>
		</div>
	</div>
</form>

