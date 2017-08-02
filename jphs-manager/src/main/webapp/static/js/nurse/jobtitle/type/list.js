$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/jobtitle/type/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/jobtitle/type/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/jobtitle/type/detail.jhtml?id=" + id;
}
function deleteById(id,status) {
	if(status==-1){
		if(confirm("您确定要删除吗？")){
			window.location.href = "/jobtitle/type/delete.jhtml?id=" + id+"&status="+status;
		}
	}else if(status ==0){
		if(confirm("您确定要停用吗？")){
			window.location.href = "/jobtitle/type/delete.jhtml?id=" + id+"&status="+status;
		}
	}else if(status == 1){
		if(confirm("您确定要开启吗？")){
			window.location.href = "/jobtitle/type/delete.jhtml?id=" + id+"&status="+status;
		}
	}
}
