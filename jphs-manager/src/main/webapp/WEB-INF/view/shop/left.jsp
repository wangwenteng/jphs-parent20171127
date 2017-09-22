<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras"%>
<%@taglib uri="http://www.jinpaihushi.com/jsp/core" prefix="jphs" %>
<div class="common_left">
	<div class="common_left_h">产品管理</div>
	<dl>
		<dt class="public_left_active">
		<span class="common_left_icon"> </span>服务管理
		 <i class="public-horn-135 left-mg"></i>
		</dt>
		
		<div class="public_left_list">
			<!-- jphs:url 为自定义标签库 用来判断是否具有需要验证的按钮的权限  -->
			<jphs:hasPermission url="/product/index.jhtml">
				<dd>
					<a href="/product/index.jhtml"><img src="/static/images/Group7.png" />品类管理</a>
				</dd>
			</jphs:hasPermission>
			<jphs:hasPermission url="/goods/index.jhtml">
				<dd>
					<a href="/goods/index.jhtml"><img src="/static/images/Group7.png" />服务管理</a>
				</dd>
			</jphs:hasPermission>
			<jphs:hasPermission url="/platform/index.jhtml">
			<dd>
				<a href="/platform/index.jhtml"><img src="/static/images/Group7.png" />平台管理</a>
			</dd>
			</jphs:hasPermission>
			<jphs:hasPermission url="/site/index.jhtml">
				<dd>
					<a href="/site/index.jhtml"><img src="/static/images/Group7.png" />站点管理</a>
				</dd>
			</jphs:hasPermission>
		</div>
	
		
	</dl>
	
		<dl>
		<dt class="public_left_active">
		<span class="common_left_icon"> </span>商品管理
		 <i class="public-horn-135 left-mg"></i>
		</dt>
		
		<div class="public_left_list">
			<!-- jphs:url 为自定义标签库 用来判断是否具有需要验证的按钮的权限  -->
			<%-- <jphs:hasPermission url="/commodity/index.jhtml"> --%>
				<dd>
					<a href="/commodity/index.jhtml"><img src="/static/images/Group7.png" />商品管理</a>
				</dd>
			<%-- </jphs:hasPermission> --%>
			
			<dd>
					<a href="/business/index.jhtml"><img src="/static/images/Group7.png" />商家管理</a>
				</dd>
		</div>
	
		
	</dl>
</div>