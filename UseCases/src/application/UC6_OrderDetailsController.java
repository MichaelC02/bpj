package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class UC6_OrderDetailsController
{	
	@FXML private Label lblOrderID;
	@FXML private Label lblState;
	@FXML private Label lblDate;
	@FXML private Label lblUserID;
	@FXML private Label lblCustID;
	@FXML private Button btcancel;
	@FXML private Button btEdit;
	@FXML private TableView<Article> ArticleList;
	
	Order curr_order;
	
	public void loadOrder(Order currentOrder)
	{
		lblOrderID.setText(String.valueOf(currentOrder.getOrderId()));
		lblState.setText(String.valueOf(currentOrder.getState()));
		lblDate.setText(String.valueOf(currentOrder.getPrettyDate()));
		lblUserID.setText(String.valueOf(currentOrder.getUsername()));
		lblCustID.setText(String.valueOf(currentOrder.getCustomerName()));
		try {
			curr_order = DBConnect.GetArticles(currentOrder);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			curr_order = null;
			return;
		}
		
		String state = curr_order.getState();
		if(state.equals("Offen") == true)
		{
			btcancel.setVisible(true);
			btEdit.setVisible(true);
		}
		else
		{
			btcancel.setVisible(false);
			btEdit.setVisible(false);
		}
		
		if(curr_order.getArticleList() != null){
			for(int i = 0; i < curr_order.getArticleList().size(); i++){
				curr_order.getArticleList().get(i).setPos(i+1);
			}
			ArticleList.getItems().addAll(curr_order.getArticleList());
		}
	}
	
	@FXML 
	public void clickcancelorder() throws SQLException
	{
		// MessageBox ausgeben
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("");
		alert.setHeaderText("Storno bestätigen");
		alert.setContentText("Wollen Sie diese Bestellung wirklich stornieren?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
		{
			if(DBConnect.setStatus(curr_order.getOrderId(), "Storniert"))
			{
				curr_order.setState("Storniert");
				lblState.setText("Storniert");
			}
		}
	}
	
	@FXML 
	public void clickEditOrder() throws SQLException
	{
		try
		{
			// Order-ID übergeben
			FXMLLoader loader = new FXMLLoader(getClass().getResource("UC3_Bestellungsaufgabe.fxml"));
			Parent windowOrderOverview = loader.load();
			
			UC3_BestellungsaufgabeController con = loader.<application.UC3_BestellungsaufgabeController>getController();
			con.loadOrder(curr_order);
			
			Scene scene = new Scene(windowOrderOverview);
			Stage stage = (Stage)ArticleList.getScene().getWindow();
			stage.setScene(scene);
			stage.setTitle("Bestellung bearbeiten");
			stage.show();
		}
		catch (IOException e) { e.printStackTrace(); }
	}

	@FXML
	public void clickBack()
	{
		// Öffne Bestellübersicht
		try
		{
			Parent windowOrderOverview = FXMLLoader.load(getClass().getResource("OrdersOverview.fxml"));
			Scene scene = new Scene(windowOrderOverview);
			Stage stage = (Stage)lblOrderID.getScene().getWindow();
			stage.setScene(scene);
			stage.setTitle("Bestellübersicht");
			stage.show();
		}
		catch(IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
