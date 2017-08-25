$(function() {
	$('.form_date').datetimepicker({
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : true,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 0
	});
	// $("#file-0a").fileinput({
	// showUpload : false,
	// showRemove : false,
	// uploadAsync : true,
	// uploadLabel : "上传",//设置上传按钮的汉字
	// uploadClass : "btn btn-primary",//设置上传按钮样式
	// showCaption : false,//是否显示标题
	// language : "zh",//配置语言
	// uploadUrl : "/nurse/images/insert.jhtml",// you must set a valid URL here
	// else you will get an error
	// maxFileSize : 0,
	// maxFilesNum : 1,
	// maxFileCount : 1,/*允许最大上传数，可以多个，当前设置单个*/
	// enctype : 'multipart/form-data',
	// allowedPreviewTypes : [ 'image' ], //allowedFileTypes: ['image', 'video',
	// 'flash'],
	// maxFileSize : 2000,
	// allowedFileExtensions : [ "jpg", "png", "gif" ],/*上传文件格式*/
	// msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
	// dropZoneTitle : "请通过拖拽图片文件放到这里",
	// dropZoneClickTitle : "或者点击此区域添加图片",
	// //uploadExtraData: {"id": id},//这个是外带数据
	// showBrowse : false,
	// browseOnZoneClick : true,
	// slugCallback : function(filename) {
	// return filename.replace('(', '_').replace(']', '_');
	// }
	// });
	$("#nurseForm").validate(
			{
				rules : {
					phone : {
						required : true,
						minlength : 11,
						// 自定义方法：校验手机号在数据库中是否存在
						// checkPhoneExist : true,
						isMobile : true
					},
					type : {
						required : true
					},
					aptitudeId : {
						required : true
					},
					name : {
						required : true,
						rangelength : [ 2, 25 ]
					// isChinese:true
					},
					sfz : {
						required : true,
						isIdCardNo : true
					},
					friendPhone : {
						minlength : 11,
						// 自定义方法：校验手机号在数据库中是否存在
						// checkPhoneExist : true,
						isMobile : true
					},
					workYear : {
						required : true,
						minlength : 10
					},
					hospital : {
						required : true
					},
					departmentId : {
						required : true
					},
					province1 : "required",
					city1 : "required",
					district1 : "required",
					adressName : {
						required : true,
						minlength : 5
					},
					sex : {
						required : true
					},
					// head_portrait:{
					// required : true
					// },
					// aptitude_positive:{
					// required : true
					// },
					// aptitude_negative:{
					// required : true
					// },
					// id_positive:{
					// required : true
					// },
					// id_negative:{
					// required : true
					// },
					brief : {
						required : true,
						maxlength : 100
					},
					details : {
						required : true,
						maxlength : 100
					}
				},
				messages : {
					phone : {
						required : "请输入手机号",
						minlength : "确认手机不能小于11个字符",
						isMobile : "请正确填写您的手机号码"
					},
					type : "请选择一项分类",
					aptitudeId : "请选择一项职称",
					name : {

						required : "请输入姓名",
						isChinese : "请输入中文"
					},
					friendPhone : {
						minlength : "确认手机不能小于11个字符",
						// 自定义方法：校验手机号在数据库中是否存在
						// checkPhoneExist : true,
						isMobile : "请正确填写您的手机号码"
					},
					sfz : {
						required : "请输入身份证号",
						isIdCardNo : "请输入正确的身份证号"
					},
					workYear : {
						required : "请选择参加工作时间",
					},
					hospital : "请输入相关医院的名称",
					departmentId : "请选择一个科室",
					province1 : "请选择省",
					city1 : "请选择市",
					district1 : "请选择区",
					adressName : "请完善联系地址",
					sex : "性别不能为空",
					// head_portrait:"请上传头像",
					// aptitude_positive:"请上传护士资格证正面",
					// aptitude_negative:"请上传护士资格证反面",
					// id_positive:"请上传身份证正面",
					// id_negative:"请上传身份证反面",
					brief : {
						required : "请输入个人简介",
						maxlength : "字符不要超过100字"
					},
					details : {
						required : "请输入详情",
						maxlength : "字符不要超过100字"
					}
				},
				submitHandler : function(form) {
					var k = 0;
					var nodes = $('#tt').tree('getChecked');
					var head_portrait = $('#head_portrait').val();
					var aptitude_positive = $('#aptitude_positive').val();
					var aptitude_negative = $('#aptitude_negative').val();
					var id_positive = $('#id_positive').val();
					var id_negative = $('#id_negative').val();
					if (head_portrait == "" || aptitude_positive == ""
							|| aptitude_negative == "" || id_positive == ""
							|| id_negative == "") {
						alert('请上传相关照片!!!!');
						return false;
					}
					if (nodes.length < 1) {
						alert('请至少选择一个服务区域!!!!');
						return false;
					}
					if (nodes.length != 0) {
						for (var i = 0; i < nodes.length; i++) {
							if (nodes[i].type != 3) {
								k += 1;
							}
						}
					}
					if (k > 0) {
						alert('请至少选择一个服务区域!!!!');
						return false;
					} else {
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
	$('#tt').tree({
		data : data.treeData
	});
});
function tr_product(id) {
	for (var a = 0; a < 20; a++) {
		$(".jobtitleType" + a).hide();
	}
	$(".jobtitleType" + id).show();
}