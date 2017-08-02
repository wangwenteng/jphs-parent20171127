<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<%@taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<div class="common_left">
	<div class="common_left_h">护士管理</div>
	<dl>
		<dt class="public_left_active">
			<span class="common_left_icon"> </span>护士管理 <i
				class="public-horn-135 left-mg"></i>
		</dt>
		<div class="public_left_list">
			<!-- jphs:url 为自定义标签库 用来判断是否具有需要验证的按钮的权限  -->
			<jphs:hasPermission url="/audit/index.jhtml">
				<dd>
					<a href="/audit/index.jhtml"><img
						src="/static/images/Group7.png" />护士审核</a>
				</dd>
			</jphs:hasPermission>
			<jphs:hasPermission url="/nurse/index.jhtml">
				<dd>
					<a href="/nurse/index.jhtml"><img
						src="/static/images/Group7.png" />护士管理</a>
				</dd>
			</jphs:hasPermission>
			<jphs:hasPermission url="/jobtitle/index.jhtml">
				<dd>
					<a href="/jobtitle/type/index.jhtml"><img
						src="/static/images/Group7.png" />职称类型管理</a>
				</dd>
			</jphs:hasPermission>
			<jphs:hasPermission url="/jobtitle/index.jhtml">
				<dd>
					<a href="/jobtitle/index.jhtml"><img
						src="/static/images/Group7.png" />职称管理</a>
				</dd>
			</jphs:hasPermission>
			<jphs:hasPermission url="/nurse/skill/index.jhtml">
				<dd>
					<a href="/nurse/skill/index.jhtml"><img
						src="/static/images/Group7.png" />技能管理</a>
				</dd>
			</jphs:hasPermission>
			
			<!-- <dd >
				<a href="#"><img src="/static/images/Group7.png" />店铺管理</a>
			</dd> -->
		</div>

	</dl>
</div>