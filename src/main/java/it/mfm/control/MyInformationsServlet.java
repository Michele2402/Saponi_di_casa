package it.mfm.control;


import it.mfm.fakeModel.UserDao;
import it.mfm.fakeModel.UserInformation;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/myInformations")
public class MyInformationsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDao userDao; // DAO for user operations
    private UserInformation userInformation; // User information;

    // Initialize the servlet
    public void init() {
        userDao = new UserDao(); // Initialize the user DAO
        userInformation = new UserInformation(); // Initialize the user information
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

            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");

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

            userInformation.setAddress(address);
            userInformation.setEmail(email);
            userInformation.setName(name);
            userInformation.setSurname(surname);
            userInformation.setPhone(phone);

            userDao.doUpdate(userInformation, user.getUsername());
            session.setAttribute("user", user); // Update the user in the session

        } catch (SQLException e) {

            System.out.println("Error:" + e.getMessage());
            response.sendRedirect("Error.jsp"); // Redirect to the error page

        }
    }


}
