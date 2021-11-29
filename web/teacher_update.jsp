<%@ page import="entity.Teacher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css" />
</head>
<body>
<%
    Teacher teacher = (Teacher) request.getAttribute("teacher");
%>

    <%--<form action="<%=request.getContextPath()%>/teacher?method=update&id=<%=id%>" method="post">
        姓名<input type="text" name="name" value="<%=name%>"/><br>
        年龄<input type="text" name="age" value="<%=age%>"/><br>
        地址<input type="text" name="address" value="<%=address%>"/><br>
        <input type="submit" value="修改"/>
    </form>--%>
    <form action="<%=request.getContextPath()%>/teacher?method=update" method="post">
        <input type="hidden" name="id" value="<%=teacher.getId()%>"/>
        <div class="form-group">
            <label>姓名</label>
            <input type="text" class="form-control" name="name" style="width: auto" value="<%=teacher.getName()%>">
        </div>
        <div class="form-group">
            <label>年龄</label>
            <input type="text" class="form-control" name="age" style="width: auto" value="<%=teacher.getAge()%>">
        </div>
        <div class="form-group">
            <label>地址</label>
            <input type="text" class="form-control" name="address" style="width: auto" value="<%=teacher.getAddress()%>">
        </div>
        <button type="submit" class="btn btn-default">确定</button>
</form>
</body>
</html>
