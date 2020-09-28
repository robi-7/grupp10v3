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
	
	public ResultSet viewStudents() throws SQLException {
		String query = "SELECT * FROM Student";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public ResultSet getStudent(String studentID) throws SQLException {
		String query = "SELECT * FROM Student WHERE studentID = '" + studentID + "'";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public String registerStudent(String name, String ssn, String address, String email) throws SQLException {
		String checkStudentIDQuery = "SELECT studentID FROM Student ORDER BY studentID DESC";
		PreparedStatement ps = con.prepareStatement(checkStudentIDQuery);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String highestStudentID = rs.getString(1);
		int indexStudentID = Integer.parseInt(highestStudentID.substring(1)) + 1;
	    String studentID;
		if (indexStudentID < 10) {
			studentID = "S00" + indexStudentID;
		 } else if(indexStudentID < 100) {
			 studentID = "S0" + indexStudentID;
		 } else  {
			 studentID = "S" + indexStudentID; 
		 }
		
		
		String registerStudentQuery = "INSERT INTO Student VALUES('"+ studentID +"', '" + name + "', '" + ssn + "', '"+ address +"', '"+ email +"')";
		Statement statement = con.createStatement();
		statement.executeUpdate(registerStudentQuery);
		
		return studentID; 
	}
	public void deleteStudent(String studentID) throws SQLException {
		String queryDeleteHasStudied = "DELETE FROM HasStudied WHERE studentID = '"+ studentID +"' " + "DELETE FROM Studies WHERE studentID = '"+ studentID +"' " + "DELETE FROM Student WHERE studentID = '"+ studentID +"'";
		Statement statementHasStudied = con.createStatement();
		statementHasStudied.executeUpdate(queryDeleteHasStudied);
	}
	
	public ResultSet getStudentCourses(String studentID) throws SQLException {
		String query = "SELECT c.courseID, c.name, c.credits, h.grade FROM Course c, HasStudied h WHERE h.studentID = '" + studentID + "' AND h.courseID = c.courseID UNION SELECT c.courseID, c.name, c.credits, 'Participating' FROM Course c, Studies s WHERE s.studentID = '" + studentID + "' AND s.courseID = c.courseID ORDER BY h.grade DESC";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public ResultSet viewCourses() throws SQLException {
		String query = "SELECT * FROM Course";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	public ResultSet getCourse(String courseID) throws SQLException {
		String query = "SELECT * FROM Course WHERE courseID = '" + courseID + "'";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	public ResultSet getCourseStudents(String courseID) throws SQLException {
		String query = "SELECT s.studentID, s.name, hs.grade FROM Student s, HasStudied hs WHERE hs.courseID = '" + courseID + "' AND hs.studentID = s.studentID UNION SELECT s.studentID, s.name, 'Participating' FROM Student s, Studies st WHERE st.courseID = '" + courseID + "' AND st.studentID = s.studentID UNION SELECT s.studentID, s.name, 'Not registered' FROM Student s WHERE s.studentID NOT IN (SELECT DISTINCT studentID FROM Studies WHERE courseID = '" + courseID + "' UNION SELECT DISTINCT studentID FROM HasStudied WHERE courseID = '" + courseID + "') ORDER BY s.studentID ASC";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	
	//Går inte att namnge String till courseID
	public String registerCourse(String name, int credits) throws SQLException {
		String checkCourseIDQuery = "SELECT courseID FROM Course ORDER BY courseID DESC";
		PreparedStatement ps = con.prepareStatement(checkCourseIDQuery);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String highestCourseID = rs.getString(1);
		int indexCourseID = Integer.parseInt(highestCourseID.substring(1)) + 1;
		String courseID;
		if (indexCourseID < 10) {
			courseID = "C00" + indexCourseID;
		} else if (indexCourseID < 100) {
			courseID = "C0" + indexCourseID;
		} else {
			courseID = "C" + indexCourseID;
		}
		
		String registerCourseQuery = "INSERT INTO Course VALUES('"+ courseID +"', '" + name + "', " + credits + ")";
		Statement statement = con.createStatement();
		statement.executeUpdate(registerCourseQuery);
		
		return courseID;
	}
	public void deleteCourse(String courseID) throws SQLException {
		String query = "DELETE FROM HasStudied WHERE courseID = '"+ courseID +"' " + "DELETE FROM Studies WHERE courseID = '"+ courseID +"' " + "DELETE FROM Course WHERE courseID = '"+ courseID +"'";
		Statement statement = con.createStatement();
		statement.executeUpdate(query);
	}
	
	public void registerStudies(String courseID, String studentID) throws SQLException {
		String query = "INSERT INTO Studies VALUES('"+ courseID +"', '" + studentID + "')";
		Statement statement = con.createStatement();
		statement.executeUpdate(query);
	}
	public void deleteStudies(String courseID, String studentID) throws SQLException {
		String query = "DELETE FROM Studies WHERE studentID = '"+ studentID +"' AND courseID = '"+ courseID +"'";
		Statement statement = con.createStatement();
		statement.executeUpdate(query);
	}
		
	public void registerHasStudied(String grade, String courseID, String studentID) throws SQLException {
		String query = "DELETE FROM Studies WHERE studentID = '" + studentID + "' AND courseID = '" + courseID + "' INSERT INTO HasStudied VALUES('"+ grade +"', '" + courseID + "', '" + studentID + "')";
		Statement statement = con.createStatement();
		statement.executeUpdate(query);
	}
	
	public String getShareGradeA(String courseID) {
		String share;
		try {
			String query = "SELECT (Count(grade)*100 / (SELECT COUNT(*) FROM HasStudied WHERE courseID = '" + courseID + "')) FROM HasStudied WHERE courseID = '" + courseID + "' GROUP BY grade HAVING grade = 'A'";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			rs.next();
			share = rs.getString(1);
		} catch (SQLException e) {
			share = "0";
		}
		return share + "%";
	}
	
	public String getThroughput(String courseID) {
		int throughput;
		try {
			String query = "SELECT (Count(hs.grade)*100 / (SELECT COUNT(*) FROM HasStudied WHERE courseID = '" + courseID + "')) FROM HasStudied hs WHERE hs.courseID = '" + courseID + "' AND hs.grade = 'F'";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			rs.next();
			throughput = 100 - Integer.parseInt(rs.getString(1));
		} catch (SQLException e) {
			throughput = 0;
		}
		return throughput + "%";
	}
	
	public int getCredits(String studentID) throws SQLException {
		int credits = 0;
		try {
			String query = "SELECT SUM(credits) FROM Course WHERE courseID IN (SELECT courseID FROM Studies WHERE studentID = '" + studentID + "')";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			rs.next();
			System.out.println(rs.getString(1));
			credits = Integer.parseInt(rs.getString(1));
		} catch (NumberFormatException e) {
			credits = 0;
		}
		return credits;
	}
}
