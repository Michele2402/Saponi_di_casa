package it.mfm.control;

import it.mfm.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registerUser")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String indirizzo = request.getParameter("indirizzo");
        String telefono = request.getParameter("telefono");
        boolean admin = Boolean.parseBoolean(request.getParameter("admin"));

        User user = new User();
        user.setUsername(username);
        user.setNome(nome);
        user.setCognome(cognome);
        user.setEmail(email);
        user.setPassword(password);
        user.setIndirizzo(indirizzo);
        user.setTelefono(telefono);
        user.setAdmin(admin);

        try {
            userDao.doSave(user);
            response.sendRedirect("success.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }
}
