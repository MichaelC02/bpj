package application;

public class CurrentUser
{
	private static Integer userId; 
	private static String username;
	private static Boolean isAdmin;
	
	public static void Set(Integer _userId, String _username, Boolean _isAdmin)
	{
		userId = _userId;
		username = _username;
		isAdmin = _isAdmin;
	}
	
	public static void Clear()
	{
		userId = null;
		username = null;
		isAdmin = null;
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
}
