package servlet.admin;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/updateUser")
public class UpdateUserServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/admin/updateUser.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = new User(
                Long.valueOf(req.getParameter("id")),
                req.getParameter("name"),
                req.getParameter("password"),
                Integer.valueOf(req.getParameter("age")));

        UserService userService = UserService.getInstance();
        userService.updateUser(user);
        req.setAttribute("user", user);
        resp.setStatus(200);
        req.getRequestDispatcher("user-info.jsp").forward(req, resp);
    }
}
