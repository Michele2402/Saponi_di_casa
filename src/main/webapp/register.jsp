<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrazione Utente</title>
</head>
<body>
<h2>Registrazione Utente</h2>
<form action="registerUser" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" required><br><br>

    <label for="cognome">Cognome:</label>
    <input type="text" id="cognome" name="cognome" required><br><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <label for="indirizzo">Indirizzo:</label>
    <input type="text" id="indirizzo" name="indirizzo" required><br><br>

    <label for="telefono">Telefono:</label>
    <input type="text" id="telefono" name="telefono" required><br><br>

    <label for="admin">Admin:</label>
    <input type="checkbox" id="admin" name="admin" value="true"><br><br>

    <input type="submit" value="Registrati">
</form>
</body>
</html>
