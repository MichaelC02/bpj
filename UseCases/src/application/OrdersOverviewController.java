package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class OrdersOverviewController
{
	@FXML TableView<Order> ordersTable;
	@FXML Label lblLoggedInAs;
	@FXML Button btnNewUser;
	
	@FXML
	public void initialize()
	{
		//Setze Label-Text
		lblLoggedInAs.setText("Eingeloggt als " + CurrentUser.getUsername());
		
		ObservableList<Order> orders = null;
		
		try
		{
			orders = DBConnect.GetOrders();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Tabelle befüllen
		ordersTable.getItems().addAll(orders);
		
		// "Neuer Benutzer"-Button anzeigen/verstecken
		btnNewUser.setVisible(CurrentUser.getIsAdmin());
		
		
		//ordersTable.getItems().add(new Order(3, new Date(), "user1", "kunde2", "Offen"));
		//((TableColumn)columns.get(1)).setCellValueFactory(new PropertyValueFactory("test"));
	}
	
	
	@FXML
	public void clickLogout() throws IOException
	{
		// Aktuellen Benutzer leeren
		CurrentUser.Clear();
		
		// Öffne Login-Maske
		Parent windowOrderOverview = FXMLLoader.load(getClass().getResource("UC1_Login.fxml"));
		Scene scene = new Scene(windowOrderOverview);
		Stage stage = (Stage)ordersTable.getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Benutzerauthentifizierung");
		stage.show();
	}
	
	
	@FXML
	public void clickAddUser() throws IOException
	{
		// Öffne "Neuen Benutzer erstellen"-Maske
		Parent windowOrderOverview = FXMLLoader.load(getClass().getResource("UC2_AddUser.fxml"));
		Scene scene = new Scene(windowOrderOverview);
		Stage stage = (Stage)ordersTable.getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Neuen Benutzer erstellen");
		stage.show();
	}
}