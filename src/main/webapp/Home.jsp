<%@ page import="java.util.List" %>
<%@ page import="it.mfm.model.CategoryBean" %>
<%@ page import="it.mfm.model.ProductBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/global.css">
    <link rel="stylesheet" type="text/css" href="css/home.css">
    <script src="script/home.js"></script>
</head>
<script>
    window.onload = function() {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "<%= request.getContextPath() %>/AllProducts", true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                console.log("Request to AllProducts successful.");
                // Handle the response if needed
            }
        };
        xhr.send();
    };
</script>
<body>

    <%@ include file="./Header.jsp" %>
    <%
        List<CategoryBean> allCategories = (List<CategoryBean>) request.getSession().getAttribute("allCategories");
        System.out.println(allCategories);
        List<ProductBean> allProducts = (List<ProductBean>) request.getSession().getAttribute("allProducts");
        System.out.println(allProducts);

    %>

    <div id="home-container">
        <div id="categories-container">
            <div id="categories-banner">Categorie</div>
            <% if (allCategories != null) { %>
            <% for (CategoryBean category : allCategories) { %>
            <div class="category" onclick="goToCategory(<%=category.getId()%>)">
                <%= category.getNome() %>
            </div>
            <% } %>
            <% } else { %>
            <p>No categories available.</p>
            <% } %>
        </div>
        <div id="products-container">
            <% if (allProducts != null) { %>
            <% for (ProductBean product : allProducts) { %>
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
