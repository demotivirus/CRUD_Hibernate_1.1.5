package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("mainPage.jsp").forward(req, resp);
        //getServletContext().getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User userLogin = UserService.getInstance().loginUser(name, password);
        resp.setStatus(200);

        //req.getSession().setAttribute(name, password);

        req.getSession().setAttribute("userLogin", userLogin);

        resp.sendRedirect("user-info");
    }
}
