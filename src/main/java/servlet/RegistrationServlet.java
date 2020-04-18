package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/registration", "/register"})
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
        //getServletContext().getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        User user;

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        int age = Integer.valueOf(req.getParameter("age"));
        String role = req.getParameter("role");

        if (role.equalsIgnoreCase("admin")){
            user = new User(name, password, age, role);
            UserService.getInstance().addUser(user);
            HttpSession session = req.getSession();
            req.setAttribute("user", user);
            resp.setStatus(200);
            req.getRequestDispatcher("/admin").forward(req, resp);
        }

        else {
            role = "user";
            user = new User(name, password, age, role);
            UserService.getInstance().addUser(user);
            req.getSession().setAttribute("user", user);
            resp.setStatus(200);
            req.getRequestDispatcher("user-info.jsp").forward(req, resp);
        }
    }
}
