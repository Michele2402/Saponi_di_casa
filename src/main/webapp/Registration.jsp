<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/global.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script src="script/login.js"></script>
    <script src="script/validate.js"></script>
</head>
<body>
<%@ include file="./Header.jsp" %>

<div id="registration-container">
    <div class="button"  onclick="showForm(0)">Login</div>
    <div class="button">Crea Account</div>
    <div id="form-login">
        <form action="registration" method="post"  onsubmit="event.preventDefault(); validateRegistration(this)">
            <%if(request.getParameter("action")!=null && request.getParameter("action").equalsIgnoreCase("error") ){ %>
            <div class="tableRow">
                <p class="error">Errore nella creazione dell'account!</p>
            </div>
            <%} %>

            <div class="tableRow">
                <p>Username:</p>
                <p><input class="input" type="text" name="username" required placeholder="inserisci il tuo username"/></p>
                <div class="error" id="RegUsername"></div>
            </div>
            <div class="tableRow">
                <p>Nome:</p>
                <p><input class="input" type="text" name="nome" required placeholder="inserisci il tuo nome"/></p>
                <div class="error" id="RegNome"></div>
            </div>
            <div class="tableRow">
                <p>Cognome:</p>
                <p><input class="input" type="text" name="cognome" required placeholder="inserisci il tuo cognome"/></p>
                <div class="error" id="RegCognome"></div>
            </div>
            <div class="tableRow">
                <p>Indirizzo:</p>
                <p><input class="input" type="text" name="indirizzo" required placeholder="inserisci il tuo indirizzo"/></p>
                <div class="error" id="RegIndirizzo"></div>
            </div>
            <div class="tableRow">
                <p>Email:</p>
                <p><input class="input" type="email" name="email" required placeholder="inserisci la tua email"/></p>
                <div class="error" id="RegEmail"></div>
            </div>
            <div class="tableRow">
                <p>Numero di telefono:</p>
                <p><input class="input" type="text" name="telefono" required placeholder="inserisci il tuo numero di telefono"/></p>
                <div class="error" id="RegTelefono"></div>
            </div>
            <div class="tableRow">
                <p>Password:</p>
                <p><input class="input" type="password" name="password" required placeholder="inserisci la tua password"/></p>
                <div class="error" id="RegPassword"></div>
            </div>
            <div class="tableRow">
                <input class="button" type="submit" value="Crea">
            </div>
        </form>
    </div>

</div>
</body>
</html>

