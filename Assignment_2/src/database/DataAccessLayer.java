package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataAccessLayer {
	
	String url = "jdbc:sqlserver://localhost:1433;database=Assignment2;";
	String loginName = "sa";
	String password = "AlexandraAmarOgnjenRobert1!";
	Connection con;
	
	public DataAccessLayer() throws SQLException {
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		con = DriverManager.getConnection(url,loginName,password);
	}
	
	public ResultSet getEmployeeInfo() throws SQLException {
		String query = "SELECT [First Name], [Last Name], [Job Title], [Address], [E-Mail] FROM [CRONUS Sverige AB$Employee]";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	public ResultSet getEmployeeRelative() throws SQLException {
		String query = "SELECT [Employee No_], [Relative Code], [First Name], [Last Name], [Birth Date] FROM [CRONUS Sverige AB$Employee Relative]";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	public ResultSet getEmployeeAbsence() throws SQLException {
		String query = "SELECT [Entry No_], [Employee No_], [From Date], [To Date], [Description] FROM [CRONUS Sverige AB$Employee Absence]";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	public ResultSet getEmployeeQualification() throws SQLException {
		String query = "SELECT [Employee No_], [From Date], [To Date], Description, Institution_Company FROM [CRONUS Sverige AB$Employee Qualification]";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	public ResultSet getEmployeeStatsGroup() throws SQLException {
		String query = "SELECT * FROM [CRONUS Sverige AB$Employee Statistics Group]";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	public ResultSet getEmployeePortalSetup() throws SQLException {
		String query = "SELECT [First Name], [Last Name], [Job Title], [Address], [E-Mail] FROM [CRONUS Sverige AB$Employee]";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}


	public ResultSet getKeys() throws SQLException {
		String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public ResultSet getConstraints() throws SQLException {
		String query = "SELECT CONSTRAINT_NAME FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public ResultSet getTables() throws SQLException {
		String query = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE'";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public ResultSet getEmployeeColumns() throws SQLException {
		String query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'CRONUS Sverige AB$Employee'";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public ResultSet getEmployeeMetadata() throws SQLException {
		String query = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME LIKE '%AB$Employee%'";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	public ResultSet getMaxRowsTable() throws SQLException {
		String query = "SELECT t.name FROM sys.tables t WHERE t.object_id IN (SELECT p1.object_id FROM sys.partitions p1 WHERE p1.rows = (SELECT MAX(p2.rows) FROM sys.partitions p2))";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}



}	

