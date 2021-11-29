<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑班级信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.4.1-dist/css/bootstrap.css" />
</head>
<body>
    <form action="${pageContext.request.contextPath}/banji?method=update" method="post">
        <input type="hidden" name="id" value="${banji.id}"/>
        <div class="form-group">
            <label>班级名称</label>
            <input type="text" class="form-control" name="name" style="width: auto" value="${banji.name}">
        </div>
        <button type="submit" class="btn btn-default">确定</button>
    </form>
</body>
</html>
