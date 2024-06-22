<%@ page import="it.mfm.model.CategoryBean" %>
<%@ page import="it.mfm.model.ProductBean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/global.css">
    <link rel="stylesheet" type="text/css" href="css/category.css">
    <script src="script/category.js"></script>
</head>
<body>

<%@ include file="./Header.jsp" %>
<%
    List<CategoryBean> allCategories = (List<CategoryBean>) request.getSession().getAttribute("allCategories");
    CategoryBean category = (CategoryBean) request.getSession().getAttribute("category");
    System.out.println(category);
    List<ProductBean> categoryProducts = (List<ProductBean>) request.getSession().getAttribute("categoryProducts");
    System.out.println(categoryProducts);
%>

<div id="home-container">
    <div id="categories-container">
        <div id="categories-banner">Categorie</div>
        <% if (allCategories != null) { %>
        <% for (CategoryBean categor : allCategories) { %>
        <div class="category" onclick="goToCategory(<%=categor.getId()%>)">
            <%= categor.getNome() %>
        </div>
        <% } %>
        <% } else { %>
        <p>Nessuna categoria disponibile</p>
        <% } %>
    </div>
    <div id="category-banner">
        <div id="title"><%= category.getNome()%></div>
        <div id="description"><%= category.getDescrizione() %></div>
    </div>
    <div id="products-container">
        <% if (categoryProducts != null) { %>
        <% for (ProductBean product : categoryProducts) { %>
        <div class="product-container" onclick="goToDetails('<%= product.getId() %>')">
            <div class="image">
                <img src="<%= product.getImmagine() %>" alt="<%= product.getNome() %>">
            </div>
            <div class="product-banner">
                <div id="product-name"><%= product.getNome() %></div>
                <div id="product-price">€<%= product.getPrezzo() %></div>
            </div>
        </div>
        <% } %>
        <% } else { %>
        <p>No products available.</p>
        <% } %>
    </div>
</div>

</body>
</html>
