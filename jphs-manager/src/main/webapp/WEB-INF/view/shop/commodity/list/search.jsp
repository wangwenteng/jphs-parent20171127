<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/commodity/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>商品名称：</td>
			<td width="200">
				<input type="text" name="title" id="title" value="${commodity.title}" />
			</td>
			<td>所属商家：</td>
			<td width="300">
				<select class="marage_select" id="businessId"
					name="businessId">
					<option value="">全部</option>
					<c:forEach items="${businessList}" var="e" varStatus="s">
						<option value="${e.id }"
							<c:if test="${commodity.businessId==e.id}">selected="selected"</c:if>>${e.name }</option>
					</c:forEach>
				</select>
			</td>
			<td>商品类型：</td>
			<td width="200">
				<select class="marage_select" id="commodityTypeId"
					name="commodityTypeId">
					<option value="">全部</option>
					<c:forEach items="${commodityTypeList}" var="e" varStatus="s">
						<option value="${e.id }"
							<c:if test="${commodity.commodityTypeId==e.id}">selected="selected"</c:if>>${e.name }</option>
					</c:forEach>
				</select>
			</td>
			</tr>
			<tr>
			<td>型号/规格：</td>
			<td width="200">
				<input type="text" name="model" id="model" value="${commodity.model}" />
			</td>
			<td>状态：</td>
			<td width="200">
				<select class="marage_select" id="status"
					name="status">
					<option value="">全部</option>
						<option value="1"
							<c:if test="${commodity.status==1}">selected="selected"</c:if>>运营中</option>
							<option value="0"
							<c:if test="${commodity.status==0}">selected="selected"</c:if>>运营审核</option>
				 		<option value="-1"
							<c:if test="${commodity.status==-1}">selected="selected"</c:if>>暂停运营</option>
				</select>
			</td>
			
			</tr>
			<tr>
			<td>入库时间：</td>
			<td width="500">
				 <td width="200"><div class="form-group">
						<div style="width: 120%;"
							class="input-group date form_date col-md-3" data-date=""
							data-date-format="yyyy-mm-dd" data-link-field="workYears"
							data-link-format="yyyy-mm-dd">
							<input class="form-control" id="beginTime"
								name="beginTime"
								value="<fmt:formatDate value="${commodity.createTime }" type="both" pattern="yyyy-MM-dd" />"
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
								value="<fmt:formatDate value="${commodity.createTime }" type="both" pattern="yyyy-MM-dd" />"
								size="14" placeholder="请选择注册时间" type="text" value="" readonly>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span> 
						</div>
					</div></td> 
			</td>
			<%--<td>售卖时间：</td>
			<td width="200">
				<input type="text" name="quality" id="quality" value="${commodity.quality}" />
			</td>
		 	<td>库存：</td>
			<td width="200">
				<input type="text" name="privacy" id="privacy" value="${commodity.privacy}" />
			</td> --%>
			<%-- <td>销量：</td>
			<td width="200">
				<input type="text" name="security" id="security" value="${commodity.security}" />
			</td> --%>
		</tr>
		<tr>
			
			<td >
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
	
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>

