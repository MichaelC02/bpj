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
		Assert.assertNotNull(DBConnect.getConnection());
	}
	
	@Test
	public void testCheckUserAndPass_TRUE()
	{
		Assert.assertTrue(DBConnect.checkUserAndPass("michael", "michi"));
	}
	
	@Test
	public void testCheckUserAndPass_FALSE()
	{
		Assert.assertFalse(DBConnect.checkUserAndPass("michael", "hannes"));
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
	public void testGetOrderById()
	{
		try
		{
			Assert.assertNotNull(DBConnect.GetOrderById(1));
		}
		catch(SQLException e)
		{
			fail();
		}
		
	}

}
