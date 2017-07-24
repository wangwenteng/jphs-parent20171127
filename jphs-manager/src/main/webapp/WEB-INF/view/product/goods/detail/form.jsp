<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
	<fieldset>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">服务名称：</label>
			<div class="controls col-md-6">
				<input type="text" id="title"  readonly="readonly"  name="title" value="${goods.title}"
					placeholder="名称" class="form-control">
			</div>
		</div>

		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">服务别名：</label>
			<div class="controls col-md-6">
				<input type="text" id="subTitle" name="subTitle"
					value="${goods.subTitle}"  readonly="readonly"  placeholder="说明" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<!-- 下拉列表 -->
			<label class="control-label col-md-3">服务品类：</label>
			<div class="controls col-md-6">
			<input type="text" id="subTitle" name="subTitle" value="${goods.product.title}"  readonly="readonly" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">是否需要服务工具：</label>
			<div class="controls col-md-6">
				<!-- 单行单选项目 -->
				<c:if test="${goods.tool == 1}">
					需要
				</c:if> 
				<c:if test="${goods.tool == 0}">
				 不需要
				</c:if>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">是否需要上保险：</label>
			<div class="controls col-md-6">
				<!-- 单行单选项目 -->
				<c:if test="${goods.insurance == 1}">
					需要
				</c:if> 
				<c:if test="${goods.insurance == 0}">
				 不需要
				</c:if>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">服务类型（内部|公开）：</label>
			<div class="controls col-md-6">
				<!-- 单行单选项目 -->
				<c:if test="${goods.type == 1}">
					内部
				</c:if> 
				<c:if test="${goods.type == 0}">
					公开
				</c:if>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-3">状态：</label>
			<div class="controls  col-md-6">
				<!-- 单行单选项目 -->
				<c:if test="${goods.status == 0}">
					使用中
				</c:if> 
				<c:if test="${goods.status == -1}">
				 	已停用
				</c:if>
			</div>
		</div>

		<div class="form-group">
			<!-- 下拉列表 -->
			<label class="control-label col-md-3">排序：</label>
			<div class="controls col-md-6">
				${goods.sort}
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-3">服务手机端logo：</label>

			<!-- 文件上传 -->
			<div class="controls col-md-6">
				<img alt="" height="200" width="200" id="pcurls" src="${wap_image.url}">
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-3">服务PC端图片：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
				<img alt="" height="200" width="200" id="moveurls" src="${pc_image.url}">
			</div>
		</div>

		<div class="form-group">

			<!-- 文本区域 -->
			<label class="control-label col-md-3">文字介绍：</label>
			<div class="controls col-md-6">
				<div class="textarea">
					<textarea id="content" name="content" readonly="readonly"  class="form-control">${goods.content}</textarea>
				</div>
			</div>
		</div>

		<div class="form-group">
			<!-- 文本区域 -->
			<label class="control-label col-md-3">备注：</label>
			<div class="controls col-md-6">
				<div class="textarea">
					<textarea id="remark" name="remark" readonly="readonly"  class="form-control">${goods.remark}</textarea>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-3">服务等级类型：</label>
			<div class="controls  col-md-6">
				<c:if test="${goods.gradeType == 0}">
						 标准服务
					</c:if>
				<c:if test="${goods.gradeType == 1}">
						等级服务
					</c:if> 
			</div>
		</div>
		
		<c:choose>
			<c:when test="${fn:length(goods.priceGrade) >0}">
				<c:forEach var="priceOne_g" items="${goods.priceGrade }" varStatus="status_g">
				<span class="price_add_title"><input style="width: 200px;" type="text"  value="${priceOne_g.gradeName }"/></span>
						<div class="marage_right_content"  style="margin-top:0;padding-top:0">
							<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
								<tbody id="addtrprice${status_g.index }">
									<tr class="headClass">
										<td width="100px">标--题</td>
										<td width="60px">服务次数</td>
										<!-- <td width="60px" >销售价格</td> -->
										<td width="60px" >成本价</td>
										<td width="60px" >利润</td>
										<td width="60px" >时长</td>
										<td width="60px" >单位</td>
										<td width="60px" >排序</td><!-- 
										<td width="60px" >授权</td> -->
									</tr>
									<c:forEach var="priceOne" items="${priceOne_g.price }" varStatus="status">
										<tr>
											<td width="100px">${priceOne.title }</td>
											<td width="60px">${priceOne.serviceNumber }</td>
											<td width="60px">${priceOne.pricePart.costPrice }</td>
											<td width="60px">${priceOne.pricePart.profit }</td>
											<td width="60px">${priceOne.serviceTime }</td>
											<td width="60px">${priceOne.unit }</td>
											<td width="60px">${priceOne.sort }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							</div>
				</c:forEach>
			</c:when>
		</c:choose>
			<%-- <div class="marage_right_content">
				<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
					<tbody id="chuji">
						<tr >
							<td width="100px">标题</td>
							<td width="60px">服务次数</td>
							<!-- <td width="60px" >销售价格</td> -->
							<td width="60px" >成本价</td>
							<td width="60px" >利润</td>
							<td width="60px" >时长</td>
							<td width="60px" >单位</td>
							<td width="60px" >排序</td>
							<td width="200px" >备注</td>
						</tr>
						<c:choose>
							<c:when test="${fn:length(goods.priceList) >0}">
								<c:forEach items="${goods.priceList }" var="priceOne" varStatus="status">
									<c:if test="${priceOne.grade == 0 }">
										<tr>
											<td width="100px">${priceOne.title }</td>
											<td width="60px">${priceOne.serviceNumber }</td>
											<td width="60px">${priceOne.pricePart.costPrice }</td>
											<td width="60px">${priceOne.pricePart.oldPrice }</td>
											<td width="60px">${priceOne.pricePart.profit }</td>
											<td width="60px">${priceOne.serviceTime }</td>
											<td width="60px">${priceOne.unit }</td>
											<td width="60px">${priceOne.sort }</td>
											<td width="200px">${priceOne.remark }</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="20" align="center">没有可显示的记录。</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</c:if> --%>
		<%-- <c:if test="${goods.gradeType == 1}">
		<div id="price_add">
		<span class="price_add_title">初级</span>
		<div class="marage_right_content">
			<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
				<tbody id="chuji">
					<tr >
						<td width="100px">标题</td>
						<td width="60px">服务次数</td><!-- 
						<td width="60px" >销售价格</td> -->
						<td width="60px" >成本价</td>
						<td width="60px" >利润</td>
						<td width="60px" >时长</td>
						<td width="60px" >单位</td>
						<td width="60px" >排序</td>
						<td width="200px" >备注</td>
					</tr>
					<c:choose>
						<c:when test="${fn:length(goods.priceList) >0}">
							<c:forEach items="${goods.priceList }" var="priceOne" varStatus="status">
								<c:if test="${priceOne.grade == 1 }">
									<tr>
										<td width="100px">${priceOne.title }</td>
										<td width="60px">${priceOne.serviceNumber }</td>
										<td width="60px" >${priceOne.pricePart.costPrice }</td>
										<td width="60px" >${priceOne.pricePart.oldPrice }</td>
										<td width="60px" >${priceOne.pricePart.profit }</td>
										<td width="60px" >${priceOne.serviceTime }</td>
										<td width="60px" >${priceOne.unit }</td>
										<td width="60px" >${priceOne.sort }</td>
										<td width="200px" >${priceOne.remark }</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="20" align="center">没有可显示的记录。</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<span class="price_add_title">中级</span>
		<div class="marage_right_content">
			
			<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
				<tbody id="zhongji">
					<tr >
						<td width="100px">标题</td>
						<td width="60px">服务次数</td><!-- 
						<td width="60px" >销售价格</td> -->
						<td width="60px" >成本价</td>
						<td width="60px" >利润</td>
						<td width="60px" >时长</td>
						<td width="60px" >单位</td>
						<td width="60px" >排序</td>
						<td width="200px" >备注</td>
					</tr>
					<c:choose>
						<c:when test="${fn:length(goods.priceList) >0}">
							<c:forEach items="${goods.priceList }" var="priceOne" varStatus="status">
								<c:if test="${priceOne.grade == 2 }">
									<tr>
										<td width="100px">${priceOne.title }</td>
										<td width="60px">${priceOne.serviceNumber }</td>
										<td width="60px" >${priceOne.pricePart.costPrice }</td>
										<td width="60px" >${priceOne.pricePart.oldPrice }</td>
										<td width="60px" >${priceOne.pricePart.profit }</td>
										<td width="60px" >${priceOne.serviceTime }</td>
										<td width="60px" >${priceOne.unit }</td>
										<td width="60px" >${priceOne.sort }</td>
										<td width="200px" >${priceOne.remark }</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="20" align="center">没有可显示的记录。</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<span class="price_add_title">高级</span>
		<div class="marage_right_content">
			<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
				<tbody id="gaoji">
					<tr >
						<td width="100px">标题</td>
						<td width="60px">服务次数</td><!-- 
						<td width="60px" >销售价格</td> -->
						<td width="60px" >成本价</td>
						<td width="60px" >利润</td>
						<td width="60px" >时长</td>
						<td width="60px" >单位</td>
						<td width="60px" >排序</td>
						<td width="200px" >备注</td>
						<!-- <td width="60px" >操作</td> -->
					</tr>
					<c:choose>
						<c:when test="${fn:length(goods.priceList) >0}">
							<c:forEach items="${goods.priceList }" var="priceOne" varStatus="status">
								<c:if test="${priceOne.grade == 3 }">
									<tr>
										<td width="100px">${priceOne.title }</td>
										<td width="60px">${priceOne.serviceNumber }</td>
										<td width="60px" >${priceOne.pricePart.costPrice }</td>
										<td width="60px" >${priceOne.pricePart.oldPrice }</td>
										<td width="60px" >${priceOne.pricePart.profit }</td>
										<td width="60px" >${priceOne.serviceTime }</td>
										<td width="60px" >${priceOne.unit }</td>
										<td width="60px" >${priceOne.sort }</td>
										<td width="200px" >${priceOne.remark }</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="20" align="center">没有可显示的记录。</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<!-- <div class="btn_add"  onclick="addTd('gaoji');">+</div> -->
		</div></div>
		</c:if> --%>
		
	</fieldset>