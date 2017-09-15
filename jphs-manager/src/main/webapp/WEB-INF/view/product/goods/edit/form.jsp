<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<input type="hidden" id="id" name="id" value="${goods.id}" />
		<input type="hidden" id="moveid" name="moveid" value="${wap_image.id}" />
		<input type="hidden" id="pcid" name="pcid" value="${pc_image.id}" />
		<input type="hidden" value="0" name="status" />
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">服务名称：</label>
			<div class="controls col-md-6">
				<input type="text" id="title" name="title" maxlength="12" value="${goods.title}"
					placeholder="名称" class="form-control" />
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">服务简介：</label>
			<div class="controls col-md-6">				
				<textarea id="subTitle" name="subTitle" placeholder="服务简介" cols="" rows="6"
						class="form-control">${goods.subTitle}</textarea>
			</div>
		</div>

		<div class="form-group">
			<!-- 下拉列表 -->
			<label class="control-label col-md-3">服务品类：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" id="productId" name="productId">
					<c:forEach var="productone" items="${product_list }"
						varStatus="status">
						<c:if test="${productone.id == goods.productId }">
							<option value="${productone.id }" selected="selected">${productone.title }</option>
						</c:if>
						<c:if test="${productone.id != goods.productId }">
							<option value="${productone.id }">${productone.title }</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">是否需要服务工具：</label>
			<div class="controls col-md-6">
				<!-- 单行单选项目 -->
				<label class="radio radio-inline">
					<c:if test="${goods.dzTool == 1}">
						<input type="radio" value="1" name="dzTool" checked="checked">
					</c:if>
					<c:if test="${goods.dzTool != 1}">
						<input type="radio" value="1" name="dzTool" checked="checked">
					</c:if> 需要
				</label> <label class="radio radio-inline">
					<c:if test="${goods.dzTool == 0}">
						<input type="radio" value="0" name="dzTool" checked="checked">
					</c:if>
					<c:if test="${goods.dzTool != 0}">
						<input type="radio" value="0" name="dzTool">
					</c:if> 不需要
				</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">是否需要护理工具：</label>
			<div class="controls col-md-6">
				<!-- 单行单选项目 -->
				<label class="radio radio-inline">
					<c:if test="${goods.hlTool == 1}">
						<input type="radio" value="1" name="hlTool" checked="checked">
					</c:if>
					<c:if test="${goods.hlTool != 1}">
						<input type="radio" value="1" name="hlTool" checked="checked">
					</c:if> 需要
				</label> <label class="radio radio-inline">
					<c:if test="${goods.hlTool == 0}">
						<input type="radio" value="0" name="hlTool" checked="checked">
					</c:if>
					<c:if test="${goods.hlTool != 0}">
						<input type="radio" value="0" name="hlTool">
					</c:if> 不需要
				</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">是否需要用药记录：</label>
			<div class="controls col-md-6">
				<!-- 单行单选项目 -->
				<label class="radio radio-inline">
					<c:if test="${goods.isPz == 1}">
						<input type="radio" value="1" name="isPz" checked="checked">
					</c:if>
					<c:if test="${goods.isPz != 1}">
						<input type="radio" value="1" name="isPz" checked="checked">
					</c:if> 需要
				</label> <label class="radio radio-inline">
					<c:if test="${goods.isPz == 0}">
						<input type="radio" value="0" name="isPz" checked="checked">
					</c:if>
					<c:if test="${goods.isPz != 0}">
						<input type="radio" value="0" name="isPz">
					</c:if> 不需要
				</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">是否需要康复记录：</label>
			<div class="controls col-md-6">
				<!-- 单行单选项目 -->
				<label class="radio radio-inline">
					<c:if test="${goods.isRr == 1}">
						<input type="radio" value="1" name="isRr" checked="checked">
					</c:if>
					<c:if test="${goods.isRr != 1}">
						<input type="radio" value="1" name="isRr" checked="checked">
					</c:if> 需要
				</label> <label class="radio radio-inline">
					<c:if test="${goods.isRr == 0}">
						<input type="radio" value="0" name="isRr" checked="checked">
					</c:if>
					<c:if test="${goods.isRr != 0}">
						<input type="radio" value="0" name="isRr">
					</c:if> 不需要
				</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">是否需要上传就医证明：</label>
			<div class="controls col-md-6">
				<!-- 单行单选项目 -->
				<label class="radio radio-inline">
					<c:if test="${goods.isProve == 1}">
						<input type="radio" value="1" name="isProve" checked="checked">
					</c:if>
					<c:if test="${goods.isProve != 1}">
						<input type="radio" value="1" name="isProve">
					</c:if> 需要
				</label> <label class="radio radio-inline">
					<c:if test="${goods.isProve == 3}">
						<input type="radio" value="3" name="isProve" checked="checked">
					</c:if>
					<c:if test="${goods.isProve != 3}">
						<input type="radio" value="3" name="isProve">
					</c:if> 不需要
				</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">是否需要上保险：</label>
			<div class="controls col-md-6">
				<!-- 单行单选项目 -->
				<label class="radio radio-inline"> <c:if
						test="${goods.insurance == 1}">
						<input type="radio" value="1" name="insurance" checked="checked">
					</c:if> <c:if test="${goods.insurance != 1}">
						<input type="radio" value="1" name="insurance" checked="checked">
					</c:if> 需要
				</label> <label class="radio radio-inline"> <c:if
						test="${goods.insurance == 0}">
						<input type="radio" value="0" name="insurance" checked="checked">
					</c:if> <c:if test="${goods.insurance != 0}">
						<input type="radio" value="0" name="insurance">
					</c:if> 不需要
				</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">服务类型（内部|公开）：</label>
			<div class="controls col-md-6">
				<!-- 单行单选项目 -->
				<label class="radio radio-inline"> <c:if
						test="${goods.type == 0}">
						<input type="radio" value="0" name="type" checked="checked">
					</c:if> <c:if test="${goods.type != 0}">
						<input type="radio" value="0" name="type" checked="checked">
					</c:if>公开
				</label> <label class="radio radio-inline"> <c:if
						test="${goods.type == 1}">
						<input type="radio" value="1" name="type" checked="checked">
					</c:if> <c:if test="${goods.type != 1}">
						<input type="radio" value="1" name="type">
					</c:if>内部
				</label>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-3">是否强制推送该商品订单：</label>
			<div class="controls col-md-6">
				<!-- 单行单选项目 -->
				<label class="radio radio-inline"> <c:if
						test="${goods.must == 0}">
						<input type="radio" value="0" name="must" checked="checked">
					</c:if> <c:if test="${goods.must != 0}">
						<input type="radio" value="0" name="must" checked="checked">
					</c:if>非强制
				</label> <label class="radio radio-inline"> <c:if
						test="${goods.must == 1}">
						<input type="radio" value="1" name="must" checked="checked">
					</c:if> <c:if test="${goods.must != 1}">
						<input type="radio" value="1" name="must">
					</c:if>强制
				</label>
			</div>
		</div>

		<div class="form-group">
			<!-- 下拉列表 -->
			<label class="control-label col-md-3">排序：</label>
			<div class="controls col-md-6">

				<input type="text" id="sort" name="sort" placeholder="排序" class="form-control" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
					onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" value="${goods.sort}" />
				<%-- <select class="form-control input-xlarge" id="sort" name="sort" >
					<c:forEach var="temps" begin="1" step="1" end="30">
						<c:if test="${temps == goods.sort }">
							<option value="${goods.sort}" selected="selected">${goods.sort}</option>
						</c:if>
						<c:if test="${temps != goods.sort }">
							<option value="${temps}">${temps}</option>
						</c:if>
					</c:forEach>
				</select> --%>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-3">服务手机端logo：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
				<img alt="" height="200" width="200" id="moveurls" src="${wap_image.url}" /><span>尺寸  140*140 比例（1：1）<br>*&nbsp;图片格式必须为.png格式</span>
				<input class="input-file" type="file" name="myfiles" id="moveurl_s" onchange="ajaxFileUpload('moveurl_s','moveurl');" />
				<input class="input-file" type="hidden" id="moveurl" name="moveurl" value="${wap_image.url}" />
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-3">服务PC端图片：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
				<img alt="" height="200" width="200" id="pcurls" src="${pc_image.url}" /><span>尺寸 360*350 <br>*&nbsp;图片格式必须为.png格式 </span>
				<input class="input-file" type="file" name="myfiles" id="pcurl_s" onchange="ajaxFileUpload('pcurl_s','pcurl');" />
				<input class="input-file" type="hidden" id="pcurl" name="pcurl" value="${pc_image.url}" />
			</div>
		</div>
		<div class="public_editor">
			<h5>服务详情：</h5>
			<p>
				<textarea name="content" id="myEditor"  class="form-control" style="width:805px;height:500px;">${goods.content}</textarea>
			</p>
		</div>
		

		<div class="form-group">
			<!-- 文本区域 -->
			<label class="control-label col-md-3">备注：</label>
			<div class="controls col-md-6">
				<div class="textarea">
					<textarea id="remark" name="remark" class="form-control">${goods.remark}</textarea>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-3">服务等级类型：</label>
			<div class="controls  col-md-6">
			
			<c:if test="${goods.id == null}">
				<!-- 单行单选项目 -->
			 <label class="radio radio-inline norm_service_l">
					<c:if test="${goods.gradeType == 0}">
						<input type="radio" value="0" name="gradeType" checked="checked">
					</c:if>
					<c:if test="${goods.gradeType != 0}">
						<input type="radio" value="0" name="gradeType" checked="checked">
					</c:if> 标准服务
				</label>
				<label class="radio radio-inline grade_service_l" >
					<c:if test="${goods.gradeType == 1}">
						<input type="radio" value="1" name="gradeType" checked="checked">
					</c:if>
					<c:if test="${goods.gradeType != 1}">
						<input type="radio" value="1" name="gradeType">
					</c:if> 等级服务
				</label>
				</c:if>
				<c:if test="${goods.id != null}">
					<c:if test="${goods.gradeType == 0}">
						 标准服务
					</c:if>
					<c:if test="${goods.gradeType == 1}">
						等级服务
					</c:if> 
				</c:if>
			</div>
		</div>
		
		<c:choose>
			<c:when test="${fn:length(goods.priceGrade) >0}">
			<div class="norm_service" <%-- style="display: ${goods.gradeType == 0 || goods.gradeType == null ?'block':'none'};" --%> >
				<c:forEach var="priceOne_g" items="${goods.priceGrade }" varStatus="status_g">
					<div class="price_gradeAdd">
					<input type="hidden" id="priceGradeid${status_g.index }${status.index }" name="priceGrade[${status_g.index }].grade" value="${priceOne_g.grade }"/>
					<input type="hidden" id="priceGradeName${status_g.index }${status.index }" name="priceGrade[${status_g.index }].gradeName" value="${priceOne_g.gradeName }"/>
					<span class="price_add_title"><input style="width: 200px;" type="text" id="gradeName${status_g.index }" value="${priceOne_g.gradeName }" name="price[${status_g.index }].gradeName" /></span>
						<div class="marage_right_content"  style="margin-top:0;padding-top:0">
							<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
								<tbody id="addtrprice${status_g.index }">
									<tr class="headClass">
										<td width="100px">标--题</td>
										<td width="60px">服务次数</td>
										<!-- <td width="60px" >销售价格</td> -->
										<td width="60px" >成本价</td>
										<td width="60px" >最高价</td>
										<td width="60px" >利润</td>
										<td width="60px" >时长</td>
										<td width="60px" >单位</td>
										<td width="60px" >排序</td>
										<td width="60px" >授权</td>
										<td width="60px" >操作</td>
									</tr>
									<c:forEach var="priceOne" items="${priceOne_g.price }" varStatus="status">
											<input type="hidden" id="id${status_g.index }${status.index }" name="priceGrade[${status_g.index }].price[${status.index }].id" value="${priceOne.id }"/>
											<input type="hidden" id="id${status_g.index }${status.index }" name="priceGrade[${status_g.index }].price[${status.index }].pricePartId" value="${priceOne.pricePart.id }"/>
											<tr class="addClassprice" id="delete${status_g.index }${status.index }">
												<td>
													<input type="hidden" id="grade${status_g.index }${status.index }" name="priceGrade[${status_g.index }].price[${status.index }].grade" value="${priceOne.grade }"/>
													<input class="form-control" style="width:100%" id="title${status_g.index }${status.index }" name="priceGrade[${status_g.index }].price[${status.index }].title" value="${priceOne.title }" />
													<input type="hidden" id="status${status_g.index }${status.index }" name="priceGrade[${status_g.index }].price[${status.index }].status" value="${priceOne.status }"/>
												</td>
												<td><input style="width:100%" class="form-control" id="service_number${status_g.index }${status.index }" name="priceGrade[${status_g.index }].price[${status.index }].serviceNumber"  value="${priceOne.serviceNumber }" onkeyup="value=value.replace(/[^\d]/g,'')" /></td>
												<td><input style="width:100%" class="form-control" id="price${status_g.index }${status.index }" name="priceGrade[${status_g.index }].price[${status.index }].costPrice"  value="${priceOne.pricePart.costPrice }" onkeyup="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.costPrice }';}"  /></td>
												<td><input style="width:100%" class="form-control" id="price${status_g.index }${status.index }" name="priceGrade[${status_g.index }].price[${status.index }].maxPrice"  value="${priceOne.pricePart.maxPrice }" onkeyup="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.costPrice }';}"  /></td>
												<td><input style="width:100%" class="form-control" id="old_price${status_g.index }${status.index }" name="priceGrade[${status_g.index }].price[${status.index }].profit"  value="${priceOne.pricePart.profit }" onkeyup="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.profit }';}"  /></td>
												<%-- <td><input style="width:100%" class="form-control" id="profit${status.index }" name="price[${status.index }].profit"  ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.profit }';}"  /></td>
												 --%><td>
													<select style="width: 100%;" class="form-control input-xlarge" id="service_time${status_g.index }${status.index }" name="priceGrade[${status_g.index }].price[${status.index }].serviceTime">
														<c:forEach var="temps" begin="1" step="1" end="60">
															<c:if test="${priceOne.serviceTime == temps}">
																<option selected="selected">${temps }</option>
															</c:if>
															<c:if test="${priceOne.serviceTime != temps}">
																<option>${temps }</option>
															</c:if>
														</c:forEach>
													</select>
												</td>
												<td> <c:set var="arr" value="秒,分钟,小时,周,月,季,年" />
													<select style="width: 100%;" class="form-control input-xlarge" id="unit${status_g.index }${status.index }" name="priceGrade[${status_g.index }].price[${status.index }].unit">
														<c:forEach items="${fn:split(arr,',')}" var="addr" begin="0" end="${fn:length(fn:split(arr,','))}" varStatus="stat">
															<c:if test="${priceOne.unit == addr}">
																<option selected="selected">${addr }</option>
															</c:if>
															<c:if test="${priceOne.unit != addr}">
																<option>${addr }</option>
															</c:if>
														</c:forEach>
													</select>
												</td>
												<td>
													<select style="width: 100%;" class="form-control input-xlarge" id="sort${status_g.index }${status.index }" name="priceGrade[${status_g.index }].price[${status.index }].sort">
														<c:forEach var="temps" begin="1" step="1" end="60">
															<c:if test="${priceOne.sort == temps}">
																<option selected="selected">${temps }</option>
															</c:if>
															<c:if test="${priceOne.sort != temps}">
																<option>${temps }</option>
															</c:if>
														</c:forEach>
													</select>
												</td>
												<td>
													<%-- <button type="button" class="public-info public_btn public_btn_center" data-toggle="modal" onclick="setJobtitle('${priceOne.id }');" data-target="#myModal" >修改</button> --%>
												
													<img style="width: 20px;height: 20px;" src="https://jinpai.b0.upaiyun.com/jinpaihushi/JP20170802114112-25444.png" data-toggle="modal" onclick="setJobtitle('${priceOne.id }');" data-target="#myModal" />
													<input type="hidden" id="aptitudeIdArr${priceOne.id }" name="priceGrade[${status_g.index }].price[${status.index }].aptitudeIdArr" value="${priceOne.aptitudeIdArr }"/>
												</td>
												<td>
													<img style="width: 20px;height: 20px;" src="/static/images/shanchu.png"  id="delete${status_g.index }${status.index }"  onclick="deleteTr('${status_g.index }${status.index }');">
												<%-- <input type="button" style="width:100%" class="form-control" value="删除" id="delete${status_g.index }${status.index }"  onclick="deleteTr('${status_g.index }${status.index }');"  />
												 --%></td>
											</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="btn_add" onclick="addTd(${status_g.index });">+</div>
							</div>
						</div>
						</c:forEach>
			</div>
			</c:when>
			<c:otherwise>
			<div class="norm_service" <%-- style="display: ${goods.gradeType == 0 || goods.gradeType == null ?'block':'none'};" --%> >
			<div class="price_gradeAdd">
			<span class="price_add_title" style="margin-left: 10px;">	
				<table cellpadding="0" cellspacing="0" style="border:none;">
					<tr>
						<td width="80">级别名称：</td>
						<td><input type="text" id="priceGrade0" name="priceGrade[0].gradeName"  class="form-control"/></td>
					</tr>
				</table>	
				<input type="hidden" id="grade0" name="priceGrade[0].grade" value="0" />				
			</span>
				<div class="marage_right_content"  style="margin-top:0;padding-top:0">
					<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
						<tbody id="addtrprice0">
							<tr class="headClass">
								<td width="100">名称</td>
								<td width="60">服务次数</td>
								<!-- <td width="60px" >销售价格</td> -->
								<td width="60" >成本价</td>
								<td width="60" >利润</td>
								<td width="60" >时长</td>
								<td width="60" >单位</td>
								<td width="60" >排序</td>
								<td width="60" >授权</td>
								<td width="60" >操作</td>
							</tr>
						</tbody>
					</table>
					<div class="btn_add" onclick="addTd(0);">+</div>
					</div>
				</div>
			</div>
			</c:otherwise>
		</c:choose>
		<c:if test="${goods.gradeType != 0}">
			<div class="btn_add btn_addsss" style="display: ${goods.gradeType == 1?'block':'none'};">+</div>
		</c:if>
		<div style="display: none;">
		</div>
		<%-- <!-- 等级服务 -->
		<div class="grade_service" style="display: ${goods.gradeType == 1?'block':'none'};">
		<div id="price_add">
		<span class="price_add_title">初级</span>
		<div class="marage_right_content"  style="margin-top:0;padding-top:0">
			<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
				<tbody id="chuji">
					<tr class="headClass">
						<td width="100px">标题</td>
						<td width="60px">服务次数</td>
						<!-- <td width="60px" >销售价格</td> -->
						<td width="60px" >成本价</td>
						<td width="60px" >利润</td>
						<td width="60px" >时长</td>
						<td width="60px" >单位</td>
						<td width="60px" >排序</td>
						<td width="60px" >授权</td>
						<td width="200px" >备注</td>
						<td width="60px" >操作</td>
					</tr>
					<c:forEach var="priceOne" items="${goods.priceList }" varStatus="status">
						<c:if test="${priceOne.grade == 1 }">
							<input type="hidden" id="id${status.index }" name="price[${status.index }].id" value="${priceOne.id }"/>
							<input type="hidden" id="id${status.index }" name="price[${status.index }].pricePartId" value="${priceOne.pricePart.id }"/>
								<tr class="addClassprice" id="delete${status.index }">
								<td>
								<input type="hidden" id="grade${status.index }" name="price[${status.index }].grade" value="${priceOne.grade }"/>
									<input class="form-control" style="width:100%" id="title${status.index }" name="price[${status.index }].title" value="${priceOne.title }" />
									<input type="hidden" id="status${status.index }" name="price[${status.index }].status" value="${priceOne.status }"/>
								</td>
								<td><input style="width:100%" class="form-control" id="service_number${status.index }" name="price[${status.index }].serviceNumber"  value="${priceOne.serviceNumber }"  /></td>
								<td><input style="width:100%" class="form-control" id="price${status.index }" name="price[${status.index }].costPrice"  value="${priceOne.pricePart.costPrice }" onkeyup="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.costPrice }';}"  /></td>
								<td><input style="width:100%" class="form-control" id="old_price${status.index }" name="price[${status.index }].oldPrice"  value="${priceOne.pricePart.oldPrice }" onkeyup="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.oldPrice }';}"  /></td>
								<td><input style="width:100%" class="form-control" id="profit${status.index }" name="price[${status.index }].profit"  value="${priceOne.pricePart.profit }" onkeyup="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.profit }';}"  /></td>
								<td>
									<select style="width: 100%;" class="form-control input-xlarge" id="service_time${status.index }" name="price[${status.index }].serviceTime">
										<c:forEach var="temps" begin="1" step="1" end="60">
											<c:if test="${priceOne.serviceTime == temps}">
												<option selected="selected">${temps }</option>
											</c:if>
											<c:if test="${priceOne.serviceTime != temps}">
												<option>${temps }</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
								<td> <c:set var="arr" value="秒,分钟,小时,周,月,季,年" />
									<select style="width: 100%;" class="form-control input-xlarge" id="unit${status.index }" name="price[${status.index }].unit">
										<c:forEach items="${fn:split(arr,',')}" var="addr" begin="0" end="${fn:length(fn:split(arr,','))}" varStatus="stat">
											<c:if test="${priceOne.unit == addr}">
												<option selected="selected">${addr }</option>
											</c:if>
											<c:if test="${priceOne.unit != addr}">
												<option>${addr }</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
								<td>
									<select style="width: 100%;" class="form-control input-xlarge" id="sort${status.index }" name="price[${status.index }].sort">
										<c:forEach var="temps" begin="1" step="1" end="60">
											<c:if test="${priceOne.sort == temps}">
												<option selected="selected">${temps }</option>
											</c:if>
											<c:if test="${priceOne.sort != temps}">
												<option>${temps }</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
								<td><button type="button" class="public-info public_btn public_btn_center" data-toggle="modal" data-target="#config${status.index }">修改</button></td>
								<td><input style="width:100%" class="form-control" id="remark${status.index }" name="price[${status.index }].remark" value="${priceOne.remark }" /></td>
								<td><input type="button" style="width:100%" class="form-control" value="删除" id="delete${status.index }"  onclick="deleteTr('${status.index }');"  />
									<input type="hidden" id="dataPtitude${status.index }" name="price[${status.index }].aptitudeIdArr" value="5,6,8,7" />
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			<div class="btn_add" onclick="addTd('chuji');">+</div>
		</div>
		<span class="price_add_title">中级</span>
		<div class="marage_right_content">
			
			<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
				<tbody id="zhongji">
					<tr class="headClass">
						<td width="100px">标题</td>
						<td width="60px">服务次数</td><!-- 
						<td width="60px" >销售价格</td> -->
						<td width="60px" >成本价</td>
						<td width="60px" >利润</td>
						<td width="60px" >时长</td>
						<td width="60px" >单位</td>
						<td width="60px" >排序</td>
						<td width="60px" >授权</td>
						<td width="200px" >备注</td>
						<td width="60px" >操作</td>
					</tr>
					<c:forEach var="priceOne" items="${goods.priceList }" varStatus="status">
						<c:if test="${priceOne.grade == 2 }">
							<input type="hidden" id="id${status.index }" name="price[${status.index }].id" value="${priceOne.id }"/>
							<input type="hidden" id="id${status.index }" name="price[${status.index }].pricePartId" value="${priceOne.pricePart.id }"/>
								<tr class="addClassprice" id="delete${status.index }">
								<td>
								<input type="hidden" id="grade${status.index }" name="price[${status.index }].grade" value="${priceOne.grade }"/>
									<input type="hidden" id="status${status.index }" name="price[${status.index }].status" value="${priceOne.status }"/>
								<input style="width:100%" class="form-control" id="title${status.index }" name="price[${status.index }].title" value="${priceOne.title }" /></td>
								<td><input style="width:100%" class="form-control" id="service_number${status.index }" name="price[${status.index }].serviceNumber"  value="${priceOne.serviceNumber }"  /></td>
								<td><input style="width:100%" class="form-control" id="price${status.index }" name="price[${status.index }].costPrice"  value="${priceOne.pricePart.costPrice }" onkeyup="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.costPrice }';}"  /></td>
								<td><input style="width:100%" class="form-control" id="old_price${status.index }" name="price[${status.index }].oldPrice"  value="${priceOne.pricePart.oldPrice }" onkeyup="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.price }';}"  /></td>
								<td><input style="width:100%" class="form-control" id="profit${status.index }" name="price[${status.index }].profit"  value="${priceOne.pricePart.profit }" onkeyup="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.price }';}"  /></td>
								<td>
									<select style="width: 100%;" class="form-control input-xlarge" id="service_time${status.index }" name="price[${status.index }].serviceTime">
										<c:forEach var="temps" begin="1" step="1" end="60">
											<c:if test="${priceOne.serviceTime == temps}">
												<option selected="selected">${temps }</option>
											</c:if>
											<c:if test="${priceOne.serviceTime != temps}">
												<option>${temps }</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
								<td> <c:set var="arr" value="秒,分钟,小时,周,月,季,年" />
									<select style="width: 100%;" class="form-control input-xlarge" id="unit${status.index }" name="price[${status.index }].unit">
										<c:forEach items="${fn:split(arr,',')}" var="addr" begin="0" end="${fn:length(fn:split(arr,','))}" varStatus="stat">
											<c:if test="${priceOne.unit == addr}">
												<option selected="selected">${addr }</option>
											</c:if>
											<c:if test="${priceOne.unit != addr}">
												<option>${addr }</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
								<td>
									<select style="width: 100%;" class="form-control input-xlarge" id="sort${status.index }" name="price[${status.index }].sort">
										<c:forEach var="temps" begin="1" step="1" end="60">
											<c:if test="${priceOne.sort == temps}">
												<option selected="selected">${temps }</option>
											</c:if>
											<c:if test="${priceOne.sort != temps}">
												<option>${temps }</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
								<td><button type="button" class="public-info public_btn public_btn_center" data-toggle="modal" data-target="#config${status.index }">修改</button></td>
								<td><input style="width:100%" class="form-control" id="remark${status.index }" name="price[${status.index }].remark" value="${priceOne.remark }" /></td>
								<td><input type="button" style="width:100%" class="form-control" value="删除" id="delete${status.index }"  onclick="deleteTr('${status.index }');"  />	
								<input type="hidden" id="dataPtitude${status.index }" name="price[${status.index }].aptitudeIdArr" value="5,6,8,7" />
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			
			<div class="btn_add"  onclick="addTd('zhongji');">+</div>
		</div>
		<span class="price_add_title">高级</span>
		<div class="marage_right_content">
			<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
				<tbody id="gaoji">
					<tr class="headClass">
						<td width="100px">标题</td>
						<td width="60px">服务次数</td><!-- 
						<td width="60px" >销售价格</td> -->
						<td width="60px" >成本价</td>
						<td width="60px" >利润</td>
						<td width="60px" >时长</td>
						<td width="60px" >单位</td>
						<td width="60px" >排序</td>
						<td width="60px" >授权</td>
						<td width="200px" >备注</td>
						<td width="60px" >操作</td>
					</tr>
					<c:forEach var="priceOne" items="${goods.priceList }" varStatus="status">
						<c:if test="${priceOne.grade == 3 }">
							<input type="hidden" id="id${status.index }" name="price[${status.index }].id" value="${priceOne.id }"/>
							<input type="hidden" id="id${status.index }" name="price[${status.index }].pricePartId" value="${priceOne.pricePart.id }"/>
							<tr class="addClassprice" id="delete${status.index }">
								<td><input type="hidden" id="grade${status.index }" name="price[${status.index }].grade" value="${priceOne.grade }"/>
									<input class="form-control" style="width:100%" id="title${status.index }" name="price[${status.index }].title" value="${priceOne.title }" />
									<input type="hidden" id="status${status.index }" name="price[${status.index }].status" value="${priceOne.status }"/></td>
								<td><input style="width:100%" class="form-control" id="service_number${status.index }" name="price[${status.index }].serviceNumber"  value="${priceOne.serviceNumber }"  /></td>
								<td><input style="width:100%" class="form-control" id="price${status.index }" name="price[${status.index }].costPrice"  value="${priceOne.pricePart.costPrice }" onkeyup="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.costPrice }';}"  /></td>
								<td><input style="width:100%" class="form-control" id="old_price${status.index }" name="price[${status.index }].oldPrice"  value="${priceOne.pricePart.oldPrice }" onkeyup="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.oldPrice }';}"  /></td>
								<td><input style="width:100%" class="form-control" id="profit${status.index }" name="price[${status.index }].profit"  value="${priceOne.pricePart.profit }" onkeyup="if( ! /^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='${priceOne.pricePart.profit }';}"  /></td>
								<td>
									<select style="width: 100%;" class="form-control input-xlarge" id="service_time${status.index }" name="price[${status.index }].serviceTime">
										<c:forEach var="temps" begin="1" step="1" end="60">
											<c:if test="${priceOne.serviceTime == temps}">
												<option selected="selected">${temps }</option>
											</c:if>
											<c:if test="${priceOne.serviceTime != temps}">
												<option>${temps }</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
								<td> <c:set var="arr" value="秒,分钟,小时,周,月,季,年" />
									<select style="width: 100%;" class="form-control input-xlarge" id="unit${status.index }" name="price[${status.index }].unit">
										<c:forEach items="${fn:split(arr,',')}" var="addr" begin="0" end="${fn:length(fn:split(arr,','))}" varStatus="stat">
											<c:if test="${priceOne.unit == addr}">
												<option selected="selected">${addr }</option>
											</c:if>
											<c:if test="${priceOne.unit != addr}">
												<option>${addr }</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
								<td>
									<select style="width: 100%;" class="form-control input-xlarge" id="sort${status.index }" name="price[${status.index }].sort">
										<c:forEach var="temps" begin="1" step="1" end="60">
											<c:if test="${priceOne.sort == temps}">
												<option selected="selected">${temps }</option>
											</c:if>
											<c:if test="${priceOne.sort != temps}">
												<option>${temps }</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
								<td><button type="button" class="public-info public_btn public_btn_center" data-toggle="modal" data-target="#config${status.index }">修改</button></td>
								<td><input style="width:100%" class="form-control" id="remark${status.index }" name="price[${status.index }].remark" value="${priceOne.remark }" /></td>
								<td><input type="button" style="width:100%" class="form-control" value="删除" id="delete${status.index }"  onclick="deleteTr('${status.index }');"  />
									<input type="hidden" id="dataPtitude${status.index }" name="price[${status.index }].aptitudeIdArr" value="5,6,8,7" />
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			<div class="btn_add"  onclick="addTd('gaoji');">+</div>
		</div></div></div> --%>
	</fieldset>
</form>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" style="height:600px">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">设置授权</h4>
			</div>
			<div class="jobtitle">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" id="saveJob" class="btn btn-primary" data-dismiss="modal">确定</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
