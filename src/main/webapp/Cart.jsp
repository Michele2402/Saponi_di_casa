<%@ page import="it.mfm.model.Cart" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="it.mfm.model.ProductBean" %>
<%@ page import="it.mfm.model.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <head>
        <title>Title</title>
        <link rel="stylesheet" type="text/css" href="css/global.css">
        <link rel="stylesheet" type="text/css" href="css/cart.css">
        <script src="script/cart.js"></script>
    </head>
</head>
<body>
<%@ include file="./Header.jsp" %>
<%
    Cart cart = (Cart) request.getSession().getAttribute("cart");
    HashMap<ProductBean, Integer> products = new HashMap<>();
    if (cart != null) {
        products = cart.getProducts();
    }
    UserBean user = (UserBean) request.getSession().getAttribute("user");
%>

<div id="cart-container">
    <% if (products == null || products.isEmpty()) { %>
    <div id="empty">Il carrello è vuoto</div>
    <% } else { %>
    <% for (HashMap.Entry<ProductBean, Integer> entry : products.entrySet()) { %>
    <div class="product">
        <div class="image-container">
            <img src="<%=entry.getKey().getImmagine()%>" alt="<%=entry.getKey().getNome()%>">
        </div>
        <div class="info-container">
            <label>Nome</label>
            <div><%= entry.getKey().getNome() %></div>
        </div>
        <div class="info-container">
            <label>Prezzo</label>
            <div>€<%= entry.getKey().getPrezzo() %></div>
        </div>
        <div class="info-container">
            <label>Quantità</label>
            <div id="quantity-container">
                <button class="quantity-button" onclick="RemoveFromCart(<%=entry.getKey().getId()%>)">-</button>
                <div><%= entry.getValue() %></div>
                <button class="quantity-button" onclick="AddToCart(<%=entry.getKey().getId()%>)">+</button>
            </div>
        </div>
        <div class="info-container">
            <button id="remove-button" onclick="DeleteFromCart(<%=entry.getKey().getId()%>)">Rimuovi</button>
        </div>
    </div>
    <% } %>
    <% } %>
    <% if(user != null)  { %>
    <button id="checkout-button" onclick="Checkout()">Acquista</button>
    <% } %>
</div>



</body>
</html>
