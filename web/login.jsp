<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fórum</title>
    </head>
    <body>
        <h1>Entre</h1>
        <c:if test="${not empty mensagem}">
            <p> ${mensagem} </p>
        </c:if>
        <form action="autenticacao" method="post" id="form_login"> 
            Login: <input type="text" name="login"><br><br>
            Senha: <input type="password" name="senha"><br><br>
            <input type="submit" value="Entrar"> 
        </form>
        <br>
        <form action="cadastro" method="get" id="form_cadastro"> 
            <input type="submit" value="Cadastrar"> 
        </form>
    </body>
</html>
