<%@ page import="entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: wanghuide
  Date: 2021/8/11
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css" />
</head>
<body>
${student}
<%
    Student student = (Student) request.getAttribute("student");
%>
<form action="<%=request.getContextPath()%>/student?method=update" method="post">
    <%--    type设置为hidden之后，不会在页面上展示出来，但是参数还是可以传递--%>
    <input type="hidden" name="id" value="<%=student.getId()%>"/>
    名字：<input type="text" name="name" value="<%=student.getName()%>"/><br>
    年龄：<input type="text" name="age" value="<%=student.getAge()%>"/><br>
    性别：<input type="text" name="gender" value="<%=student.getGender()%>"/><br>
    <input type="submit" value="修改"/>
</form>
<%--form action="<%=request.getContextPath()%>/student?method=insert" method="post">
    <div class="form-group">
        <label>名字</label>
        <input type="text" class="form-control" name="name" style="width: auto">
    </div>
    <div class="form-group">
        <label>年龄</label>
        <input type="text" class="form-control" name="age" style="width: auto">
    </div>
    <div class="form-group">
        <label>性别</label>
        <input type="text" class="form-control" name="gender" style="width: auto">
    </div>
    <button type="submit" class="btn btn-default">添加</button>
</form>--%>
</body>
</html>
