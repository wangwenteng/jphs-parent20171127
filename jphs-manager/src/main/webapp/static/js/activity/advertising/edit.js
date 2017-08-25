$(function() {
	$("#advertisingForm").validate(
			{
				rules : {
					sort : {
						number : true,
						digits : true,
					},
					type : {
						required : true
					},
				},
				messages : {
					sort : {
						number : "请输入合法的数字",
						digits : "只能输入整数",
					},
					type : "请选择渠道",
				},
				submitHandler : function(form) {
					var image = $('#image').val();
					var bgImage = $('#bgImage').val();
					if (image == "") {
						alert('请上传图片!!!!');
						return false;
					}
					// if (bgImage == "" ) {
					// alert('请上传图片!!!!');
					// return false;
					// }
					else {
						var province1 = $('#province1').val();
						var city1 = $('#city1').val();
						var district1 = $('#district1').val();
						var adressName = $('#adressName').val();
						$('#address').val(
								province1 + '-' + city1 + '-' + district1 + '-'
										+ adressName);
						// 获取选中的节点
						var areas = '';
						var nodes = $('#tt').tree('getChecked');
						for (var i = 0; i < nodes.length; i++) {
							areas += nodes[i].id + ',';
						}
						$('#areas').val(areas);
						form.submit();
						alert("操作成功");
					}
				}
			});

})