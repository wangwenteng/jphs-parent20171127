<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<style type="text/css">
#one {
	width: 20px;
	height: 180px;
	float: left
}

#two {
	width: 50px;
	height: 180px;
	float: left
}

#three {
	width: 200px;
	height: 180px;
	float: left
}

.btn {
	width: 50px;
	height: 30px;
	margin-top: 10px;
	cursor: pointer;
}
</style>
<div style="padding-left:120px;padding-top:20px">
	<div>
	<input type="hidden" id="priceId" value="${priceID }" />
	  <select multiple="multiple" id="select1" style="width:150px;height:400px; float:left; border:4px #A0A0A4 outset; padding:4px; ">
	  	<c:choose>
			<c:when test="${jobtitlePriceList !=null && jobtitlePriceList != ''}">
				<c:forEach var="arrOne" items="${jobtitlePriceList }" varStatus="status_arr">
							<option value="${arrOne.id }">${arrOne.name }</option>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach var="joOne" items="${jobtitleList }" varStatus="status">
					<option value="${joOne.id }">${joOne.name }</option>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	  </select>
	</div>
	<div style="float:left"> <span id="add">
	  <input type="button" class="btn" value=">"/>
	  </span><br />
	  <span id="add_all">
	  <input type="button" class="btn" value=">>"/>
	  </span> <br />
	  <span id="remove">
	  <input type="button" class="btn" value="<"/>
	  </span><br />
	  <span id="remove_all">
	  <input type="button" class="btn" value="<<"/>
	  </span> </div>
	<div>
	  <select multiple="multiple" id="select2" style="width: 150px;height:400px; float:lfet;border:4px #A0A0A4 outset; padding:4px;">
	 	 <c:choose>
			<c:when test="${jobtitleArr !=null && jobtitleArr != ''}">
				<c:forEach var="joOne" items="${jobtitleList }" varStatus="status">
					<c:forEach var="arrOne" items="${fn:split(jobtitleArr,',') }" varStatus="status_arr">
						<c:if test="${joOne.id == arrOne }">
							<option value="${joOne.id }">${joOne.name }</option>
						</c:if>
					</c:forEach>
				</c:forEach>
			</c:when>
			<%-- <c:otherwise>
				<c:forEach var="joOne" items="${jobtitleList }" varStatus="status">
					<option value="${joOne.id }">${joOne.name }</option>
				</c:forEach>
			</c:otherwise> --%>
		</c:choose>
	  </select>
	</div>
</div>