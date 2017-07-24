<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${orderGoods.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${orderGoods.id == null?'添加':'编辑'}</div>
		<table id="orderGoodsTable" class="tableStyle">
			<tr>
				<td align="right"width="100">订单id：</td>
				<td>
					<input value="${orderGoods.orderId}" 
						id="orderId"
						name="orderId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">商品id：</td>
				<td>
					<input value="${orderGoods.goodsId}" 
						id="goodsId"
						name="goodsId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">商品名称：</td>
				<td>
					<input value="${orderGoods.title}" 
						id="title"
						name="title"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">销售价：</td>
				<td>
					<input value="${orderGoods.price}" 
						id="price"
						name="price"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">实付金额：</td>
				<td>
					<input value="${orderGoods.payPrice}" 
						id="payPrice"
						name="payPrice"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">利润：</td>
				<td>
					<input value="${orderGoods.profit}" 
						id="profit"
						name="profit"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">指定人：</td>
				<td>
					<input value="${orderGoods.expectorId}" 
						id="expectorId"
						name="expectorId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">执行人：</td>
				<td>
					<input value="${orderGoods.executorId}" 
						id="executorId"
						name="executorId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">备注：</td>
				<td>
					<input value="${orderGoods.remark}" 
						id="remark"
						name="remark"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[500]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>