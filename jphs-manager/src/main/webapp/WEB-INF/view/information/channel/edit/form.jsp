<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal">
	<fieldset>
		<input type="hidden" id="id" name="id" value="${informationChannel.id}" />

		<div class="form-group">


			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">名称：</label>
			<div class="controls col-md-6">
				<input type="text" placeholder="请输入频道名称" name="name" class="form-control"  value="${informationChannel.name}" />
			</div>
		</div>

		<div class="form-group">
			<!-- 下拉列表 -->
			<label class="control-label col-md-3">排序：</label>
			<div class="controls col-md-6">
			<input type="text" id="sort" name="sort" placeholder="排序" class="form-control" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
					onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" value="${informationChannel.sort}" />
				
				<%-- <select class="form-control input-xlarge" name="sort">
				      <c:forEach var="temps" begin="1" step="1" end="30">
						<c:if test="${temps == product.sort }">
							<option value="${product.sort}" selected="selected">${product.sort}</option>
						</c:if>
						<c:if test="${temps != product.sort }">
							<option value="${temps}">${temps}</option>
						</c:if>
					</c:forEach>
				 </select> --%>
			</div>

		</div>

	</fieldset>
</form>