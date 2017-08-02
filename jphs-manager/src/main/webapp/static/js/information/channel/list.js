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
	/*if(status == 0){
		if(confirm('确实启用吗？')){
			window.location.href = "/information/channel/delete.jhtml?id=" + id+"&status="+status;
		}
	}else{
		if(confirm('确实停用吗？')){
			window.location.href = "/information/channel/delete.jhtml?id=" + id+"&status="+status;
		}
	}*/
	
	var stat = "删除";
	if(status == -1){
		stat = "删除";
	}else if(status == 1){
		stat = "启用";
	}else{
		stat = "停用";
	}
	
	if(confirm("确认要"+stat+"吗?")){
		window.location.href = "/information/channel/delete.jhtml?id=" + id+"&status="+status;
	}
	
	
}
