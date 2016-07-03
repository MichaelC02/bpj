package application;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UC2_AddUserController
{
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	@FXML private PasswordField txtPasswordRepeat;
	@FXML private CheckBox cbIsAdmin;
	@FXML private Label lblErrorMsg;
	
	private void openOrdersOverview()
	{
		try
		{
			// Gehe zurück zu Bestellübersicht
			Parent windowOrderOverview = FXMLLoader.load(getClass().getResource("OrdersOverview.fxml"));
			Scene scene = new Scene(windowOrderOverview);
			Stage stage = (Stage)txtUsername.getScene().getWindow();
			stage.setScene(scene);
			stage.setTitle("Bestellübersicht");
			stage.show();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void clickOk()
	{
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		String passwordRepeat = txtPasswordRepeat.getText();
		
		if(username.isEmpty() || password.isEmpty() || passwordRepeat.isEmpty())
		{
			lblErrorMsg.setText("Es müssen alle Felder befüllt sein!");
			lblErrorMsg.setVisible(true);
			return;
		}
		else if(!password.equals(txtPasswordRepeat.getText()))
		{
			lblErrorMsg.setText("Passwörter stimmen nicht überein!");
			lblErrorMsg.setVisible(true);
			return;
		}
		
		try
		{
			if (DBConnect.CheckUsernameExists(username))
			{
				lblErrorMsg.setText("Benutzername bereits vergeben!");
				lblErrorMsg.setVisible(true);
			}
			else
			{
				DBConnect.CreateUser(username, password, cbIsAdmin.isSelected());
				
				// MessageBox ausgeben
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle(null);
				alert.setHeaderText(null);
				alert.setContentText("Benutzer "+username+" wurde erfolgreich erstellt.");
				alert.showAndWait();
				
				openOrdersOverview();
			}
		}
		catch (SQLException | NoSuchAlgorithmException | UnsupportedEncodingException e)
		{
			e.printStackTrace();
			lblErrorMsg.setText("Unbekannter Fehler!");
			lblErrorMsg.setVisible(true);
			return;
		}		
	}
	
	@FXML
	public void clickCancel()
	{
		openOrdersOverview();
	}
}
