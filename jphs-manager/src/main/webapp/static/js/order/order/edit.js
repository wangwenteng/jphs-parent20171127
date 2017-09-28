$(function() {
	var date = new Date();
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$('#departmentId').val("");
	});
	$('.form_date').datetimepicker({
	    language:  'zh-CN',
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 2,
		startDate:date,
		forceParse: 0
	});
	
});