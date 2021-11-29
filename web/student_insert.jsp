<%@ page import="java.util.List" %>
<%@ page import="entity.Banji" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-2.1.4.js" charset="UTF-8"></script>
</head>
<body>
<%--<form action="<%=request.getContextPath()%>/student?method=insert" method="post">
    名字：<input type="text" name="name"/><br>
    年龄：<input type="text" name="age"/><br>
    性别：<input type="text" name="gender"/><br>
    <input type="submit" value="添加"/>
</form>--%>
<%
    List<Banji> list = (List<Banji>) request.getAttribute("banjiList");
%>
<form action="<%=request.getContextPath()%>/student?method=insert" method="post">
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
    <%--        班级: <select class="form-control" name="banjiId" style="width: auto">--%>
    <%--                <%--%>
    <%--                    for (Banji banji : list) {--%>
    <%--                %>--%>
    <%--                        <option value="<%=banji.getId()%>"><%=banji.getName()%></option>--%>
    <%--                <%--%>
    <%--                    }--%>
    <%--                %>--%>
    <%--             </select>--%>
    <div class="form-group">
        <label>班级</label>
        <select class="form-control" id="banjiId" name="banjiId" style="width: auto">
            <%--<%
                for (Banji banji : list) {
            %>--%>
            <option>----请选择----</option>
            <%--<%
                }
            %>--%>
        </select>
    </div>
    <button type="submit" class="btn btn-success">添加</button>
    <a class="btn btn-default" href="<%=request.getContextPath()%>/student?method=selectByPage">返回</a>
</form>

    <script type="text/javascript">
        $(function () {
            $.post(
                '<%=request.getContextPath()%>/banji?method=selectAll',
                function (jsonObj) {
                    console.log(jsonObj);

                    $(jsonObj).each(function () {
                        $('#banjiId').append('<option id="' + this.id + '">' + this.name + '</option>');
                    });
                }
            );
        });
    </script>
</body>
</html>
