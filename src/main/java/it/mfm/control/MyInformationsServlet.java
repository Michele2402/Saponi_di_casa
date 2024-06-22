package it.mfm.control;


import it.mfm.model.UserDao;
import it.mfm.model.UserBean;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/MyInformations")
public class MyInformationsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDao userDao; // DAO for user operations
    private UserBean userInformation; // User information;

    // Initialize the servlet
    public void init() {
        userDao = new UserDao(); // Initialize the user DAO
        userInformation = new UserBean(); // Initialize the user information
    }

    // Handle GET requests
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    // Handle POST requests
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8"); // Set request encoding

        try {

            HttpSession session = request.getSession();
            UserBean user = (UserBean)session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("login.jsp"); // Redirect to the login page
                return;
            }

            String address = request.getParameter("indirizzoMyInf");
            String email = request.getParameter("emailMyInf");
            String name = request.getParameter("nomeMyInf");
            String surname = request.getParameter("cognomeMyInf");
            String phone = request.getParameter("telefonoMyInf");
            String password = request.getParameter("passwordInf");

            // Validate input
            if (!address.matches("^[a-zA-Z0-9\\s,]+$") ||
                    !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$") ||
                    !name.matches("^[a-zA-Z\\s]+$") ||
                    !surname.matches("^[a-zA-Z\\s]+$") ||
                    !phone.matches("^\\d{10}$") ||
                    !password.matches("/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/")) {
                response.sendRedirect("MyInformations.jsp?error=true"); // Redirect to the error page
                return;
            }

            userInformation.setIndirizzo(address);
            userInformation.setEmail(email);
            userInformation.setNome(name);
            userInformation.setCognome(surname);
            userInformation.setTelefono(phone);
            userInformation.setPassword(password);
            userInformation.setAdmin(user.getAdmin());
            userInformation.setUsername(user.getUsername());

            userDao.doUpdate(userInformation);
            session.setAttribute("user", user); // Update the user in the session

        } catch (SQLException e) {

            System.out.println("Error:" + e.getMessage());
            response.sendRedirect("Error.jsp"); // Redirect to the error page

        }
    }


}
