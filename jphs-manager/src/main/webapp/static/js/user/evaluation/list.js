
$(document).ready(function() {
	$('.form_date').datetimepicker({
		 format: 'yyyy-mm-dd hh:ii:ss' 
	});
})


$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/evaluation/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id,status) {
	window.location.href = "/evaluation/redirectUpdate.jhtml?id=" + id + "&status="+status;
}
function redirectDetailPage(id) {
	window.location.href = "/evaluation/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/evaluation/delete.jhtml?id=" + id;
}
