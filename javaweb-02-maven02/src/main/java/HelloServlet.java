/**
 * @ClassName HelloServlet
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/2 21:10
 * @Version 1.0
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取相应的类型
        response.setContentType("text/html");
        // 获取相应的输出流
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello World!</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}
