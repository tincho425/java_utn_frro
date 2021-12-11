package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


import entities.Documento;
import entities.Usuario;

public class DataUsuario {

	public Usuario getByUser(Usuario usu) {
		DataRol dr = new DataRol();
		Usuario u = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre, apellido, tipo_doc, nro_doc, email, tel, habilitado from usuario where email=? and password=?");
			stmt.setString(1, usu.getEmail());
			stmt.setString(2, usu.getPassword());
			rs = stmt.executeQuery();
			if(rs != null && rs.next()){
				u = new Usuario();
				u.setDocumento(new Documento());
				u.setId(rs.getInt("id"));
				u.setNombre(rs.getString("nombre"));
				u.setApellido(rs.getString("apellido"));
				u.getDocumento().setTipo(rs.getString("tipo_doc"));
				u.getDocumento().setNro(rs.getString("nro_doc"));
				u.setEmail(rs.getString("email"));
				u.setTel(rs.getString("tel"));
				u.setHabilitado(rs.getBoolean("habilitado"));
				dr.setRoles(u);
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
		return u;
	}

	public LinkedList<Usuario> getAll(String rol) {
		Usuario u = null;
		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String query = "select * from usuario";
			/*
			 * Filtrar por rol si se pasa como argumento
			 */
			if(rol != null) {
				query = query+" left join rol_usuario\r\n"
						+ "    on rol_usuario.id_usuario = usuario.id\r\n"
						+ "    	left join rol\r\n"
						+ "        on rol_usuario.id_rol = rol.id\r\n"
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
				u = new Usuario();
				u.setDocumento(new Documento());
				u.setId(rs.getInt("id"));
				u.setNombre(rs.getString("nombre"));
				u.setApellido(rs.getString("apellido"));
				u.getDocumento().setTipo(rs.getString("tipo_doc"));
				u.getDocumento().setNro(rs.getString("nro_doc"));
				u.setEmail(rs.getString("email"));
				u.setTel(rs.getString("tel"));
				u.setHabilitado(rs.getBoolean("habilitado"));
				usuarios.add(u);
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
		return usuarios;
	}

	public Usuario getByDocumento(Usuario p) {
		return p;
	}

	public Usuario getById(Usuario usu) {
		DataRol dr = new DataRol();
		Usuario u = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select id,nombre, apellido, tipo_doc, nro_doc, email, tel, habilitado from usuario where id=?");
			stmt.setInt(1, usu.getId());
			System.out.println("===================SQL");
			System.out.println(stmt);
			rs = stmt.executeQuery();
			if(rs != null && rs.next()){
				u = new Usuario();
				u.setDocumento(new Documento());
				u.setId(rs.getInt("id"));
				u.setNombre(rs.getString("nombre"));
				u.setApellido(rs.getString("apellido"));
				u.getDocumento().setTipo(rs.getString("tipo_doc"));
				u.getDocumento().setNro(rs.getString("nro_doc"));
				u.setEmail(rs.getString("email"));
				u.setTel(rs.getString("tel"));
				u.setHabilitado(rs.getBoolean("habilitado"));
				dr.setRoles(u);
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
		return u;
	}

}
