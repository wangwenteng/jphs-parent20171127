<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${commodityOrder.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${commodityOrder.id == null?'添加':'编辑'}</div>
		<table id="commodityOrderTable" class="tableStyle">
			<tr>
				<td align="right"width="100">：</td>
				<td>
					<input value="${commodityOrder.orderNo}" 
						id="orderNo"
						name="orderNo"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">：</td>
				<td>
					<input value="${commodityOrder.payPrice}" 
						id="payPrice"
						name="payPrice"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">：</td>
				<td>
					<input value="<fmt:formatDate value="${commodityOrder.payTime}" type="both" pattern="yyyy-MM-dd" />"
						id="payTime"
						name="payTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">发货时间：</td>
				<td>
					<input value="<fmt:formatDate value="${commodityOrder.sendTime}" type="both" pattern="yyyy-MM-dd" />"
						id="sendTime"
						name="sendTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">收货时间：</td>
				<td>
					<input value="<fmt:formatDate value="${commodityOrder.takeTime}" type="both" pattern="yyyy-MM-dd" />"
						id="takeTime"
						name="takeTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">完成时间：</td>
				<td>
					<input value="<fmt:formatDate value="${commodityOrder.confirmTime}" type="both" pattern="yyyy-MM-dd" />"
						id="confirmTime"
						name="confirmTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">提醒发货时间：</td>
				<td>
					<input value="<fmt:formatDate value="${commodityOrder.remindTime}" type="both" pattern="yyyy-MM-dd" />"
						id="remindTime"
						name="remindTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">：</td>
				<td>
					<input value="${commodityOrder.protectDay}" 
						id="protectDay"
						name="protectDay"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[integer]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">：</td>
				<td>
					<input value="<fmt:formatDate value="${commodityOrder.protectTime}" type="both" pattern="yyyy-MM-dd" />"
						id="protectTime"
						name="protectTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">：</td>
				<td>
					<input value="${commodityOrder.voucherUseId}" 
						id="voucherUseId"
						name="voucherUseId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">优惠券金额：</td>
				<td>
					<input value="${commodityOrder.voucherPrice}" 
						id="voucherPrice"
						name="voucherPrice"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">进度(-1已取消，0:待支付,1待发货,2运输中,3待评价,4已完成,5删除订单-2退货中,-3已取消,)：</td>
				<td>
					<input value="${commodityOrder.schedule}" 
						id="schedule"
						name="schedule"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[integer]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">下单设备/来源 pc=5 安卓=2 IOS =1 其他 =3：</td>
				<td>
					<input value="${commodityOrder.device}" 
						id="device"
						name="device"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[integer]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">平台id,下单平台：</td>
				<td>
					<input value="${commodityOrder.platformId}" 
						id="platformId"
						name="platformId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">省：</td>
				<td>
					<input value="${commodityOrder.address}" 
						id="address"
						name="address"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">详细地址：</td>
				<td>
					<input value="${commodityOrder.detailAddress}" 
						id="detailAddress"
						name="detailAddress"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">：</td>
				<td>
					<input value="${commodityOrder.phone}" 
						id="phone"
						name="phone"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">：</td>
				<td>
					<input value="${commodityOrder.receivename}" 
						id="receivename"
						name="receivename"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>