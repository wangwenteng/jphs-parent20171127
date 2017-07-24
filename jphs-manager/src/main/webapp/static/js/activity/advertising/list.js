$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/advertising/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/advertising/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/advertising/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/advertising/delete.jhtml?id=" + id;
}
