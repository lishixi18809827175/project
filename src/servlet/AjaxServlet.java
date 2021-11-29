package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AjaxServlet.doGet");
        String name = req.getParameter("name");
        System.out.println(name);

        // {"name" : "张三", "age" : 23}
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("{\"name\" : \"张三\", \"age\" : 23}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AjaxServlet.doPost");
        String name = req.getParameter("name");
        System.out.println(name);

        // {"name" : "李四", "age" : 23}
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("{\"name\" : \"李四\", \"age\" : 23}");
    }
}
