<%@ page import="it.mfm.model.OrderBean" %>
<%@ page import="it.mfm.model.PurchasedProductBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="it.mfm.model.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../css/global.css">
    <link rel="stylesheet" type="text/css" href="../css/viewOrder.css">
    <script src="../script/viewOrders.js"></script>
</head>
<body>
<%@ include file="../Header.jsp" %>

<%
    Map<OrderBean, List<PurchasedProductBean>> ordersWithProducts = (Map<OrderBean, List<PurchasedProductBean>>) request.getSession().getAttribute("allOrders");
    Map<OrderBean, List<PurchasedProductBean>> userOrders = (Map<OrderBean, List<PurchasedProductBean>>) request.getSession().getAttribute("userOrders");
    Map<OrderBean, List<PurchasedProductBean>> intervalOrders = (Map<OrderBean, List<PurchasedProductBean>>) request.getSession().getAttribute("intervalOrders");
%>

<%
    UserBean user = (UserBean) session.getAttribute("user");

    if(user == null || !user.isAdmin()) {
        response.sendRedirect(request.getContextPath() + "/NotAuthorized.jsp");
        return;
    }
%>

<div id="orders-container">
    <div id="filter">
        <div id="all">
            <label>Tutti</label>
            <button onclick="all()">Cerca</button>
        </div>
        <div id="by-client">
            <label>Per cliente</label>
            <input type="text" id="clientInput" placeholder="cerca un cliente">
            <button onclick="byClient(document.getElementById('clientInput').value)">Cerca</button>
        </div>
        <div class="by-interval">
            <label>Per intervallo, da - a</label>
            <input type="date" id="startDate" placeholder="da">
            <input type="date" id="endDate" placeholder="a">
            <button onclick="byInterval()">Cerca</button>
        </div>
    </div>
    <% if (ordersWithProducts != null && !ordersWithProducts.isEmpty()) { %>
    <% for (Map.Entry<OrderBean, List<PurchasedProductBean>> entry : intervalOrders.entrySet()) { %>
    <div class="order-container">
        <div class="order-details">
            <div class="info">Cliente: <%= entry.getKey().getUtente_username() %></div>
            <div class="info">Effettuato il:<%= entry.getKey().getData_creazione() %></div>
            <div class="info">Totale: €<%= entry.getKey().getTotale() %></div>
        </div>
        <div class="products-container">
            <% List<PurchasedProductBean> products = entry.getValue(); %>
            <% for (PurchasedProductBean product : products) { %>
            <div class="product" data-id="<%= product.getId() %>">
                <div class="name"><%= product.getNome() %></div>
                <div>x<%= product.getQuantita() %></div>
                <div>€<%= product.getPrezzo() %></div>
            </div>
            <% } %>
        </div>
    </div>
    <% } %>
    <% } else { %>
    <p>Nessun ordine effettuato.</p>
    <% } %>
</div>

<%@ include file="../Footer.jsp" %>

</body>
</html>
