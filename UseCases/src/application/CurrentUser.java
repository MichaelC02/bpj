package application;

public class CurrentUser
{
	private static Integer userId; 
	private static String username;
	
	public static void Set(Integer _userId, String _username)
	{
		userId = _userId;
		username = _username;
	}
	
	public static void Clear()
	{
		userId = null;
		username = null;
	}
	
	public static Integer getUserId() {
		return userId;
	}
	public static String getUsername() {
		return username;
	}
}
