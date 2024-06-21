<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/global.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script src="script/login.js"></script>
</head>
<body>
    <%@ include file="./Header.jsp" %>

    <div id="login-container">
        <div class="button">Login</div>
        <div class="button"  onclick="showForm(1)">Crea Account</div>
        <div id="form-login">
            <form action="Login" method="post">
                <%if(request.getParameter("action")!=null && request.getParameter("action").equalsIgnoreCase("error") ){ %>
                <div class="tableRow">
                    <p class="error">Username o password errati!</p>
                </div>
                <%} %>

                <div class="tableRow">
                    <p>Username:</p>
                    <p><input class="input" type="text" name="un" required placeholder="inserisci username"/></p>
                </div>
                <div class="tableRow">
                    <p>Password:</p>
                    <p><input class="input"  type="password" name="pw" required placeholder="inserisci password"/></p>
                </div>
                <div class="tableRow">
                    <input class="button"  type="submit" Value="login">
                </div>
            </form>
        </div>

    </div>

</body>
</html>
