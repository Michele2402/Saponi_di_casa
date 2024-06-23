<%@ page import="it.mfm.model.CategoryBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../css/global.css">
    <link rel="stylesheet" type="text/css" href="../css/addProduct.css">
    <script src="../script/addProduct.js"></script>
</head>
<body>
<%@ include file="../Header.jsp" %>

<%
    List<CategoryBean> categories = (List<CategoryBean>) request.getSession().getAttribute("allCategories");
%>

<div id="add-product-container">
    <form action="../Product" method="post" id="myForm">
        <input type="hidden" name="action" value="add">

        <div class="placeholder top category">
            <p class="top">Categoria:</p>
            <select name="IDcategoria" id="IDcategoria" required>
                <option value="" disabled selected>Scegli una categoria</option>
                <% for (CategoryBean category : categories) { %>
                <option value="<%= category.getId() %>"><%= category.getNome() %></option>
                <% } %>
                <option value="newCategory">Aggiungi una nuova categoria</option>
            </select>
        </div>

        <div class="placeholder">
            <p class="top">Nome:</p>
            <p class="bottom"><input type="text" name="nome" required placeholder="Inserisci nome"/></p>
        </div>
        <div class="placeholder">
            <p class="top">Descrizione:</p>
            <p class="bottom"><input type="text" name="descrizione" required placeholder="Inserisci descrizione"/></p>
        </div>
        <div class="placeholder">
            <p class="top">Prezzo</p>
            <p class="bottom"><input type="number" name="prezzo" required placeholder="Inserisci prezzo"/></p>
        </div>
        <div class="placeholder">
            <p class="top">URL dell'immagine:</p>
            <p class="bottom"><input type="text" name="immagine" required placeholder="immagini/***.jpg"/></p>
        </div>
        <div class="placeholder middle">
            <input class="button"  type="submit" Value="Add">
        </div>
    </form>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('IDcategoria').addEventListener('change', function() {
            if (this.value === 'newCategory') {
                window.location.href = 'AddCategory.jsp';
            }
        });
    });
</script>

</body>
</html>
