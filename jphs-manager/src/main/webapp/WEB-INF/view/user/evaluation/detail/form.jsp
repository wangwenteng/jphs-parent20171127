<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="form-horizontal" >
	<div class="title_defalt"></div>
	
		  <div class="form-group">
          <!-- 文本输入 -->
          <label class="control-label col-md-3" for="input01">订单编号：</label>
          <div class="controls col-md-6">
            ${evaluation.orderId}
          </div>
        </div>
          <div class="form-group">
          <!-- 文本输入 -->
          <label class="control-label col-md-3" for="input01">用户昵称：</label>
          <div class="controls col-md-6">
            ${evaluation.creatorName}
          </div>
        </div>
		 <div class="form-group">
          <!-- 文本输入 -->
          <label class="control-label col-md-3" for="input01">护士昵称：</label>
          <div class="controls col-md-6">
            ${evaluation.nurseName}
          </div>
        </div>
         <div class="form-group">
          <!-- 文本输入 -->
          <label class="control-label col-md-3" for="input01">评价星级：</label>
          <div class="controls col-md-6">
            ${evaluation.level}
          </div>
        </div>
        <div class="form-group">

          <!-- 文本区域 -->
          <label class="control-label col-md-3">评价内容：</label>
          <div class="controls col-md-6">
            <div class="textarea">
                  <textarea readonly="readonly" class="form-control"> ${evaluation.content}</textarea>
            </div>
          </div>
        </div>
         <div class="form-group">
          <!-- 文本输入 -->
          <label class="control-label col-md-3" for="input01">评价时间：</label>
          <div class="controls col-md-6">
          <fmt:formatDate value="${evaluation.createTime}" pattern="yyyy-MM-dd HH:mm" />
          </div>
        </div>
</div>