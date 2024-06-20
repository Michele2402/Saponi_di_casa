package it.mfm.control;

import it.mfm.fakeModel.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet("/details")
public class DetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDao productDao; // DAO for product operations
    private ProductBean productBean; // Product bean
    private CategoryBean categoryBean; // Category bean
    private CategoryDao categoryDao;

    // Initialize the servlet
    public void init() {
        productDao = new ProductDao(); // Initialize the product DAO
        productBean = new ProductBean(); // Initialize the product bean
        categoryBean = new CategoryBean(); // Initialize the category bean
        categoryDao = new CategoryDao(); // Initialize the category DAO

    }

    // Handle GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        int id = Integer.parseInt(request.getParameter("id"));

        try {
            productBean = productDao.doRetreveById(id);
            categoryBean = categoryDao.doRetrieveById(productBean.getIDcategoria());
            ArrayList<ReviewBean> reviews = productDao.doRetrieveReviewsById(id);
            HttpSession session = request.getSession();
            session.setAttribute("category", categoryBean);
            session.setAttribute("product", productBean);
            session.setAttribute("reviews", reviews);
            String redirectedpage = (String) session.getAttribute("page");
            response.sendRedirect(request.getContextPath() + "/" + redirectedpage);

            }

        catch (SQLException e) {

            System.out.println("Error:" + e.getMessage());
            response.sendRedirect("Error.jsp");

            }
    }

}

