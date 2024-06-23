<%@ page import="it.mfm.model.CategoryBean" %>
<%@ page import="java.util.List" %>
<%@ page import="it.mfm.model.ProductBean" %>
<%@ page import="it.mfm.model.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../css/global.css">
    <link rel="stylesheet" type="text/css" href="../css/viewCategory.css">
    <script src="../script/viewCategory.js"></script>

</head>
<body>
<%@ include file="../Header.jsp" %>
<%
    CategoryBean category = (CategoryBean) request.getSession().getAttribute("category");
    List<ProductBean> categoryProducts = (List<ProductBean>) request.getSession().getAttribute("categoryProducts");
%>

<% if(request.getParameter("update") != null && request.getParameter("update").equals("true")) { %>
<div class="message">
    Prodotto modificato
</div>
<% } %>

<%
    UserBean user = (UserBean) session.getAttribute("user");

    if(user == null || !user.isAdmin()) {
        response.sendRedirect(request.getContextPath() + "/NotAuthorized.jsp");
        return;
    }
%>


<div id="products-container">
    <% if (categoryProducts != null) { %>
    <% for (ProductBean product : categoryProducts) { %>
    <div class="product-container">
        <div class="product-name"><%= product.getNome() %></div>
        <button class="modify-button" onclick="goToModify(<%=product.getId()%>)">Modifica</button>
        <button class="remove-button" onclick="goToDelete(<%=product.getId()%>)">Rimuovi</button>
    </div>
    <% } %>
    <% } else { %>
    <p>Nessun prodotto disponibile.</p>
    <% } %>
</div>

<%@ include file="../Footer.jsp" %>

</body>
</html>
