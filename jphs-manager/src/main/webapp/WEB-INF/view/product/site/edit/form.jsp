<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<%
	int i = 1;
%>
<script type="text/javascript">
	var data = ${data };
</script>
<form class="form-horizontal">
	<fieldset>
		<input type="hidden" id="id" name="id" value="${site.id}" />
		<input type="hidden" value="0" name="status" />
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">名称：</label>
			<div class="controls col-md-6">
				<input type="text" placeholder="站点名称" id="name" name="name" value="${site.name }" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">服务器地址：</label>
			<div class="controls col-md-6">
				<input type="text" placeholder="服务器地址" id="url" name="url"  value="${site.url }"  class="form-control">
			</div>
		</div>

		<%-- <div class="form-group">
			<label class="control-label col-md-3">状态</label>
			<div class="controls  col-md-6">
				<!-- 单行单选项目 -->
				<label class="radio radio-inline"> <c:if
						test="${site.status == 0}">
						<input type="radio" value="0" name="status" checked="checked">
					</c:if> <c:if test="${site.status != 0}">
						<input type="radio" value="0" name="status">
					</c:if> 开启
				</label> <label class="radio radio-inline"> <c:if
						test="${site.status == -1}">
						<input type="radio" value="-1" name="status" checked="checked">
					</c:if> <c:if test="${site.status != -1}">
						<input type="radio" value="-1" name="status">
					</c:if> 关闭
				</label>
			</div>
		</div> --%>

		<%-- <div class="form-group">
			<label class="control-label col-md-3" for="input01">所属区域：</label>
			<div data-toggle="distpicker">
				<div class="form-group nurse_width" style="width: 18%; margin-left: 14px;">
					<label class="sr-only" for="province1">Province</label>
					<select class="form-control" id="province1" name="locationId" style="width: 80%;">
						<option selected="selected">${site.locationId }</option>
					</select>
				</div>
				<div class="form-group nurse_width" style="width: 20%;">
					<label class="sr-only" for="city1">City</label>
					<select class="form-control" id="city1" name="locationId" style="width: 80%"></select>
				</div>
			</div>
		</div> --%>
		<div class="form-group">
			<label class="control-label col-md-3">服务区域：</label>
			<div class="controls  col-md-6">
				<ul id="tt" class="easyui-tree edit_tree" checkbox="true"
					data-options="animate:true"
					style="margin-top: 10px; height: 540px; overflow-y: auto;">
				</ul>
			</div>
			<input type="hidden" id="areas" name="areas">
		</div>
		<div class="form-group">
			<!-- 文本区域 -->
			<label class="control-label col-md-3">备注：</label>
			<div class="controls col-md-6">
				<div class="textarea">
					<textarea type="" id="remark" name="remark" class="form-control">${site.remark }</textarea>
				</div>
			</div>
		</div>
		<div class="clearfix">
			<c:forEach items="${listProduct }" var="productOne" varStatus="status">
				<button type="button" class="public-info public_btn public_btn_left" onclick="tr_product('${status.index}');" >${productOne.title }</button>
			</c:forEach>
		</div>
		<div class="marage_right_content"  style="margin-top:0;padding-top:0">
			<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
				<tbody>
					<tr >
						<td width="30px">序号</td>
						<td width="100px">服务名称</td>
						<td width="60px">服务等级</td>
						<td width="60px">服务等级名称</td>
						<td width="60px">服务次数</td>
						<td width="60px">服务套餐名称</td>
						<td width="60px" >成本价</td>
						<td width="60px" >销售价格</td>
						<td width="60px" >原价</td>
						<td width="60px" >利润</td>
						<td width="60px" >派单模式</td>
					</tr>
					<c:forEach items="${listProduct }" var="productOne" varStatus="productStatus">
						<c:if test="${productStatus.index == 0 }">
							<c:forEach var="goodsOne" items="${productOne.goodsList }" varStatus="goodsStatus">
								<c:forEach var="priceOne" items="${goodsOne.priceList }" varStatus="priceStatus">
									<tr class="price${productStatus.index }">
										<td>
											<input type="hidden" name="productList[${productStatus.index }].goodsList[${goodsStatus.index }].priceList[${priceStatus.index }].pricePart.id" value="${priceOne.pricePart.id }"/>
											<input type="hidden" name="productList[${productStatus.index }].goodsList[${goodsStatus.index }].priceList[${priceStatus.index }].pricePart.priceId" value="${priceOne.id }"/>
											<input type="hidden" name="productList[${productStatus.index }].goodsList[${goodsStatus.index }].priceList[${priceStatus.index }].pricePart.siteId" value="${priceOne.pricePart.siteId }"/>
											<input type="hidden" name="productList[${productStatus.index }].goodsList[${goodsStatus.index }].priceList[${priceStatus.index }].pricePart.status" value="${priceOne.status }"/>
											<%=i++%>
										</td>
										<td>${goodsOne.title }</td>
										<td>${priceOne.grade }
										<td>${priceOne.gradeName }
											<%-- <c:if test="${priceOne.grade == 0 }">
												初级
											</c:if>
											<c:if test="${priceOne.grade == 1 }">
												中级
											</c:if>
											<c:if test="${priceOne.grade == 2 }">
												高级
											</c:if> --%>
										</td>
										<td>${priceOne.serviceNumber }</td>
										<td>${priceOne.title }</td>
										<td><input style="width:100%" class="form-control" readonly="readonly" name="productList[${productStatus.index }].goodsList[${goodsStatus.index }].priceList[${priceStatus.index }].pricePart.costPrice"  value="${priceOne.pricePart.costPrice }" /></td>
										<td><input style="width:100%" class="form-control" name="productList[${productStatus.index }].goodsList[${goodsStatus.index }].priceList[${priceStatus.index }].pricePart.price"  value="${priceOne.pricePart.price }" 
											onchange="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.price }';}if(${priceOne.pricePart.costPrice } > this.value){alert('销售价格必须大于成本价格');this.value='${priceOne.pricePart.price }';}" /></td>
										<td><input style="width:100%" class="form-control" name="productList[${productStatus.index }].goodsList[${goodsStatus.index }].priceList[${priceStatus.index }].pricePart.oldPrice"  value="${priceOne.pricePart.oldPrice }" 
											onchange="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.price }';}if(${priceOne.pricePart.costPrice } > this.value){alert('原价价格必须大于成本价格');this.value='${priceOne.pricePart.oldPrice }';}" /></td>
										<td><input style="width:100%" class="form-control" name="productList[${productStatus.index }].goodsList[${goodsStatus.index }].priceList[${priceStatus.index }].pricePart.profit" value="${priceOne.pricePart.profit }" 
											onchange="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.price }';}if(${priceOne.pricePart.profit } > this.value){alert('站点利润必须大于/等于原利润');this.value='${priceOne.pricePart.profit }';}" /></td>
										<td><c:if test="${goodsOne.type == 0 }">
												内部订单
											</c:if>
											<c:if test="${goodsOne.type == 1 }">
												抢单
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:forEach>
						</c:if>
						<c:if test="${productStatus.index != 0 }">
							<c:forEach var="goodsOne" items="${productOne.goodsList }" varStatus="goodsStatus">
								<c:forEach var="priceOne" items="${goodsOne.priceList }" varStatus="priceStatus">
									<tr class="price${productStatus.index }" style="display: none;">
										<td>
											<input type="hidden" name="productList[${productStatus.index }].goodsList[${goodsStatus.index }].priceList[${priceStatus.index }].pricePart.id" value="${priceOne.pricePart.id }"/>
											<input type="hidden" name="productList[${productStatus.index }].goodsList[${goodsStatus.index }].priceList[${priceStatus.index }].pricePart.siteId" value="${priceOne.pricePart.siteId }"/>
											<input type="hidden" name="productList[${productStatus.index }].goodsList[${goodsStatus.index }].priceList[${priceStatus.index }].pricePart.priceId" value="${priceOne.id }"/>
											<input type="hidden" name="productList[${productStatus.index }].goodsList[${goodsStatus.index }].priceList[${priceStatus.index }].pricePart.status" value="${priceOne.pricePart.status }"/>
											<%=i++%>
										</td>
										<td>${goodsOne.title }</td>
										<td>${priceOne.grade }</td>
										<td>${priceOne.gradeName }
										<td>${priceOne.serviceNumber }</td>
										<td>${priceOne.title }</td>
										<td><input style="width:100%" class="form-control" readonly="readonly" name="productList[${productStatus.index }].goodsList[${goodsStatus.index }].priceList[${priceStatus.index }].pricePart.costPrice"  value="${priceOne.pricePart.costPrice }" /></td>
										<td>
											<input style="width:100%" class="form-control" name="productList[${productStatus.index }].goodsList[${goodsStatus.index }].priceList[${priceStatus.index }].pricePart.price"  value="${priceOne.pricePart.price }" 
												onchange="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.price }';}if(${priceOne.pricePart.costPrice } > this.value){alert('销售价格必须大于成本价格');this.value='${priceOne.pricePart.price }';}" /></td>
										<td>
											<input style="width:100%" class="form-control" name="productList[${productStatus.index }].goodsList[${goodsStatus.index }].priceList[${priceStatus.index }].pricePart.oldPrice"  value="${priceOne.pricePart.oldPrice }" 
												onchange="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.price }';}if(${priceOne.pricePart.costPrice } > this.value){alert('原价价格必须大于成本价格');this.value='${priceOne.pricePart.oldPrice }';}" /></td>
										<td>
											<input style="width:100%" class="form-control" name="productList[${productStatus.index }].goodsList[${goodsStatus.index }].priceList[${priceStatus.index }].pricePart.profit" value="${priceOne.pricePart.profit }" 
												onchange="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.price }';}if(${priceOne.pricePart.profit } > this.value){alert('站点利润必须大于/等于原利润');this.value='${priceOne.pricePart.profit }';}" /></td>
										<td>
											<c:if test="${goodsOne.type == 0 }">
												内部订单
											</c:if>
											<c:if test="${goodsOne.type == 1 }">
												抢单
											</c:if>
											<%-- <input style="width:100%" class="form-control" readonly="readonly" value="${goodsOne.type }" /> --%>
										</td>
									</tr>
								</c:forEach>
							</c:forEach>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</fieldset>
</form>