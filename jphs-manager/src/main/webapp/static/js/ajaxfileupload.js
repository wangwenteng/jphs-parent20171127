jQuery.extend({

    createUploadIframe: function(id, uri)
	{
			//create frame
            var frameId = 'jUploadFrame' + id;
            
            if(window.ActiveXObject) {
                var io = document.createElement('<iframe id="' + frameId + '" name="' + frameId + '" />');
                if(typeof uri== 'boolean'){
                    io.src = 'javascript:false';
                }
                else if(typeof uri== 'string'){
                    io.src = uri;
                }
            }
            else {
                var io = document.createElement('iframe');
                io.id = frameId;
                io.name = frameId;
            }
            io.style.position = 'absolute';
            io.style.top = '-1000px';
            io.style.left = '-1000px';

            document.body.appendChild(io);

            return io			
    },
    createUploadForm: function(id, fileElementId,postData)
	{
		//create form	
		var formId = 'jUploadForm' + id;
		var fileId = 'jUploadFile' + id;
		var form = $('<form  action="" method="POST" name="' + formId + '" id="' + formId + '" enctype="multipart/form-data"></form>');	
		var oldElement = $('#' + fileElementId);
		var newElement = $(oldElement).clone();
		$(oldElement).attr('id', fileId);
		$(oldElement).before(newElement);
		$(oldElement).appendTo(form);
		//添加自定义参数  
        if (postData) {  
            //递归遍历JSON所有键值  
            function recurJson(json) {  
                for (var i in json) {  
                    //alert(i+"="+json[i])  
                    $("<input name='" + i + "' id='" + i + "' value='" + json[i] + "' />").appendTo(form);  
                    if (typeof json[i] == "object") {  
                        recurJson(json[i]);  
                    }  
                }  
            }  
            recurJson(postData);  
        }  
		
		//set attributes
		$(form).css('position', 'absolute');
		$(form).css('top', '-1200px');
		$(form).css('left', '-1200px');
		$(form).appendTo('body');		
		return form;
    },

    ajaxFileUpload: function(s) {
        // TODO introduce global settings, allowing the client to modify them for all requests, not only timeout		
       /* s = jQuery.extend({}, jQuery.ajaxSettings, s);*/
        s = jQuery.extend({fileFilter:"",fileSize:1}, jQuery.ajaxSettings, s);  
      //文件格式限制  
        var fileName = $('#' + s.fileElementId).val();  
        if(fileName == undefined || fileName == null || fileName == ""){  
            return;  
        }  
        var extention = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();  
        
        if (s.fileFilter && s.fileFilter.indexOf(extention) < 0) {  
            alert("不支持 " + extention + " 为后缀名的文件!");  
            $('#' + s.fileElementId).val("");  
            return;  
        } 
        
        if ("png".indexOf(extention) < 0) {  
            alert("不支持 " + extention + " 为后缀名的文件!");  
            $('#' + s.fileElementId).val("");  
            return;  
        }
        //文件大小限制  
        if (s.fileSize > 0) {  
            var fs = 0;  
            try {  
                if (window.ActiveXObject) {  
                    //IE浏览器  
                    var image = new Image();  
                    image.dynsrc = fileName;  
                    fs = image.fileSize;  
                } else {  
                    fs = $('#' + s.fileElementId)[0].files[0].size;  
                }  
            } catch(e) {  
            }
            fs = fs/1024/1024;
            if (fs > s.fileSize) {
            	if(fs.toString().indexOf('.') > 0 ){
            		fs = fs.toString().substring(0,fs.toString().indexOf('.')+2);
            	}
                /*fs = fs/1024; */ 
                /*fs = fs.toString().substring(0,4);  */
                alert("当前文件大小 " + fs + "MB 超过允许的限制值 " + s.fileSize +"MB！");  
                $('#' + s.fileElementId).val("");  
                return;  
            }  
        }  
        
        var id = s.fileElementId;        
		var form = jQuery.createUploadForm(id, s.fileElementId,s.data);
		var io = jQuery.createUploadIframe(id, s.secureuri);
		var frameId = 'jUploadFrame' + id;
		var formId = 'jUploadForm' + id;		
        // Watch for a new set of requests
        if ( s.global && ! jQuery.active++ )
		{
			jQuery.event.trigger( "ajaxStart" );
		}            
        var requestDone = false;
        // Create the request object
        var xml = {}   
        if ( s.global )
            jQuery.event.trigger("ajaxSend", [xml, s]);
        // Wait for a response to come back
        var uploadCallback = function(isTimeout)
		{			
			var io = document.getElementById(frameId);
            try 
			{				
				if(io.contentWindow)
				{
					 xml.responseText = io.contentWindow.document.body?io.contentWindow.document.body.innerHTML:null;
                	 xml.responseXML = io.contentWindow.document.XMLDocument?io.contentWindow.document.XMLDocument:io.contentWindow.document;
					 
				}else if(io.contentDocument)
				{
					 xml.responseText = io.contentDocument.document.body?io.contentDocument.document.body.innerHTML:null;
                	xml.responseXML = io.contentDocument.document.XMLDocument?io.contentDocument.document.XMLDocument:io.contentDocument.document;
				}						
            }catch(e)
			{
				jQuery.handleError(s, xml, null, e);
			}
            if ( xml || isTimeout == "timeout") 
			{				
                requestDone = true;
                var status;
                try {
                    status = isTimeout != "timeout" ? "success" : "error";
                    // Make sure that the request was successful or notmodified
                    if ( status != "error" )
					{
                        // process the data (runs the xml through httpData regardless of callback)
                        var data = jQuery.uploadHttpData( xml, s.dataType );    
                        // If a local callback was specified, fire it and pass it the data
                        if ( s.success )
                            s.success( data, status );
    
                        // Fire the global callback
                        if( s.global )
                            jQuery.event.trigger( "ajaxSuccess", [xml, s] );
                    } else
                        jQuery.handleError(s, xml, status);
                } catch(e) 
				{
                    status = "error";
                    jQuery.handleError(s, xml, status, e);
                }

                // The request was completed
                if( s.global )
                    jQuery.event.trigger( "ajaxComplete", [xml, s] );

                // Handle the global AJAX counter
                if ( s.global && ! --jQuery.active )
                    jQuery.event.trigger( "ajaxStop" );

                // Process result
                if ( s.complete )
                    s.complete(xml, status);

                jQuery(io).unbind()

                setTimeout(function()
									{	try 
										{
											$(io).remove();
											$(form).remove();	
											
										} catch(e) 
										{
											jQuery.handleError(s, xml, null, e);
										}									

									}, 100)

                xml = null

            }
        }
        // Timeout checker
        if ( s.timeout > 0 ) 
		{
            setTimeout(function(){
                // Check to see if the request is still happening
                if( !requestDone ) uploadCallback( "timeout" );
            }, s.timeout);
        }
        try 
		{
           // var io = $('#' + frameId);
			var form = $('#' + formId);
			$(form).attr('action', s.url);
			$(form).attr('method', 'POST');
			$(form).attr('target', frameId);
            if(form.encoding)
			{
                form.encoding = 'multipart/form-data';				
            }
            else
			{				
                form.enctype = 'multipart/form-data';
            }			
            $(form).submit();

        } catch(e) 
		{			
            jQuery.handleError(s, xml, null, e);
        }
        if(window.attachEvent){
            document.getElementById(frameId).attachEvent('onload', uploadCallback);
        }
        else{
            document.getElementById(frameId).addEventListener('load', uploadCallback, false);
        } 		
        return {abort: function () {}};	

    },

    uploadHttpData: function( r, type ) {
        var data = !type;
        data = type == "xml" || data ? r.responseXML : r.responseText;
        // If the type is "script", eval it in global context
        if ( type == "script" )
            jQuery.globalEval( data );
        // Get the JavaScript object, if JSON is used.
        if ( type == "json" )
            eval( "data = " + data );
        // evaluate scripts within html
        if ( type == "html" )
            jQuery("<div>").html(data).evalScripts();
			//alert($('param', data).each(function(){alert($(this).attr('value'));}));
        return data;
    },
  handleError: function( s, xhr, status, e )      {  
        // If a local callback was specified, fire it  
                if ( s.error ) {  
                    s.error.call( s.context || s, xhr, status, e );  
                }  
  
                // Fire the global callback  
                if ( s.global ) {  
                    (s.context ? jQuery(s.context) : jQuery.event).trigger( "ajaxError", [xhr, s, e] );  
                }  
    }
   /* handleError: function (s, xhr, status, e) {  
        if (s.error) {  
            s.error.call(s.context || s, xhr, status, e);  
        }  
        if (s.global) {  
            (s.context ? jQuery(s.context) : jQuery.event).trigger("ajaxError", [xhr, s, e]);  
        }  
    },  
    httpData: function (xhr, type, s) {  
        var ct = xhr.getResponseHeader("content-type"),  
xml = type == "xml" || !type && ct && ct.indexOf("xml") >= 0,  
data = xml ? xhr.responseXML : xhr.responseText;  
        if (xml && data.documentElement.tagName == "parsererror")  
            throw "parsererror";  
        if (s && s.dataFilter)  
            data = s.dataFilter(data, type);  
        if (typeof data === "string") {  
            if (type == "script")  
                jQuery.globalEval(data);  
            if (type == "json")  
                data = window["eval"]("(" + data + ")");  
        }  
        return data;  
    }  */
})