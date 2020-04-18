package filter;

import model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class UserFilter implements Filter {
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("Filter initilized\n");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(); //для получения атрибута

        //получаем атрибуты из сервлета
        User userLogin = (User) session.getAttribute("userLogin");
        String userRole = userLogin.getRole();

        //перенаправляем на ReadServlet
        if (userRole.equals("admin")) {
            chain.doFilter(req, resp);
        }
        //перенаправляем на UserServlet
        else if (userRole.equals("user")) {
            req.setAttribute("userLogin", userLogin);
            resp.sendRedirect("user");
        }
        //перенаправляем на LoginServlet
        else if (userRole == null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/mainPage.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void destroy() {
        System.out.println("Filter destroyed");
    }
}
