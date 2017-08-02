<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/voucher/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>批次编号：</td>
			<td width="200">
				<input type="text" name="batchNo" id="batchNo" value="${voucher.batchNo}" />
			</td>
			<td>支持 品类 ：</td>
			<td width="200">
			
				<select class="form-control input-xlarge" id="productId"
					name="productId" value="" onchange="change(this.options[this.options.selectedIndex].value)">
					<option >请选择</option>
				
					<c:forEach items="${productList}" var="e" varStatus="s">
						<option value="${e.id }"
						<c:if test="${voucher.productId==e.id}">selected="selected"</c:if>>${e.title }</option>
					</c:forEach>
				</select>
				
			<select class="form-control input-xlarge" id="goodsId"
					name="goodsId" >
					<option value="" >请选择</option>
					<c:forEach items="${goodsList}" var="e" varStatus="s">
						<option value="${e.id }"
						<c:if test="${voucher.goodsId==e.id}">selected="selected"</c:if>>${e.title }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>当前状态：</td>
			<td width="200">
				<select class="form-control input-xlarge" id="status"
					name="status">
					<option value="">请选择</option>
					<option value="0"
						<c:if test="${voucher.status==0}">selected="selected"</c:if>>生效</option>
					<option value="-1"
						<c:if test="${voucher.status==-1}">selected="selected"</c:if>>暂停</option>
				</select>
			</td>
				<td>优惠券类型：</td>
			<td width="200">
				<select class="form-control input-xlarge" id="type"
					name="type">
					<option value="">请选择</option>
					<option value="1"
						<c:if test="${voucher.batchNo==1}">selected="selected"</c:if>>现金券</option>
					<option value="2"
						<c:if test="${voucher.batchNo==2}">selected="selected"</c:if>>满减券</option>
					<option value="3"
						<c:if test="${voucher.batchNo==3}">selected="selected"</c:if>>折扣券</option>
				</select>
			</td>
		</tr>
		<tr>
			
			 <td>注册时间：</td>
				<td width="200"><div class="form-group">
						<div style="width: 120%;"
							class="input-group date form_date col-md-3" data-date=""
							data-date-format="yyyy-mm-dd" data-link-field="workYears"
							data-link-format="yyyy-mm-dd">
							<input class="form-control" id="beginTime"
								name="beginTime"
								value="<fmt:formatDate value="${voucher.beginTime }" type="both" pattern="yyyy-MM-dd" />"
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
								value="<fmt:formatDate value="${voucher.stopTime }" type="both" pattern="yyyy-MM-dd" />"
								size="14" placeholder="请选择注册时间" type="text" value="" readonly>
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

