<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-2.1.4.js" charset="UTF-8"></script>
</head>
<body>
    <input type="button" value="get方式请求服务器" onclick="ajaxGet()"></input><span id="spanId1"></span><br>
    <input type="button" value="post方式请求服务器" onclick="ajaxPost()"></input><span id="spanId2"></span><br>
    <input type="button" value="ajax方式请求服务器" onclick="ajax()"></input><br>

    <script type="text/javascript">
        function ajaxGet() {
            $.get('<%=request.getContextPath()%>/ajax',
                {'name' : 'zhangsan'},
                function(data) {// callback(回调函数): 表示服务器成功响应所触发的函数
                    // 后台返回的是json对象，但是Ajax自动把json格式对象转换成js对象
                    console.log(data);
                    $('#spanId1').html(JSON.stringify(data));
                },
                'json'
            );
        }

        function ajaxPost() {
            $.post('<%=request.getContextPath()%>/ajax',
                {'name' : 'lisi'},
                function(data) {// callback(回调函数): 表示服务器成功响应所触发的函数
                    // 后台返回的是json对象，但是Ajax自动把json格式对象转换成js对象
                    console.log(data);
                    $('#spanId2').html(JSON.stringify(data));
                },
                'json'
            );
        }

        function ajax() {
            $.ajax({
                async : true,
                type : 'GET',
                url : '<%=request.getContextPath()%>/ajax',
                data : {'name' : '小赵'},
                success : function (jsonObj) {
                    console.log(jsonObj);
                },
                dataType : 'json',
            });
        }
    </script>
</body>
</html>
