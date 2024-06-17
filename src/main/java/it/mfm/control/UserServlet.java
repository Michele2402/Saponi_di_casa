package it.mfm.control;

import it.mfm.model.UserBean;
import it.mfm.model.UserDao;

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

        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        userBean.setNome(nome);
        userBean.setCognome(cognome);
        userBean.setEmail(email);
        userBean.setPassword(password);
        userBean.setIndirizzo(indirizzo);
        userBean.setTelefono(telefono);
        userBean.setAdmin(admin);

        try {
            userDao.doSave(userBean);
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
