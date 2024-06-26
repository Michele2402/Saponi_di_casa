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

import static it.mfm.control.LoginServlet.hashPassword;

@WebServlet("/Registration")
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

        String surname = request.getParameter("cognome").trim(); // Get the surname
        String phone = request.getParameter("telefono").trim(); // Get the phone
        String name = request.getParameter("nome").trim(); // Get the name
        String address = request.getParameter("indirizzo").trim();

///*
//        // Validate input
//        if (!username.matches("^[a-zA-Z0-9_]{1,20}$") ||
//                //!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$") ||
//                !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$") ||
//                !name.matches("^[a-zA-Z\\s]{1,20}$") ||
//                !surname.matches("^[a-zA-Z\\s]{1,20}$") ||
//                !phone.matches("^[0-9]{10}$")) {
//            System.out.println(request.getContextPath());
//            response.sendRedirect("Registration.jsp?action=error"); // Redirect to the registration page
//            return;
//        }
//*/

        // Create a new user bean
        userBean.setUsername(username);
        userBean.setPassword(password);
        userBean.setEmail(email);
        userBean.setNome(name);
        userBean.setCognome(surname);
        userBean.setTelefono(phone);
        userBean.setIndirizzo(address);
        System.out.println(request.getContextPath());


        System.out.println("Username: " + userBean.getUsername());
        System.out.println("Password: " + userBean.getPassword());
        System.out.println("Email: " + userBean.getEmail());
        System.out.println("Name: " + userBean.getNome());
        System.out.println("Surname: " + userBean.getCognome());
        System.out.println("Phone: " + userBean.getTelefono());
        System.out.println("Address: " + userBean.getIndirizzo());


        try {

            userDao.doSave(userBean); // Save the user
            HttpSession session = request.getSession();
            session.setAttribute("user", userBean); // Set the user in the session

            response.sendRedirect("Home.jsp"); // Redirect to the index page

        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
            response.sendRedirect("Error.jsp");
        }
    }
}


