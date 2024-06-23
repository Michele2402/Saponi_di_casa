<%@ page import="it.mfm.model.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../css/global.css">
    <link rel="stylesheet" type="text/css" href="../css/addCategory.css">
</head>
<body>
<%@ include file="../Header.jsp" %>

<%
    UserBean user = (UserBean) session.getAttribute("user");

    if(user == null || !user.isAdmin()) {
        response.sendRedirect(request.getContextPath() + "/NotAuthorized.jsp");
        return;
    }
%>

<div id="add-category-container">
    <form action="../Category" method="post" id="myForm">
        <input type="hidden" name="action" value="new">
        <input type="hidden" name="page" value="/admin/AddProduct.jsp">

        <div class="placeholder">
            <p class="top">Nome:</p>
            <p class="bottom"><input type="text" name="nome" required placeholder="Inserisci nome"/></p>
        </div>
        <div class="placeholder">
            <p class="top">Descrizione:</p>
            <p class="bottom"><input type="text" name="descrizione" required placeholder="Inserisci descrizione"/></p>
        </div>
        <div class="placeholder">
            <input class="button"  type="submit" Value="Add">
        </div>
    </form>
</div>

<%@ include file="../Footer.jsp" %>

</body>
</html>
