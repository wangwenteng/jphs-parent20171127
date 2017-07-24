$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/nurse/skill/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#status").get(0).selectedIndex=0;
	});
	$("#all").click(function(){   
	    if(this.checked){
	    	$('input:checkbox[name=skillId]').prop("checked", true); 
	    }else{   
	    	$('input:checkbox[name=skillId]').prop("checked", false);
	    } 
	    var valArr = new Array;
	    $('input[name="skillId"]:checked').each(function(i){
			valArr[i] = $(this).val();
	    });
		var vals = valArr.join(',');//转换为逗号隔开的字符串
	});
});

function redirectUpdatePage(id) {
	window.location.href = "/nurse/skill/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/nurse/skill/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/nurse/skill/delete.jhtml?id=" + id;
}
