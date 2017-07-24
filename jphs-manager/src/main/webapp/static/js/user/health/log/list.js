$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/health/log/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/health/log/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/health/log/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/health/log/delete.jhtml?id=" + id;
}
