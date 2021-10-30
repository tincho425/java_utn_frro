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
					"select id,nombre, apellido, tipo_doc, nro_doc, email, tal, habilitado from persona where email=? and password=?");
			stmt.setString(1, per.getEmail());
			stmt.setString(1, per.getPassword());
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
		}
		return p;
	}

	public LinkedList<Persona> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Persona getByDocumento() {
		// TODO Auto-generated method stub
		return null;
	}

}
