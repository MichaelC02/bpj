package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnect
{
	Connection conn = null;
	
	public static void main(String[] args) throws ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
	}
	
	DBConnect() throws SQLException
	{
		conn = DriverManager.getConnection("jdbc:mysql://michaelmarolt.synology.me:3306/bpj", "java", "java");
		
		conn.close();
	}
}
