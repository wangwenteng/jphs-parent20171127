window.onload=function(){
	$(".btn_addsss").click(function(){
		var trlength = Number($('.price_gradeAdd').length);
		/*alert(trlength);*/
		var a='<div class="price_gradeAdd"><span class="price_add_title">'
				+'		<input type="hidden" id="grade'+trlength+'" name="priceGrade['+trlength+'].grade" value="'+trlength+'" />'
				+'等级名称：<input style="width: 200px;" type="text" id="gradeName'+trlength+'" name="priceGrade['+trlength+'].gradeName" /></span>'
				+'	<div class="marage_right_content" style="margin-top: 0; padding-top: 0">'
				+'<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">'
				+'<tbody id="addtrprice'+trlength+'"> <tr class="headClass">'
				+'		<td width="100px">标--题</td>'
				+'		<td width="60px">服务次数</td>'
				+'		<td width="60px">成本价</td>'
				+'		<td width="60px">最高价</td>'	
				+'		<td width="60px">利润</td>'
				+'		<td width="60px">时长</td>'
				+'		<td width="60px">单位</td>'
				+'		<td width="60px">排序</td>'
				+'		<td width="60px">授权</td>'
				+'		<td width="60px">操作</td>'
				+'	</tr> </tbody> </table>'
				+'<div class="btn_add" onclick="addTd('+trlength+');">+</div></div></div>';
		
	    $(".norm_service").append(a);
	})
	
	
	/* 等级服务 */
	$('#saveJob').click(function(){
		var priceID= $('#priceId').val();
		var array = new Array();  //定义数组   
		$('#select2 option').each(function () {
		    var $option = $(this);
		    var value = $option.val();
		    if(value!=''){  
	               array.push(value);  //添加到数组中  
	          }  
		});
		$('#aptitudeIdArr'+priceID).val(array);
	})
	
	
	
	/* 标准服务 */
	$('.norm_service_l').click(function(){
		// norm_service 显示
		// grade_service 隐藏
		for(var a=0;a<100;a++){
			$('#status'+a).val('-1');
		}
		
		$(".addClassprice").remove();
		$(".btn_addsss").hide();
		/*
		 * $(".grade_service").hide(); $(".norm_service").show();
		 */
	})
	/* 等级服务 */
	$('.grade_service_l').click(function(){
		// grade_service 显示
		// norm_service 隐藏
		for(var a=0;a<100;a++){
			$('#status'+a).val('-1');
		}
		$(".addClassprice").remove();
		$(".btn_addsss").show();
		/*
		 * $(".norm_service").hide(); $(".grade_service").show();
		 */
	})
	
	$('#save').click(function() {
		var title=$('#title').val();
		if(title == null || title == ''){
			alert("标题不能为空~~");
			return false;
		}
		var subTitle=$('#subTitle').val();
		if(subTitle == null || subTitle == ''){
			alert("副标题不能为空~~");
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
		
		for(var a=0;a<50;a++){
	    	// dataPtitude
	    	if($("#aptitude"+a).length>0){
		 	    $("#dataPtitude"+a).val($("#aptitude"+a).val());
	    	}
	    }
		
		document.goodsForm.submit();
	});
	
	// 实例化编辑器
	var um = UM.getEditor('myEditor');
	um.addListener('blur', function() {
		$('#focush2').html('编辑器失去焦点了');
	});
	um.addListener('focus', function() {
		$('#focush2').html('');
	});
	
	var goodsId =$('#id').val();
	$('#tt').tree({
	    url: '/location/getAllLocation.jhtml?nurseId='+goodsId,    
	    loadFilter: function(data){
	        if (data.result ==1){    
	            return data.data;    
	        }   
	    } ,
	});
		
	for(var a=0;a<50;a++){
    	// dataPtitude
    	if($("#dataPtitude"+a).length>0){
	 	    var arr=$("#dataPtitude"+a).val().split(',');
	 	    $('#aptitude'+a).selectpicker('val', arr);
	 	    
	 	   $('#aptitude'+a).selectpicker({
		        'selectedText': 'cat'
		    });
    	}
    }
}

function addTd(id){
	
		var number = '';
		
		for(var a=1;a<61;a++){
			number+= '<option>'+a+'</option>'
		}
		// <input type="hidden" id="grade" name="grade" value="1" />
		/*var grade;
		if(id=='chuji'){
			grade = 1;
		}
		if(id=='zhongji'){
			grade = 2;
		}
		if(id=='gaoji'){
			grade = 3;
		}
		if(id=='biaozhun'){
			grade = 0;
		}*/
	/*
	 * var trlength = Number($('#addClassprice tr').length) -
	 * Number($('.headClass').length);
	 */
		var trlength = Number($('.addClassprice').length);
		var tr = '<tr class="addClassprice" id="delete'+trlength+'"><td><input style="width:100%" class="form-control" id="title'+trlength+'" name="priceGrade['+id+'].price['+trlength+'].title" /></td>'
				+'		<td><input style="width:100%" class="form-control" id="service_number'+id+''+trlength+'" name="priceGrade['+id+'].price['+trlength+'].serviceNumber" /></td>'
				+'		<td><input style="width:100%" class="form-control" id="price'+id+''+trlength+'" name="priceGrade['+id+'].price['+trlength+'].costPrice"  /></td>'
				+'		<td><input style="width:100%" class="form-control" id="price'+id+''+trlength+'" name="priceGrade['+id+'].price['+trlength+'].maxPrice"  /></td>'
				+'		<td><input style="width:100%" class="form-control" id="profit'+id+''+trlength+'" name="priceGrade['+id+'].price['+trlength+'].profit" /></td>'
				+'		<td>'
				+'			<select style="width: 100%;" class="form-control input-xlarge" id="service_time'+id+''+trlength+'" name="priceGrade['+id+'].price['+trlength+'].serviceTime">'
				+					number
				+'			</select>'
				+'		</td>'
				+'		<td>'
				+'			<select style="width: 100%;" class="form-control input-xlarge" id="unit'+id+''+trlength+'" name="priceGrade['+id+'].price['+trlength+'].unit">'
				+'				<option>秒</option>'
				+'				<option>分钟</option>'
				+'				<option>小时</option>'
				+'				<option>周</option>'
				+'				<option>月</option>'
				+'				<option>季</option>'
				+'				<option>年</option>'
				+'			</select>'
				+'		</td>'
				+'		<td>'
				+'			<select style="width: 100%;" class="form-control input-xlarge" id="sort'+id+''+trlength+'" name="priceGrade['+id+'].price['+trlength+'].sort">'
				+				number
				+'			</select>'
				+'		</td><td>'
				+'<img style="width: 20px;height: 20px;" src="https://jinpai.b0.upaiyun.com/jinpaihushi/JP20170802114112-25444.png" data-toggle="modal" onclick="setJobtitle(\''+id+''+trlength+'\');" data-target="#myModal" />'
				+'<input type="hidden" id="aptitudeIdArr'+id+''+trlength+'" name="priceGrade['+id+'].price['+trlength+'].aptitudeIdArr" value=""/>'
				+'</td>'
				+'<td>'
				+'	<img style="width: 20px;height: 20px;" src="/static/images/shanchu.png" id="delete'+id+''+trlength+'" onclick="deleteTr('+trlength+');">'
				+'</td>'
				+'	<input type="hidden" id="grade'+trlength+'" name="priceGrade['+id+'].price['+trlength+'].grade" value="'+id+'" />'
				+'	</tr>';
		$("#addtrprice"+id).append(tr);
		/*<img style="width: 20px;height: 20px;" src="https://jinpai.b0.upaiyun.com/jinpaihushi/JP20170802114112-25444.png" data-toggle="modal" onclick="setJobtitle('${priceOne.id }');" data-target="#myModal" />
		*//*<input type="button" style="width:100%" class="form-control" value="删除" id="delete'+id+''+trlength+'"  onclick="deleteTr('+trlength+');"  />
		*<button type="button" class="public-info public_btn public_btn_center" data-toggle="modal" onclick="setJobtitle(\''+id+''+trlength+'\');" data-target="#myModal" >修改</button>
*/}
//data-target="#myModal"
function setJobtitle(id){
	var aptitudeIdArr = $('#aptitudeIdArr'+id).val();
	$(".jobtitle").load("/goods/jobtitlePriceList.jhtml", {id: id,arr:aptitudeIdArr} , function(){
	});
}

function deleteTr(id){
	if(confirm('确实要删除吗？')){
		 $("#delete"+id).hide();
		 $('#status'+id).val('-1');
	}
}
