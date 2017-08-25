$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/aptitude/nurse/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/aptitude/nurse/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/aptitude/nurse/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/aptitude/nurse/delete.jhtml?id=" + id;
}
