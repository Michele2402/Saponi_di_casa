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
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/AllOrders")
public class AllOrdersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private OrderDao orderDao;
    private PurchasedProductDao purchasedProductDao;

    public void init() {
        orderDao = new OrderDao();
        purchasedProductDao = new PurchasedProductDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        String filter = "";

        if(user == null || !user.isAdmin()) {
            response.sendRedirect(request.getContextPath() + "/NotAuthorized.jsp");
            return;
        }

        try {
            if ("all".equals(action)) {
                List<OrderBean> allOrders = orderDao.doRetrieveAll();
                Map<OrderBean, List<PurchasedProductBean>> ordersWithProducts = new HashMap<>();

                for (OrderBean order : allOrders) {
                    List<PurchasedProductBean> products = purchasedProductDao.doRetrieveAllByOrderId(order.getId());
                    ordersWithProducts.put(order, products);
                }

                filter = "?filter=all";
                session.setAttribute("allOrders", ordersWithProducts);

            } else if ("cliente".equals(action)) {

                String username = request.getParameter("username");
                Map<OrderBean, List<PurchasedProductBean>> ordersWithProducts = new HashMap<>();
                List<OrderBean> allOrders = orderDao.doRetrieveByUsername(username);

                for (OrderBean order : allOrders) {
                    List<PurchasedProductBean> products = purchasedProductDao.doRetrieveAllByOrderId(order.getId());
                    ordersWithProducts.put(order, products);
                }

                filter = "?filter=cliente";
                session.setAttribute("userOrders", ordersWithProducts);



            } else if ("intervallo".equals(action)) {
                Date startDate = Date.valueOf(request.getParameter("startDate"));
                Date endDate = Date.valueOf(request.getParameter("endDate"));
                Map<OrderBean, List<PurchasedProductBean>> ordersWithProducts = new HashMap<>();
                List<OrderBean> allOrders = orderDao.doRetrieveByDateInterval(startDate, endDate);

                for (OrderBean order : allOrders) {
                    List<PurchasedProductBean> products = purchasedProductDao.doRetrieveAllByOrderId(order.getId());
                    ordersWithProducts.put(order, products);
                }

                filter = "?filter=intervallo";
                session.setAttribute("intervalOrders", ordersWithProducts);


            }

            response.sendRedirect(request.getContextPath() + "/admin/ViewOrders.jsp" + filter);

        } catch (SQLException e) {
            throw new ServletException("Error retrieving orders", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
