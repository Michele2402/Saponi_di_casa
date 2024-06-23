package it.mfm.control;

import it.mfm.model.ProductBean;
import it.mfm.model.ReviewBean;
import it.mfm.model.ReviewDao;
import it.mfm.model.UserBean;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet("/Review")
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

            String nomeProdotto = request.getParameter("prodotto_id");
            int idProdotto = 0;

            ArrayList<ProductBean> prodotti = (ArrayList<ProductBean>) session.getAttribute("allProducts");
            //find the product id
            for (ProductBean p : prodotti){
                if(p.getNome().equals(nomeProdotto)){
                    idProdotto = p.getId();
                }
            }

            ReviewBean review = new ReviewBean();
            review.setUtente_username(user.getUsername());
            review.setValutazione(Integer.parseInt(request.getParameter("valutazione")));
            review.setTesto(request.getParameter("testo"));
            review.setData(new Date(System.currentTimeMillis()));
            review.setProdotto_id(idProdotto);

            reviewDao.doSave(review);
            response.sendRedirect("MyOrders.jsp");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            response.sendRedirect("Error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
