<%@ page import="entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--JSP脚本--%>
    <%
        // HttpServletRequest request = new HttpServletRequest();
        // ServletContext application = new ServletContext();
        // 内置对象(页面已经创建好了的对象)：pageContext、request、session、application
        // 1、普通字符串
        pageContext.setAttribute("name", "lisi");
        request.setAttribute("name", "zhangsan");
        // 2、Student对象
        Student student = new Student(1, "王五", 23, "男");
        session.setAttribute("student", student);
        // 3、List集合
        Student student1 = new Student(1, "王五1", 23, "男");
        Student student2 = new Student(1, "王五2", 23, "男");
        Student student3 = new Student(1, "王五3", 23, "男");
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        application.setAttribute("stuList", list);
    %>


    <%--1、普通字符串--%>
    <%--JSP表达式方式拿出来--%>
    <%=pageContext.getAttribute("name")%><br>
    <%=request.getAttribute("name")%><br>
    <hr>
    <%--EL表达式的方式--%>
    ${pageScope.name}<br>
    ${requestScope.name}<br>
    <%--直接写name的话,如果有重名的情况,会按域对象作用范围从小到大的
    顺序来拿,所以这里显示的是pageContext里的,但是一般不会重名--%>
    ${name}<br>
    <hr>

    <%--2、获取对象--%>
    <%--使用JSP表达式--%>
    <%=session.getAttribute("student")%><br>
    <%
        Student stu = (Student) session.getAttribute("student");
    %>
    <%=stu.getId()%>--<%=stu.getName()%><br>
    <hr>
    <%--EL表达式--%>
    ${student}<br>
    ${student.id}<br>
    ${student.name}<br>
    <hr>

    <%--List<Student>--%>
    <%-- 使用JSP表达式--%>
    <%
        List<Student> stuList = (List<Student>) application.getAttribute("stuList");
    %>
    <%=stuList.get(0).getName()%><br>
    <hr>
    <%--EL表达式--%>
    ${stuList}<br>
    ${stuList[0].name}
    <hr/>

    <%--获取项目路径--%>
    <%--JSP表达式--%>
    <%=request.getContextPath()%><br>
    <hr>
    <%--EL表达式--%>
    ${pageContext.request.contextPath}
    <hr>

</body>
</html>
