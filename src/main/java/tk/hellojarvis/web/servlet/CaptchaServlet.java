package tk.hellojarvis.web.servlet;

import cn.personal.Captcha;

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
 * @create 2020/07/24 18:04
 */
@WebServlet(name = "CaptchaServlet", urlPatterns = "/captchaServlet")
public class CaptchaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Captcha captcha = new Captcha();
        request.getSession().setAttribute("CaptchaValue",captcha.getName());
        captcha.outputCaptcha(response.getOutputStream());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
