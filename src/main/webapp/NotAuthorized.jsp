<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Accesso Non Autorizzato</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #FAF3E0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .unauthorized-container {
            background-color: #FFF7E3;
            padding: 20px 30px;
            border: 1px solid #E5C49D;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .unauthorized-container h1 {
            color: #D2691E;
            margin-bottom: 20px;
        }
        .unauthorized-container p {
            color: #8B4513;
            margin-bottom: 20px;
        }
        .unauthorized-container a {
            text-decoration: none;
            color: #FFF7E3;
            background-color: #8B4513;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .unauthorized-container a:hover {
            background-color: #D2691E;
        }
    </style>
</head>
<body>
<div class="unauthorized-container">
    <h1>Accesso Non Autorizzato</h1>
    <p>Non sei autorizzato ad accedere a questa pagina.</p>
    <a href="<%= request.getContextPath() %>/Home.jsp">Torna alla homepage</a>
</div>
</body>
</html>