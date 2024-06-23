<%@ page import="it.mfm.model.ProductBean" %>
<%@ page import="it.mfm.model.CategoryBean" %>
<%@ page import="it.mfm.model.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../css/global.css">
    <link rel="stylesheet" type="text/css" href="../css/modifyProduct.css">
    <script src="../script/modifyProduct.js"></script>
</head>
<body>
<%@ include file="../Header.jsp" %>
<%
    ProductBean product = (ProductBean) request.getSession().getAttribute("product");
    CategoryBean category = (CategoryBean) request.getSession().getAttribute("category");
%>

<%
    UserBean user = (UserBean) session.getAttribute("user");

    if(user == null || !user.isAdmin()) {
        response.sendRedirect(request.getContextPath() + "/NotAuthorized.jsp");
        return;
    }
%>

<div id="modify-product-container">
    <form action="../Product" method="post" id="myForm">
        <input type="hidden" name="action" value="modify">

        <div class="placeholder top category">
            <p class="top">Categoria: <%= category.getNome() %></p>
        </div>

        <div class="placeholder">
            <p class="top">Nome:</p>
            <p class="bottom"><input type="text" name="nome" required placeholder="Inserisci nome" value="<%= product.getNome() %>"/></p>
        </div>
        <div class="placeholder">
            <p class="top">Descrizione:</p>
            <p class="bottom"><input type="text" name="descrizione" required placeholder="Inserisci descrizione" value="<%= product.getDescrizione() %>"/></p>
        </div>
        <div class="placeholder">
            <p class="top">Prezzo</p>
            <p class="bottom"><input type="number" name="prezzo" required placeholder="Inserisci prezzo" value="<%= product.getPrezzo() %>"/></p>
        </div>
        <div class="placeholder">
            <p class="top">URL dell'immagine:</p>
            <p class="bottom"><input type="text" name="immagine" required placeholder="immagini/***.jpg" value="<%= product.getImmagine() %>"/></p>
        </div>
        <div class="placeholder middle">
            <input class="button"  type="submit" Value="Add">
        </div>
    </form>
</div>

<%@ include file="../Footer.jsp" %>

</body>
</html>
