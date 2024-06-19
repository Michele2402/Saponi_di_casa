<%--
  Created by IntelliJ IDEA.
  User: Utente
  Date: 17/06/2024
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
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

    <div id="account-container">
        <div class="card">
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
</body>
</html>
