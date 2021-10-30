package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.LinkedList;

import entities.Product;

public class DbHandler {

	private Connection conn = null;
	private String option = "?useLegacyDatetimeCode=false&serverTimezone=Asia/Hong_Kong";
	
	private Connection getConnection() { // No implementado abajo
		try {
			if(conn == null || conn.isClosed())
				conn = DriverManager.getConnection("jdbc:mysql://localhost/javaMarket?serverTimezone=UTC","java","himitsu");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private void releaseConnection() { // No implementado abajo
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public DbHandler() {
		// registrar el conector
		try {
			//new com.mysql.cj.jdbc.Driver();
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void initConnection() {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost/javaMarket","java","himitsu");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public LinkedList<Product> list(){
		LinkedList<Product> prods = new LinkedList<Product>();
		try {
			// crear y abrir una conexión
			//this.initConnection();
			Connection conn = null;
			conn = this.getConnection();
			
			// ejecutar la query
	        Statement stmt = conn.createStatement();
	        ResultSet rs= stmt.executeQuery("select * from product");
	
	        // mapear de resultset a objeto
	        while(rs.next()) {
	        	Product p=new Product();
	
	            p.setId(rs.getInt("id"));
	            p.setName(rs.getString("name"));
	            p.setDescription(rs.getString("description"));
	            p.setPrice(rs.getDouble("price"));
	            p.setStock(rs.getInt("stock"));
	            p.setShippingIncluded(rs.getBoolean("shippingIncluded"));
	            p.setDisabledOn(rs.getTimestamp("disabledOn").toLocalDateTime());
	
	            prods.add(p);
	
	        }
	        //cerrar recursos
	        if(rs!=null){rs.close();}
	        if(stmt!=null){stmt.close();}
	
		    this.releaseConnection();
		} catch (SQLException ex) {
		    // Manejo de errores
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
	    return prods;
	    
	}

	public Product search(Product p) {
		Product prod = new Product();
		try {
			// crear y abrir una conexión
			this.initConnection();
			
			// ejecutar la query
	        PreparedStatement stmt = this.conn.prepareStatement("select * from product where id=?");
	        stmt.setInt(1, p.getId());
	        ResultSet rs= stmt.executeQuery();
	
	        // mapear de resultset a objeto
	        while(rs.next()) {
	        	
	            prod.setId(rs.getInt("id"));
	            prod.setName(rs.getString("name"));
	            prod.setDescription(rs.getString("description"));
	            prod.setPrice(rs.getDouble("price"));
	            prod.setStock(rs.getInt("stock"));
	            prod.setShippingIncluded(rs.getBoolean("shippingIncluded"));
	            java.sql.Timestamp t = rs.getTimestamp("disabledOn");
	            p.setDisabledOn(t == null ? null : t.toLocalDateTime());
	            
	            // p.setDisabledOn(rs.getObject("disabledOn", LocalDateTime.class));
	            p.setDisabledDate(rs.getObject("disabledDate", LocalDate.class));
	            p.setDisabledTime(rs.getObject("disabledTime", LocalTime.class));
	            
	        }
	        //cerrar recursos
	        if(rs!=null){rs.close();}
	        if(stmt!=null){stmt.close();}
	
		    this.conn.close();
		} catch (SQLException ex) {
		    // Manejo de errores
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
	    return prod;
	}
	
	public LinkedList<Integer> newP(LinkedList<Product> ps) {
		LinkedList<Integer> ids = new LinkedList<>(); 
		// crear y abrir una conexión
		this.initConnection();
		
		for (Product product : ps) {
			
			PreparedStatement stmt = null;
			try {
				stmt = this.conn.prepareStatement("INSERT INTO `product` (name,description,price,stock,shippingIncluded, disabledOn, disabledDate, disabledTiem) VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, product.getName());
		        stmt.setString(2, product.getDescription());
		        stmt.setDouble(3, product.getPrice());
		        stmt.setInt(4, product.getStock());
		        stmt.setBoolean(5, product.isShippingIncluded());
		        stmt.setTimestamp(6, java.sql.Timestamp.valueOf(product.getDisabledOn()));
		        // stmt.setTimestamp(6, product.getDisabledOn());  Esto funciona. javaSQL y conector se encargan
		        // stmt.setObject(6, MysqlType.)
		        stmt.setDate(7, java.sql.Date.valueOf(product.getDisabledDate()));
		        stmt.setTime(8, java.sql.Time.valueOf(product.getDisabledTime()));
		        
		        stmt.executeUpdate();
		        ResultSet rs = stmt.getGeneratedKeys();
		        if(rs != null && rs.next()) {
	                int last_inserted_id = rs.getInt(1);
	                ids.add(last_inserted_id);
	                product.setId(last_inserted_id); // El objeto queda completo
	            }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	        
		}
		
		// return product Ya queda completado la instancia lista para retornarla
		return ids;
	}
	
	public void delete(Product delPrd) {
		LinkedList<Integer> ids = new LinkedList<>(); 
		// crear y abrir una conexión
		this.initConnection();

		PreparedStatement stmt = null;
		try {
			stmt = this.conn.prepareStatement("DELETE FROM `product` WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, delPrd.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(Product updPrd) {
		this.initConnection();

		PreparedStatement stmt = null;
		try {
			stmt = this.conn.prepareStatement("UPDATE product SET name = ?, description = ?, price = ?, stock = ?, shippingIncluded = ?, disabledOn = ?, disabledDate = ?, disabledTime = ? "+
					"WHERE id = ?");
			stmt.setString(1, updPrd.getName());
	        stmt.setString(2, updPrd.getDescription());
	        stmt.setDouble(3, updPrd.getPrice());
	        stmt.setInt(4, updPrd.getStock());
	        stmt.setBoolean(5, updPrd.isShippingIncluded());
	        stmt.setObject(6, updPrd.getDisabledOn());
	        stmt.setObject(7, updPrd.getDisabledDate());
	        stmt.setObject(8, updPrd.getDisabledTime());
	        stmt.setObject(9, updPrd.getId());
	        stmt.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
