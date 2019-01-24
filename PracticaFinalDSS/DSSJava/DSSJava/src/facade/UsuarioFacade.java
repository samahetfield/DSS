package facade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import servidor.Db;
import servidor.Usuario;
import servidor.Usuario;
import servidor.Usuario;
import servidor.Usuario;

public class UsuarioFacade {

	Statement stmt = null;
	ResultSet rs = null;
	Db database = null;
	Connection con = null;
	
	PreparedStatement pstmt = null;
	
	public UsuarioFacade() {
		try {
			this.database = new Db();
			this.con = this.database.getDB();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean newUsuario(Usuario u) {
		String insertarUsuario = "INSERT INTO USUARIO"
				+ "(USERNAME,NOMBRE, CORREO, CONTRASENA) VALUES"
				+ "(?,?,?,?)";
		//System.out.println(insertarUsuario);

		try {
			pstmt = this.con.prepareStatement(insertarUsuario);
			pstmt.setString(1,u.getNombreUsuario());
			pstmt.setString(2,u.getNombre());
			pstmt.setString(3,u.getCorreo());
			pstmt.setString(4,u.getContrasena());
		
			pstmt.execute();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return false;
	}
	
	public boolean updateUsuario(Usuario u) {
		
		String updateUsuario = "UPDATE USUARIO SET NOMBRE=?,CORREO=?,CONTRASENA=? where USERNAME=?";
		try {
			pstmt= this.con.prepareStatement(updateUsuario);
			pstmt.setString(1, u.getNombre());
			pstmt.setString(2, u.getCorreo());
			pstmt.setString(3, u.getContrasena());
			pstmt.setString(4, u.getNombreUsuario());
			pstmt.execute();
			
			//this.con.close();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteUsuario(Usuario u) {
		
		String deleteUsuario = "DELETE FROM USUARIO WHERE USERNAME=?";
		try {
			pstmt= this.con.prepareStatement(deleteUsuario);
			pstmt.setString(1, u.getNombreUsuario());
			pstmt.execute();
			
			//this.con.close();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Usuario> getUsuarios(){
		String getUsuarios = "SELECT * FROM USUARIO";
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			stmt = this.con.createStatement();
			this.rs = stmt.executeQuery(getUsuarios);
			
			while(rs.next()) {
				usuarios.add(new Usuario(rs.getString("NOMBRE"),rs.getString("USERNAME"),rs.getString("CORREO"), rs.getString("CONTRASENA")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public void close() {
		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
