$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/voucher/repertory/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/voucher/repertory/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/voucher/repertory/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/voucher/repertory/delete.jhtml?id=" + id;
}
