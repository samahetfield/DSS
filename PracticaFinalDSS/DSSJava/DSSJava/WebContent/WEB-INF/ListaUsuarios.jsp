<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Lista de usuarios</title>
</head>
<body>

	<h1>LISTA DE USUARIOS</h1>
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="servidor.Usuario" %>
	<%
	
		ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
		for( int i = 0; i < usuarios.size(); i++)
			out.println("<p>" + usuarios.get(i) + "</p>");
	
	%>

</body>
</html>