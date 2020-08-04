package tk.hellojarvis.web.servlet;

import tk.hellojarvis.db.entity.User;
import tk.hellojarvis.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : JarvisHsu
 * @create 2020/08/03 19:18
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String captcha = request.getParameter("Captcha");
        String captchaValue = (String) request.getSession().getAttribute("CaptchaValue");
        if(!captcha.equalsIgnoreCase(captchaValue)){
            response.getWriter().print(2);
        }else {
            String userName = request.getParameter("UserName");
            String passWord = request.getParameter("PassWord");
            User user = new User();
            user.setUserName(userName);
            user.setUserPwd(passWord);
            UserService userService = new UserService();
            boolean bool = userService.checkUser(user);
            if(bool){
                request.getSession().setAttribute("User",user);
                response.getWriter().print(1);
            }else {
                if (request.getSession().getAttribute("count")==null){
                    request.getSession().setAttribute("count",1);
                }
                response.getWriter().print(0);
            }

        }
        response.getWriter().flush();



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
