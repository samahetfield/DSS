<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Farmacias</title>
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
      <h1><a href="index.html">Farmacias</a></h1>
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
	<h2>Listado de farmacias</h2>
   <ul>
        <%@ page import="java.util.ArrayList" %>
		<%@ page import="facade.FarmaciaFacade" %>
		<%@ page import="servidor.Farmacia" %>
		<%
		
			FarmaciaFacade farmafac = new FarmaciaFacade();
			
			String aniadirNombre = request.getParameter("aniadirNombre");
			String aniadirLatitud = request.getParameter("aniadirLatitud");
			String aniadirLongitud = request.getParameter("aniadirLongitud");

			String borrarID = request.getParameter("borrarID");
			
			String modificarID = request.getParameter("modificarID");
			String modificarNombre = request.getParameter("modificarNombre");
			String modificarLatitud = request.getParameter("modificarLatitud");
			String modificarLongitud = request.getParameter("modificarLongitud");

		
			if(aniadirNombre != null && aniadirLatitud != null && aniadirLongitud != null){
				Farmacia f = new Farmacia(0,aniadirNombre,Float.parseFloat(aniadirLatitud), Float.parseFloat(aniadirLongitud));
				farmafac.newFarmacia(f);
			}
			if(borrarID != null){
				Farmacia f = new Farmacia(Integer.parseInt(borrarID),"",0,0);
				farmafac.deleteFarmacia(f);
			}
			if(modificarID != null && modificarNombre != null && modificarLatitud != null && modificarLongitud != null){
				Farmacia f = new Farmacia(Integer.parseInt(modificarID), modificarNombre, Float.parseFloat(modificarLatitud), Float.parseFloat(modificarLongitud) );
				farmafac.updateFarmacia(f);
			}
				
			
			
			ArrayList<Farmacia> farmacias = farmafac.getFarmacias();
			
			out.println("<table>");
			out.println("<tr>");
			out.println("<th> ID </th>");
			out.println("<th> Nombre </th>");
			out.println("<th> Latitud </th>");
			out.println("<th> Longitud </th>");
			out.println("</tr>");
			

			for(Farmacia f: farmacias){
				out.println("<tr>");
				out.println("<td>" + f.getID() +"</td>");
				out.println("<td>" + f.getNombre() +"</td>");
				out.println("<td>" + f.getLatitud() +"</td>");
				out.println("<td>" + f.getLongitud() +"</td>");
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
				Añadir farmacia:
				<form action="farmacias.jsp" method="post">
					<input type="text" name="aniadirNombre" placeholder="Nombre"><br>
					<input type="text" name="aniadirLatitud" placeholder="Latitud"><br>
					<input type="text" name="aniadirLongitud" placeholder="Longitud"><br>
					<input type="submit" value="Añadir">
				</form>	
			</th>
			<th>
				Borrar farmacia:
				<form action="farmacias.jsp" method = "post">
					<input type="text" name="borrarID" placeholder="ID"><br>
					<input type="submit" value="Borrar">
				</form>
			</th>
			<th>
				Modificar farmacia:
				<form action="farmacias.jsp" method = "post">
					<input type="text" name="modificarID" placeholder="ID"><br>
					<input type="text" name="modificarNombre" placeholder="Nuevo nombre"><br>
					<input type="text" name="modificarLatitud" placeholder="Nuevo latitud"><br>
					<input type="text" name="modificarLongitud" placeholder="Nuevo longitud"><br>
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