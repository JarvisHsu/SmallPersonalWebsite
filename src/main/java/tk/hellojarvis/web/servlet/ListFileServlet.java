package tk.hellojarvis.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : JarvisHsu
 * @create 2020/07/27 14:38
 */
@WebServlet(name = "ListFileServlet", urlPatterns = "/listFileServlet")
public class ListFileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        Map<String, String> fileNameMap = new HashMap<>();
        listfile(new File(uploadFilePath), fileNameMap);
        request.setAttribute("fileNameMap", fileNameMap);
        request.getRequestDispatcher("/download.jsp").forward(request, response);
    }
    private void listfile(File file, Map<String, String> map) {
        if (!file.isFile()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    listfile(f, map);
                }
            }
        } else {
            String realName = file.getName().substring(file.getName().indexOf("_") + 1);
            System.out.println(realName);
            map.put(file.getName(), realName);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
