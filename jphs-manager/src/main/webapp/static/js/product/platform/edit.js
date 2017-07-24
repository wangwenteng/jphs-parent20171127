$(function() {
	$('#tt').tree({
		data : data.treeData
	});
	/*
	 * $('#save').click(function() { var goods =''; var nodes =
	 * $('#tt').tree('getChecked'); for (var i = 0; i < nodes.length; i++) {
	 * if(nodes[i].parentId!=0){ goods +=nodes[i].id+','; } }
	 * $('#goodsIds').val(goods); var sites=''; var siteIds
	 * =$('input:checkbox:checked'); for (var i = 0; i < siteIds.length; i++) {
	 * sites +=siteIds[i].value+','; } $('#sites').val(sites);
	 * alert($('#goodsIds').val()); alert($('#sites').val());
	 * document.platformForm.submit(); });
	 */

	$("#platformForm").validate({
		rules : {
			name : {
				required : true,
				minlength : 2
			},
			company : {
				required : true,
				minlength : 4
			},
			contactsName : {
				required : true,
				rangelength : [ 2, 4 ]
			},
			contactsPhone : {
				required : true,
				minlength : 11,
				// 自定义方法：校验手机号在数据库中是否存在
				// checkPhoneExist : true,
				isMobile : true
			},
			companyAddress : {
				required : true,
				minlength : 10
			},
			remark : {
				required : true,
				maxlength : 100
			}
		},
		messages : {
			name : {
				required : "请输入平台名称",
				minlength : "请输入最少两位名称"
			},
			company : {
				required : "请输入公司名称",
				minlength : "请输入最少四 位名称"
			},
			contactsName : {
				required : "请输入公司名称",
				rangelength : "请输入最二到四位姓名"
			},
			contactsPhone : {
				required : "请输入手机号",
				minlength : "确认手机不能小于11个字符",
				isMobile : "请正确填写您的手机号码"
			},
			companyAddress : {
				required : "请输入公司名称",
				minlength : "请输入最少十位字符"
			},
			remark : {
				required : "必填字段",
				maxlength : "请输入的不要超过100字"
			}
		},
		submitHandler : function(form) {
			var goods = '';
			var nodes = $('#tt').tree('getChecked');
			for (var i = 0; i < nodes.length; i++) {
				if (nodes[i].parentId != 0) {
					goods += nodes[i].id + ',';
				}
			}
			var sites = '';
			var siteIds = $('input:checkbox:checked');
			for (var i = 0; i < siteIds.length; i++) {
				sites += siteIds[i].value + ',';
			}
			if (goods == '') {
				alert('请选择所提供的服务');
				return false;
			}
			if (sites == '') {
				alert('请选择一个站点');
				return false;
			} else {
				$('#goodsIds').val(goods);
				$('#sites').val(sites);
				alert("操作成功");
				form.submit();
			}
		}
	});
})