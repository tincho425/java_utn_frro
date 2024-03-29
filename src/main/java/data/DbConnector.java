package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
	
	private static DbConnector instancia;
	
	private String driver="com.mysql.cj.jdbc.Driver";
	/*private String host="localhost";
	private String port="3306";
	private String user="web";
	private String password="himitsu";
	private String db="CallCenter";*/
	private int conectados=0;
	private Connection conn=null;
	
	private DbConnector() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static DbConnector getInstancia() {
		if (instancia == null) {
			instancia = new DbConnector();
		}
		return instancia;
	}
	
	public Connection getConn() {
		try {
			if(conn==null || conn.isClosed()) {
				String dbHost = System.getenv("DB_HOST");
				String connectionString = "jdbc:mysql://"+dbHost+"/martin-java_callcenter?user=290076&password=tincho123&serverTimezone=UTC";
				System.out.println("---connectionString: "+connectionString);
				conn=DriverManager.getConnection(connectionString);
				conectados=0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conectados++;
		return conn;
	}
	
	public void releaseConn() {
		conectados--;
		try {
			if (conectados<=0) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
