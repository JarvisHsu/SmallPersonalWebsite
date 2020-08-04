package tk.hellojarvis.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : JarvisHsu
 * @create 2020/07/24 13:14
 */
@SuppressWarnings("unchecked")
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        List<String> releaseList = new ArrayList<>();
        releaseList.add("login.jsp");
        releaseList.add("loginServlet");
        releaseList.add("captchaServlet");
        releaseList.add("checkCaptchaServlet");
        releaseList.add("errorPage.jsp");
        releaseList.add("js");
        releaseList.add("ico");
        releaseList.add("svg");
        releaseList.add("webspeed.js.map");
        releaseList.add("css");
        filterConfig.getServletContext().setAttribute("releaseList", releaseList);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        List<String> releaseList = (List<String>) servletRequest.getServletContext().getAttribute("releaseList");
        String requestUrl = request.getRequestURI();
        String substring1 = requestUrl.substring(requestUrl.lastIndexOf('.')+1);
        String substring = requestUrl.substring(requestUrl.lastIndexOf('/') + 1);
        //已登录或者拦截页是登录Servlet或者errorPage页，放行.
        //否则拦截并强制前往登录页
        if (request.getSession().getAttribute("User") != null || releaseList.contains(substring)||releaseList.contains(substring1)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            System.out.println(getClass().getName() + ": go to login.jsp");
            response.sendRedirect("./login.jsp");
        }
    }

    @Override
    public void destroy() {
    }
}
