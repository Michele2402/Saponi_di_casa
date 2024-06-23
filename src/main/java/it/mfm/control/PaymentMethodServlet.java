package it.mfm.control;

import it.mfm.model.PaymentMethodBean;
import it.mfm.model.PaymentMethodDao;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static it.mfm.control.Utils.escapeHtml;

@WebServlet("/PaymentMethod")
public class PaymentMethodServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private PaymentMethodDao paymentMethodDao;
    private PaymentMethodBean paymentMethodBean;

    public void init() {
        paymentMethodDao = new PaymentMethodDao();
        paymentMethodBean = new PaymentMethodBean();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = escapeHtml(request.getParameter("action"));

        HttpSession session = request.getSession();

        UserBean user = (UserBean)session.getAttribute("user");
        String username = user.getUsername();

        try {
            if ("add".equals(action)) {

                String nome = escapeHtml(request.getParameter("no"));
                String cognome = escapeHtml(request.getParameter("co"));
                String numeroCarta = escapeHtml(request.getParameter("nu"));
                String data = escapeHtml(request.getParameter("da"));
                System.out.println("Data: " + data);
                Date scadenza = stringToDate(escapeHtml(request.getParameter("da")));
                String cvv = escapeHtml(request.getParameter("cv"));

                //validate input
                if (!nome.matches("^[a-zA-Z\\s]{1,20}$") ||
                        !numeroCarta.matches("^[0-9]{16}$")||
                        !cognome.matches("^[a-zA-Z\\s]{1,20}$") ||
                        !cvv.matches("^[0-9]{3}$")) {
                    response.sendRedirect("AddPaymentMethod.jsp?error=true");
                    return;
                }

                paymentMethodBean.setCvv(cvv);
                paymentMethodBean.setNome(nome);
                paymentMethodBean.setCognome(cognome);
                paymentMethodBean.setNumero_di_carta(numeroCarta);
                paymentMethodBean.setData_di_Scadenza(scadenza);
                System.out.println("Scadenza: " + scadenza);
                paymentMethodBean.setUtente_username(username);
                // Add the payment method to the database

                paymentMethodDao.doSave(paymentMethodBean);

                // Update the payment methods in the session
                UserDao userDao = new UserDao();
                session.setAttribute("paymentMethods", userDao.doRetrivePaymentMethods(user));

            } else if ("delete".equals(action)) {
                // Remove the payment method from the database
                paymentMethodDao.doDeleteByNumber(escapeHtml(request.getParameter("numeroCarta")));
                // Update the payment methods in the session
                UserDao userDao = new UserDao();
                session.setAttribute("paymentMethods", userDao.doRetrivePaymentMethods(user));

            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            response.sendRedirect("Error.jsp");
        }

        // Redirect to the page
        response.sendRedirect(request.getContextPath() + "/Account.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    public static Date stringToDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
