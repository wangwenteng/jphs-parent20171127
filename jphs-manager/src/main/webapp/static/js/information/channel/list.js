$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/information/channel/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/information/channel/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/information/channel/detail.jhtml?id=" + id;
}
function deleteById(id,status) {
	window.location.href = "/information/channel/delete.jhtml?id=" + id+"&status="+status;
}
