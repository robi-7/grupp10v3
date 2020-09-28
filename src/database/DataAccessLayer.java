package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataAccessLayer {
	
	String url = "jdbc:sqlserver://localhost:1433;database=Assignment1;";
	String loginName = "sa";
	String password = "AlexandraAmarOgnjenRobert1!";
	Connection con;
	
	public DataAccessLayer() throws SQLException {
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		con = DriverManager.getConnection(url,loginName,password);
	}
	
	
}
