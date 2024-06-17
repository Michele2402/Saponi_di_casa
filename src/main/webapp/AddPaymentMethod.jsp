<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/global.css">
    <link rel="stylesheet" type="text/css" href="css/addPaymentMethod.css">
    <script src="script/validate.js"></script>
</head>
<body>

<%@ include file="./Header.jsp" %>

<div id="add-payment-method-container">
    <form action="PaymentMethod" method="post" id="myForm" onsubmit="event.preventDefault(); validate(this)">
        <input type="hidden" name="action" value="add">
        <div class="placeholder">
            <p class="top">Nome:</p>
            <p class="bottom"><input type="text" name="no" required placeholder="Inserisci nome"/></p>
            <div class="error nome"></div>
        </div>
        <div class="placeholder">
            <p class="top">Cognome:</p>
            <p class="bottom"><input type="text" name="co" required placeholder="Inserisci cognome"/></p>
            <div class="error cognome"></div>
        </div>
        <div class="placeholder">
            <p class="top">Numero Carta:</p>
            <p class="bottom"><input type="number" name="nu" required placeholder="Inserisci numero"/></p>
            <div class="error numero"></div>
        </div>
        <div class="placeholder">
            <p class="top">Data Di Scadenza:</p>
            <p class="bottom"><input type="date" name="da" required/></p>
            <div class="error data"></div>
        </div>
        <div class="placeholder middle">
            <p class="top">CVV:</p>
            <p class="bottom"><input type="number" name="cv" required placeholder="Inserisci cvv"/></p>
            <div class="error cvv"></div>
        </div>
        <div class="placeholder middle">
            <input class="button"  type="submit" Value="Add">
        </div>
    </form>
</div>

</body>
</html>

</body>
</html>
