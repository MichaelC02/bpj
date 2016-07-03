package application;

import java.util.Date;

public class CurrentUser
{
	private static Integer userId; 
	private static String username;
	private static Boolean isAdmin;
	private static Date lastLogin;
	
	public static void Set(Integer _userId, String _username, Boolean _isAdmin, Date _lastLogin)
	{
		userId = _userId;
		username = _username;
		isAdmin = _isAdmin;
		lastLogin = _lastLogin;
	}
	
	public static void Clear()
	{
		userId = null;
		username = null;
		isAdmin = null;
		lastLogin = null;
	}
	
	public static Integer getUserId() {
		return userId;
	}
	public static String getUsername() {
		return username;
	}
	public static Boolean getIsAdmin() {
		return isAdmin;
	}
	public static Date getLastLogin() {
		return lastLogin;
	}
}
