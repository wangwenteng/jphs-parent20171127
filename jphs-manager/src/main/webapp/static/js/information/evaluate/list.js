$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/information/evaluate/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});
	// 全选
	/*$("#statusAll").on("click", function(){
		$("input[name='status']").attr("checked","true"); 
	});
	// 全不选
	$("#statusNoAll").on("click", function(){
		$("input[name='status']").removeAttr("checked"); 
	});*/
	// 提交审核
	$("#save").on("click", function() {
		$("#auditForm").submit();
	});
	
	$('.devicexz').val($(".device").val());
	$('.statusxz').val($(".status").val());
});

/*function essenceById(id,essence){
	window.location.href = "/information/evaluate/insert.jhtml?id=" + id+"&audit=1&essence="+essence;
}*/

function byauditid(id){
	if(id == 0){
		var arr=new Array(); 
		var checkbox = document.getElementsByName('status'); 
			for(var i=0;i < checkbox.length; i++){ 
				if(checkbox[i].checked==true){ 
				arr.push(checkbox[i].value); 
				
			} 
		} 
		id = arr;
	}
	
	$('#auditarr').val(id);
	
}

function redirectUpdatePage(id) {
	window.location.href = "/information/evaluate/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/information/evaluate/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/information/evaluate/delete.jhtml?id=" + id;
}
