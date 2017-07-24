<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="form-horizontal" >
	<div class="title_defalt"></div>
		<div class="form-group">
			 <label class="control-label col-md-3" for="input01">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
          <div class="controls col-md-6">
          ${customService.name}
          </div>
        </div>
        
        <div class="form-group">
          <label class="control-label col-md-3" for="input01">手机号：</label>
          <div class="controls col-md-6">
          ${customService.phone}
          </div>
        </div>
          <div class="form-group">
          <label class="control-label col-md-3" for="input01">咨询时间：</label>
          <div class="controls col-md-6">
          <fmt:formatDate value="${customService.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
          </div>
        </div>
           <div class="form-group">
          <label class="control-label col-md-3" for="input01">回访客服：</label>
          <div class="controls col-md-6">
          		<c:if test="${customService.visitor ==null }">暂未回访</c:if>
          		<c:if test="${customService.visitor !=null }">${customService.visitor}</c:if>
          </div>
        </div>
        
           <div class="form-group">
          <label class="control-label col-md-3" for="input01">回访时间：</label>
          <div class="controls col-md-6">
          		<c:if test="${customService.visitTime ==null }">暂未回访</c:if>
          		<c:if test="${customService.visitTime !=null }">
						 <fmt:formatDate value="${customService.visitTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</c:if>
          </div>
        </div>
        
        
		 <div class="form-group">
          <label class="control-label col-md-3">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>
          <div class="controls col-md-6">
            <div class="textarea">
                  <textarea readonly="readonly" class="form-control">${customService.remark}</textarea>
            </div>
          </div>
        </div>
</div>