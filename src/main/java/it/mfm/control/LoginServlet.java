package it.mfm.control;

import it.mfm.fakeModel.PaymentMethodBean;
import it.mfm.fakeModel.UserBean;
import it.mfm.fakeModel.UserDao;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

// Servlet for handling login requests
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDao userDao; // DAO for user operations
    private ArrayList<PaymentMethodBean> paymentMethods; // List of payment methods

    // Initialize the servlet
    public void init() {
        userDao = new UserDao(); // Initialize the user DAO
        paymentMethods = null; // Initialize the payment methods list
    }

    // Handle POST requests
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8"); // Set request encoding

        String username = request.getParameter("username").trim(); // Get the username
        String password = hashPassword(request.getParameter("password").trim()); // Hash the password

        try {
            // Retrieve the user by username and password
            UserBean userBean = userDao.doRetrieveByUsernamePassword(username, password);
            if (userBean == null) {
                // If user not found, redirect to login page
                System.out.println(request.getContextPath());
                response.sendRedirect("login.jsp");
            } else {
                // If user found, create a new session
                HttpSession session = request.getSession();
                session.setAttribute("user", userBean); // Set the user in the session

                // Retrieve the payment methods for the user
                paymentMethods = UserDao.doRetrievePaymentMethods(userBean);
                session.setAttribute("paymentMethods", paymentMethods); // Set the payment methods in the session

                // Set the admin status in the session
                if(userBean.isAdmin())
                    session.setAttribute("isAdmin", true);
                else
                    session.setAttribute("isAdmin", false);

                // Redirect to home page
                response.sendRedirect("home.jsp");
            }
        } catch (SQLException e) {
            // Handle SQL exception
            System.out.println("Error:" + e.getMessage());
            response.sendRedirect("Error.jsp");
        }
    }

    // Handle GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response); // Delegate to doPost method
    }

    // Hash the password using SHA-256
    protected static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // Get the SHA-256 MessageDigest instance
            byte[] hash = md.digest(password.getBytes()); // Hash the password
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b)); // Convert the hash to hexadecimal
            }
            return sb.toString(); // Return the hashed password
        } catch (NoSuchAlgorithmException e) {
            // Handle NoSuchAlgorithmException
            throw new RuntimeException(e);
        }
    }
}