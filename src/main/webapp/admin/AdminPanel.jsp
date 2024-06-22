<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../css/global.css">
    <link rel="stylesheet" type="text/css" href="../css/adminPanel.css">
    <script src="../script/adminPanel.js"></script>
</head>
<body>

<%@ include file="../Header.jsp" %>

<div id="admin-container">
    <div class="card" onclick="goToViewCatalogue()">
        <div class="util">
            <p>Visualizza Catalogo</p>
        </div>
    </div>
    <div class="card" onclick="goToAddProduct()">
        <div  class="util">
            <p>Aggiungi Prodotto</p>
        </div>
    </div>
    <div class="card" onclick="goToPaymentMethod()">
        <div class="util" >
            <p>Visualizza Ordini</p>
        </div>
    </div>
</div>

</body>
</html>
