<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Lista de los productos</title>
</head>
<body>

	<h1>LISTA DE PRODUCTOS</h1>
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="servidor.Producto" %>
	<%
	
		ArrayList<Producto> productos = (ArrayList<Producto>) request.getAttribute("productos");
		for( int i = 0; i < productos.size(); i++)
			out.println("<p>" + productos.get(i) + "</p>");
	
	%>

</body>
</html>