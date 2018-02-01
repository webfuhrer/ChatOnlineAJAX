<%-- 
    Document   : vercomentarios
    Created on : 01-feb-2018, 10:24:21
    Author     : luis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String usuario=(String)session.getAttribute("usuario");
    if(usuario==null)
    {
        request.getRequestDispatcher("entrada.html").forward(request, response);
    }
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chat online</title>
         <script src="javascript/funciones.js"></script>
    </head>
    <body onload="recagoAutomatico()">
        <h1>Bienvenido <%=usuario%></h1>
        <textarea id="comentario">
        </textarea>
        <button id="boton" type="button" onclick="enviarComentario();">Enviar</button>
        <script>
            setInterval(function(){recagoAutomatico();}, 3000)
            </script>
    </body>
   
</html>
