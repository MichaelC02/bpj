package application;

import java.sql.SQLException;
import javafx.fxml.FXML;

public class UC1_LoginController
{
	@FXML
	public void clickLogin() throws SQLException
	{
		Boolean loginSuccess = DBConnect.checkUserAndPass("michael", "michi");
		
		if (loginSuccess)
			System.out.println("Success!");
		else
			System.out.println("Failure!");
	}
}
