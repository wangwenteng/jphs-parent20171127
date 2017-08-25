$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/order/goods/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/order/goods/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/order/goods/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/order/goods/delete.jhtml?id=" + id;
}
