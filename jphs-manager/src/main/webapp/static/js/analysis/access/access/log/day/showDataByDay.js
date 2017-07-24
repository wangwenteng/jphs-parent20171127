$(document).ready(function() {
	$('.form_date').datetimepicker({
	    language:  'zh-CN',
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});
	$("#search").click(function() {
		var days = $("#dtp_input1").val();
		if(days==""){
			alert("请选择查询日期");
		}else{
			$('#data').load("/access/log/showDataByDay.jhtml?days="+ days, "");
		}
	});
});
