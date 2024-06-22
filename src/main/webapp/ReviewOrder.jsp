<%@ page import="it.mfm.model.Cart" %>
<%@ page import="it.mfm.model.UserBean" %>
<%@ page import="java.util.List" %>
<%@ page import="it.mfm.model.PaymentMethodBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/global.css">
    <link rel="stylesheet" type="text/css" href="css/reviewOrder.css">
    <script src="script/reviewOrder.js"></script>
</head>
<body>
<%@ include file="./Header.jsp" %>
<%
    Cart cart = (Cart) request.getSession().getAttribute("cart");
    UserBean user = (UserBean) request.getSession().getAttribute("user");
    List<PaymentMethodBean> paymentMethods = (List<PaymentMethodBean>) request.getSession().getAttribute("paymentMethods");

    double cartTotal = cart != null ? cart.geTotal() : 0.0;
    double shippingCost = cartTotal * 0.05;
    double orderTotal = cartTotal + shippingCost;
%>

<div id="review-order-container">
    <div id="order-info">
        <h2>Riepilogo Ordine</h2>
        <p>Totale Carrello: €<%= String.format("%.2f", cartTotal) %></p>
        <p>Costo di Spedizione (5%): €<%= String.format("%.2f", shippingCost) %></p>
        <p>Totale Ordine: €<%= String.format("%.2f", orderTotal) %></p>
    </div>
    <div id="payment-methods-container">
        <% if (paymentMethods != null && !paymentMethods.isEmpty()) { %>
        <% for (PaymentMethodBean paymentMethod : paymentMethods) {%>
        <div class="review-container">
            <div class="numero-carta"><%= paymentMethod.getNumero_di_carta() %></div>
            <div class="name-surname"><%= paymentMethod.getNome()%> <%= paymentMethod.getCognome()%></div>
            <div class="date"><%= paymentMethod.getData_di_Scadenza()%></div>
        </div>
        <% } %>
        <% } else { %>
            <p>Nessun metodo di pagamento insrito</p>
        <% } %>
    </div>
</div>


</body>
</html>
