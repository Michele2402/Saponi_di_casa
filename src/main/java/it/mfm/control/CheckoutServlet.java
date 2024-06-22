package it.mfm.control;

import it.mfm.model.*;
import it.mfm.model.Cart;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Checkout")
public class CheckoutServlet extends HttpServlet {

    private OrderDao orderDao;
    private PurchasedProductDao purchasedProductDao;

    public void init() {
        orderDao = new OrderDao();
        purchasedProductDao = new PurchasedProductDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        UserBean user = (UserBean) session.getAttribute("user");

        try {
            // Create and save a new order
            OrderBean order = new OrderBean();
            order.setUtente_username(user.getUsername());
            order.setTotale(cart.geTotal());
            order.setData_creazione(new java.sql.Date(System.currentTimeMillis()));
            int orderId = orderDao.doSave(order);

            // get all products and their quantities from the cart

            ArrayList<PurchasedProductBean> purchasedProducts = getPurchasedProductBeans(cart, orderId);
            System.out.println(purchasedProducts);

            // Save all the purchased products to the database
            purchasedProductDao.doSaveAll(purchasedProducts);

            // If everything is successful, redirect back to the calling JSP with parameter "success=true"
            Cart newCart = new Cart();
            session.setAttribute("cart", newCart);
            response.sendRedirect("checkout.jsp?success=true");

        } catch (SQLException e) {
            // If an error occurs, redirect back to the calling JSP with parameter "success=false"
            // and print the error message to the console
            e.printStackTrace();
            response.sendRedirect("Home.jsp");
        }
    }

    private static ArrayList<PurchasedProductBean> getPurchasedProductBeans(Cart cart, int orderId) {
        HashMap<ProductBean, Integer> products = cart.getProducts();

        // Create an array to store the PurchasedProductBeans
        ArrayList<PurchasedProductBean> purchasedProducts = new ArrayList<>();

        for (Map.Entry<ProductBean, Integer> entry : products.entrySet()) {
            ProductBean product = entry.getKey();
            int quantity = entry.getValue();

            // Create a new PurchasedProductBean for each product
            PurchasedProductBean purchasedProduct = new PurchasedProductBean();
            purchasedProduct.setNome(product.getNome());
            purchasedProduct.setOrdine_id(orderId);
            purchasedProduct.setQuantita(quantity);
            purchasedProduct.setPrezzo(product.getPrezzo());
            System.out.println(purchasedProduct.getNome() + " " + purchasedProduct.getPrezzo() + " " + purchasedProduct.getQuantita() + " " + purchasedProduct.getOrdine_id());

            // Add the PurchasedProductBean to the array
            purchasedProducts.add(purchasedProduct);
        }
        return purchasedProducts;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
