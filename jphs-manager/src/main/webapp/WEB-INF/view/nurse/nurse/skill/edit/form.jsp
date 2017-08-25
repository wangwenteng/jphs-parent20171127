<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal">
	<fieldset>
		<div id="legend" class="">
			<legend>${nurseSkill.id == null?'技能添加':'技能编辑'}</legend>
		</div>
		<div class="form-group">
			<input type="hidden" value="${nurseSkill.creatorId }" id="creatorId" name="creatorId"/>
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input02">护士名称：</label>
			<div class="controls col-md-6">
				<input type="text" value="${nurseSkill.creatorName}" id="creatorName"
					name="creatorName" maxlength="4" placeholder="发布价格" class="form-control required" required>
			</div>
		</div>
		<div class="form-group">
			<input type="hidden" id="id" name="id" value="${nurseSkill.id}" /> 
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">技能名称：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" id="skillId"
							name="skillId">
							<option value="">全部</option>
							<c:forEach items="${skill}" var="e" varStatus="s">
								<option value="${e.id }"
									<c:if test="${nurseSkill.skillId==e.id}">selected="selected"</c:if>>${e.name }</option>
									<input type="hidden" id="skill.name" name="skill.name" value="${e.name}" />
							</c:forEach>
						</select>
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input02">发布价格：</label>
			<div class="controls col-md-6">
				<input type="text" value="${nurseSkill.price}" id="price"
					name="price" placeholder="发布价格" class="form-control required" required>
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input02">平台价格：</label>
			<div class="controls col-md-6">
				<input type="text" value="${nurseSkill.skill.amount}"
					id="skill.amount" readonly="readonly" name="skill.amount"
					placeholder="发布价格" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input02">服务区域：</label>
			<%-- <div class="controls col-md-6">
				<input type="text" value="${nurseSkill.location.content}"
					id="location.content" name="location.content" placeholder="发布价格"
					class="form-control">
			</div> --%>
			<div data-toggle="distpicker">
				<div class="form-group nurse_width"
					style="width: 24%; margin-left: 14px;">
					<label class="sr-only" for="province1">Province</label> <select
						class="form-control" id="province1" style="width: 77%;"></select>
				</div>
				<div class="form-group nurse_width" style="width: 30%;">
					<label class="sr-only" for="city1">City</label> <select
						class="form-control" id="city1" style="width: 70%"></select>
				</div>
				<div class="form-group nurse_width"
					style="width: 30%; margin-left: -38px;">
					<label class="sr-only" for="district1">District</label> <select
						class="form-control" id="district1" style="width: 60%;"></select>
				</div>
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">空闲日期：</label>
			<div class="controls col-md-2">
				<select class="form-control input-xlarge" id="week1" name="week1"
					style="width: 100px">
					<option
						<c:if test="${fn:substring(nurseSkill.freeCycle, 0, 3)=='星期一'}">selected="selected"</c:if>
						value="星期一">星期一</option>

					<option
						<c:if test="${fn:substring(nurseSkill.freeCycle, 0, 3)=='星期二'}">selected="selected"</c:if>
						value="星期二">星期二</option>
					<option
						<c:if test="${fn:substring(nurseSkill.freeCycle, 0, 3)=='星期三'}">selected="selected"</c:if>
						value="星期三">星期三</option>
					<option
						<c:if test="${fn:substring(nurseSkill.freeCycle, 0, 3)=='星期四'}">selected="selected"</c:if>
						value="星期四">星期四</option>
					<option
						<c:if test="${fn:substring(nurseSkill.freeCycle, 0, 3)=='星期五'}">selected="selected"</c:if>
						value="星期五">星期五</option>
					<option
						<c:if test="${fn:substring(nurseSkill.freeCycle, 0, 3)=='星期六'}">selected="selected"</c:if>
						value="星期六">星期六</option>
					<option
						<c:if test="${fn:substring(nurseSkill.freeCycle, 0, 3)=='星期日'}">selected="selected"</c:if>
						value="星期日">星期日</option>
				</select>
			</div>
			<div class="controls col-md-2" style="margin-left: 20px;">
				<select class="form-control input-xlarge" id="week2" name="week2"
					style="width: 100px">
					<option
						<c:if test="${fn:substring(nurseSkill.freeCycle, 4, 7)=='星期一'}">selected="selected"</c:if>
						value="星期一">星期一</option>
					<option
						<c:if test="${fn:substring(nurseSkill.freeCycle, 4, 7)=='星期二'}">selected="selected"</c:if>
						value="星期二">星期二</option>
					<option
						<c:if test="${fn:substring(nurseSkill.freeCycle, 4, 7)=='星期三'}">selected="selected"</c:if>
						value="星期三">星期三</option>
					<option
						<c:if test="${fn:substring(nurseSkill.freeCycle, 4, 7)=='星期四'}">selected="selected"</c:if>
						value="星期四">星期四</option>
					<option
						<c:if test="${fn:substring(nurseSkill.freeCycle, 4, 7)=='星期五'}">selected="selected"</c:if>
						value="星期五">星期五</option>
					<option
						<c:if test="${fn:substring(nurseSkill.freeCycle, 4, 7)=='星期六'}">selected="selected"</c:if>
						value="星期六">星期六</option>
					<option
						<c:if test="${fn:substring(nurseSkill.freeCycle, 4, 7)=='星期日'}">selected="selected"</c:if>
						value="星期日">星期日</option>
				</select>
			</div>
			<input type="hidden" name="freeCycle" id="freeCycle"
				value="${nurseSkill.freeCycle }" />
		</div>
		<%-- <div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">空闲时间：</label>
			<div class="controls col-md-2">
				<select class="form-control input-xlarge" id="time1" name="time1" style="width: 100px">
					<option <c:if test="${fn:substring(nurseSkill.freeCycle, 0, 3)=='星期一'}">selected="selected"</c:if> value="星期一">星期一</option>
					
					<option <c:if test="${fn:substring(nurseSkill.freeCycle, 0, 3)=='星期二'}">selected="selected"</c:if> value="星期二">星期二</option>
					<option <c:if test="${fn:substring(nurseSkill.freeCycle, 0, 3)=='星期三'}">selected="selected"</c:if> value="星期三">星期三</option>
					<option <c:if test="${fn:substring(nurseSkill.freeCycle, 0, 3)=='星期四'}">selected="selected"</c:if> value="星期四">星期四</option>
					<option <c:if test="${fn:substring(nurseSkill.freeCycle, 0, 3)=='星期五'}">selected="selected"</c:if> value="星期五">星期五</option>
					<option <c:if test="${fn:substring(nurseSkill.freeCycle, 0, 3)=='星期六'}">selected="selected"</c:if> value="星期六">星期六</option>
					<option <c:if test="${fn:substring(nurseSkill.freeCycle, 0, 3)=='星期日'}">selected="selected"</c:if> value="星期日">星期日</option>
				</select>
			</div>
			<div class="controls col-md-2" style="margin-left: 20px;">
				<select class="form-control input-xlarge" id="time2" name="time2" style="width: 100px">
					<option <c:if test="${fn:substring(nurseSkill.freeCycle, 4, 7)=='星期一'}">selected="selected"</c:if> value="星期一">星期一</option>
					<option <c:if test="${fn:substring(nurseSkill.freeCycle, 4, 7)=='星期二'}">selected="selected"</c:if> value="星期二">星期二</option>
					<option <c:if test="${fn:substring(nurseSkill.freeCycle, 4, 7)=='星期三'}">selected="selected"</c:if> value="星期三">星期三</option>
					<option <c:if test="${fn:substring(nurseSkill.freeCycle, 4, 7)=='星期四'}">selected="selected"</c:if> value="星期四">星期四</option>
					<option <c:if test="${fn:substring(nurseSkill.freeCycle, 4, 7)=='星期五'}">selected="selected"</c:if> value="星期五">星期五</option>
					<option <c:if test="${fn:substring(nurseSkill.freeCycle, 4, 7)=='星期六'}">selected="selected"</c:if> value="星期六">星期六</option>
					<option <c:if test="${fn:substring(nurseSkill.freeCycle, 4, 7)=='星期日'}">selected="selected"</c:if> value="星期日">星期日</option>
				</select>
			</div>
			<input type="hidden" name ="freeCycle" id="freeCycle" value ="${nurseSkill.freeCycle }"/>
		</div> --%>
		<div class="form-group">
			<label class="control-label col-md-3">技能图片：</label>
			<!-- 文件上传 -->
			<div id="image_url" style=" width: 600px; height: 400px;   margin-left: 155px; ">
				<c:choose>
					<c:when test="${fn:length(nurseSkill.nurseImage) >0}">
						<c:forEach items="${nurseSkill.nurseImage}" var="e" varStatus="s">
							<div class="controls col-md-3"
								style="float:left;width: 200px; <c:if test="${(s.index+1)%2!=1 }">margin-left: 70px;</c:if> ">
								<img alt="" height="200" width="200" id="image_url${s.index+1}s"
									src="${e.url}"> <input class="input-file" type="file"
									name="myfiles" id="image_url${s.index+1}_s"
									onchange="ajaxFileUpload('image_url${s.index+1}_s','image_url${s.index+1}');"  />
								<input class="input-file" type="hidden" value="${e.url}"
									id="image_url${s.index+1}" name="nurseImage[${s.index}].url"  />
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						 <div class="controls col-md-3"
						 	style="float:left;width: 200px;">
							<img alt="" height="200" width="200" id="image_url1s"
								src="/static/img/def.jpg"> <input
								class="input-file" type="file" name="myfiles" id="image_url1_s"
								onchange="ajaxFileUpload('image_url1_s','image_url1');" /> <input
								class="input-file" type="hidden"
								value="" id="image_url1"
								name="nurseImage[1].url" />
						</div>
						<div class="controls col-md-3"
							style="float:left;width: 200px;margin-left: 70px; ">
							<img alt="" height="200" width="200" id="image_url2s"
								src="/static/img/def.jpg"> <input
								class="input-file" type="file" name="myfiles" id="image_url2_s"
								onchange="ajaxFileUpload('image_url2_s','image_url2');" /> <input
								class="input-file" type="hidden"
								value="" id="image_url2"
								name="nurseImage[2].url" />
						</div>
						<div class="controls col-md-3" style="float:left;width: 200px;">
							<img alt="" height="200" width="200" id="image_url3s"
								src="/static/img/def.jpg"> <input
								class="input-file" type="file" name="myfiles" id="image_url3_s"
								onchange="ajaxFileUpload('image_url3_s','image_url3');" /> <input
								class="input-file" type="hidden"
								value="" id="image_url3"
								name="nurseImage[3].url" />
						</div> 
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</fieldset>
</form>
