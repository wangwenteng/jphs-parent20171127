<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${userAddress.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${userAddress.id == null?'添加':'编辑'}</div>
		<table id="userAddressTable" class="tableStyle">
			<tr>
				<td align="right"width="100">标题地址：</td>
				<td>
					<input value="${userAddress.title}" 
						id="title"
						name="title"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[500]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">省：</td>
				<td>
					<input value="${userAddress.province}" 
						id="province"
						name="province"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[100]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">市：</td>
				<td>
					<input value="${userAddress.city}" 
						id="city"
						name="city"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[100]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">区（县）：</td>
				<td>
					<input value="${userAddress.area}" 
						id="area"
						name="area"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[100]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">详细地址：</td>
				<td>
					<input value="${userAddress.detailaddress}" 
						id="detailaddress"
						name="detailaddress"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[500]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>