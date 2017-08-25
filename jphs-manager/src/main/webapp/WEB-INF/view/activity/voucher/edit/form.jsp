<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<script type="text/javascript">
	var data = ${data };
</script>

<div class="form-horizontal" >
	<div class="title_defalt"></div>
		<input type="hidden" id="goodsId" name="goodsId">
			<input type="hidden" id="productId" name="productId">
		<div class="form-group">
			   <label class="control-label col-md-3">优惠券类型：</label>
          <div class="controls  col-md-6">
            <!-- 单行单选项目 -->
             <label class="radio  radio-inline" hidden="true">
              <input hidden="true" type="radio" value="1" checked="checked" name="group2">
            </label>
            
            <label class="radio  radio-inline">
              <input type="radio" value="1" checked="checked" name="type" onclick="showMoney()">
              	现金券
            </label>
            <label class="radio radio-inline">
              <input type="radio" value="2" name="type" onclick="showCon()">
              	满减券
            </label>
            <label class="radio  radio-inline">
              <input type="radio" value="3" name="type" onclick="showDis()">
              	折扣券
            </label>
          </div>
        </div>
        
        <div class="form-group">
			<label class="control-label col-md-3">提供服务：</label>
			<div class="controls  col-md-6">
				<ul id="tt" class="easyui-tree edit_tree" checkbox="true"
					data-options="animate:true"
					style="margin-top: 10px; height: 540px; overflow-y: auto;">
				</ul>
			</div>
		</div>
        
       <%--  <div class="form-group">
          <!-- 下拉列表 -->
          <label class="control-label col-md-3">支持品类：</label>
          <div class="controls col-md-6">
            <select class="form-control input-xlarge" name="productId">
              <c:choose>
					<c:when test="${fn:length(list) >0}">
						<c:forEach items="${list}" var="e" varStatus="s">
							<option value="${e.id }">&nbsp;&nbsp;&nbsp;&nbsp;${e.title }</option>
						</c:forEach>
					</c:when>
				</c:choose>
            </select>
          </div>
        </div> 
          <div class="form-group">
          <label class="control-label col-md-3" for="input01">批次号开始号：</label>
          <div class="controls col-md-6">
          <input type="text"  class="form-control" name="batchNo"  >
          </div>
        </div>--%>
           <div class="form-group">
          <label class="control-label col-md-3" for="input01">兑换开始时间：</label>
          <div class="controls col-md-6">
          	<div style="width: 120%;"
				class="input-group date form_date col-md-3" data-date=""
				data-date-format="yyyy-mm-dd" data-link-field="workYears"
				data-link-format="yyyy-mm-dd">
				<input class="form-control" id="startTime"
					name="startTime"
					size="14" placeholder="请选择兑换开始时间" type="text" value="" readonly>
				<span class="input-group-addon"><span
					class="glyphicon glyphicon-remove"></span></span> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-calendar"></span></span> 
			</div>
          
          </div>
        </div>
        
            <div class="form-group">
          <label class="control-label col-md-3" for="input01">兑换结束时间：</label>
          <div class="controls col-md-6">
           	<div style="width: 120%;"
				class="input-group date form_date col-md-3" data-date=""
				data-date-format="yyyy-mm-dd" data-link-field="workYears"
				data-link-format="yyyy-mm-dd">
				<input class="form-control" id="endTime"
					name="endTime"
					size="14" placeholder="请选择兑换结束时间" type="text" value="" readonly>
				<span class="input-group-addon"><span
					class="glyphicon glyphicon-remove"></span></span> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-calendar"></span></span> 
			</div>
          </div>
        </div>
         <!--  <div class="form-group">
          <label class="control-label col-md-3" for="input01">激活后有效时长：</label>
          <div class="controls col-md-6">
           <input type="text"  class="form-control" name="days"  > 
          </div>
        </div> -->
        
        <div class="form-group">
		<label class="control-label col-md-3" for="input01">激活开始时间：</label>
		<div class="controls col-md-6">
			<div style="width: 120%;" class="input-group date form_date col-md-3"
				data-date="" data-date-format="yyyy-mm-dd"
				data-link-field="workYears" data-link-format="yyyy-mm-dd">
				<input class="form-control" id="activationStartTime" name="activationStartTime" size="14"
					placeholder="请选择激活开始时间" type="text" value="" readonly> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-remove"></span></span> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-calendar"></span></span>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-md-3" for="input01">激活结束时间：</label>
		<div class="controls col-md-6">
			<div style="width: 120%;" class="input-group date form_date col-md-3"
				data-date="" data-date-format="yyyy-mm-dd"
				data-link-field="workYears" data-link-format="yyyy-mm-dd">
				<input class="form-control" id="activationEndTime" name="activationEndTime" size="14"
					placeholder="请选择激活结束时间" type="text" value="" readonly> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-remove"></span></span> <span
					class="input-group-addon"><span
					class="glyphicon glyphicon-calendar"></span></span>
			</div>
		</div>
	</div>
        
            <div class="form-group">
          <label class="control-label col-md-3" for="input01">数量：</label>
          <div class="controls col-md-6">
           <input type="text"  class="form-control" name="count">
          </div>
        </div>
        
            <div class="form-group" id="money" >
	          <label class="control-label col-md-3" for="input01">面值：</label>
	          <div class="controls col-md-6">
	          <input type="text"  class="form-control" name="amount">
	          </div>
	        </div>
            <div class="form-group" id="con" style="display: none">
	          <label class="control-label col-md-3" for="input01">满减券：</label>
	          	消费满<input type="text" name="conditionAmount">元减
	          	<input type="text" name="CAmount">元
	        </div>
            <div class="form-group" id="dis" style="display: none">
	          <label class="control-label col-md-3" for="input01">折扣券：</label>
	         	 <input type="text" name="discountAmount" style="display: none" value="0" >
	         	 <div class="controls col-md-6">
	        	  <input type="text"  class="form-control" name="DAmount">折
	          </div>
</div>


   
    