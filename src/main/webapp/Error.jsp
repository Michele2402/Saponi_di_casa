<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Errore</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .error-container {
            background-color: #ffffff;
            padding: 20px 30px;
            border: 1px solid #cccccc;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .error-container h1 {
            color: #ff6b6b;
            margin-bottom: 20px;
        }
        .error-container p {
            color: #333333;
            margin-bottom: 20px;
        }
        .error-container a {
            text-decoration: none;
            color: #ffffff;
            background-color: #007bff;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .error-container a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1>Errore</h1>
    <p>Si è verificato un errore durante l'elaborazione della tua richiesta.</p>
    <a href="Home.jsp">Torna alla homepage</a>
</div>
</body>
</html>