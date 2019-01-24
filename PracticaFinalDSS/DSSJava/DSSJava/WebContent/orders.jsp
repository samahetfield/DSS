<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Encargos</title>
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
      <h1><a href="index.html">Encargos</a></h1>
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
	<h2>Listado de encargos</h2>
   <ul>
        <%@ page import="java.util.ArrayList" %>
		<%@ page import="facade.OrderFacade" %>
		<%@ page import="servidor.Order" %>
		<%
		
			OrderFacade orfac = new OrderFacade();
			
			String aniadirUserName = request.getParameter("aniadirUserName");
			String aniadirProductoID =request.getParameter("aniadirProductoID");
			
			String borrarID = request.getParameter("borrarID");
			
			String modificarID = request.getParameter("modificarID");
			String modificarUserName = request.getParameter("modificarUserName");
			String modificarProductoID = request.getParameter("modificarProductoID");


		
			if(aniadirUserName != null && aniadirProductoID != null){
				Order o = new Order(0,Integer.parseInt(aniadirProductoID),aniadirUserName);
				orfac.newOrder(o);
			}
			
			if(borrarID != null){
				Order o = new Order(Integer.parseInt(borrarID),0,"");
				orfac.deleteOrder(o);
			}
			
			if(modificarID != null && modificarUserName != null && modificarProductoID != null){
				Order o = new Order(Integer.parseInt(modificarID),Integer.parseInt(modificarProductoID),modificarUserName);
				orfac.updateOrder(o);
			}
				
		
			
			ArrayList<Order> orders = orfac.getOrders();
			
			out.println("<table>");
			out.println("<tr>");
			out.println("<th> ID </th>");
			out.println("<th> ID del producto </th>");
			out.println("<th> Nombre de usuario </th>");
			out.println("</tr>");
			

			for(Order o: orders){
				out.println("<tr>");
				out.println("<td>" + o.getOrderID() +"</td>");
				out.println("<td>" + o.getProducto().getProductoID() +"</td>");
				out.println("<td>" + o.getUsuario().getNombreUsuario() +"</td>");
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
				Añadir encargo:
				<form action="orders.jsp" method="post">
					<input type="text" name="aniadirUserName" placeholder="Nombre de usuario"><br>
					<input type="text" name="aniadirProductoID" placeholder="ID del producto"><br>
					<input type="submit" value="Añadir">
				</form>	
			</th>
			<th>
				Borrar encargo:
				<form action="orders.jsp" method = "post">
					<input type="text" name="borrarID" placeholder="ID"><br>
					<input type="submit" value="Borrar">
				</form>
			</th>
			<th>
				Modificar encargo:
				<form action="orders.jsp" method = "post">
					<input type="text" name="modificarID" placeholder="ID"><br>
					<input type="text" name="modificarProductoID" placeholder="ID del producto"><br>
					<input type="text" name="modificarUserName" placeholder="Nombre de usuario"><br>
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