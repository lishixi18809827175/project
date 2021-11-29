<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css" />
</head>
<body>
<div class="jumbotron">
    <div class="container">
        <h1>Hello, world!</h1>
        <p>This is a Management System</p>
        <p><a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/student" role="button">Enter</a></p>
    </div>
</div>
</body>
</html>
