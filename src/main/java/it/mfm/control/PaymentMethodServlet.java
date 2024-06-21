package it.mfm.control;

import it.mfm.fakeModel.PaymentMethodBean;
import it.mfm.fakeModel.PaymentMethodDao;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static it.mfm.control.Utils.escapeHtml;

@WebServlet("/paymentMethod")
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
        String redirectedPage = escapeHtml(request.getParameter("page"));
        HttpSession session = request.getSession();

        UserBean user = (UserBean)session.getAttribute("user");
        String username = user.getUsername();

        try {
            if ("add".equals(action)) {

                String nome = escapeHtml(request.getParameter("nome"));
                String cognome = escapeHtml(request.getParameter("cognome"));
                String numeroCarta = escapeHtml(request.getParameter("numeroCarta"));
                Date scadenza = stringToDate(escapeHtml(request.getParameter("scadenza")));
                String cvv = escapeHtml(request.getParameter("cvv"));

                //validate input
                if (!nome.matches("^[a-zA-Z\\s]{1,20}$") ||
                        !numeroCarta.matches("^[0-9]{16}$")||
                        !cognome.matches("^[a-zA-Z\\s]{1,20}$") ||
                        !cvv.matches("^[0-9]{3}$")) {
                    response.sendRedirect("AddPaymentMethod.jsp?error=true");
                    return;
                }

                int numeroCartaInt = Integer.parseInt(numeroCarta);
                int cvvInt = Integer.parseInt(cvv);

                paymentMethodBean.setCvv(cvvInt);
                paymentMethodBean.setNome(nome);
                paymentMethodBean.setCognome(cognome);
                paymentMethodBean.setNumero_di_carta(numeroCartaInt);
                paymentMethodBean.setData_di_Scadenza(scadenza);
                paymentMethodBean.setUtente_username(username);
                // Add the payment method to the database

                paymentMethodDao.doSave(paymentMethodBean);

                // Update the payment methods in the session
                UserDao userDao = new UserDao();
                session.setAttribute("paymentMethods", userDao.doRetrievePaymentMethods(user));

            } else if ("delete".equals(action)) {
                // Remove the payment method from the database
                paymentMethodDao.doDeleteByNumber(Integer.parseInt(escapeHtml(request.getParameter("numeroCarta"))));
                // Update the payment methods in the session
                UserDao userDao = new UserDao();
                session.setAttribute("paymentMethods", userDao.doRetrievePaymentMethods(user));

            }
        } catch (SQLException e) {
            throw new ServletException("Error updating payment method", e);
        }

        // Redirect to the page
        response.sendRedirect(request.getContextPath() + "/" + redirectedPage);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    public static Date stringToDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
