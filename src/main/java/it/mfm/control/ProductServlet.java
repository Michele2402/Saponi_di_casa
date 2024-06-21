package it.mfm.control;

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

import static it.mfm.control.Utils.escapeHtml;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ProductDao productDao; // DAO for product operations
    private ProductBean productBean; // Product bean

    // Initialize the servlet
    public void init() {
        productDao = new ProductDao(); // Initialize the product DAO
        productBean = new ProductBean(); // Initialize the product bean
    }

    // Handle GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "add":

                String nome = escapeHtml(request.getParameter("nome"));
                String descrizione = escapeHtml(request.getParameter("descrizione"));
                int prezzo = Integer.parseInt(escapeHtml(request.getParameter("prezzo")));
                String immagine = escapeHtml(request.getParameter("immagine"));
                int IDcategoria = Integer.parseInt(escapeHtml(request.getParameter("IDcategoria")));

                //validation of the input
                if (!nome.matches("^[a-zA-Z0-9\\s]{1,50}$") ||
                        !descrizione.matches("^[a-zA-Z0-9\\s]{1,100}$") ||
                        !immagine.matches("^[a-zA-Z0-9\\s]{1,50}$")||
                        !(prezzo < 0)) {
                    response.sendRedirect("addProduct.jsp?error=true"); // Redirect to the add product page
                    return;
                }

                productBean.setNome(nome);
                productBean.setDescrizione(descrizione);
                productBean.setPrezzo(prezzo);
                productBean.setImmagine(immagine);
                productBean.setIDcategoria(IDcategoria);

                try {

                    productDao.doSave(productBean);
                    HttpSession session = request.getSession(); //get the session+
                    session.setAttribute("allProducts", productDao.doRetrieveAll()); //update the list of products

                } catch (SQLException e) {
                    System.out.println("Error:" + e.getMessage());
                    response.sendRedirect(request.getContextPath()+ "/Error.jsp");

                }

                response.sendRedirect(request.getContextPath() + "/admin/AddProduct.jsp");
                break;

            case "delete":

                int id = Integer.parseInt(request.getParameter("id"));
                try {
                    productDao.doDelete(id);
                    HttpSession session = request.getSession(); //get the session
                    session.setAttribute("allProducts", productDao.doRetrieveAll()); //update the list of products

                } catch (SQLException e) {
                    System.out.println("Error:" + e.getMessage());
                    response.sendRedirect(request.getContextPath()+ "/Error.jsp");
                }

                response.sendRedirect(request.getContextPath() + "/ViewCategory.jsp");

                break;

            case "modify":

                HttpSession session = request.getSession(); //get the session
                ProductBean currentProduct = (ProductBean) session.getAttribute("product"); //get the product to modify
                String nomeModify = escapeHtml(request.getParameter("nome"));
                String descrizioneModify = escapeHtml(request.getParameter("descrizione"));
                int prezzoModify = Integer.parseInt(escapeHtml(request.getParameter("prezzo")));
                String immagineModify = escapeHtml(request.getParameter("immagine"));
                int idModify = currentProduct.getIDcategoria();

                //validation of the input
                if (!nomeModify.matches("^[a-zA-Z0-9\\s]{1,50}$") ||
                        !descrizioneModify.matches("^[a-zA-Z0-9\\s]{1,50}$") ||
                        !immagineModify.matches("^[a-zA-Z0-9\\s]{1,50}$")||
                        prezzoModify >= 0) {
                    response.sendRedirect("modifyProduct.jsp?error=true"); // Redirect to the modify product page
                    return;
                }

                productBean.setIDcategoria(idModify);
                productBean.setNome(nomeModify);
                productBean.setDescrizione(descrizioneModify);
                productBean.setPrezzo(prezzoModify);
                productBean.setImmagine(immagineModify);

                try {

                    int idProduct = currentProduct.getID();
                    productDao.doUpdate(idProduct, productBean);
                    session.setAttribute("allProducts", productDao.doRetrieveAll()); //update the list of products

                } catch (SQLException e) {
                    System.out.println("Error:" + e.getMessage());
                    response.sendRedirect(request.getContextPath()+ "/Error.jsp");
                }

                response.sendRedirect(request.getContextPath() + "/ViewCategory.jsp");

                break;

            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    // Handle POST requests
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doGet(request, response);
    }

}
