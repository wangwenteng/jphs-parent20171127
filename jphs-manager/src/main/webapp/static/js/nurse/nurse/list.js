$(function() {
	var date = new Date();
	alert(date);
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/nurse/redirectAddPage.jhtml";
	});
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
		startView: 2,
		minView: 2,
		startDate:'',
		forceParse: 0
	});
	
});
function redirectUpdatePage(id) {
	window.location.href = "/nurse/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/nurse/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/nurse/delete.jhtml?id=" + id;
}
