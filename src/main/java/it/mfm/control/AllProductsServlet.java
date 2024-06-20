package it.mfm.control;

import it.mfm.fakeModel.CategoryBean;
import it.mfm.fakeModel.CategoryDao;
import it.mfm.fakeModel.ProductBean;
import it.mfm.fakeModel.ProductDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/allProducts")
public class AllProductsServlet extends HttpServlet {

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
                HttpSession session = request.getSession();
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
