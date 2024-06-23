<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Errore del Server</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5dc; /* Beige chiaro */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .error-container {
            background-color: #fffaf0; /* Beige */
            padding: 20px 30px;
            border: 1px solid #dcdcdc; /* Beige scuro */
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .error-container h1 {
            color: #d2691e; /* Marrone chiaro */
            margin-bottom: 20px;
        }
        .error-container p {
            color: #8b4513; /* Marrone scuro */
            margin-bottom: 20px;
        }
        .error-container a {
            text-decoration: none;
            color: #ffffff;
            background-color: #d2691e; /* Marrone chiaro */
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .error-container a:hover {
            background-color: #8b4513; /* Marrone scuro */
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1>Errore del Server</h1>
    <p>Si è verificato un errore interno al server..</p>
    <a href="Home.jsp">Torna alla homepage</a>
</div>
</body>
</html>