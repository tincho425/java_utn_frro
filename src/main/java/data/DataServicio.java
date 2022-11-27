package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.Servicio;

public class DataServicio {

	public LinkedList<Servicio> getAll() {
		Servicio s = null;
		LinkedList<Servicio> servicios = new LinkedList<Servicio>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String query = "select * from servicio where eliminado = FALSE";
			stmt = DbConnector.getInstancia().getConn().prepareStatement(query);
			System.out.println("===================SQL");
			System.out.println(stmt);
			rs = stmt.executeQuery();
			while(rs.next()){
				s = new Servicio();
				s.setId(rs.getInt("id"));
				s.setNombre(rs.getString("nombre"));
				s.setDescripcion(rs.getString("descripcion"));
				
				servicios.add(s);
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
		return servicios;
	}
	
	public Servicio getById(Servicio s) {
		Servicio sCallback = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String query = "select * from servicio where eliminado = FALSE and id = ?";
			stmt = DbConnector.getInstancia().getConn().prepareStatement(query);
			stmt.setInt(1, s.getId());
			System.out.println("===================SQL");
			System.out.println(stmt);
			rs = stmt.executeQuery();
			while(rs.next()){
				sCallback = new Servicio();
				sCallback.setId(rs.getInt("id"));
				sCallback.setNombre(rs.getString("nombre"));
				sCallback.setDescripcion(rs.getString("descripcion"));
				
				return sCallback;
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
		return sCallback;
	}
	
	public Boolean update(Servicio s) {
		PreparedStatement stmt = null;
		int rs = 0;
		try {
			String query = "update servicio set"
					+ " nombre = ?, descripcion = ?"
					+ " where id = ?";
			stmt = DbConnector.getInstancia().getConn().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, s.getNombre());
			stmt.setString(2, s.getDescripcion());
			stmt.setInt(3, s.getId());
			System.out.println("===================SQL");
			System.out.println(stmt);
			rs = stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(stmt != null) { stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Boolean insert(Servicio s) {
		PreparedStatement stmt = null;
		Integer rs = null;
		try {
			String query = "insert into servicio (nombre, descripcion) values (?, ?)";
			stmt = DbConnector.getInstancia().getConn().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, s.getNombre());
			stmt.setString(2, s.getDescripcion());
			
			System.out.println("===================SQL");
			System.out.println(stmt);
			rs = stmt.executeUpdate();

			return true;			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(stmt != null) { stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Boolean delete(Servicio s) {
		PreparedStatement stmt = null;
		int rs = 0;
		try {
			String query = "update servicio set eliminado = 1 where id = ?";
			stmt = DbConnector.getInstancia().getConn().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, s.getId());
			System.out.println("===================SQL");
			System.out.println(stmt);
			rs = stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(stmt != null) { stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
