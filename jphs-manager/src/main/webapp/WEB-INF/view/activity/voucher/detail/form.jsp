<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form" id="serach-form"  action="/voucher/detail.jhtm">
	<div class="form-group">
		<label class="control-label col-md-5" for="input01">优惠券类型：</label>
		<div class="controls col-md-6">
			<c:if test="${voucher.type==1 }">现金券</c:if>
			<c:if test="${voucher.type==2 }">满减券</c:if>
			<c:if test="${voucher.type==3 }">折扣券</c:if>
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-md-5" for="input01">支持品类/服务：</label>
		<div class="controls col-md-6">${voucher.productName }${voucher.goodsName }</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-5" for="input01">批次号：</label>
		<div class="controls col-md-6">${voucher.batchNo}</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-5" for="input01">兑换开始时间：</label>
		<div class="controls col-md-6">
			<fmt:formatDate value="${voucher.startTime}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-md-5" for="input01">激活开始时间：</label>
		<div class="controls col-md-6">
			<fmt:formatDate value="${voucher.activationStartTime}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</div>
	</div>
		<div class="form-group">
		<label class="control-label col-md-5" for="input01">激活结束时间：</label>
		<div class="controls col-md-6">
			<fmt:formatDate value="${voucher.activationEndTime}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</div>
	</div>
		<div class="form-group">
		<label class="control-label col-md-5" for="input01">兑换结束时间：</label>
		<div class="controls col-md-6">
			<fmt:formatDate value="${voucher.endTime}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-md-5" for="input01">数量：</label>
		<div class="controls col-md-6">${count }</div>
	</div>

	<c:if test="${voucher.type==1 }">
		<div class="form-group">
			<label class="control-label col-md-5" for="input01">面值：</label>
			<div class="controls col-md-6">${voucher.amount }</div>
		</div>
	</c:if>
	<c:if test="${voucher.type==2 }">
		<div class="form-group">
			<label class="control-label col-md-5" for="input01">满减券：</label>
			<div class="controls col-md-6">消费满${voucher.conditionAmount }减${voucher.amount }
			</div>
		</div>
	</c:if>
	<c:if test="${voucher.type==3 }">
		<div class="form-group">
			<label class="control-label col-md-5" for="input01">折扣券：</label>
			<div class="controls col-md-6">打${voucher.amount }折</div>
		</div>
	</c:if>

	<div class="form-group">
		<label class="control-label col-md-5" for="input01">创建人：</label>
		<div class="controls col-md-6">${voucher.creatorName }</div>
	</div>

	<div class="form-group">
		<label class="control-label col-md-5" for="input01">创建时间：</label>
		<div class="controls col-md-6">
			<fmt:formatDate value="${voucher.createTime}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</div>
	</div>
	<div class="marage_right_content">
		<table id="dateTable" cellpadding="0" cellspacing="0"
			class="text-center">
			<tbody>
				<tr>
					<td width="30px">优惠券编号</td>
					<td width="60px">领取时间</td>
					<td width="60px">领取人</td>
					<td width="60px">发放人</td>
					<td width="60px">状态</td>
					<td width="60px">操作</td>
				</tr>
				<c:choose>
					<c:when test="${fn:length(list) >0}">
						<c:forEach items="${list}" var="e" varStatus="s">
							<tr>
								<td><%-- ${voucher.id} --%>${e.repertoryId }</td>
								<td><fmt:formatDate value="${e.createTime}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${e.creatorName}</td>
								<td>
									<c:if test="${e.grantName !=null}">${e.grantName}</c:if>
									<c:if test="${e.status ==1}">
										<c:if test="${e.grantName ==null}">本人领取</c:if>
									</c:if>
								</td>
								<td>
									<c:if test="${e.status ==1}">已领取</c:if> 
									<c:if test="${e.status ==0}">未领取</c:if>
								</td>
								<td>
									<c:if test="${e.status ==0}">
										<jphs:hasPermission url="/voucher/addUser.jhtml">
											<a onclick="showDiv('${e.repertoryId }')">操作</a>
										</jphs:hasPermission>
									</c:if>
								</td>
							<tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</tbody>
		</table>
		<div class="page">
			<jphs:page pageInfos="${pageInfo}" ></jphs:page>	
		</div>
	</div>
</form>

