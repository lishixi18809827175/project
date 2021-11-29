package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// /*代表拦截所有的请求
@WebFilter(filterName = "hello", urlPatterns = "/*")
public class HelloFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("HelloFilter.init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("HelloFilter.doFilter");
        // 如果有下一个过滤器，就执行下一个过滤器，如果没有就访问这次请求要访问的后台资源
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("HelloFilter.destroy");
    }
}
