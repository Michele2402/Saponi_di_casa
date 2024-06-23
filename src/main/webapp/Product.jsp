<%@ page import="it.mfm.model.CategoryBean" %>
<%@ page import="it.mfm.model.ProductBean" %>
<%@ page import="java.util.List" %>
<%@ page import="it.mfm.model.ReviewBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/global.css">
    <link rel="stylesheet" type="text/css" href="css/product.css">
    <script src="script/product.js"></script>
</head>
<body>
<%@ include file="./Header.jsp" %>
<%
    CategoryBean category = (CategoryBean) request.getSession().getAttribute("category");
    ProductBean product = (ProductBean) request.getSession().getAttribute("product");
    List<ReviewBean> reviews = (List<ReviewBean>) request.getSession().getAttribute("reviews");
%>

<% if(request.getParameter("success") != null && request.getParameter("success").equals("true")) { %>
<div class="message">
    Aggiunto al carrello
</div>
<% } %>

<div id="product-container">
    <div id="left">
        <div id="image-container">
            <div><%=product.getNome()%></div>
            <img src="<%=product.getImmagine()%>" alt="<%=product.getNome()%>">
        </div>
    </div>
    <div id="right">
        <div id="info-container">
            <div id="category"><%=category.getNome()%></div>
            <div><%=product.getDescrizione()%></div>
            <div>€<%=product.getPrezzo()%></div>
        </div>
        <div id="cart-container">
            <div>Aggiungi al carrello</div>
            <button onclick="AddToCart(<%=product.getId()%>)">+</button>
        </div>
        <div id="reviews-container">
            <% if (reviews != null) { %>
            <% for (ReviewBean review : reviews) {%>
            <div class="review-container">
                <div class="username"><%= review.getUtente_username() %></div>
                <div class="stars"><%= review.getValutazione()%>/5</div>
                <div class="review"><%= review.getTesto() %></div>
                <div class="date">Recensito il <%= review.getData() %></div>
            </div>
            <% } %>
            <% } else { %>
            <div class="review-container">Nessuna recensione</div>
            <% } %>
        </div>
    </div>

</div>

<%@ include file="./Footer.jsp" %>

</body>
</html>
