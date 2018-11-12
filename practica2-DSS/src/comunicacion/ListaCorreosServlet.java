package comunicacion;

import java.util.List;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

import modelo.BDUsuario;
import modelo.Usuario;

@WebServlet("/ListaCorreosServlet")
public class ListaCorreosServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("action");
		String name = request.getParameter("nombre");
		String surname = request.getParameter("apellidos");
		String email = request.getParameter("email");

		if (accion == null) {
			response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			writer.println("<h1>Practica 2 de DSS: Arquitecturas Software</h1>");
			writer.print("<style>\r\n" + 
					"	table, th, td{\r\n" + 
					"    	border: 1px solid black;\r\n" + 
					"        border-collapse: collapse;\r\n" + 
					"    }\r\n" + 
					"</style>");
			writer.print("<table style=\"width:100%\">\r\n" + 
					"  <tr style=\"text-align: left\">\r\n" + 
					"    <th>Nombre</th>\r\n" + 
					"    <th>Apellidos</th> \r\n" + 
					"    <th>Email</th>\r\n" + 
					"  </tr>");
			
			for(Usuario usuario:BDUsuario.listarUsuarios()) {
				writer.println("<tr> "
							 + "<td>" + usuario.getName() + "</td>"
							 + "<td>" + usuario.getApellidos() + "</td>"
							 + "<td>" + usuario.getEmail() + "</td> "
							 + "</tr>");
			}
			
			writer.print("</table>");
		} else {
			//PrintWriter writer2 = response.getWriter();

			ObjectOutputStream objeto = new ObjectOutputStream(response.getOutputStream());
			
			switch (accion) {
			case "listarUsuario":
				List<Usuario> listaUsuarios = BDUsuario.listarUsuarios();
				objeto.writeObject(listaUsuarios);
				objeto.flush();
				objeto.close();
				break;
			case "eliminarUsuario":
				if (BDUsuario.existeEmail(email)) {
					Usuario user = BDUsuario.seleccionarUsuario(email);
					BDUsuario.eliminar(user);
					
					objeto.writeInt(0);
					objeto.writeObject("Usuario eliminado correctamente");

				} else {
					
					objeto.writeInt(1);
					objeto.writeObject("Usuario no eliminado correctamente");
					//writer2.println("<span> Usuario no encontrado </span>");
				}
				break;
			case "aniadirUsuario":
				if (!BDUsuario.existeEmail(email)) {
					Usuario u = new Usuario();
					u.setName(name);
					u.setApellidos(surname);
					u.setEmail(email);
					//Usuario user = BDUsuario.seleccionarUsuario(email);
					BDUsuario.insertar(u);
					
					objeto.writeInt(0);
					objeto.writeObject("Usuario añadido correctamente");
					
				} else {
					objeto.writeInt(1);
					objeto.writeObject("El usuario ya exsite");

					//writer2.println("<span> El usuario ya existe </span>");
				}
				break;
			case "actualizarUsuario":
				if (BDUsuario.existeEmail(email)) {
					Usuario user = BDUsuario.seleccionarUsuario(email);
					BDUsuario.actualizar(user);
					
					objeto.writeInt(0);
					objeto.writeObject("Usuario actualizado correctamente");
				} else {
					objeto.writeInt(1);
					objeto.writeObject("Usuario no actualizado");
					//writer2.println("<span> El usuario no existe </span>");
				}
				break;
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}