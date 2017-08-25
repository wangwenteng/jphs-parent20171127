$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/information/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#informationChannelId").get(0).selectedIndex=0;
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/information/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/information/detail.jhtml?id=" + id;
}
function deleteById(id,status) {
	/*if(status == 0){
		if(confirm('确实启用吗？')){
			window.location.href = "/information/delete.jhtml?id=" + id+"&status="+status;
		}
	}else{
		if(confirm('确实停用吗？')){
			window.location.href = "/information/delete.jhtml?id=" + id+"&status="+status;
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
		window.location.href = "/information/delete.jhtml?id=" + id+"&status="+status;
	}
	
}

function stickId(id,top) {
	var tin = getNowFormatDate();
	var top_s = "";
	if(top == 0){
		top_s = "取消置顶";
	}else{
		top_s = "置顶";
	}
	if(confirm("确认要"+top_s+"吗?")){
	window.location.href = "/information/insert.jhtml?id=" + id+"&top="+top+"&topTimet="+tin;
	}
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}