package application;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UC6_OrderDetailsController {
	
	@FXML private Label lblOrderID;
	@FXML private Label lblState;
	@FXML private Label lblDate;
	@FXML private Label lblUserID;
	@FXML private Label lblCustID;
	
	public void loadOrder(Order currentOrder)
	{
		lblOrderID.setText(String.valueOf(currentOrder.getOrderId()));
		lblState.setText(String.valueOf(currentOrder.getState()));
		lblDate.setText(String.valueOf(currentOrder.getDate()));
		lblUserID.setText(String.valueOf(currentOrder.getUsername()));
		lblCustID.setText(String.valueOf(currentOrder.getCustomerName()));
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
