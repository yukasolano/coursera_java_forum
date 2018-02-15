<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Fórum</title>
    </head>
    <body>
        <h1>Cadastro de novo usuário</h1>
        <form action="cadastro" method="post" id="form_cadastro"> 
            Nome: <input type="text" name="nome"><br><br>
            Login: <input type="text" name="login"><br><br>
            Email: <input type="text" name="email"><br><br>
            Senha: <input type="password" name="senha"><br><br>
            <input type="submit" value="Submeter"> 
        </form>
    </body>
</html>
