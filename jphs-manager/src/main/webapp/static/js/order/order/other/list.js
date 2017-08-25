$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/order/other/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/order/other/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/order/other/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/order/other/delete.jhtml?id=" + id;
}
