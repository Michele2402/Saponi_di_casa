package it.mfm.control;

import it.mfm.fakeModel.UserBean;
import it.mfm.fakeModel.UserDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static it.mfm.control.LoginServlet.hashPassword;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDao userDao; // DAO for user operations
    private UserBean userBean; // User bean

    // Initialize the servlet
    public void init() {
        userDao = new UserDao(); // Initialize the user DAO
        userBean = new UserBean(); // Initialize the user bean
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    // Handle POST requests
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8"); // Set request encoding

        String username = request.getParameter("username").trim(); // Get the username
        String password = hashPassword(request.getParameter("password").trim()); // Hash the password
        String email = request.getParameter("email").trim(); // Get the email
        String name = request.getParameter("name").trim(); // Get the name
        String surname = request.getParameter("surname").trim(); // Get the surname
        String address = request.getParameter("address").trim(); // Get the address
        String phone = request.getParameter("phone").trim(); // Get the phone

        // Validate input
        if (!username.matches("^[a-zA-Z0-9_]{1,20}$") ||
                !password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$") ||
                !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$") ||
                !name.matches("^[a-zA-Z\\s]{1,20}$") ||
                !surname.matches("^[a-zA-Z\\s]{1,20}$") ||
                !address.matches("^[a-zA-Z0-9\\s,]{1,50}$") ||
                !phone.matches("^[0-9]{10}$")) {
            response.sendRedirect("registration.jsp?error=true"); // Redirect to the registration page
            return;
        }

        // Create a new user bean
        userBean.setUsername(username);
        userBean.setPassword(password);
        userBean.setEmail(email);
        userBean.setNome(name);
        userBean.setCognome(surname);
        userBean.setIndirizzo(address);
        userBean.setTelefono(phone);

        try {

            userDao.doSave(userBean); // Save the user
            HttpSession session = request.getSession();
            session.setAttribute("user", userBean); // Set the user in the session
            response.sendRedirect("Home.jsp"); // Redirect to the index page

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("Error.jsp"); // Redirect to the registration page
        }
    }
}


