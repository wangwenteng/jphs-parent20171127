$(document).ready(function() {
	$('.form_date').datetimepicker({
		 format: 'yyyy-mm-dd hh:ii:ss' 
	});
})

$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/user/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#province10").empty();
		$("#city10").empty();
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/user/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/user/detail.jhtml?id=" + id;
}
function deleteById(id) {
	if(confirm("确定删除吗")){
		window.location.href = "/user/delete.jhtml?id=" + id;
	}
}
