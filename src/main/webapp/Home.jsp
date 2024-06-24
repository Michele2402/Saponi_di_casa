<%@ page import="java.util.List" %>
<%@ page import="it.mfm.model.CategoryBean" %>
<%@ page import="it.mfm.model.ProductBean" %>
<%@ page import="it.mfm.model.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/global.css">
    <link rel="stylesheet" type="text/css" href="css/home.css">
    <script src="script/home.js"></script>
</head>

<body>
<script>
    window.addEventListener('DOMContentLoaded', function () {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'AllProducts', true);

        xhr.onload = function () {
            if (xhr.status >= 200 && xhr.status < 300) {
                document.body.style.display = 'block';
            } else {
                console.error('Errore nel caricamento dei prodotti');
                location.reload();
            }
        };

        xhr.send();
    });

</script>

<%@ include file="./Header.jsp" %>
<%
    List<CategoryBean> allCategories = (List<CategoryBean>) request.getSession().getAttribute("allCategories");
        List<ProductBean> allProducts = (List<ProductBean>) request.getSession().getAttribute("allProducts");
        UserBean user = (UserBean) request.getSession().getAttribute("user");
        boolean admin = false;
        if(user != null) {
            if(user.isAdmin())
                admin = true;
        }

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
            <p>Nessuna categoria disponibile</p>
            <% } %>
        </div>
        <div id="products-container">
            <% if (allProducts != null) { %>
            <% for (ProductBean product : allProducts) { %>
            <div class="product-container" onclick="goToDetails('<%= product.getId() %>')">
                <div class="image">
                    <img class="please" src="<%= product.getImmagine() %>" alt="<%= product.getNome() %>">
                </div>
                <div class="product-banner">
                    <div id="product-name"><%= product.getNome() %></div>
                    <div id="product-price">€<%= product.getPrezzo() %></div>
                </div>
            </div>
            <% } %>
            <% } else { %>
            <p>Nessun prodotto disponibile.</p>
            <% } %>
        </div>
    </div>

    <% if(admin) {%>
    <div id="button-container">
        <button id="admin-button" onclick="goToAdminPanel()">Sezione admin</button>
    </div>
    <%}%>

</body>
</html>
