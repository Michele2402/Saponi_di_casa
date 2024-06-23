<%@ page import="it.mfm.model.CategoryBean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../css/global.css">
    <link rel="stylesheet" type="text/css" href="../css/viewCatalogue.css">
    <script src="../script/viewCatalogue.js"></script>
</head>
<body>
<%@ include file="../Header.jsp" %>

<%
    List<CategoryBean> allCategories = (List<CategoryBean>) request.getSession().getAttribute("allCategories");
%>

<div id="categories-container">
    <% if (allCategories != null) { %>
    <% for (CategoryBean category : allCategories) { %>
    <div class="category-container" onclick="goToCategory(<%= category.getId() %>)">
        <div class="category-name"><%= category.getNome() %></div>
        <div class="category-description"><%= category.getDescrizione() %></div>
    </div>
    <% } %>
    <% } else { %>
    <p>Nessuna categoria disponibile.</p>
    <% } %>
</div>

</body>
</html>
