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
	
	public void loadOrder(int orderID)
	{
		try {
			Order CurrentOrder = DBConnect.GetOrderById(orderID);
			
			lblOrderID.setText(String.valueOf(orderID));
			lblState.setText(String.valueOf(CurrentOrder.getState()));
			lblDate.setText(String.valueOf(CurrentOrder.getDate()));
			lblUserID.setText(String.valueOf(CurrentOrder.getUsername()));
			lblCustID.setText(String.valueOf(CurrentOrder.getCustomerName()));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
