package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/lastAccessTime")
public class LastAccessTimeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 现在设置的访问时间，当下一次访问的时候就变成上一次访问的时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddhh:mm:ss");
        String time = format.format(date);
        Cookie cookie = new Cookie("lastAccessTime", time);
        resp.addCookie(cookie);

        String lastAccessTime = "";
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cook : cookies) {
                String name = cook.getName();
                if (name.equals("lastAccessTime")) {
                    lastAccessTime = cook.getValue();
                    break;
                }
            }
        }

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("您上一次访问的时间：" + lastAccessTime);
    }
}
