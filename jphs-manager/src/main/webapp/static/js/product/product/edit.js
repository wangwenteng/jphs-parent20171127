window.onload=function(){
$('#save').click(function() {
		var title=$('#title').val();
		if(title == null || title == ''){
			alert("标题不能为空~~");
			return false;
		}
		var moveurl=$('#moveurl').val();
		if(moveurl == null || moveurl == '' || moveurl== undefined  || moveurl== 'undefined'){
			alert("请上传手机图片~~");
			return false;
		}
		var pcurl=$('#pcurl').val();
		if(pcurl == null || pcurl == '' || pcurl== undefined  || pcurl== 'undefined'){
			alert("请上传pc图片~~");
			return false;
		}
		document.productForm.submit();
	});
}