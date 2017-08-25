<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<form id="serach-form" method="get" action="/information/evaluate/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>评论渠道：</td>
			<td>
			<input type="hidden" class="device" value="${informationEvaluate.device }" />
				<div class="controls col-md-6">
					<select class="form-control input-xlarge devicexz" style="width: 180px;" id="device" name="device" >
							<option value="">全部</option>
						<option value="0">官网</option>
						<option value="1">IOS-APP</option>
						<option value="2">安卓-APP</option>
						<option value="3">微信公众号</option>              
					</select>
				</div>
			</td>
			
			<td>审核状态${informationEvaluate.status }：</td>
			<td>
				<div class="controls col-md-6">
					<input type="hidden" class="status" value="${informationEvaluate.status }" />
					<select class="form-control input-xlarge statusxz" style="width: 180px;" id="status" name="status" >
						<option value="">全部</option>
						<option value="-1">未审核</option>
						<option value="0">已审核通过</option>
						<option value="1">已审核未通过</option>
					</select>
				</div>
			</td>
		</tr>
		
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
	</div>
</form>

