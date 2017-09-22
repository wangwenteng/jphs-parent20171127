$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/withdraw/cash/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/withdraw/cash/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/withdraw/cash/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/withdraw/cash/delete.jhtml?id=" + id;
}
