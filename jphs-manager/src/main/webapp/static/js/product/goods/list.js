$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/goods/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#productId").get(0).selectedIndex=0;
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/goods/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/goods/detail.jhtml?id=" + id;
}
function deleteById(id,status) {
	var stat = "删除";
	if(status == -1){
		stat = "删除";
	}else if(status == 1){
		stat = "启用";
	}else{
		stat = "停用";
	}
	
	if(confirm("确认要"+stat+"吗?")){
		window.location.href = "/goods/delete.jhtml?id=" + id+"&status="+status;
	}
	
}