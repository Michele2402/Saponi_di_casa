<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/global.css">
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
    <style>
        #back-button {
            background-color: #DBB5B5;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            font-weight: bold;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin-left: auto;
            margin-right: auto;
            margin-top: 10px;
            width: 80px;
        }

        #back-button:hover {
            background-color: #a98e8e; /* Cambia il colore di sfondo al passaggio del mouse */
        }
    </style>
</head>
<body>
<div id="back-button" onclick="goBack()">Indietro</div>
</body>
</html>