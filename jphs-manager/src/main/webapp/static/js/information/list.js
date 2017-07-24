$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/information/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/information/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/information/detail.jhtml?id=" + id;
}
function deleteById(id,status) {
	window.location.href = "/information/delete.jhtml?id=" + id+"&status="+status;
}

function stickId(id,top) {
	var tin = getNowFormatDate();
	window.location.href = "/information/insert.jhtml?id=" + id+"&top="+top+"&topTimet="+tin;
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