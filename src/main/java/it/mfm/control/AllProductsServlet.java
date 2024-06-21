package it.mfm.control;

import it.mfm.model.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/AllProducts")
public class AllProductsServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;

        private ProductDao productDao;
        private CategoryDao categoryDao;

        public void init() {
            productDao = new ProductDao();
            categoryDao = new CategoryDao();
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {

                List<CategoryBean> allCategories = categoryDao.doRetrieveAll();
                List<ProductBean> allProducts = productDao.doRetrieveAll();
                Cart cart = new Cart();
                HttpSession session = request.getSession();
                session.setAttribute("cart", cart);
                session.setAttribute("allProducts", allProducts);
                session.setAttribute("allCategories", allCategories);
                response.sendRedirect("Home.jsp");

            } catch (SQLException e) {
                System.out.println("Error:" + e.getMessage());
                response.sendRedirect("Error.jsp");
            }
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
        }

}
