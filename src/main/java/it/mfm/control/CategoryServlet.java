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
import java.util.ArrayList;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CategoryDao categoryDao;

    public void init() {
        categoryDao = new CategoryDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String redirectedPage = request.getParameter("page");

        try {
            if ("add".equals(action)) {
                // Create and save a new category
                String nome = request.getParameter("nome");
                String descrizione = request.getParameter("descrizione");

                CategoryBean category = new CategoryBean();
                category.setNome(nome);
                category.setDescrizione(descrizione);

                categoryDao.doSave(category);
                //update the list of categories
                HttpSession session = request.getSession();
                session.setAttribute("categories", categoryDao.doRetrieveAll());

            } else if ("find".equals(action)) {
                // Find an existing category
                int id = Integer.parseInt(request.getParameter("id"));
                CategoryBean category = categoryDao.doRetrieveById(id);

                HttpSession session = request.getSession();
                ProductDao productDao = new ProductDao();
                ArrayList<ProductBean> categoryProducts = productDao.doRetrieveByCategory(id);
                session.setAttribute("category", category);
                session.setAttribute("categoryProducts", categoryProducts);

            }

        } catch (SQLException e) {
            throw new ServletException("Error updating category", e);
        }

        response.sendRedirect(request.getContextPath() + "/" + redirectedPage);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
