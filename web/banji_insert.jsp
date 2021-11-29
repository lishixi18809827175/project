<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加班级</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css" />
</head>
<body>
    <form action="<%=request.getContextPath()%>/banji?method=insert" method="post">
        <div class="form-group">
            <label>班级名称</label>
            <input type="text" class="form-control" name="name" style="width: auto">
        </div>
        <button type="submit" class="btn btn-success">添加</button>
        <a class="btn btn-default" href="<%=request.getContextPath()%>/banji?method=selectByPage">返回</a>
    </form>
</body>
</html>
