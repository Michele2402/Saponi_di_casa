
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.mfm.model.UserBean" %>
<%@ page import="it.mfm.model.PaymentMethodBean" %>
<%@ page import="java.util.List" %>
<%--<%@ page import="com.example.UserBean" %>
<%@ page import="com.example.PaymentMethodBean" %> --%>
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
%>

<div id="myInformations-container">
    <form action="MyInformations" method="post" id="modify-form"  onsubmit="event.preventDefault(); validateRegistration(this)">
        <div class="tableRow">
            <p>Nome:</p>
            <p><input class="input" type="text" name="nomeMyInf" required placeholder="inserisci il tuo nome"/></p>
            <div class="error" id="RegNome"></div>
        </div>
        <div class="tableRow">
            <p>Cognome:</p>
            <p><input class="input" type="text" name="cognomeMyInf" required placeholder="inserisci il tuo cognome"/></p>
            <div class="error" id="RegCognome"></div>
        </div>
        <div class="tableRow">
            <p>Data di nascita:</p>
            <p> <input class="input" type="date" name="dataNascitaMyInf" required placeholder="inserisci la tua data di nascita"/></p>
            <div class="error" id="RegData"></div>
        </div>
        <div class="tableRow">
            <p>Indirizzo:</p>
            <p><input class="input" type="text" name="indirizzoMyInf" required placeholder="inserisci il tuo indirizzo"/></p>
            <div class="error" id="RegIndirizzo"></div>
        </div>
        <div class="tableRow">
            <p>Email:</p>
            <p><input class="input" type="email" name="emailMyInf" required placeholder="inserisci la tua email"/></p>
            <div class="error" id="RegEmail"></div>
        </div>
        <div class="tableRow">
            <p>Password:</p>
            <p><input class="input" type="password" name="passwordInf" required placeholder="inserisci la tua password"/></p>
            <div class="error" id="RegPassword"></div>
        </div>
        <div class="tableRowButton">
            <input class="button" type="submit" value="Modifica">
        </div>
    </form>
</div>



</body>
</html>
