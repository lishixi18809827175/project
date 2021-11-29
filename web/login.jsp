<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-2.1.4.js" charset="UTF-8"></script>
</head>
<body>
<form action="<%=request.getContextPath()%>/user?method=login" method="post">
    用户名：<input type="text" name="name"/><br>
    密码：<input type="password" name="password"/><br>
    验证码：<input type="text" name="code"/><img id="imgCode" src="<%=request.getContextPath()%>/code" onclick="refresh()"><br>
    <input type="submit" value="登录"/>
</form>

    <script type="text/javascript">
        function refresh() {
            $('#imgCode').attr('src','<%=request.getContextPath()%>/code?' + Math.random());
        }
    </script>
</body>
</html>
