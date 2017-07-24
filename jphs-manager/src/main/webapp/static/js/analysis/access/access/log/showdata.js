$(document).ready(function() {
var pvChart = echarts.init(document.getElementById("accessPvChart")); 
var uvChart = echarts.init(document.getElementById("accessUvChart")); 
var pvData=[];
var uvData=[];
	for(var i =0 ; i<platformName.length;i++){
		pvData.push( {
            name:platformName[i],
            type:'bar',
            data:totalPv[i],
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            }
        }
		);
		uvData.push( {
            name:platformName[i],
            type:'bar',
            data:totalUv[i],
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            }
        }
		);
	}
	var uvOption= {
	        title : {
	            text: uvtext,
	        },
	        tooltip : {
	            trigger: 'axis'
	        },
	        legend: {
	            data:platformName
	        },
	        //右上角工具条
	        toolbox: {
	            show : true,
	            feature : {
	            	dataZoom:{
	            		yAxisIndex:'none'
	            	},
	                mark : {show: true},
	                dataView : {show: true, readOnly: false},
	                magicType : {show: true, type: ['line', 'bar']},
	                restore : {show: true},
	                saveAsImage : {show: true}
	            }
	        },
	        calculable : true,
	        xAxis : [
	            {
	                type : 'category',
	                boundaryGap : false,
	                data : timeList
	            }
	        ],
	        yAxis : [
	            {
	                type : 'value',
	                axisLabel : {
	                    formatter: '{value}次'
	                }
	            }
	        ],
	        series :  uvData
	    };
	var pvOption = {
	        title : {
	            text: pvtext,
	        },
	        tooltip : {
	            trigger: 'axis'
	        },
	        legend: {
	        	data:platformName
	        },
	        //右上角工具条
	        toolbox: {
	            show : true,
	            feature : {
	                mark : {show: true},
	                dataView : {show: true, readOnly: false},
	                magicType : {show: true, type: ['line', 'bar']},
	                restore : {show: true},
	                saveAsImage : {show: true}
	            }
	        },
	        calculable : true,
	        xAxis : [
	            {
	                type : 'category',
	                boundaryGap : false,
	                data : timeList
	} ],
		yAxis : [ {
			type : 'value',
			axisLabel : {
				formatter : '{value}次'
			}
		} ],
		series : pvData
	};
	// 为echarts对象加载数据
	pvChart.setOption(pvOption);
	uvChart.setOption(uvOption);
})
