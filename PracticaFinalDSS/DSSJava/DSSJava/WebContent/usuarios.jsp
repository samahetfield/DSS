<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Usuarios</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="styles/layout.css" type="text/css">
<!--[if lt IE 9]><script src="scripts/html5shiv.js"></script><![endif]-->
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body>
<div class="wrapper row1">
  <header id="header" class="clear">
    <div id="hgroup">
      <h1><a href="index.html">Usuarios</a></h1>
    </div>
    <nav>
      <ul>
        <li><a href="productos.jsp">Productos</a></li>
        <li><a href="farmacias.jsp">Farmacias</a></li>
        <li><a href="usuarios.jsp">Usuarios</a></li>
        <li><a href="orders.jsp">Encargos</a></li>
      </ul>
    </nav>
  </header>
</div>
<!-- content -->
<div class="wrapper row2">
	<h2>Listado de usuarios</h2>
   <ul>
        <%@ page import="java.util.ArrayList" %>
		<%@ page import="facade.UsuarioFacade" %>
		<%@ page import="servidor.Usuario" %>
		<%
		
			UsuarioFacade userfac = new UsuarioFacade();
			
			String aniadirNombre = request.getParameter("aniadirNombre");
			String aniadirUserName = request.getParameter("aniadirUserName");
			String aniadirCorreo = request.getParameter("aniadirCorreo");
			String aniadirContrasena = request.getParameter("aniadirContrasena");


			String borrarUserName = request.getParameter("borrarUserName");
			
			
			String modificarUserName = request.getParameter("modificarUserName");
			String modificarNombre = request.getParameter("modificarNombre");
			String modificarCorreo = request.getParameter("modificarCorreo");
			String modificarContrasena = request.getParameter("modificarContrasena");

		
			if(aniadirNombre != null && aniadirUserName != null && aniadirCorreo != null && aniadirContrasena != null){
				Usuario u = new Usuario(aniadirNombre, aniadirUserName, aniadirCorreo, aniadirContrasena);
				userfac.newUsuario(u);
			}
			
			if(borrarUserName != null){
				Usuario u = new Usuario("",borrarUserName,"","");
				userfac.deleteUsuario(u);
			}
			
			if(modificarUserName != null && modificarNombre != null && modificarCorreo != null && modificarContrasena != null){
				Usuario u = new Usuario( modificarNombre, modificarUserName, modificarCorreo, modificarContrasena);
				userfac.updateUsuario(u);
			}
				
			
			
			ArrayList<Usuario> usuarios = userfac.getUsuarios();
			
			out.println("<table>");
			out.println("<tr>");
			out.println("<th> Nombre de usuario </th>");
			out.println("<th> Nombre </th>");
			out.println("<th> Correo </th>");
			out.println("<th> Contrasena </th>");
			out.println("</tr>");
			

			for(Usuario u: usuarios){
				out.println("<tr>");
				out.println("<td>" + u.getNombreUsuario() +"</td>");
				out.println("<td>" + u.getNombre() +"</td>");
				out.println("<td>" + u.getCorreo() +"</td>");
				out.println("<td>" + u.getContrasena() +"</td>");
				out.println("</tr>");

			}
			out.println("</table>");

		
		%>
   </ul>
</div>
<div class="wrapper row2">
	
	<table>
		<tr>
			<th>
				Añadir usuario:
				<form action="usuarios.jsp" method="post">
					<input type="text" name="aniadirUserName" placeholder="Nombre de usuario"><br>
					<input type="text" name="aniadirNombre" placeholder="Nombre"><br>
					<input type="text" name="aniadirCorreo" placeholder="Correo"><br>
					<input type="text" name="aniadirContrasena" placeholder="Contraseña"><br>
					<input type="submit" value="Añadir">
				</form>	
			</th>
			<th>
				Borrar usuario:
				<form action="usuarios.jsp" method = "post">
					<input type="text" name="borrarUserName" placeholder="Nombre de usuario"><br>
					<input type="submit" value="Borrar">
				</form>
			</th>
			<th>
				Modificar usuario:
				<form action="usuarios.jsp" method = "post">
					<input type="text" name="modificarUserName" placeholder="Nombre de usuario"><br>
					<input type="text" name="modificarNombre" placeholder="Nuevo nombre"><br>
					<input type="text" name="modificarCorreo" placeholder="Nuevo correo"><br>
					<input type="text" name="modificarContrasena" placeholder="Nueva contrasena"><br>
					<input type="submit" value="Modificar">
				</form>
			</th>
		</tr>
	</table>

</div>
<!-- Footer -->
<div class="wrapper row3">
  <footer id="footer" class="clear">
    <p class="fl_left">Copyright &copy; 2018 - All Rights Reserved - <a href="#">Domain Name</a></p>
    <p class="fl_right">Template by <a target="_blank" href="https://www.os-templates.com/" title="Free Website Templates">OS Templates</a></p>
  </footer>
</div>
</body>
</html>