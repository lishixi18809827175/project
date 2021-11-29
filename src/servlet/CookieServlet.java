package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/setCookie")
public class CookieServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CookieServlet.service");
        Cookie cookie1 = new Cookie("goods", "IPhone");
        Cookie cookie2 = new Cookie("name", "IPhone");
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
    }
}
