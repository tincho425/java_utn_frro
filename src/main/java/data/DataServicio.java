package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Servicio;

public class DataServicio {

	public LinkedList<Servicio> getAll() {
		Servicio s = null;
		LinkedList<Servicio> servicios = new LinkedList<Servicio>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String query = "select * from servicio";
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

}
