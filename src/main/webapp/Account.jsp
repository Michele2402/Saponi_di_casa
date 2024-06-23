<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/global.css">
    <link rel="stylesheet" type="text/css" href="css/account.css">
    <script src="script/account.js"></script>
</head>
<body>
    <%@ include file="./Header.jsp" %>

    <% if(request.getParameter("success") != null && request.getParameter("success").equals("true")) { %>
    <div class="message">
        Metodo di pagamento aggiunto
    </div>
    <% } %>

    <div id="util">
        <div id="account-container">
            <div class="card" onclick="goToMyOrders()">
                <div class="util">
                    <p>I Miei Ordini</p>
                </div>
            </div>
            <div class="card" onclick="goToMyInformations()">
                <div  class="util">
                    <p>Informazioni Personali</p>
                </div>
            </div>
            <div class="card" onclick="goToPaymentMethod()">
                <div class="util" >
                    <p>Aggiungi Metodo Di Pagamento</p>
                </div>
            </div>
        </div>
        <button onclick="logout()">Logout</button>
    </div>

    <%@ include file="./Footer.jsp" %>

</body>
</html>
