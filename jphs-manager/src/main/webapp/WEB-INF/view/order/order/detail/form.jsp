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
			<span>订单号：</span> ${order.orderNo }
		</p>
		<p>
			<span>下单区域：</span>24
		</p>
		<p>
			<span>服务名称：</span>${og.title }</p>
		<p>
			<span>服务次数：</span>${count	 }
		</p>
		<p>
			<span>下单时间：</span>
			<fmt:formatDate value="${order.createTime }"
				pattern="yyyy-MM-dd  HH:mm:ss" />
		</p>
		<p>
			<span>预约服务时间：</span>
			<fmt:formatDate value="${order.appointmentTime }"
				pattern="yyyy-MM-dd  HH:mm:ss" />
		</p>
		<p>
			<span>接单类型：</span>
		</p>
		<p>
			<span>服务地址：</span>${orderOther.address },${orderOther.detailAddress }
		</p>
		<p>
			<span>备注：</span>${order.remarks }
		</p>
		<p>
			<span>订单来源：</span>
			<c:if test="${order.device ==1 }">IOS</c:if>
			<c:if test="${order.device ==2 }">安卓</c:if>
			<c:if test="${order.device ==3 }">微信</c:if>
			<c:if test="${order.device ==4 }">114等网站</c:if>
			<c:if test="${order.device ==5 }">后台</c:if>
		</p>
	</div>
	<div class="col-md-6">
		<p>
			<span>用户信息：</span>
		</p>
		<p>
			<span>用户名称：</span>${user.name }</p>
		<p>
			<span>用户联系电话：</span>${user.phone }</p>
		<p>
			<span>用户性别：</span>
			<c:if test="${user.sex==0 }">男</c:if>
			<c:if test="${user.sex==1 }">女</c:if>
		</p>

		<p>
			<span> 患者姓名：</span>${orderService.patientName }
		</p>
		<p>
			<span>患者联系电话：</span>${orderService.patientPhone }
		</p>
		<p>
			<span> </span>
		</p>
		<p>
			<span>接单人信息：</span>
		</p>
		<p>
			<span>接单人名称：</span>${nurse.name }</p>
		<p>
			<span>接单时间：</span>
			<fmt:formatDate value="${order.acceptTime }"
				pattern="yyyy-mm-dd  HH:mm:ss" />
		</p>
		<p>
			<span>接单人联系电话：</span>${nurse.phone }</p>
	</div>
</div>
<div class="details_box clearfix">
	<p class="details_box_xinxi">服务信息</p>
	<div class="col-md-6">
		<table style="width: 800px; height: 100px; text-align: center;">
			<th>序号</th>
			<th>患者姓名</th>
			<th>患者电话</th>
			<th>服务开始时间</th>
			<th>护士确认结束时间</th>
			<th>用户确认结束时间</th>
			<th>功能服务者</th>
			<th>状态</th>
			<c:choose>
				<c:when test="${fn:length(orderServiceList) >0}">
					<c:forEach items="${orderServiceList}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td>${e.patientName}</td>
							<td>${e.patientPhone}</td>
							<td><fmt:formatDate value="${e.startServiceTime}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${e.endServiceTime}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${e.remindTime}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${e.nurseName }</td>
							<td><c:if test="${e.schedule==1 }">未执行</c:if> <c:if
									test="${e.schedule==2 }">执行中</c:if> <c:if
									test="${e.schedule==3 }">待确定</c:if> <c:if
									test="${e.schedule==4 }">已完成</c:if></td>
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
<div class="details_box clearfix">
	<p class="details_box_xinxi">交易信息</p>
	<div class="col-md-6">
		<p>
			<span>订单金额：</span>${og.price }<span>实际支付：</span>${og.payPrice }
		</p>
		<p>
			<span>支付时间：</span>
			<fmt:formatDate value="${og.createTime }"
				pattern="yyyy-mm-dd  HH:mm:ss" />
		</p>
		<p>
			<span>优惠券编号：</span>${order.voucherUseId } <span>优惠券面额：</span>
			${order.voucherPrice } <span>优惠金额：</span>
				<c:if test="${og.price - og.payPrice <0 }">未使用优惠券</c:if>
				<c:if test="${og.price - og.payPrice >=0 }">${og.price - og.payPrice }</c:if>
		</p>
	</div>

</div>
<div class="details_box clearfix">
	<p class="details_box_xinxi">保险信息</p>
	<div class="col-md-6">
		<p>
			<span>保险公司：</span>
		</p>
		<p>
			<span>被保人姓名：</span>
			<c:if test="${insurance.name==null }">未申请保险</c:if>
			<c:if test="${insurance.name!=null }">${insurance.name }</c:if>
		</p>
		<p>
			<span>被保人身份证号：</span>
			<c:if test="${insurance.sfz==null }">暂无相关信息</c:if>
			<c:if test="${insurance.sfz!=null }">${insurance.sfz }</c:if>
		</p>
	</div>

</div>
<div class="details_box clearfix">
	<p class="details_box_xinxi">退款信息</p>
	<div class="col-md-6">
		<table style="width: 600px;">
			<th>退款方式</th>
			<th>退款金额</th>
			<th>退款状态</th>
			<th>退款时间</th>
			<th>退款备注</th>
			<tr style="text-align: center;">
				<td>${refund.payType }</td>
				<td>${refund.amount }</td>
				<td>
					<c:if test="${refund.remark!=null }">已完成</c:if>
				</td>
				<td><fmt:formatDate value="${refund.createTime}"
						pattern="yyyy-mm-dd  HH:mm:ss" /></td>
				<td>${refund.remark }</td>
			</tr>
		</table>
	</div>
</div>
<div class="details_box clearfix">
	<p class="details_box_xinxi">评价信息</p>
	<div class="col-md-6">
		<p>
			<span>评价人：${user.name }</span><span>被评价人：</span>${nurse.name }</p>
		<p>
			<span>星级：</span>${evaluation.level }
		</p>
		<p>
			<span>用户获得分值：</span> ${transactionUser.score } <span>护士获得分值：</span>
			${transactionNurse.score }
		</p>
		<p>
			<span>评价内容：</span>${evaluation.content }
		</p>
		<p>
			<span>评价时间：</span>
			<fmt:formatDate value="${evaluation.createTime}"
				pattern="yyyy-MM-dd  HH:mm:ss" />
		</p>
	</div>
</div>
</div>




<!-- 模态框（Modal） -->
<div class="modal fade" id="phoneModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">订单信息</h4>
			</div>
			<form action="/order/updatePatientName.jhtml" method="get">
				<div style="width: 500px; height: 50px">
					<input type="hidden" id="creatorId" name="creatorId" value="" /> <input
						type="hidden" id="creatorName" name="creatorName" value="" /> <label
						class='col-lg-3 col-md-3 col-sm-3 col-xs-3 control-label'></label>
					<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8' id='remark'>
						<p>
							<span>订单编号：</span>${order.orderNo} <input type="hidden" id="orderId"
								name="orderId" value="${order.orderNo }">
								<input type="hidden" id="id"
								name="id" value="${order.id }">
						</p>
						<p>
							患者电话：<input id="patientPhone" name="patientPhone">
						</p>
					</div>
				</div>
				<div class="modal-footer mt-50 text-center" style="border-top:0;width:150px;margin:0 auto">
					<button type="button" class="btn btn-default" data-dismiss="modal" >取消
					</button>
					<button type="submit" class="btn btn-primary" >提交</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>





<!-- 模态框（Modal） -->
<div class="modal fade" id="timeModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">订单信息</h4>
			</div>
			<form action="/order/updateAppointmentTime.jhtml" method="post">
				<div style="width: 500px; height: 50px">
					<input type="hidden" id="creatorId" name="creatorId" value="" /> <input
						type="hidden" id="creatorName" name="creatorName" value="" /> <label
						class='col-lg-3 col-md-3 col-sm-3 col-xs-3 control-label'></label>
					<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8' id='remark'>
						<p>
							<span>订单编号：</span>${order.orderNo} <input type="hidden" id="orderId"
								name="orderId" value="${order.orderNo }">
								<input type="hidden" id="id"
								name="id" value="${order.id }">
						</p>
						<p>预约时间：</p>
						<div style="width: 100%;"
							class="input-group date form_date col-md-3" data-date=""
							data-date-format="yyyy-mm-dd" data-link-field="workYears"
							data-link-format="yyyy-mm-dd">
							<input class="form-control" id="appointmentTime"
								name="appointmentTime"
								value="<fmt:formatDate value="${order.appointmentTime }" type="both" pattern="yyyy-MM-dd HH:mm:ss" />"
								size="14" placeholder="请选择开始工作时间" type="text" value="" readonly>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span> <input
								type="hidden" name="workYears" id="workYears" value="" />
						</div>

					</div>
				</div>
				<div class="modal-footer mt-50 text-center" style="border-top:0;width:150px;margin:0 auto">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button type="submit" class="btn btn-primary">提交</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="addressModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">订单信息</h4>
			</div>
			<form action="/order/updateDetailAddress.jhtml" method="post">
				<div >
					<input type="hidden" id="creatorId" name="creatorId" value="" /> <input
						type="hidden" id="creatorName" name="creatorName" value="" /> <label
						class='col-lg-3 col-md-3 col-sm-3 col-xs-3 control-label'></label>
					<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8' id='remark'>
						<p>
							<span>订单编号：</span>${order.orderNo}<input type="hidden" id="orderId"
								name="orderId" value="${order.orderNo }">
								<input type="hidden" id="id"
								name="id" value="${order.id }">
						</p>
						请填写详细地址：
						<div data-toggle="distpicker">
							<div class="form-group nurse_width" style="width:30%">
								<label class="sr-only" for="province1">Province</label> <select
									class="form-control" id="province1" name="address"
									>
									<option selected="selected">${site.locationId }</option>
								</select>
								</div>
								<div class="form-group nurse_width" style="width: 40%;">
									<label class="sr-only" for="city1">City</label> <select
										class="form-control" id="city1" name="address"
										></select>
								</div>
								<div class="form-group nurse_width" style="width: 30%;">
									<label class="sr-only" for="district1">District</label> <select
										class="form-control" id="district1" name="address" ></select>
								</div>
							
						</div>
						<textarea rows="1" cols="" id="detailAddress" name="detailAddress" style="width:100%" class="mt-20"></textarea>
					</div>
				</div>
				<div class="modal-footer mt-50 text-center" style="border-top:0;width:150px;margin:0 auto">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button type="submit" class="btn btn-primary">提交</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>




<!-- 模态框（Modal） -->
<div class="modal fade" id="nurseModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">订单信息</h4>
			</div>
			<div style="width: 500px; height: 300px" id="timeModalData">
				<input type="hidden" id="creatorId" name="creatorId" value="" /> <input
					type="hidden" id="creatorName" name="creatorName" value="" /> <label
					class='col-lg-3 col-md-3 col-sm-3 col-xs-3 control-label'></label>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消
				</button>
				<button type="submit" class="btn btn-primary">提交</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>


<!-- 模态框（Modal） -->
<div class="modal fade" id="amountModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">订单信息</h4>
			</div>
			<form action="/order/refund.jhtml" method="post">
				<div style="width: 500px; height: 50px">
					<input type="hidden" id="creatorId" name="creatorId" value="" /> <input
						type="hidden" id="creatorName" name="creatorName" value="" /> <label
						class='col-lg-3 col-md-3 col-sm-3 col-xs-3 control-label'></label>
					<div class='col-lg-8 col-md-8 col-sm-8 col-xs-8' id='remark'>
						<p>
							<span>订单编号：</span>${order.orderNo} <<input type="hidden" id="orderId"
								name="orderId" value="${order.orderNo }">
								<input type="hidden" id="id"
								name="id" value="${order.id }">
						</p>
						<p>
							退款金额：<input id="amount" name="amount" placeholder="元">
							<input	id="operate" name="operate"  value="4" type="hidden">
						</p>
						<p>
							备注：
							<textarea rows="2" id="remark" name="remark" style="width:100%"  ></textarea>
						</p>
					</div>
				</div>
				<div class="modal-footer mt-50 text-center" style="border-top:0;width:150px;margin:0 auto">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button type="submit" class="btn btn-primary">提交</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
