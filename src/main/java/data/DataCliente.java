package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Cliente;

public class DataCliente {

	public Cliente getByDni(Cliente cli) {
		Cliente c = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select dni, apellido, nombre, CUIT, telefono, email from cliente where dni=?");
			stmt.setInt(1, cli.getDni());
			rs = stmt.executeQuery();
			if(rs != null && rs.next()){
				c = new Cliente();
				c.setDni(rs.getInt("dni"));
				c.setNombre(rs.getString("nombre"));
				c.setApellido(rs.getString("apellido"));
				c.setCUIT(rs.getInt("CUIT"));
				c.setTelefono(rs.getString("telefono"));
				c.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) { rs.close(); }
				if(stmt != null) { stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}

	public LinkedList<Cliente> getAll() {
		Cliente c = null;
		LinkedList<Cliente> clientes = new LinkedList<Cliente>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String query = "select * from cliente";
			stmt = DbConnector.getInstancia().getConn().prepareStatement(query);
			System.out.println("===================SQL");
			System.out.println(stmt);
			rs = stmt.executeQuery();
			while(rs.next()){
				c = new Cliente();                   
				c.setDni(rs.getInt("dni"));
				c.setNombre(rs.getString("nombre"));
				c.setApellido(rs.getString("apellido"));
				c.setCUIT(rs.getInt("CUIT"));
				c.setTelefono(rs.getString("telefono"));
				c.setEmail(rs.getString("email"));
				clientes.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) { rs.close(); }
				if(stmt != null) { stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return clientes;
	}

}
