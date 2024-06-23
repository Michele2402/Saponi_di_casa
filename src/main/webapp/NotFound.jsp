<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Errore 404 - Pagina Non Trovata</title>
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
        .error-container {
            background-color: #FFF7E3;
            padding: 20px 30px;
            border: 1px solid #E5C49D;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .error-container h1 {
            color: #D2691E;
            margin-bottom: 20px;
        }
        .error-container p {
            color: #8B4513;
            margin-bottom: 20px;
        }
        .error-container a {
            text-decoration: none;
            color: #FFF7E3;
            background-color: #8B4513;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .error-container a:hover {
            background-color: #D2691E;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1>Errore 404 - Pagina Non Trovata</h1>
    <p>La pagina che stavi cercando non è disponibile.</p>
    <a href="Home.jsp">Torna alla homepage</a>
</div>
</body>
</html>
