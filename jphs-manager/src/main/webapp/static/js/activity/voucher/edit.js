


$(document).ready(function() {
	$('.form_date').datetimepicker({
		 format: 'yyyy-mm-dd hh:ii:ss' 
	});
	$("#voucherForm").validate({
		rules : {
			batchNo : {
				required : true
			},
			startTime : {
				required : true
			},
			endTime : {
				required : true
			},
			days : {
				required : true,
				digits   : true
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
			batchNo : {
				required : "请输入批次开始号"
			},
			startTime : {
				required : "请输入兑换开始时间"
			},
			days : {
				required : "请输入兑换结束时间",
				digits   : "请输入有效时间"
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