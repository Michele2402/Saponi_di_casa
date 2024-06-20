package it.mfm.control;

import it.mfm.fakeModel.ReviewBean;
import it.mfm.fakeModel.ReviewDao;
import it.mfm.fakeModel.UserBean;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;

@WebServlet("/review")
public class ReviewServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ReviewDao reviewDao;

    public void init() {
        reviewDao = new ReviewDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        try {
            // Create and save a new review
            ReviewBean review = new ReviewBean();
            review.setUtente_username(user.getUsername());
            review.setProdotto_id(Integer.parseInt(request.getParameter("prodotto_id")));
            review.setValutazione(Integer.parseInt(request.getParameter("valutazione")));
            review.setTesto(request.getParameter("testo"));
            review.setData(new Date(System.currentTimeMillis()));

            reviewDao.doSave(review);
            response.sendRedirect("MyOrders.jsp");

        } catch (SQLException e) {
            throw new ServletException("Error saving review", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
