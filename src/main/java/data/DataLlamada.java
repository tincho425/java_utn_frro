package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Llamada;

public class DataLlamada {

	public LinkedList<Llamada> getAll() {
		Llamada ll = null;
		LinkedList<Llamada> llamadas = new LinkedList<Llamada>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String query = "select * from llamada";
			stmt = DbConnector.getInstancia().getConn().prepareStatement(query);
			System.out.println("===================SQL");
			System.out.println(stmt);
			rs = stmt.executeQuery();
			while(rs.next()){
				ll = new Llamada();
				ll.setId(rs.getInt("id"));
				ll.setRemitente(rs.getString("remitente"));
				ll.setNota(rs.getString("nota"));
				ll.setTimestamp_created(rs.getTimestamp("timestamp_created"));
				ll.setTimestamp_ended(rs.getTimestamp("timestamp_ended"));
				ll.setId_usuario(rs.getInt("id_usuario"));
				ll.setId_cliente(rs.getInt("id_cliente"));
				
				llamadas.add(ll);
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
		return llamadas;
	}
	
	public Boolean insert(Llamada l, String[] servicios) {
		PreparedStatement stmt = null;
		Integer rs = null;
		try {
			String query = "insert into llamada (timestamp_created, remitente, nota, id_usuario, id_cliente) values (?, ?, ?, ?, ?)";
			stmt = DbConnector.getInstancia().getConn().prepareStatement(query);
			stmt.setTimestamp(1, l.getTimestamp_created());
			stmt.setString(2, l.getRemitente());
			stmt.setString(3, l.getNota());
			stmt.setInt(4, l.getId_usuario());
			if(l.getId_cliente() != null)
				stmt.setInt(5, l.getId_cliente());
			else
				stmt.setNull(5, java.sql.Types.INTEGER);
			
			System.out.println("===================SQL");
			System.out.println(stmt);
			rs = stmt.executeUpdate();
			
			// Add servicios to recent created Llamada
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) { stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
