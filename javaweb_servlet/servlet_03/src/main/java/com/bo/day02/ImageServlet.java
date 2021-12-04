package com.bo.day02;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @ClassName ImageServlet
 * @Description 实现验证码功能
 * @Author huangbo1221
 * @Date 2021/12/4 21:33
 * @Version 1.0
 */
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 让浏览器3秒刷新一次
        resp.setHeader("refresh", "3");
        // 在内存中创建一个图片
        BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
        // 创建一个画笔
        Graphics graphics = image.getGraphics();
        // 设置图片的背景颜色，填充所有背景
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0 , 0,80, 20);
        // 再将画笔的颜色置为蓝色
        graphics.setColor(Color.BLUE);
        // 设置后面用这个笔写字时的font
        graphics.setFont(new Font(null, Font.BOLD, 20));
        // 开始写字
        graphics.drawString(getRandomNum(9999999), 0 , 20);

        // 告诉浏览器，这个请求用图片的方式打开
        resp.setContentType("image/jpeg");
        // 网站有缓存的能力，因此不让浏览器缓存
        resp.setDateHeader("expires", -1);
        resp.setHeader("Cache-Control",  "no-cache");
        resp.setHeader("Prama", "no-cache");

        // 把图片写给浏览器
        ImageIO.write(image, "jpg", resp.getOutputStream());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private String getRandomNum(int maxInt) {
        Random random = new Random();
        int num = random.nextInt(maxInt);
        String numStr = String.valueOf(num);
        int maxLength = Integer.valueOf(String.valueOf(maxInt).length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxLength - numStr.length(); i++) {
            sb.append(0);
        }
        return sb.toString() + numStr;
    }
}
