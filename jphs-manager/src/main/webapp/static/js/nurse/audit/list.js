$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/audit/redirectAddPage.jhtml";
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
		forceParse: 0
	});
});

function redirectUpdatePage(id) {
	window.location.href = "/audit/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/audit/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/audit/delete.jhtml?id=" + id;
}
