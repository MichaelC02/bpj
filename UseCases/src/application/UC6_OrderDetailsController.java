package application;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UC6_OrderDetailsController {
	
	@FXML private Label lblOrderID;
	@FXML private Label lblState;
	@FXML private Label lblDate;
	@FXML private Label lblUserID;
	@FXML private Label lblCustID;
	@FXML private Button btcancel;
	@FXML private TableView<Article> ArticleList;
	
	Order curr_order;
	
	public void loadOrder(Order currentOrder)
	{
		lblOrderID.setText(String.valueOf(currentOrder.getOrderId()));
		lblState.setText(String.valueOf(currentOrder.getState()));
		lblDate.setText(String.valueOf(currentOrder.getDate()));
		lblUserID.setText(String.valueOf(currentOrder.getUsername()));
		lblCustID.setText(String.valueOf(currentOrder.getCustomerName()));
		try {
			curr_order = DBConnect.GetOrderById(currentOrder.getOrderId());
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
		}
		else
		{
			btcancel.setVisible(false);
		}
		System.out.println("TEST");
		if(curr_order.getArticleList() != null){
			System.out.println("TEST2");
			for(int i = 0; i < curr_order.getArticleList().size(); i++){
				curr_order.getArticleList().get(i).setPos(i+1);
			}
			ArticleList.getItems().addAll(curr_order.getArticleList());
		}
		System.out.println("TEST");
	}
	
	@FXML
	public void initialize()
	{		
	}
	
	@FXML 
	public void clickcancelorder() throws SQLException
	{
		int yesno;
		boolean save;
		String message = "Wollen Sie den Vorgang wirklich stornieren?";
		yesno = JOptionPane.showConfirmDialog(null, message, "Stornierung best�tigen", JOptionPane.YES_NO_CANCEL_OPTION);
		if(yesno == 0)
		{
			save = DBConnect.setStatus(curr_order.getOrderId(), "Storniert");
			if(save==true)
			{
				curr_order.setState("Storniert");
				lblState.setText("Storniert");
			}
		}
		
		return;
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
