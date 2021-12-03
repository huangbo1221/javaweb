package com.bo.day02;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @ClassName DownFileServlet
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/3 22:01
 * @Version 1.0
 */
public class DownFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、要获取下载的路径
//        String realPath = req.getServletContext().getRealPath("/kobe.jpg");
        String realPath = "D:\\projects\\javaweb\\javaweb_servlet\\servlet_03\\src\\main\\resources\\kobe.jpg";
        System.out.println("realpath: " + realPath);
        // 2、下载的文件名是啥
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
        // 3、想办法设置让浏览器能够支持（Content-Disposition）下载我们需要的东西
        // 使用URLEncoder.encode，是为了便于读取名称为中文的资源
        resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        // 4、获取下载文件的输入流
        FileInputStream inputStream = new FileInputStream(realPath);
        // 5、创建缓冲区
        byte[] bytes = new byte[1024];
        // 6、获取OutputStream对象
        ServletOutputStream outputStream = resp.getOutputStream();
        // 7、将FileOutputStream流写入到buffer缓冲区，使用OutputStream将缓冲区的数据输出到客户端
        int len = 0;
        while ((len = inputStream.read(bytes)) > 0) {
            outputStream.write(bytes);
        }
        inputStream.close();
        outputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
