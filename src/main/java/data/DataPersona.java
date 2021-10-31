package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


import entities.Documento;
import entities.Persona;

public class DataPersona {

	public Persona getByUser(Persona per) {
		DataRol dr = new DataRol();
		Persona p = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre, apellido, tipo_doc, nro_doc, email, tel, habilitado from persona where email=? and password=?");
			stmt.setString(1, per.getEmail());
			stmt.setString(2, per.getPassword());
			rs = stmt.executeQuery();
			if(rs != null && rs.next()){
				p = new Persona();
				p.setDocumento(new Documento());
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.getDocumento().setTipo(rs.getString("tipo_doc"));
				p.getDocumento().setNro(rs.getString("nro_doc"));
				p.setEmail(rs.getString("email"));
				p.setTel(rs.getString("tel"));
				p.setHabilitado(rs.getBoolean("habilitado"));
				dr.setRoles(p);
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
		return p;
	}

	public LinkedList<Persona> getAll(String rol) {
		Persona p = null;
		LinkedList<Persona> personas = new LinkedList<Persona>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String query = "select * from persona";
			/*
			 * Filtrar por rol si se pasa como argumento
			 */
			if(rol != null) {
				query = query+" left join rol_persona\r\n"
						+ "    on rol_persona.id_persona = persona.id\r\n"
						+ "    	left join rol\r\n"
						+ "        on rol_persona.id_rol = rol.id\r\n"
						+ "        	where rol.descripcion = ?";
			}
			stmt = DbConnector.getInstancia().getConn().prepareStatement(query);
			if(rol != null) {
				stmt.setString(1, rol);
			}
			System.out.println("===================SQL");
			System.out.println(stmt);
			rs = stmt.executeQuery();
			while(rs.next()){
				p = new Persona();
				p.setDocumento(new Documento());
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.getDocumento().setTipo(rs.getString("tipo_doc"));
				p.getDocumento().setNro(rs.getString("nro_doc"));
				p.setEmail(rs.getString("email"));
				p.setTel(rs.getString("tel"));
				p.setHabilitado(rs.getBoolean("habilitado"));
				personas.add(p);
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
		return personas;
	}

	public Persona getByDocumento() {
		// TODO Auto-generated method stub
		return null;
	}

}
