<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="form-horizontal" >
	<div class="title_defalt"></div>
		<div class="form-group">
			<label class="control-label col-md-3" for="input01">头&nbsp;&nbsp;&nbsp;&nbsp;像：</label>
          <div class="controls col-md-6">
            <c:if test="${images.url != null}"><img alt="" height="200" width="200" id="aptitude_positives"
			src="${images.url}"></c:if>
          </div>
			
		</div>
		<div class="form-group">
			 <label class="control-label col-md-3" for="input01">用户标识：</label>
          <div class="controls col-md-6">
            ${user.id}
          </div>
        </div>
        
        <div class="form-group">
          <label class="control-label col-md-3" for="input01">昵&nbsp;&nbsp;&nbsp;&nbsp;称：</label>
          <div class="controls col-md-6">
            ${user.name}
          </div>
        </div>
          <div class="form-group">
          <label class="control-label col-md-3" for="input01">性&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
          <div class="controls col-md-6">
            <c:if test="${user.sex == 0}">男</c:if> <c:if
					test="${user.sex == 1}">女</c:if>
          </div>
        </div>
           <div class="form-group">
          <label class="control-label col-md-3" for="input01">联系方式：</label>
          <div class="controls col-md-6">
           	${user.phone}
          </div>
        </div>
        
           <div class="form-group">
          <label class="control-label col-md-3" for="input01">注册时间：</label>
          <div class="controls col-md-6">
           	<fmt:formatDate value="${user.createTime}"
					pattern="yyyy-MM-dd HH：mm" />
          </div>
        </div>
           <div class="form-group">
          <label class="control-label col-md-3" for="input01">账户余额：</label>
          <div class="controls col-md-6">
           	<c:if test="${user.balance != null}">
					<c:out value="${user.balance}" />
				</c:if> <c:if test="${user.balance == null}">
					0
				</c:if>
          </div>
        </div>
        
           <div class="form-group">
          <label class="control-label col-md-3" for="input01">当前积分：</label>
          <div class="controls col-md-6">
           	<c:if test="${user.score != null}">
					<c:out value="${user.score}" />
				</c:if> <c:if test="${user.score == null}">
					暂无积分
				</c:if>
          </div>
        </div>
          <div class="form-group">
          <label class="control-label col-md-3" for="input01">地址记录：</label>
          <div class="controls col-md-6">
           	 	<c:choose>
					<c:when test="${fn:length(list) >0}">
						<c:forEach items="${list}" var="e" varStatus="s">
							<div> ${s.index+1 }&nbsp;&nbsp;&nbsp;&nbsp;${e.detailaddress }</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div>暂无相关地址记录</div>
					</c:otherwise>
				</c:choose>
          </div>
        </div>
        
		 <div class="form-group">

          <label class="control-label col-md-3">个人简介：</label>
          <div class="controls col-md-6">
            <div class="textarea">
                  <textarea readonly="readonly" class="form-control">${user.brief } </textarea>
            </div>
          </div>
        </div>
</div>