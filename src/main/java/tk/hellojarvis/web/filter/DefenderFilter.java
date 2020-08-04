package tk.hellojarvis.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : JarvisHsu
 * @create 2020/08/03 22:35
 */
@WebFilter(filterName = "DefenderFilter",urlPatterns = "/*")
public class DefenderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(request.getSession().getAttribute("blackList")==null){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            int blackList = (int) request.getSession().getAttribute("blackList");
            if (blackList==1){
                System.out.println("拉黑此人");
                response.sendRedirect("http://www.baidu.com");
            }
        }
    }

    @Override
    public void destroy() {
    }
}
