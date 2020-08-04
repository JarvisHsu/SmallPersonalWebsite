package tk.hellojarvis.web.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : JarvisHsu
 * @create 2020/07/25 22:30
 */
@WebServlet(name = "UploadHandleServlet", urlPatterns = "/uploadHandleServlet")
public class UploadHandleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        //上传时生成的临时文件保存目录
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        File tempFile = new File(tempPath);
        //临时文件夹不存在，则创建
        if (!tempFile.exists() && !tempFile.isDirectory()) {
            System.out.println(savePath + "目录不存在，需要创建！");
            //创建临时目录
            //noinspection ResultOfMethodCallIgnored
            tempFile.mkdir();
        }
        //消息提示
        String message = "";
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            factory.setSizeThreshold(1024 * 100);
            //设置上传时生成的临时文件的保存目录
            factory.setRepository(tempFile);
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //监听文件上传进度
            upload.setProgressListener(new ProgressListener() {
                @Override
                public void update(long l, long l1, int i) {
                    System.out.println("文件大小为：" + l1 + ",当前已处理：" + l);
                }
            });
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(request)) {
                //按照传统方式获取数据
                return;
            }
            //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
            upload.setFileSizeMax(1024 * 1024);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
            upload.setSizeMax(1024 * 1024 * 10);
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    System.out.println(name + " = " + value);
                } else {
                    //如果fileitem中封装的是上传文件
                    //得到上传的文件名称
                    String filename = item.getName();
                    System.out.println(filename);
                    if (filename == null || "".equals(filename.trim())) {
                        continue;
                    }
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    //得到上传文件的扩展名,如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    String fileTye = filename.substring(filename.lastIndexOf(".") + 1);
                    System.out.println("上传文件的拓展名是： " + fileTye);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //得到文件保存的名称
                    String saveFileName = makeFileName(filename);
                    //得到文件的保存目录
                    String realSavePath = makePath(saveFileName, savePath);
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFileName);
                    //创建一个缓冲区
                    byte[] buffer = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while ((len = in.read(buffer)) > 0) {
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    message = "文件上传成功";
                }
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            message = "单个文件超出最大值！！！";
            e.printStackTrace();
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        } catch (FileUploadBase.SizeLimitExceededException e) {
            message = "上传文件的总的大小超出限制的最大值！！！";
            e.printStackTrace();
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        } catch (Exception e) {
            message = "文件上传失败！";
            e.printStackTrace();
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
     *
     * @param filename
     * @return
     */
    private String makeFileName(String filename) {
        return UUID.randomUUID().toString() + "_" + filename;
    }

    /**
     * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
     *
     * @param filename
     * @param filePath
     * @return
     */
    private String makePath(String filename, String filePath) {
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 0xf;
        int dir2 = (hashcode & 0xf0) >> 4;
        String dir = filePath + "\\" + dir1 + "\\" + dir2;
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }
}
