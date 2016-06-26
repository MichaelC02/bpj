package application;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class DBConnect
{
	static Connection conn;
	
	static Connection getConnection()
	{
		if (conn == null)
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://michaelmarolt.synology.me:3306/bpj", "java", "java");
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		
		return conn;
	}
	
	
	//Matteo neu
	static Boolean neworder( String name2 )
	{
		return true;
	}
	
	
	static Boolean checkUserAndPass(String username, String password)
	{
		try
		{
			conn = getConnection();
			
			
			// MD5 generieren START
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes("UTF-8"));

			byte byteData[] = md.digest();
		    StringBuffer sb = new StringBuffer();
		    for (int i = 0; i < byteData.length; i++)
		        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

		    String md5password = sb.toString();
		    // MD5 generieren ENDE
		    
			
			PreparedStatement ps = conn.prepareStatement("select count(*) from user where username = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, md5password);
			
			ResultSet rs = ps.executeQuery();
			rs.first();
			
			return rs.getInt(1) == 1;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (NoSuchAlgorithmException e1)
		{
			e1.printStackTrace();
			return false;
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return false;
		}

	}
}
