package application;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		
		//Setze Label-Text
		lblLoggedInAs.setText("Eingeloggt als " + CurrentUser.getUsername() +
							  "\nLetzter Login: " + sdf.format(CurrentUser.getLastLogin()));
		
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
		
		//
		ordersTable.setRowFactory( tv ->
		{
		    TableRow<Order> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (!row.isEmpty()))
		        {
		            onSelectOrder(row);
		        }
		    });
		    
		    return row;
		});
		
		// "Neuer Benutzer"-Button anzeigen/verstecken
		btnNewUser.setVisible(CurrentUser.getIsAdmin());
		
		
		//ordersTable.getItems().add(new Order(3, new Date(), "user1", "kunde2", "Offen"));
		//((TableColumn)columns.get(1)).setCellValueFactory(new PropertyValueFactory("test"));
	}
	
	@FXML 
	public void clickaddorder() throws IOException
	{
		// Bestellung erstellen Maske
		Parent windowOrderOverview = FXMLLoader.load(getClass().getResource("UC2_Bestellungsaufgabe.fxml"));
		Scene scene = new Scene(windowOrderOverview);
		Stage stage = (Stage)ordersTable.getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Bestellungsaufgabe");
		stage.show();
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
	
	private void onSelectOrder(TableRow<Order> row)
	{		
		// Öffne Bestelldetails-Maske
		try
		{
			// Order-ID übergeben
			FXMLLoader loader = new FXMLLoader(getClass().getResource("UC6_OrderDetails.fxml"));
			Parent windowOrderOverview = loader.load();
			
			UC6_OrderDetailsController con = loader.<application.UC6_OrderDetailsController>getController();
			con.loadOrder(row.getItem());
			
			Scene scene = new Scene(windowOrderOverview);
			Stage stage = (Stage)ordersTable.getScene().getWindow();
			stage.setScene(scene);
			stage.setTitle("Bestelldetails");
			stage.show();
		}
		catch (IOException e) { e.printStackTrace(); }
	}
}