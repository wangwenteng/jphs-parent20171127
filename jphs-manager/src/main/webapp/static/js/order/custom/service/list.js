$(document).ready(function() {
	$('.form_date').datetimepicker({
		 format: 'yyyy-mm-dd hh:ii:ss' 
	});
})



$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/custom/service/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/custom/service/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/custom/service/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/custom/service/delete.jhtml?id=" + id;
}
