<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.mfm.model.UserBean" %>
<%@ page import="it.mfm.model.PaymentMethodBean" %>
<%@ page import="java.util.List" %>
<%@ page import="it.mfm.util.Utility" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/global.css">
    <link rel="stylesheet" type="text/css" href="css/myInformations.css">
</head>
<body>
<%@ include file="./Header.jsp" %>

<%
    UserBean user = (UserBean) session.getAttribute("user");
    List<PaymentMethodBean> paymentMethods = (List<PaymentMethodBean>) session.getAttribute("paymentMethods");
    boolean error = Boolean.parseBoolean(String.valueOf(session.getAttribute("error")));
%>

<div id="myInformations-container">
    <% if (error) { %>
    <div class="error-message">
        Si è verificato un errore durante la modifica delle informazioni.
    </div>
    <% } %>
    <form action="MyInformations" method="post" id="modify-form">
        <div class="tableRow">
            <p>Nome:</p>
            <p><input class="input" type="text" name="nomeMyInf" required value="<%= user.getNome() %>"/></p>
        </div>
        <div class="tableRow">
            <p>Cognome:</p>
            <p><input class="input" type="text" name="cognomeMyInf" required value="<%= user.getCognome() %>"/></p>
        </div>
        <div class="tableRow">
            <p>Numero di telefono:</p>
            <p><input class="input" type="text" name="telefonoMyInf" required value="<%= user.getTelefono() %>"/></p>
        </div>
        <div class="tableRow">
            <p>Indirizzo:</p>
            <p><input class="input" type="text" name="indirizzoMyInf" required value="<%= user.getIndirizzo() %>"/></p>
        </div>
        <div class="tableRow">
            <p>Email:</p>
            <p><input class="input" type="email" name="emailMyInf" required value="<%= user.getEmail() %>"/></p>
        </div>
        <div class="tableRow">
            <p>Password:</p>
            <p><input class="input" type="password" name="passwordInf" required placeholder="inserisci la tua password"/></p>
        </div>
        <div class="tableRowButton">
            <input class="button" type="submit" value="Modifica">
        </div>
    </form>

    <div id="payment-methods-container">
        <div>Metodi di pagamento:</div>
        <% if (paymentMethods != null && !paymentMethods.isEmpty()) { %>
        <% for (PaymentMethodBean paymentMethod : paymentMethods) { %>
        <div class="payment-method-container">
            <div class="numero-carta"><%= Utility.maskCardNumber(paymentMethod.getNumero_di_carta()) %></div>
            <div><%= paymentMethod.getNome() %> <%= paymentMethod.getCognome() %></div>
            <div class="date"><%= new SimpleDateFormat("MM/yyyy").format(paymentMethod.getData_di_Scadenza()) %></div>
        </div>
        <% } %>
        <% } else { %>
        <p>Nessun metodo di pagamento inserito</p>
        <% } %>
    </div>
</div>

</body>
</html>
