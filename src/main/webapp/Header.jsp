<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/header.css">
    <script>
        var contextPath = "<%= request.getContextPath() %>";
    </script>
    <script src="<%= request.getContextPath() %>/script/header.js"></script>
</head>
<body>
    <div id="header-container">
        <div id="item1">
            <div id="banner" onclick="backHome()">
               Saponi Di Casa
            </div>
        </div>
        <div id="item2">
            <form action="Search" method="get">
                <input type="text" name="query" placeholder="Cerca un prodotto">
                <input type="submit" value="Search">
            </form>
        </div>
        <div id="item3">
            <div id="personal-options">
                <%if(request.getSession().getAttribute("user") == null) {%>
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
