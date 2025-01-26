package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connectionBBDD implements parametrizable {
	private Connection conn=null;
	private Statement stmt=null;
	private ResultSet res=null;
	
	public Connection openConn() throws ClassNotFoundException, SQLException {
		//Class.forName(driver);
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn=DriverManager.getConnection(getURL());
		return conn;
	}
	public boolean setQuery(String sql) throws ClassNotFoundException, SQLException {
		stmt=(Statement)openConn().createStatement();
		 stmt.executeUpdate(sql);
		 return closeConn();
	}
	public ResultSet getQuery(String sql) throws SQLException, ClassNotFoundException {
		stmt=(Statement)openConn().createStatement();
		return stmt.executeQuery(sql);
	}
	public boolean closeConn() throws SQLException{
		if(conn!=null) {
			stmt.close();
			conn.close();
			return true;
		}
		return false;
	}
}
