package facade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import servidor.Db;
import servidor.Farmacia;
import servidor.Order;

public class OrderFacade {

	Statement stmt = null;
	ResultSet rs = null;
	Db database = null;
	Connection con = null;
	
	PreparedStatement pstmt = null;
	
	public OrderFacade() {
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
	
	public boolean newOrder(Order o) {
		String insertarOrder = "INSERT INTO ORDERS"
				+ "(PRODUCTO,USUARIO) VALUES"
				+ "(?,?)";
		System.out.println(insertarOrder);

		try {
			pstmt = this.con.prepareStatement(insertarOrder);
			pstmt.setInt(1, o.getProducto().getProductoID());
			pstmt.setString(2, o.getUsuario().getNombreUsuario());
			
			pstmt.execute();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return false;
	}
	
	public boolean updateOrder(Order o) {
		
		String updateOrder = "UPDATE ORDERS SET PRODUCTO=?, USUARIO=? where ID=?";
		try {
			pstmt= this.con.prepareStatement(updateOrder);
			pstmt.setInt(1, o.getProducto().getProductoID());
			pstmt.setString(2, o.getUsuario().getNombreUsuario());
			pstmt.setInt(3, o.getOrderID());
			pstmt.execute();
			
			//this.con.close();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteOrder(Order o) {
		
		String deleteOrder = "DELETE FROM ORDERS WHERE ID=?";
		try {
			pstmt= this.con.prepareStatement(deleteOrder);
			pstmt.setInt(1, o.getOrderID());
			pstmt.execute();
			
			//this.con.close();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Order> getOrders(){
		String getOrders = "SELECT * FROM ORDERS";
		ArrayList<Order> orders= new ArrayList<Order>();
		
		try {
			stmt = this.con.createStatement();
			this.rs = stmt.executeQuery(getOrders);
			
			while(rs.next()) {
				orders.add(new Order(rs.getInt("ID"),rs.getInt("PRODUCTO"),rs.getString("USUARIO")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return orders;
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
