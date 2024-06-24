package it.mfm.control;

import it.mfm.model.ProductBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static it.mfm.control.LoginServlet.hashPassword;
@WebServlet("/Search")
public class SearchServlet extends HttpServlet {

    //servlet that takes as input a string and redirects to the page of the corresponding product
    private static final long serialVersionUID = 1L;

    // Initialize the servlet
    public void init() {
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

            String search = request.getParameter("query");
            HttpSession session = request.getSession();
            ArrayList<ProductBean> products = (ArrayList<ProductBean>)session.getAttribute("allProducts");

            for(ProductBean product : products){
                if(product.getNome().toLowerCase().contains(search.toLowerCase())){
                    System.out.println("Found product: " + product.getNome());
                    RequestDispatcher rd = request.getRequestDispatcher("Details?id=" + product.getId() + "&page=Product.jsp");
                    rd.forward(request,response);
                    return;
                }
            }
            // Redirect to the product page
            response.sendRedirect("Home.jsp?error=true");

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            response.sendRedirect("Error.jsp");
        }
    }

}
