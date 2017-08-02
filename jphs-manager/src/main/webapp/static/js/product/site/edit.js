$(function() {
	/*var siteId =$('#id').val();
	$('#tt').tree({
	    url: '/location/getAllLocation.jhtml?nurseId='+siteId,    
	    loadFilter: function(data){
	        if (data.result ==1){    
	            return data.data;    
	        }   
	    } ,
	}); */
	$('#tt').tree({
		data : data.treeData
	});
	$('#save').click(function() {
		var name=$('#name').val();
		if(name == null || name == ''){
			alert("站名不能为空~~");
			return false;
		}
		var url=$('#url').val();
		if(url == null || url == ''){
			alert("url不能为空~~");
			return false;
		}
		//获取选中的节点
		var areas ='';
		var nodes = $('#tt').tree('getChecked'); 
		for (var i = 0; i < nodes.length; i++) {
			areas +=nodes[i].id+',';
		}
		if(areas == null || areas == ''){
			alert("区域不能为空~~");
			return false;
		}
		$('#areas').val(areas);
		document.siteForm.submit();
	});
	
	
})

function tr_product(id){
	for(var a=0;a<20;a++){
		$(".price"+a).hide();
	}
	$(".price"+id).show();
}
