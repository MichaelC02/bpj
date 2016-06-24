package application;

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

}
