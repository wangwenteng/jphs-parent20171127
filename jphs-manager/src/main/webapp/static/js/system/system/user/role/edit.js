$(function() {
	$('#save').click(function() {
		var roleIds ='';
		var checkedId=$('input[name="roleId"]:checked');
		for (var i = 0; i < checkedId.length; i++) {
			if((i+1)==checkedId.length){
				roleIds +=checkedId[i].value;
			}else{
				roleIds +=checkedId[i].value+",";
			}
		}
		$('#roleIds').val(roleIds);
		alert($('#roleIds').val());
		 document.nurseForm.submit();
	});
});