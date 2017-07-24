$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/order/service/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	var url = "/order/service/redirectUpdate.jhtml?id=" + id;
	window.showModalDialog(url,window,'dialogWidth:400px;dialogHeight:400px');
}
function redirectDetailPage(id) {
	window.location.href = "/order/service/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/order/service/delete.jhtml?id=" + id;
}
