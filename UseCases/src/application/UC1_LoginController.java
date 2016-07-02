package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UC1_LoginController
{
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	@FXML private Label lblErrorMsg;
	
	@FXML
	public void clickLogin() throws SQLException, IOException
	{
		Boolean loginSuccess = DBConnect.checkUserAndPass(txtUsername.getText(), txtPassword.getText());
		
		if (loginSuccess)
		{			
			// Öffne Bestellübersicht
			Parent windowOrderOverview = FXMLLoader.load(getClass().getResource("UC2_Bestellungsaufgabe.fxml"));
			Scene scene = new Scene(windowOrderOverview);
			Stage stage = (Stage)txtUsername.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			lblErrorMsg.setVisible(true);
		}
	}
}
