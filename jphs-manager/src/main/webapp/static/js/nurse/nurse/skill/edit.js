$(function() {
	var amount =document.getElementById('skill.amount').value;
	$("#nurseSkillForm").validate({
		rules:{
			creatorName :{
				required:true,
				rangelength: [2,4],
				isChinese:true
			},
			price:{
				required:true,
				min:amount
			}
		},
		messages:{
			creatorName:{
				rquired:"请输入姓名",
				rangelength:"请输入最二到四位姓名",
				isChinese:"请输入汉字"
			},
			price:"请输入不低于平台价格的数字"
		},
		submitHandler : function(form) {
			var imgs=$("#image_url img");
			//var imgs = $('#image_url input').val();
			var flag =0;
			for(var i =0;i++;i<imgs.length){
				if(imgs[i].attr("src")==""){
					flag+=1;
				}
			}
			if(flag>0){
				alert('请最少上传一张图片');
			}else{
				var week1 =$('#week1').val();
				var week2 =$('#week2').val();
				var freeCycle =week1+'至'+week2;
				$('#freeCycle').val(freeCycle);
				alert("操作成功");
				form.submit();
			}
		}
	});
});