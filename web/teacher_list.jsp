<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.4.1-dist/css/bootstrap.css" />
</head>
<body>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/teacher_insert.jsp">添加</a>
    <table border="1" cellspacing="0" class="table table-striped table-bordered table-hover table-condensed">
        <tr>
            <td>ID</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>地址</td>
            <td>删除/编辑</td>
        </tr>

        <c:forEach items="${pageInfo.list}" var="teacher">
            <tr>
                <td>${teacher.id}</td>
                <td>${teacher.name}</td>
                <td>${teacher.age}</td>
                <td>${teacher.address}</td>
                <td>
                    <a class="btn btn-danger btn-sm" href="javascript:void(0)" onclick="deleteById(${teacher.id})">删除</a>
                    <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/teacher?method=selectById&id=${teacher.id}">编辑</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <nav aria-label="Page navigation">
        <ul class="pagination">

            <c:if test="${pageInfo.pageNo > 1}">
                <li>
                    <a href="${pageContext.request.contextPath}/teacher?method=selectByPage&pageNo=${pageInfo.pageNo-1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${pageInfo.pageNo == 1}">
                <li class="disabled">
                    <a aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${pageInfo.totalPage}" var="page">
                <c:if test="${page == pageInfo.pageNo}">
                    <li class="active">
                        <span href="${pageContext.request.contextPath}/teacher?method=selectByPage&pageNo=${page}">${page}<span class="sr-only"></span></span>
                    </li>
                </c:if>
                <c:if test="${page != pageInfo.pageNo}">
                    <li>
                        <a href="${pageContext.request.contextPath}/teacher?method=selectByPage&pageNo=${page}">${page}</a>
                    </li>
                </c:if>
            </c:forEach>

            <c:if test="${pageInfo.pageNo < pageInfo.totalPage}">
                <li>
                    <a href="${pageContext.request.contextPath}/teacher?method=selectByPage&pageNo=${pageInfo.pageNo+1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${pageInfo.pageNo == pageInfo.totalPage}">
                <li class="disabled">
                    <a aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>

    <script type="text/javascript">
        function deleteById(id) {
            var isDelete = confirm("是否确认删除？");
            if (isDelete) {
                location.href = '${pageContext.request.contextPath}/teacher?method=deleteById&id=' + id;
            }
        }
    </script>
</body>
</html>
