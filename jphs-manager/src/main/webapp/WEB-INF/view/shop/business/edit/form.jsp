<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">商家名称：</label>
			<div class="controls col-md-6">
				<input value="${business.name}" id="name" name="name" type="text"
					class="inputText" data-validation-engine="validate[maxSize[255]]"></input>
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">官网地址：</label>
			<div class="controls col-md-6">
				<input value="${business.officialWebsiteUrl}"
					id="officialWebsiteUrl" name="officialWebsiteUrl" type="text"
					class="inputText" data-validation-engine="validate[maxSize[255]]"></input>
			</div>
		</div>


		<div class="form-group">
			<!-- 文本输入 -->
			
			
			<label class="control-label col-md-4" for="input01">商家地址：</label>
			<div class="controls col-md-6">
			
				<input value="${business.address}" id="address" name="address"
					type="text" class="inputText"
					data-validation-engine="validate[maxSize[255]]"></input>
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">联系人：</label>
			<div class="controls col-md-6">
				<input value="${business.contactsName}" id="contactsName"
					name="contactsName" type="text" class="inputText"
					data-validation-engine="validate[maxSize[50]]"></input>
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">联系电话：</label>
			<div class="controls col-md-6">
				<input value="${business.contactsPhone}" id="contactsPhone"
					name="contactsPhone" type="text" class="inputText"
					data-validation-engine="validate[maxSize[50]]"></input>
			</div>
		</div>



		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">结算周期：</label>
			<div class="controls col-md-6">
					
				<select class="marage_select" id="settlementCycle" name="settlementCycle">
					 
				 <option value="1"
					<c:if test="${business.settlementCycle == 1}">selected="selected"</c:if>>1</option>
					<option value="2"
					<c:if test="${business.settlementCycle == 2}">selected="selected"</c:if>>2</option>
					<option value="3"
					<c:if test="${business.settlementCycle == 3}">selected="selected"</c:if>>3</option>
					<option value="4"
					<c:if test="${business.settlementCycle == 4}">selected="selected"</c:if>>4</option>
					<option value="5"
					<c:if test="${business.settlementCycle == 5}">selected="selected"</c:if>>5</option>
					<option value="6"
					<c:if test="${business.settlementCycle == 6}">selected="selected"</c:if>>6</option>
					<option value="7"
					<c:if test="${business.settlementCycle == 7}">selected="selected"</c:if>>7</option>
					<option value="8"
					<c:if test="${business.settlementCycle == 8}">selected="selected"</c:if>>8</option>
					<option value="9"
					<c:if test="${business.settlementCycle == 9}">selected="selected"</c:if>>9</option>
				</select>	
				<select class="marage_select" id="unit" name="unit">
				<option value="日"
							<c:if test="${business.unit == 日}">selected="selected"</c:if>>日</option>
				<option value="周"
							<c:if test="${business.unit == 周}">selected="selected"</c:if>>周</option>
				<option value="月"
							<c:if test="${business.unit == 月}">selected="selected"</c:if>>月</option>
				<option value="季度"
							<c:if test="${business.unit == 季度}">selected="selected"</c:if>>季度</option>
				<option value="年"
							<c:if test="${business.unit == 年}">selected="selected"</c:if>>年</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">结算方式：</label>
			<div class="controls col-md-6">
				<select class="marage_select" id="payType" name="payType">
				<option value="银行卡"
							<c:if test="${business.payType == 银行卡}">selected="selected"</c:if>>银行卡</option>
				<option value="支付宝"
							<c:if test="${business.payType == 支付宝}">selected="selected"</c:if>>支付宝</option>
				<option value="微信"
							<c:if test="${business.payType == 微信}">selected="selected"</c:if>>微信</option>
				<option value="现金"
							<c:if test="${business.payType == 现金}">selected="selected"</c:if>>现金</option>
				<option value="其他"
							<c:if test="${business.payType == 其他}">selected="selected"</c:if>>其他</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">银行：</label>
			<div class="controls col-md-6">
				<input value="${business.bank}" id="bank" name="bank" type="text"
					class="inputText" data-validation-engine="validate[maxSize[255]]"></input>
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">开户行地址：</label>
			<div class="controls col-md-6">
				<input value="${business.openBankAddress}" id="openBankAddress"
					name="openBankAddress" type="text" class="inputText"
					data-validation-engine="validate[maxSize[255]]"></input>
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">卡号：</label>
			<div class="controls col-md-6">
				<input value="${business.cardNumber}" id="cardNumber"
					name="cardNumber" type="text" class="inputText"
					data-validation-engine="validate[maxSize[255]]"></input>
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">开卡人：</label>
			<div class="controls col-md-6">
				<input value="${business.cardName}" id="cardName" name="cardName"
					type="text" class="inputText"
					data-validation-engine="validate[maxSize[50]]"></input>
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">备注：</label>
			<div class="controls col-md-6">
				<input value="${business.remark}" id="remark" name="remark"
					type="text" class="inputText"
					data-validation-engine="validate[maxSize[500]]"></input>
			</div>
		</div>

	</fieldset>
</form>