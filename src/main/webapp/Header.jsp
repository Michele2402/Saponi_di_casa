<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
   <link rel="stylesheet" type="text/css" href="css/global.css">
    <link rel="stylesheet" type="text/css" href="css/header.css">
    <script src="script/header.js"></script>
</head>
<body>
    <div id="header-container">
        <div id="item1">
            <div id="banner" onclick="backHome()">
               Saponi Di Casa
            </div>
        </div>
        <div id="item2">
            <input type="text" placeholder="Cerca un prodotto">
        </div>
        <div id="item3">
            <div id="personal-options">
                <%
                    request.getSession().setAttribute("username", "Michele");
                    if(request.getSession().getAttribute("username") == null) {%>
                <div class="option" onclick="goToLogin()">Login</div>
                <% } else {%>
                <div class="option" onclick="goToAccount()">Account</div>
                <% } %>
                <div class="option" onclick="goToCart()">Carrello</div>
            </div>
        </div>
    </div>
</body>
</html>
