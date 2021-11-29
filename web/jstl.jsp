<%@ page import="entity.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        request.setAttribute("age", 18);
        request.setAttribute("gender", "女");
    %>
    <c:set var="age" value="20" scope="request"></c:set>
    ${age}
    <hr>
    <%--1、单条件判断--%>
    <c:if test="${age==20}">
        今年20岁了
    </c:if>
    <c:if test="${age!=20}">
        今年不是20岁了
    </c:if>
    <hr>
    <c:if test="${gender=='男'}">
        男
    </c:if>
    <c:if test="${gender=='女'}">
        女
    </c:if>
    <hr>

    <%--2、多条件判断--%>
    <c:set var="score" value="90" scope="request"></c:set>
    <c:choose>
        <c:when test="${score >= 90 && score <= 100}">
            优秀
        </c:when>
        <c:when test="${score >= 80 && score < 90}">
            良好
        </c:when>
        <c:when test="${score >= 70 && score < 80}">
            一般
        </c:when>
        <c:when test="${score >= 60 && score < 70}">
            及格
        </c:when>
        <c:otherwise>
            不及格
        </c:otherwise>
    </c:choose>
    <hr>

    <%--遍历集合--%>
    <c:forEach begin="0" end="5" var="i">
        ${i}<br>
    </c:forEach>
    <hr>
    <%
        Student student1 = new Student(1, "王五1", 23, "男");
        Student student2 = new Student(1, "王五2", 23, "男");
        Student student3 = new Student(1, "王五3", 23, "男");
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        application.setAttribute("stuList", list);
    %>
    <c:forEach items="${stuList}" var="student">
        ${student.id} -- ${student.name} -- ${student.age} <br>
    </c:forEach>
    <hr>
    <%
        Map<String, String> map = new HashMap<>();
        map.put("1", "11");
        map.put("2", "22");
        application.setAttribute("map", map);
    %>
    <c:forEach items="${map}" var="entry">
        ${entry.key} -- ${entry.value}
    </c:forEach>
</body>
</html>
