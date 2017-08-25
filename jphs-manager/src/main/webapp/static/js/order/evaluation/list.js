$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/evaluation/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/order/evaluation/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/order/evaluation/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/order/evaluation/delete.jhtml?id=" + id;
}

function updateStatus(id,status) {
	window.location.href = "/order/evaluation/updateStatus.jhtml?id=" + id +"&status="+status;
}
