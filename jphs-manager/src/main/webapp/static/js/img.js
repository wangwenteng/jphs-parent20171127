function ajaxFileUpload(imgid,inputid){
    //执行上传文件操作的函数
    $.ajaxFileUpload({
        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
        url:'/upload/index.jhtml?uname=jinpai&type=0',
        secureuri:false,                           //是否启用安全提交,默认为false 
        fileElementId :imgid,               //文件选择框的id属性
        dataType:'text',                           //服务器返回的格式,可以是json或xml等
        success:function(data, status){            //服务器响应成功时的处理函数
        	data=data.substr(data.indexOf('{"'),data.indexOf('"}')-1);
        	data = data.replace("</pre>", '');   //本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
        	data = data.replace("</", ''); 
        	data = data.replace("<", ''); 
        	data=eval("("+data+")");
            if(data.resultcode == 1){         //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
               $('img[id='+inputid+'s]').attr("src", data.msg);
               $("#"+inputid).val(data.msg);
            }else{
            	alert(data.msg);
            }
        },
        error:function(data, status, e){ //服务器响应失败时的处理函数
        	data=data.substr(data.indexOf('{"'),data.indexOf('"}')-1);
        	data = data.replace("</pre>", '');  
        	data = data.replace("</", ''); 
        	data = data.replace("<", ''); 
            var s= eval("("+data+")");
        	alert(s.mag);
        }
    });
}