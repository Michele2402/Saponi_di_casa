package it.mfm.control;

import it.mfm.model.ProductBean;
import it.mfm.model.ProductDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static it.mfm.control.Utils.escapeHtml;

@WebServlet("/Product")
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

        //Check if the user is an admin
        HttpSession session = request.getSession();
        if (session.getAttribute("isAdmin") == null || !((Boolean)session.getAttribute("isAdmin")) ){
            //System.out.println("admin status " + session.getAttribute("isAdmin") + " not authorized to access this page");
            response.sendRedirect("NotAuthorized.jsp");
            return;
        }

        String action = request.getParameter("action");

        switch (action) {

            case "add":

                String nome = escapeHtml(request.getParameter("nome"));
                String descrizione = escapeHtml(request.getParameter("descrizione"));
                int prezzo = Integer.parseInt(escapeHtml(request.getParameter("prezzo")));
                String immagine = request.getParameter("immagine");
                int IDcategoria = Integer.parseInt(escapeHtml(request.getParameter("IDcategoria")));
                System.out.println(immagine);

                if (!immagine.matches("^[a-zA-Z0-9\\s/.]{1,50}$") || (prezzo < 0)) {
                    System.out.println("Error: invalid input");
                    response.sendRedirect(request.getContextPath() + "/admin/AddProduct.jsp?error=true"); // Redirect to the add product page
                    return;
                }

                productBean.setNome(nome);
                productBean.setDescrizione(descrizione);
                productBean.setPrezzo(prezzo);
                productBean.setImmagine(immagine);
                productBean.setCategoria_id(IDcategoria);

                try {

                    productDao.doSave(productBean);
                    session.setAttribute("allProducts", productDao.doRetrieveAll()); //update the list of products

                } catch (SQLException e) {
                    System.out.println("Error:" + e.getMessage());
                    response.sendRedirect(request.getContextPath()+ "/Error.jsp");

                }

                response.sendRedirect(request.getContextPath() + "/admin/AddProduct.jsp?success=true");
                break;

            case "delete":
                String page = request.getParameter("page");
                int id = Integer.parseInt(request.getParameter("id"));
                try {
                    ProductBean newBean = new ProductBean();
                    newBean.setId(id);
                    productDao.doDelete(newBean);
                    session.setAttribute("allProducts", productDao.doRetrieveAll()); //update the list of products

                } catch (SQLException e) {
                    System.out.println("Error:" + e.getMessage());
                    response.sendRedirect(request.getContextPath()+ "/Error.jsp");
                }

                response.sendRedirect(request.getContextPath() + "/" + page);

                break;

            case "modify":

                ProductBean currentProduct = (ProductBean) session.getAttribute("product"); //get the product to modify
                String nomeModify = escapeHtml(request.getParameter("nome"));
                String descrizioneModify = escapeHtml(request.getParameter("descrizione"));
                int prezzoModify = Integer.parseInt(escapeHtml(request.getParameter("prezzo")));
                String immagineModify = request.getParameter("immagine");
                int idModify = currentProduct.getCategoria_id();

                //validation of the input
                if (!nomeModify.matches("^[a-zA-Z0-9\\s]{1,50}$") ||
                        //!descrizioneModify.matches("^[a-zA-Z0-9\\s]{1,50}$") ||
                        !immagineModify.matches("^[a-zA-Z0-9\\s/.]{1,50}$") ||
                        prezzoModify < 0) {
                    response.sendRedirect("admin/ModifyProduct.jsp?error=true"); // Redirect to the modify product page
                    return;
                }

                productBean.setCategoria_id(idModify);
                productBean.setNome(nomeModify);
                productBean.setDescrizione(descrizioneModify);
                productBean.setPrezzo(prezzoModify);
                productBean.setImmagine(immagineModify);

                try {

                    int idProduct = currentProduct.getId();
                    productBean.setId(idProduct);
                    productDao.doUpdate(productBean);
                    session.setAttribute("allProducts", productDao.doRetrieveAll()); //update the list of products

                } catch (SQLException e) {
                    System.out.println("Error:" + e.getMessage());
                    response.sendRedirect(request.getContextPath()+ "/Error.jsp");
                }

                response.sendRedirect(request.getContextPath() + "/admin/ViewCategory.jsp?update=true");

                break;

        }
    }

    // Handle POST requests
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       doGet(request, response);
    }

}
