$(document).ready(function() {
	
	$('#tt').tree({
		data : data.treeData
	});
	
	$('.form_date').datetimepicker({
		 format: 'yyyy-mm-dd',
		 minView: 2
	});
	$("#voucherForm").validate({
		rules : {
			startTime : {
				required : true
			},
			endTime : {
				required : true
			},
			activationStartTime :{
				required : true
			},
			activationEndTime :{
				required : true
			},
			count : {
				required : true,
				digits   : true
			},
			amount : {
				required : true,
				number   : true
			},
			conditionAmount : {
				required : true,
				number   : true
			},
			discountAmount : {
				required : true,
				number   : true 
			}
			
		},
		messages : {
			startTime : {
				required : "请选择兑换开始时间"
			},
			endTime : {
				required : "请选择兑换结束时间"
			},
			activationStartTime :{
				required : "请选择激活开始时间"
			},
			activationEndTime :{
				required : "请选择激活结束时间"
			},
			count : {
				required : "请输入数量",
				digits   : "请输入整数"
			},
			amount : {
				required : "请输入面值",
				number   : "请输入数字"
			},
			conditionAmount : {
				required : "请输入数值",
				number   : "请输入数字"
			},
			discountAmount : {
				required :  "请输入数值",
				number   : "请输入数字" 
			}
		},
		submitHandler : function(form) {
			var goods = '';
			var product = '';
			var nodes = $('#tt').tree('getChecked');
			for (var i = 0; i < nodes.length; i++) {
				if (nodes[i].parentId != 0) {
					goods += nodes[i].id + ',';
				}else{
					product += ','+ nodes[i].id;
				}
				
			}
			
			if (goods == '') {
				alert('请选择所提供的服务');
				return false;
			} else {
				if(product!=''){
					$('#productId').val(product);
					$('#goodsId').val('');
				}else{
					$('#goodsId').val(goods);
					$('#productId').val('');
				}
				form.submit();
			}
		}
	})
})


function showMoney() {
	$("#money").show();
	$("#con").hide();
	$("#dis").hide();
}

function showDis() {
	$("#money").hide();
	$("#con").hide();
	$("#dis").show();
}

function showCon() {
	$("#money").hide();
	$("#con").show();
	$("#dis").hide();
}

