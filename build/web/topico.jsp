<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fórum</title>
    </head>
    <body>        
        <h1> ${topico.getTitulo()} <small>  - Por ${topico.getUsuario().getNome()} </small></h1>
        
        <p>  ${topico.getConteudo()} </p>
        
        <div id="comentarios">
            <h2> Comentários </h2>
            <ul>
            <c:forEach var="comentario" items="${topico.getComentarios()}">
                <li><small>Por ${comentario.getUsuario().getNome()}</small></li>
                <p>${comentario.getComentario()}</p>
            </c:forEach> 
            </ul>
        </div>
        
        <div id="novo_comentario">
            <h2>Adicione um comentário</h2>

            <textarea rows="4" cols="50" name="comentario" form="form_novocomentario"></textarea>
            <br>
            <form action="comentario" method="post" id="form_novocomentario"> 
                <input type="hidden" name="id_topico" value="${topico.getId_topico()}">
                <input type="submit" value="Comentar"> 
            </form>
        </div> 
        <br>    
        <form action="topico" method="get" id="goto_topicos"> 
            <input type="submit" value="Voltar"> 
        </form>   
   </body>
       
</html>