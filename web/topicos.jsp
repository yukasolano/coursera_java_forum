<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fórum</title>
    </head>
    <body>
        <h1>Lista de Tópico</h1>
        
        <table id="topicos">
            <thead>
                <tr>
                    <th>Tópico</th><th> Usuário</th> 
                </tr>
            </thead>
            <tbody>
                <c:forEach var="topico" items="${topicos}">
                    <tr>       
                        <th><a href="/Forum/topico?id_topico=${topico.getId_topico()}">${topico.getTitulo()}</a></th>
                        <th>${topico.getUsuario().getNome()}</th>
                    </tr>
                </c:forEach> 
            </tbody>
        </table> 
        <br>
        <form action="criarTopico" method="get" id="goto_novotopico"> 
            <input type="submit" value="Criar novo tópico"> 
        </form>
        <br>
        <form action="verRanking" method="get" id="goto_ranking"> 
            <input type="submit" value="Ver Ranking"> 
        </form>
   </body>
       
</html>