package application;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class DBConnect_Test
{
	
	@Test
	public void testConnection()
	{
		Assert.assertNotNull(DBConnect.GetConnection());
	}
	
	@Test
	public void testCheckUserAndPass_TRUE()
	{
		Assert.assertTrue(DBConnect.CheckUserAndPass("michael", "michi"));
	}
	
	@Test
	public void testCheckUserAndPass_FALSE()
	{
		Assert.assertFalse(DBConnect.CheckUserAndPass("michael", "hannes"));
	}
	
	@Test
	public void testCheckUserAndPass_FALSE_2()
	{
		Assert.assertFalse(DBConnect.CheckUserAndPass("0815", "michi"));
	}
	
	@Test
	public void testCheckUsernameExists_TRUE()
	{
		try
		{
			Assert.assertTrue(DBConnect.CheckUsernameExists("michael"));
		}
		catch (SQLException e)
		{
			fail();
		}
	}
	
	@Test
	public void testCheckUsernameExists_FALSE()
	{
		try
		{
			Assert.assertFalse(DBConnect.CheckUsernameExists("0815"));
		}
		catch (SQLException e)
		{
			fail();
		}
	}
	
	@Test
	public void testGetOrders()
	{
		try
		{
			Assert.assertNotNull(DBConnect.GetOrders());
		}
		catch (SQLException e)
		{
			fail();
		}
	}

	@Test
	public void testGetArticles()
	{
		try
		{
			Order o = new Order(1, null, null, null, null);
			Assert.assertNotNull(DBConnect.GetArticles(o));
		}
		catch(SQLException e)
		{
			fail();
		}
		
	}

}
