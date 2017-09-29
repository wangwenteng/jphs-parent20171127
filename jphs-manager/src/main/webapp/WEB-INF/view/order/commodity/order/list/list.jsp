<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0"
	class="text-center">
	<thead>
		<tr>
			<th width="30"></th>
			<th width="140">订单号</th>
			<th width="70">下单人</th>
			<th width="80">订单金额</th>
			<th width="80">实付金额</th>
			<th width="80">优惠金额</th>
			<th width="100">物流费</th>
			<th width="140">物流信息</th>
			<th width="100">下单时间</th>
			<th width="80">状态</th>
			<th width="125">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td><c:out value="${e.orderNo}" /></td>
						<td><c:out value="${e.creatorName}" /></td>
						<td><c:out value="${e.payPrice}" /></td>
						<td><c:out value="${e.payPrice}" /></td>
						<td><c:out value="${e.voucherPrice}" /></td>
						<td>0.00</td>
						<td><c:if test="${e.logisticsName != null}">
							${e.logisticsName}：${e.no}</td>
						</c:if>

						<td><fmt:formatDate value="${e.createTime}"
								pattern="yy-MM-dd HH:mm" /></td>
						<td><c:if test="${e.schedule == -3}">
								已取消
								</c:if> <c:if test="${e.schedule == -2}">
								退货中
								</c:if> <c:if test="${e.schedule == -1}">
								已取消
								</c:if> <c:if test="${e.schedule == 0}">
								待支付
								</c:if> <c:if test="${e.schedule == 1}">
								待发货
								</c:if> <c:if test="${e.schedule == 2}">
								待收货
								</c:if> <c:if test="${e.schedule == 4}">
								已完成
								</c:if> <c:if test="${e.schedule == 5}">
								已删除订单
								</c:if></td>
						<td>
							<%-- <a onclick="redirectDetailPage('${e.id}')">
								<img src="/static/images/chakan.png">
							</a>								
							<a onclick="redirectUpdatePage('${e.id}')">
								<img  src="/static/images/xiugai.png">
							</a>									
							<a onclick="deleteById('${e.id}')">
								<img src="/static/images/shanchu.png">
							</a> --%> <a onclick="redirectDetailPage('${e.id}')"> <img
								src="/static/images/chakan.png">
						</a> <c:if test="${e.schedule == 1}">
								<a data-toggle="modal" data-target="#Modal"
									onclick="fh('${e.id}')"> <img src="/static/images/fh.png">
								</a>

							</c:if> <c:if test="${e.payTime !=null}">
								<a data-toggle="modal" data-target="#Modal"
									onclick="fh('${e.id}')"> <img src="/static/images/fh.png">
								</a>

							</c:if>
						</td>
					</tr>
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

<div>
	<div class="modal fade" id="Modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加物流信息</h4>
				</div>

				<form id="logistics">
					<div style="text-align: center;">
						<div class="controls col-md-6"
							style="width: 100%; margin-left: 15px;">
							<select class="marage_select" id="logisticsId" name="logisticsId">
								<option value="">全部</option>
								<c:forEach items="${lList}" var="e" varStatus="s">
									<option value="${e.id }">${e.name }</option>
								</c:forEach>
							</select> <span>物流单号</span> <span> <input type="text"
								id="${s.index+1}" name="no" /> <input type="hidden"
								id="commodityOrderId" name="commodityOrderId" />
							</span>
						</div>
						<div>
							<a onclick='addLogistics("${s.index+1}")'>确定</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<div class="page">
	<jphs:page pageInfos="${pageInfo}"></jphs:page>
</div>
</div>

</div>

