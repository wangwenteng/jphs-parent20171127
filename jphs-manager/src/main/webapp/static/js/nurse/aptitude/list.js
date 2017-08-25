$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/aptitude/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/aptitude/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/aptitude/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/aptitude/delete.jhtml?id=" + id;
}
