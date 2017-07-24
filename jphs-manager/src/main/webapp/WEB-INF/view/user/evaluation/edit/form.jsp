<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${evaluation.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${evaluation.id == null?'添加':'编辑'}</div>
		<table id="evaluationTable" class="tableStyle">
			<tr>
				<td align="right"width="100">主订单id：</td>
				<td>
					<input value="${evaluation.orderId}" 
						id="orderId"
						name="orderId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">商品id：</td>
				<td>
					<input value="${evaluation.goodsId}" 
						id="goodsId"
						name="goodsId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">护士id：</td>
				<td>
					<input value="${evaluation.nurseId}" 
						id="nurseId"
						name="nurseId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">星级：</td>
				<td>
					<input value="${evaluation.level}" 
						id="level"
						name="level"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">内容：</td>
				<td>
					<textArea id="content"
						name="content"
						rows="12"
					 	data-validation-engine="validate[${validates}]">${fn:escapeXml(evaluation.content)}</textArea>
				</td>
			</tr>
		</table>
	</div>
</div>