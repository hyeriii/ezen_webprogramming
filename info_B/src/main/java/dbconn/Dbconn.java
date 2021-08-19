package dbconn;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbconn {
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "c##BCLASS";
	private String pass = "1234";
	
	Connection conn = null;

	public Connection getConnect() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			conn = DriverManager.getConnection(url,user,pass); 
	
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
}
