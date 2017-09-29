<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>

<div class="details_box clearfix">

	<p class="details_box_xinxi">基本信息</p>
	<div class="col-md-6" style="border-right: 1px solid #e0e0e0;">
		<p>
			<span>订单信息：</span>
		</p>
		<p>
			<span>订单号：</span> ${commodityOrder.orderNo }
		</p>
		<p>
			<span>订单状态：</span>
			<c:if test="${commodityOrder.schedule == -3}">
								已取消
								</c:if>
			<c:if test="${commodityOrder.schedule == -2}">
								退货中
								</c:if>
			<c:if test="${commodityOrder.schedule == -1}">
								已取消
								</c:if>
			<c:if test="${commodityOrder.schedule == 0}">
								待支付
								</c:if>
			<c:if test="${commodityOrder.schedule == 1}">
								待发货
								</c:if>
			<c:if test="${commodityOrder.schedule == 2}">
								待收货
								</c:if>
			<c:if test="${commodityOrder.schedule == 4}">
								已完成
								</c:if>
			<c:if test="${commodityOrder.schedule == 5}">
								已删除订单
								</c:if>
		</p>
		<p>
			<span>订单金额：</span> ${commodityOrder.payPrice }
			</p><p>
			<span>实付金额：</span> ${commodityOrder.orderNo }
		</p>
		<p>
			<span>优惠券：</span> ${commodityOrder.voucherPrice }
			</p><p>
			<span>物流费：</span> 0.00
		</p>
		<p>
			<span>物流信息：</span> 
			<c:if test="${cl.no!=null }">
				${cl.name }
				：${cl.no }
				
			<a data-toggle="modal" data-target="#Modal"  >查看物流</a>
			</c:if>
		</p>
		<p>
			<span>支付方式：</span>
			  
			<c:if test="${transaction.payType==1 }">
				 支付宝
			 </c:if>
			 <c:if test="${transaction.payType==2 }">
				 微信
			 </c:if>
			 <c:if test="${transaction.payType==3 }">
				余额
			 </c:if>
			 <c:if test="${transaction.payType==4 }">
				银联
			 </c:if>
			<c:if test="${transaction.payType==5 }">
				VIP卡
			 </c:if>
			 
		</p>
		<p></p>
	</div>
	<div class="col-md-6">
		<p>
			<span>用户信息：</span>
		</p>
		<p>
			<span>姓名：</span>${user.name }</p>
		<p>
			<span>联系电话：</span>${user.phone }</p>
		<p>
			<span>收货人：</span>
			 ${commodityOrder.receiveName }
		</p>

		<p>
			<span> 收货地址：</span>${commodityOrder.address } ${commodityOrder.detailAddress }
		</p>
		<p>
			<span>收货人电话：</span>${commodityOrder.phone }
		</p>
		<p>
			<span>备注：</span>${remark }
		</p>
		<p>
			<span> </span>
		</p>
	</div>
</div>

<div class="details_box clearfix">
	<p>

		<span></span><span></span><span></span> <span>下单时间：</span>
		<fmt:formatDate value="${commodityOrder.createTime}"
							pattern="yyyy-MM-dd HH：mm" />
		<span></span><span></span><span></span><span></span>
		<span></span> <span>支付时间：</span>
		 	<fmt:formatDate value="${commodityOrder.payTime}"
					pattern="yyyy-MM-dd HH：mm" />
	</p>
	<p>
		<span></span><span></span><span></span> <span>提醒发货：</span>
		<fmt:formatDate value="${commodityOrder.remindTime}"
							pattern="yyyy-MM-dd HH：mm" />
		<span></span><span></span><span></span> <span></span><span></span><span></span><span></span>
		<span></span> <span>发货时间：</span>
		<fmt:formatDate value="${commodityOrder.sendTime}"
							pattern="yyyy-MM-dd HH：mm" />
	</p>
	<p>
		<span></span><span></span><span></span> <span>收货时间：</span>
		<fmt:formatDate value="${commodityOrder.takeTime}"
							pattern="yyyy-MM-dd HH：mm" />
		<span></span><span></span><span></span> <span></span><span></span><span></span><span></span>
		<span></span> <%-- <span>评价时间：</span>
		<fmt:formatDate value="${commodityOrder.sendTime}"
							pattern="yyyy-MM-dd HH：mm" /> --%>
	</p>
</div>


<div class="details_box clearfix">
	<p class="details_box_xinxi">商品信息</p>
	<div class="col-md-6">
		<table style="width: 800px; height: 100px; text-align: center;">
			<th>序号</th>
			<th>商品LOGO</th>
			<th>商品名称</th>
			<th>型号/规格</th>
			<th>商品SKU</th>
			<th>数量</th>
			<th>金额</th>
			<th>分销人</th>
			<th>分销金额</th>
			 <c:if test="${commodityOrder.schedule == -2}">
			<th width="250px">退款原因</th>
			<th>货品状态</th>
			</c:if>
			<c:choose>
				<c:when test="${fn:length(coiList) >0}">
					<c:forEach items="${coiList}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td><img alt="" height="50" width="50" id="aptitude_positives"
			src="${e.url}"></td>
							<td>${e.title}</td>
							<td>${e.commodityModel}</td>
							<td>${e.commodityPriceName}</td>
							<td>${e.number}</td>
							<td>${e.price }</td>
							<td>${e.userName }</td>
							<td>${e.profit }</td>
							 <c:if test="${commodityOrder.schedule == -2}">
							<td>${e.crReason }</td>
							<td>
								<c:if test="${e.crStatus == -2 }">
									正常
								</c:if>
								<c:if test="${e.crStatus == 2 }">
									退款
								</c:if>
								<c:if test="${e.crStatus == 1 }">
									<a onclick=updateStatus("${e.crId }","-2")>拒绝</a>
									<a onclick=updateStatus("${e.crId }","2")>同意</a>
								</c:if>
							</td>
							</c:if>
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
	</div>
</div>




<div>
	<div class="modal fade" id="Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">物流信息</h4>
			</div>
				
					<div style="text-align: center;">
						<div class="controls col-md-6"
							style="width: 150%; margin-left: 15px;">
						</div>
						<div >
							 <c:choose>
				<c:when test="${fn:length(LogisticsList) >0}">
					<c:forEach items="${LogisticsList}" var="e" varStatus="s">
						<div >
							 
							<span>${e.AcceptStation }</span>
							 <span>${e.AcceptTime }</span>
							  
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="20" align="center">没有可显示的记录。</td>
					</tr>
				</c:otherwise>
			</c:choose>
						</div>
						</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>