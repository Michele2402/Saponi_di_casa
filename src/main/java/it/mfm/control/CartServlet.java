package it.mfm.control;

import it.mfm.model.ProductBean;
import it.mfm.model.ProductDao;
import it.mfm.model.Cart;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Cart")
public class CartServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private Cart cart;
    private ProductDao productDao;

    public void init() {
        cart = new Cart();
        productDao = new ProductDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            HttpSession session = request.getSession();
            String action = request.getParameter("action");
            String id = request.getParameter("id");
            String pageredirect = request.getParameter("page");




            if(action != null && id != null) {
                ProductBean product = productDao.doRetrieveById(Integer.parseInt(id));
                if(product != null) {
                    switch (action) {
                        case "add":

                            cart.addProduct(product);
                            break;

                        case "remove":

                            cart.decreaseProduct(product);
                            break;

                        case "delete":

                            cart.removeProduct(product);
                            break;

                    }

                }
            }

            session.setAttribute("cart", cart);
            response.sendRedirect(request.getContextPath() + "/" + pageredirect);

        } catch(SQLException e) {
            System.out.println("Error:" + e.getMessage());
            response.sendRedirect("Error.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
