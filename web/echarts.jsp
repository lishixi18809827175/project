<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/static/echarts.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-2.1.4.js" charset="UTF-8"></script>
</head>
<body>
    <div id="main1" style="width: 600px;height:400px;"></div>
    <div id="main2" style="width: 600px;height:400px;"></div>

    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart1 = echarts.init(document.getElementById('main1'));
        // 指定图表的配置项和数据
        $.post(
            '${pageContext.request.contextPath}/banji?method=selectBanjiCount',
            function (jsonObj) {
                console.log(jsonObj);
                var xArray = new Array();
                var yArray = new Array();
                $(jsonObj).each(function (){
                    xArray.push(this.name);
                    yArray.push(this.value);
                })
                var option = {
                    title: {
                        text: '班级人数统计'
                    },
                    tooltip: {},
                    legend: {
                        data:['班级']
                    },
                    xAxis: {
                        data: xArray
                    },
                    yAxis: {},
                    series: [{
                        name: '人数',
                        type: 'bar',
                        data: yArray
                    }]
                };
                // 使用刚指定的配置项和数据显示图表
                myChart1.setOption(option);
            },
            'json'
        );

        // 基于准备好的dom，初始化echarts实例
        var myChart2 = echarts.init(document.getElementById('main2'));
        // 指定图表的配置项和数据
        $.post(
            '${pageContext.request.contextPath}/banji?method=selectBanjiCount',
            function (jsonObj) {
                console.log(jsonObj);
                var array = new Array();
                $(jsonObj).each(function (){
                    array.push(this);
                })
                var option = {
                    title: {
                        text: '班级学生人数统计',
                        subtext: '纯属虚构',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                    },
                    series: [
                        {
                            name: '班级名称',
                            type: 'pie',
                            radius: '50%',
                            data: array,
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                // 使用刚指定的配置项和数据显示图表
                myChart2.setOption(option);
            },
            'json'
        );
    </script>
</body>
</html>
