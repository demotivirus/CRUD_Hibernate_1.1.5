package servlet.admin;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/showAllUsers", "/admin/allUsers"})
public class ListUsersServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listUser", UserService.getInstance().getAll());
        resp.setStatus(200);
        req.getRequestDispatcher("/WEB-INF/admin/allUsers.jsp").forward(req, resp);
    }
}
