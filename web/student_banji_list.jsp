<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>学生班级</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap-3.4.1-dist/css/bootstrap.css" />
</head>
<body>
<a class="btn btn-default" href="${pageContext.request.contextPath}/student?method=selectByPage">返回</a>
    <table class="table table-striped table-bordered table-hover table-condensed">
        <tr>
            <td>学生ID</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>性别</td>
            <td>班级</td>
        </tr>

        <c:forEach items="${pageInfo.list}" var="studentBanji">
            <tr>
                <td>${studentBanji.studentId}</td>
                <td>${studentBanji.studentName}</td>
                <td>${studentBanji.studentAge}</td>
                <td>${studentBanji.studentGender}</td>
                <td>${studentBanji.banjiName}</td>
            </tr>
        </c:forEach>
    </table>


    <ul class="pagination">

        <c:if test="${pageInfo.pageNo > 1}">
            <li>
                <a href="${pageContext.request.contextPath}/student?method=selectStudentBanjiByPage&pageNo=${pageInfo.pageNo-1}" aria-label="Previous">
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
                    <span href="${pageContext.request.contextPath}/student?method=selectStudentBanjiByPage&pageNo=${page}">${page}<span class="sr-only"></span></span>
                </li>
            </c:if>

            <c:if test="${page != pageInfo.pageNo}">
                <li>
                    <a href="${pageContext.request.contextPath}/student?method=selectStudentBanjiByPage&pageNo=${page}">${page}</a>
                </li>
            </c:if>
        </c:forEach>

        <c:if test="${pageInfo.pageNo < pageInfo.totalPage}">
            <li>
                <a href="${pageContext.request.contextPath}/student?method=selectStudentBanjiByPage&pageNo=${pageInfo.pageNo+1}" aria-label="Next">
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
</body>
</html>
