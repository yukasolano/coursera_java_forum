<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Fórum</title>
    </head>
    <body>
      <h1>Crie novo tópico</h1>
        
      <form action="topico" method="post" id="form_novotopico"> 
            Título: <input type="text" name="titulo">      
            <input type="submit" value="Criar"> 
      </form>
      <br>
      <textarea rows="4" cols="50" name="conteudo" form="form_novotopico"></textarea>           
   </body>
       
</html>