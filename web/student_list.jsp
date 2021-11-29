<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Student" %>
<%@ page import="util.PageInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/bootstrap-3.4.1-dist/css/bootstrap.css" />
</head>
<body>
<%--    虽然是超链接，但是美化成了按钮的效果--%>
<a class="btn btn-success" href="<%=request.getContextPath()%>/student_insert.jsp">添加</a>
<a class="btn btn-info" href="<%=request.getContextPath()%>/student?method=selectStudentBanjiByPage">显示学生班级信息</a>
<table border="1" cellspacing="0" class="table table-striped table-bordered table-hover table-condensed">
    <tr>
        <td>ID</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>性别</td>
        <td>删除/编辑</td>
        <%--            <td>编辑</td>--%>
    </tr>
    <%
        // request: 内置对象（这个对象已经创建好了，名字也起好了叫request）
        // 里面可以写任意的java代码--jsp脚本
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        ArrayList<Student> list = pageInfo.getList();
        for (Student student : list) {
    %>
    <tr>
        <td><%=student.getId()%></td>
        <td><%=student.getName()%></td>
        <td><%=student.getAge()%></td>
        <td><%=student.getGender()%></td>
        <%--                    <td><a href="<%=request.getContextPath()%>/student?method=deleteById&id=<%=student.getId()%>">删除</a></td>--%>
        <td>
            <a class="btn btn-danger btn-sm" href="javascript:void(0)" onclick="deleteById(<%=student.getId()%>)">删除</a>
            <a class="btn btn-warning btn-sm" href="<%=request.getContextPath()%>/student?method=selectById&id=<%=student.getId()%>">编辑</a>
        </td>
        <%--                    <td><a class="btn btn-warning" href="javascript:void(0)" onclick="deleteById(<%=student.getId()%>)">编辑</a></td>--%>
    </tr>
    <%
        }
    %>
</table>

<nav aria-label="Page navigation">
    <ul class="pagination">

        <%
            if (pageInfo.getPageNo() > 1) {
        %>
        <li>
            <a href="<%=request.getContextPath()%>/student?method=selectByPage&pageNo=<%=pageInfo.getPageNo()-1%>" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <%
        } else {
        %>
        <li class="disabled">
            <a aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <%
            }
        %>

        <%
            for (int i = 1; i <= pageInfo.getTotalPage(); i++) {
                if (i == pageInfo.getPageNo()) {
        %>
        <li class="active">
            <span href="<%=request.getContextPath()%>/student?method=selectByPage&pageNo=<%=i%>"><%=i%><span class="sr-only"></span></span>
        </li>
        <%
        } else {
        %>
        <li>
            <a href="<%=request.getContextPath()%>/student?method=selectByPage&pageNo=<%=i%>"><%=i%></a>
        </li>
        <%
                }
            }
        %>

        <%
            if (pageInfo.getPageNo() < pageInfo.getTotalPage()) {
        %>
        <li>
            <a href="<%=request.getContextPath()%>/student?method=selectByPage&pageNo=<%=pageInfo.getPageNo()+1%>" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
        <%
        } else {
        %>
        <li class="disabled">
            <a aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
        <%
            }
        %>

    </ul>
</nav>

<script type="text/javascript">
    function deleteById(id) {
        var isDelete = confirm("您确认要删除吗？");
        if (isDelete) {
            location.href = '<%=request.getContextPath()%>/student?method=deleteById&id=' + id;
        }
    }
</script>
</body>
</html>
