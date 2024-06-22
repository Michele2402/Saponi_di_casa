<%@ page import="it.mfm.model.OrderBean" %>
<%@ page import="it.mfm.model.PurchasedProductBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/global.css">
    <link rel="stylesheet" type="text/css" href="css/myOrders.css">
    <script src="script/myOrders.js"></script>
</head>
<body>
<%@ include file="./Header.jsp" %>

<%
    Map<OrderBean, List<PurchasedProductBean>> ordersWithProducts = (Map<OrderBean, List<PurchasedProductBean>>) request.getSession().getAttribute("ordersWithProducts");
%>

<div id="orders-container">
    <% if (ordersWithProducts != null && !ordersWithProducts.isEmpty()) { %>
    <% for (Map.Entry<OrderBean, List<PurchasedProductBean>> entry : ordersWithProducts.entrySet()) { %>
    <div class="order-container">
        <div class="order-details">
            <div>Effettuato il:<%= entry.getKey().getData_creazione() %></div>
            <div>€ <%= entry.getKey().getTotale() %></div>
        </div>
        <div class="products-container">
            <% List<PurchasedProductBean> products = entry.getValue(); %>
            <% for (PurchasedProductBean product : products) { %>
            <div id="product">
                <p><%= product.getNome() %></p>
                <p>x<%= product.getQuantita() %></p>
                <p>€<%= product.getPrezzo() %></p>
            </div>
            <% } %>
        </div>
    </div>
    <% } %>
    <% } else { %>
    <p>No orders available.</p>
    <% } %>
</div>
</body>
</html>
