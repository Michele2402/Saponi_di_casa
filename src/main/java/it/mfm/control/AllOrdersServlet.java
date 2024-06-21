package it.mfm.control;

import com.mysql.cj.Session;
import it.mfm.fakeModel.OrderBean;
import it.mfm.fakeModel.OrderDao;
import it.mfm.fakeModel.PurchasedProductBean;
import it.mfm.fakeModel.PurchasedProductDao;
import it.mfm.fakeModel.UserBean;
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


@WebServlet("/allOrders")
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

        if(user == null || !user.isAdmin()) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
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

                session.setAttribute("allOrders", ordersWithProducts);

            } else if ("cliente".equals(action)) {

                String username = request.getParameter("username");
                Map<OrderBean, List<PurchasedProductBean>> ordersWithProducts = new HashMap<>();
                List<OrderBean> allOrders = orderDao.doRetrieveByUsername(username);

                for (OrderBean order : allOrders) {
                    List<PurchasedProductBean> products = purchasedProductDao.doRetrieveAllByOrderId(order.getId());
                    ordersWithProducts.put(order, products);
                }

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

                session.setAttribute("intervalOrders", ordersWithProducts);


            }

            response.sendRedirect(request.getContextPath() + "/admin/ViewOrders.jsp");

        } catch (SQLException e) {
            throw new ServletException("Error retrieving orders", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
