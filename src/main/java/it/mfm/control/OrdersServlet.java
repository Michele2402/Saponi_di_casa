package it.mfm.control;

import it.mfm.model.OrderBean;
import it.mfm.model.OrderDao;
import it.mfm.model.PurchasedProductBean;
import it.mfm.model.PurchasedProductDao;
import it.mfm.model.UserBean;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/Orders")
public class OrdersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrderDao orderDao;
    private PurchasedProductDao purchasedProductDao;

    public void init() {
        orderDao = new OrderDao();
        purchasedProductDao = new PurchasedProductDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        try {
            List<OrderBean> orders = orderDao.doRetrieveByUsername(user.getUsername());
            Map<OrderBean, List<PurchasedProductBean>> ordersWithProducts = new HashMap<>();

            for (OrderBean order : orders) {
                List<PurchasedProductBean> products = purchasedProductDao.doRetrieveAllByOrderId(order.getId());
                ordersWithProducts.put(order, products);
            }

            session.setAttribute("ordersWithProducts", ordersWithProducts);
            response.sendRedirect("MyOrders.jsp");
        } catch (SQLException e) {
            throw new ServletException("Error retrieving orders and products", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
