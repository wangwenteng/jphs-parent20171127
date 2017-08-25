$(document).ready(function() {
	$(".form_date").datetimepicker({  
		 format: 'yyyy',  
         weekStart: 1,  
         autoclose: true,  
         startView: 4,  
         minView: 4,  
         forceParse: false,  
         language: 'zh-CN'
    });
	var startDay =timeList[0];
	/**
     * 获取上一个月
     *
     * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
     */
    function getPreYear(date) {
        var arr = date.split('-');
        var year = arr[0]; //获取当前日期的年份
        return parseInt(year) - 1;
    }
    
    /**
     * 获取下一个月
     *
     * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
     */        
    function getNextYear(date) {
    	var arr = date.split('-');
        var year = arr[0]; //获取当前日期的年份
        return parseInt(year) +1;
    }
	$("#last-year").click(function() {
		var year=getPreYear(startDay);
		$('#data').load("/access/log/showDataByYear.jhtml?year="+ year, "");
	});
	$("#next-year").click(function() {
		var year=getNextYear(startDay);
		$('#data').load("/access/log/showDataByYear.jhtml?year="+ year, "");
	});
	
	$("#search").click(function() {
		var year = $("#year").val();
		if(year==""){
			alert("请选择查询日期");
		}else{
			$('#data').load("/access/log/showDataByYear.jhtml?year="+ year, "");
		}
	});
});
