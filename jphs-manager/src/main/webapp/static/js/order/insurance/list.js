$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/insurance/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/insurance/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/insurance/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/insurance/delete.jhtml?id=" + id;
}
