package filter;

import entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "login", urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoginFilter.init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("LoginFilter.doFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String servletPath = httpServletRequest.getServletPath();
        System.out.println(servletPath);
        // /user?method=login
        String method = httpServletRequest.getParameter("method");

        /*int lastIndex = servletPath.lastIndexOf('.');
        String extension = servletPath.substring(lastIndex);
        System.out.println("extension: " + extension);*/

        // 这些操作就是要去完成登录，不需要去验证后面是不是登录的流程
        if (servletPath.equals("/login.jsp")
                || servletPath.equals("/user") && (method.equals("login"))
                || servletPath.equals("/fail.html")
                || servletPath.equals("/code")
                || servletPath.endsWith(".js")
                || servletPath.endsWith(".css")) {
            // 继续执行后面的Filter或者放行（即让浏览器进行这次访问）
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 没有登录的凭证
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("LoginFilter.destroy");
    }
}
