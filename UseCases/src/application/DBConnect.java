package application;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class DBConnect
{
	static Connection conn;
	
	static Connection GetConnection()
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
	
	
	
	static Boolean CheckUserAndPass(String username, String password)
	{
		try
		{
			conn = GetConnection();
			
			
			// MD5 generieren START
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes("UTF-8"));

			byte byteData[] = md.digest();
		    StringBuffer sb = new StringBuffer();
		    for (int i = 0; i < byteData.length; i++)
		        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

		    String md5password = sb.toString();
		    // MD5 generieren ENDE
		    
			
			PreparedStatement ps = conn.prepareStatement("select userId, isAdmin, last_login from user where username = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, md5password);
			
			ResultSet rs = ps.executeQuery();
			rs.first();
			
			Integer userId = rs.getInt("userId"); // Wirft SQLException wenn no_data_found
			String isAdmin = rs.getString("isAdmin");
			
			// Datum muss als Timestamp geholt werden, weil ResultSet.getDate keine Uhrzeit liefert
			Timestamp lastLoginTS = rs.getTimestamp("last_login");
			Date lastLogin = new Date(lastLoginTS.getTime());
			
			CurrentUser.Set(userId, username, isAdmin.equalsIgnoreCase("Y"), lastLogin);
			
			
			// Setze letztes Login-Datum
			ps = conn.prepareStatement("update user set last_login = sysdate() where userId = ?");
			ps.setInt(1, userId);
			Boolean dummy = ps.execute();
			
			return true;
		}
		catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e )
		{
			e.printStackTrace();
			CurrentUser.Clear();
			return false;
		}
	}
	
	
	static Boolean CheckUsernameExists(String username) throws SQLException
	{
		conn = GetConnection();
		
		PreparedStatement ps = conn.prepareStatement("select count(*) from user where username = ?");
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		rs.first();
		
		return rs.getInt(1) > 0;
	}
	
	
	static void CreateUser(String username, String password, Boolean isAdmin) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException
	{
		conn = GetConnection();
		
		
		// MD5 generieren START
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes("UTF-8"));

		byte byteData[] = md.digest();
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < byteData.length; i++)
	        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

	    String md5password = sb.toString();
	    // MD5 generieren ENDE
	    
		
	    String isAdminString = isAdmin ? "Y" : "N";
	    
		PreparedStatement ps = conn.prepareStatement("insert into user (username, password, isAdmin) values (?, ?, ?)");
		ps.setString(1, username);
		ps.setString(2, md5password);
		ps.setString(3, isAdminString);
		
		Boolean dummy = ps.execute();
	}
	
	
	
	static ObservableList<Order> GetOrders() throws SQLException
	{
		conn = GetConnection();
		
		PreparedStatement ps = conn.prepareStatement(
				"select orderId, date, username, customerName, state "
				+ "from orders o "
				+ "join user u on o.userId = u.userId "
				+ "join customers c on o.customerId = c.customerId");
		
		ResultSet rs = ps.executeQuery();
		
		ObservableList<Order> orders = FXCollections.observableArrayList();
		while (rs.next())
		{
			orders.add(new Order(rs.getInt("orderId"),
								 rs.getDate("date"),
								 rs.getString("username"),
								 rs.getString("customerName"),
								 rs.getString("state")));
	    }
	
		return orders;
	}
	
	static ObservableList<Article> GetArticles() throws SQLException
	{
		conn = GetConnection();
		
		PreparedStatement ps = conn.prepareStatement(
				"select article_id, name, price "
				+ "from article "
				);
		
		ResultSet rs = ps.executeQuery();
		
		ObservableList<Article> articles = FXCollections.observableArrayList();
		while (rs.next())
		{
			articles.add(new Article(rs.getString("name"),
								     rs.getInt("article_id"),
									 rs.getInt("price"), 0));
	    }
	
		return articles;
	}
	
	
	static Order GetOrderById(int orderId) throws SQLException
	{
		conn = GetConnection();
		
		PreparedStatement ps = conn.prepareStatement(
				"select date, username, customerName, state "
				+ "from orders o "
				+ "join user u on o.userId = u.userId "
				+ "join customers c on o.customerId = c.customerId "
				+ "where orderId = ?");
		
		ps.setInt(1, orderId);
		
		ResultSet rs = ps.executeQuery();
		rs.first();
		
		Order currentOrder = new Order(orderId,
							    	   rs.getDate("date"),
									   rs.getString("username"),
									   rs.getString("customerName"),
									   rs.getString("state"));
		
		ps = conn.prepareStatement(
				"select a.name, a.article_id, a.price, oa.quantity "
				+ "from article a "
				+ "join order_article oa on a.article_id = oa.article_id "
				+ "where order_id = ?");
		
		ps.setInt(1, orderId);
		
		rs = ps.executeQuery();
		
		ObservableList<Article> articles = FXCollections.observableArrayList();
		while (rs.next())
		{
			articles.add(new Article(rs.getString("name"),
					                 rs.getInt("article_id"),
					                 rs.getInt("price"),
								     rs.getInt("quantity")));
	    }
		
		currentOrder.setArticleList(articles);
	
		return currentOrder;
	}
	
	static int get_max_orderid()throws SQLException
	{
		conn = GetConnection();
		
		PreparedStatement ps = conn.prepareStatement("SELECT MAX(orderId) FROM orders");
		
		ResultSet rs = ps.executeQuery();
		rs.first();
		int orderid = rs.getInt(1);

		return ++orderid;
	}
	
	static ObservableList<customer> get_custs() throws SQLException
	{
		conn = GetConnection();
		
		PreparedStatement ps = conn.prepareStatement(
				"select customerId, customerName "
				+ "from customers "
				);
		
		ResultSet rs = ps.executeQuery();
		
		ObservableList<customer> customer = FXCollections.observableArrayList();
		while (rs.next())
		{
			customer.add(new customer( rs.getInt("customerId"),
									   rs.getString("customerName")));
	    }
	
		return customer;
	}
	
	
	
	
	//Matteo neu
	static boolean neworder(ObservableList<Article> articles, int custId)
	{
		Article article;
		try
		{
			conn = GetConnection();
			
			int orderid = get_max_orderid();
			
			int userid = CurrentUser.getUserId();
			
			PreparedStatement ps = conn.prepareStatement("insert into orders (orderId, state, date, userId, customerId) values (?, 'Offen', sysdate(), ?, ?)");
			ps.setInt(1, orderid);
			ps.setInt(2, userid);
			ps.setInt(3, custId);
			
			Boolean dummy = ps.execute();
			
			for(int row = 0; row < articles.size(); row++)
			{
				article = articles.get(row);
				int art_id = article.getArticleID();
				
				int pric = article.getPrice();
				
				int quan = article.getQuantity();
				
			
				ps = conn.prepareStatement("insert into order_article (order_id, article_id, price, quantity) values(?, ?, ?, ?)");
				ps.setInt(1, orderid);
				ps.setInt(2, art_id);
				ps.setInt(3, pric);
				ps.setInt(4, quan);
				
				Boolean dummy2 = ps.execute();
			    
			    
				
			
			//return rs.getInt(1) == 1;
			}
			
		/*	java.sql.Statement statement = conn.createStatement();
			statement.executeUpdate("Insert into orders Values(");*/
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	/*	catch (NoSuchAlgorithmException e1)
		{
			e1.printStackTrace();
			return false;
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return false;
		}*/
	}


}
