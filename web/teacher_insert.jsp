<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css" />
</head>
<body>

<%--<form action="<%=request.getContextPath()%>/teacher?method=insert" method="post">
    姓名<input type="text" name="name"/><br>
    年龄<input type="text" name="age"/><br>
    地址<input type="text" name="address"/><br>
    <input type="submit" value="添加"/>
</form>--%>
<form action="<%=request.getContextPath()%>/teacher?method=insert" method="post">
    <div class="form-group">
        <label>姓名</label>
        <input type="text" class="form-control" name="name" style="width: auto">
    </div>
    <div class="form-group">
        <label>年龄</label>
        <input type="text" class="form-control" name="age" style="width: auto">
    </div>
    <div class="form-group">
        <label>地址</label>
        <input type="text" class="form-control" name="address" style="width: auto">
    </div>
    <button type="submit" class="btn btn-success">添加</button>
    <a class="btn btn-default" href="<%=request.getContextPath()%>/teacher?method=selectByPage">返回</a>
</form>
</body>
</html>
