$(document).ready(function() {
	$('.form_date1').datetimepicker({
		 format: 'yyyy-mm-dd hh:ii:ss' 
	});
})


$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/transaction/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#operate").empty();
		$("#payType").empty();
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/transaction/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/transaction/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/transaction/delete.jhtml?id=" + id;
}
