$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/user/address/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/user/address/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/user/address/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/user/address/delete.jhtml?id=" + id;
}
