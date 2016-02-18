<%-- 
    Document   : index
    Created on : 21-ene-2016, 18:29:47
    Author     : fernandoarenasdev
--%>

<%@page import="negocio.dominio.Ejercicio"%>
<%@page import="presentacion.json.impl.JsonTransformerImplJackson"%>
<%@page import="presentacion.json.JsonTransformer"%>
<%@page import="persistencia.dao.impl.EjercicioDAOImplJDBC"%>
<%@page import="persistencia.dao.EjercicioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <% EjercicioDAO ejercicioDAO = new EjercicioDAOImplJDBC();
        JsonTransformer jsonTransformer=new JsonTransformerImplJackson();
        Ejercicio ejercicio = new Ejercicio(1, ejercicioDAO.get(1).getNombreEjercicio(), ejercicioDAO.get(1).getDescripcionEjercicio(), ejercicioDAO.get(1).getCategoriaEjercicio(),
                ejercicioDAO.get(1).getFechaEjercicio(), ejercicioDAO.get(1).getObservacionesEjercicio());
        
String d = jsonTransformer.toJson(ejercicio);
Ejercicio e = (Ejercicio)jsonTransformer.fromJSON(d, Ejercicio.class);        %>
        
        <p><%=d+" "+e.getNombreEjercicio()%></p>
    </body>
</html>
