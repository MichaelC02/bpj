package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UC3_BestellungsaufgabeController
{
	@FXML private Label lbcreator;
	@FXML private Label lbstate;
	@FXML private TextField txtarticle_id;
	@FXML private TextField txtquantity;
	@FXML private TextField txtarticle_name;
	@FXML private Label lbcustomer;
	@FXML private Label lblErrorMsg;
	@FXML private TableView<Article> table ;
	@FXML private TableView<Article> dbtable ;
	@FXML private TableView<customer> dbcust ;
	
	
	String article_name;
	String article_id;
	int art_id;
	String quantity;
	int quant;
	String error_msg;
	Boolean error = false;
	Boolean save = false;
	Article selarticle;
	int cust_id;
	
	public void loadOrder(Order currentOrder){
		
	}
	
	@FXML
	public void clickcancel() throws SQLException, IOException
	{
		Parent windowOrderOverview = FXMLLoader.load(getClass().getResource("OrdersOverview.fxml"));
		Scene scene = new Scene(windowOrderOverview);
		Stage stage = (Stage)txtquantity.getScene().getWindow();
		stage.setScene(scene);
		stage.setTitle("Bestellübersicht");
		stage.show();
	}
	
	@FXML
	public void clickaddorder() throws SQLException, IOException
	{
		error = false;
		ObservableList<Article> allarticles;
		allarticles = table.getItems();
		article_id = txtarticle_id.getText();		
		article_name = txtarticle_name.getText();	
		quantity = txtquantity.getText();
				
		boolean lv_valid;
		
		lv_valid = validate_order();
		
		
		if(lv_valid  == true)
		{
			save = DBConnect.neworder(allarticles, cust_id);
			if(save == true)
			{
				Parent windowOrderOverview = FXMLLoader.load(getClass().getResource("OrdersOverview.fxml"));
				Scene scene = new Scene(windowOrderOverview);
				Stage stage = (Stage)txtquantity.getScene().getWindow();
				stage.setScene(scene);
				stage.setTitle("Bestellübersicht");
				stage.show();
			}
			else
			{
				lblErrorMsg.setVisible(true);
				lblErrorMsg.setText("");
				lblErrorMsg.setText("Fehler beim Speichern");
			}
		}
		return;
	}
	
	@FXML
	public void clickaddarticle() throws SQLException
	{
		

	    article_id = txtarticle_id.getText();		
		article_name = txtarticle_name.getText();	
		quantity = txtquantity.getText();
				
		boolean lv_valid;
		
		lv_valid = validate();
		
		if(lv_valid == true)
		{
			error_msg = "";
			error = false;
			try {
				quant = Integer.parseInt(quantity);
			} catch (Exception e) {
				error = true;
				error_msg +="Geben Sie einen ganzzahligen\n Wert als Anzahl ein!\n";
			}
			try {
				art_id = Integer.parseInt(article_id);
			} catch (Exception e) {
				error = true;
				error_msg += "Geben Sie einen ganzzahligen\n Wert als ID ein!";
			}
			if (error == false){
				lblErrorMsg.setVisible(false);
				
				
				Article article = new Article(article_name, art_id, selarticle.getPrice(), quant);
				
		        
		        table.getItems().addAll(article);
		        txtarticle_id.clear();
		        txtarticle_name.clear();
		        txtquantity.clear();
		        article_id = "";
		        article_name = "";
		        quantity = "";
		        art_id = 0;
		        quant = 0; 
			}
			else
			{
				lblErrorMsg.setVisible(true);
				lblErrorMsg.setText("");
				lblErrorMsg.setText(error_msg);
			}
		}
		
		
		
		return;
	}
	
	@FXML
	public void clickdelarticle() throws SQLException
	{
		ObservableList<Article> articleselected, allarticles;
		allarticles = table.getItems();
		articleselected = table.getSelectionModel().getSelectedItems();
		
		articleselected.forEach(allarticles::remove);
		return;
	}
	
	@FXML
	public void clickaddcust() throws SQLException
	{
		customer customer;
		customer = dbcust.getSelectionModel().getSelectedItem();
		lblErrorMsg.setVisible(false);
		lblErrorMsg.setText("");
		if(customer == null)
		{
			lblErrorMsg.setVisible(true);
			lblErrorMsg.setText("W�hlen Sie einen Kunden aus!");
		}
		else
		{
			lbcustomer.setText(customer.getCustomerName());
			cust_id = customer.getCustomerId();
		}
	}
	
	@FXML
	public void initialize()
	{
		lbcreator.setText(CurrentUser.getUsername());
		lbstate.setText("In Erfassung");
		
		ObservableList<Article> articles = null;
		ObservableList<customer> cust = null;
		
		try
		{
			articles = DBConnect.GetArticles();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
		
		// Tabelle befüllen
		dbtable.getItems().addAll(articles);
		
		try
		{
			cust = DBConnect.get_custs();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
		
		dbcust.getItems().addAll(cust);
		
		dbtable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    
			selarticle = dbtable.getSelectionModel().getSelectedItem();
			
			art_id = selarticle.getArticleID();
			article_id = Integer.toString(art_id);
			txtarticle_id.setText(article_id);
			
			txtarticle_name.setText(selarticle.getName());
		    
		});
	}
	
	public boolean validate_order()
	{
		error = false;
		error_msg = "";
		if(table.getItems().size() == 0)
		{
		   error = true;
		   error_msg = "Erfassen Sie zumindest einen Artikel!\n";
		}
		
		if(lbcustomer.getText().equals(""))
		{
			error = true;
			error_msg += "Erfassen Sie einen Kunden!";
		}
		
		if(error == true)
		{
			lblErrorMsg.setVisible(true);
			lblErrorMsg.setText(error_msg);
			return false;
			
		}
		else
		{
			lblErrorMsg.setVisible(false);
			lblErrorMsg.setText("");
			return true;
		}
	}
	
	public boolean validate( )
	{
		
		error_msg = "";
		error = false;
		//check if fields are initial 
		if( article_id.equals(""))
		{
			lblErrorMsg.setVisible(true);
			error_msg = "Geben Sie eine Artikelnummer ein!\n";
			//lblErrorMsg.setText("Geben Sie eine Artikelnummer ein!");
			error = true;
		}
		
		if( article_name.equals("") )
		{
			lblErrorMsg.setVisible(true);
			error_msg += "Geben Sie einen Artikelnamen ein!\n";
			//lblErrorMsg.setText("Geben Sie einen Artikelnamen ein!");
			error = true;
		}
		
		if( quantity.equals("") )
		{
			lblErrorMsg.setVisible(true);
			error_msg += "Geben Sie eine Artikelmenge ein!\n";
		//	lblErrorMsg.setText("Geben Sie eine Artikelmenge ein!");
			error = true;
		}

		if(error == true)
		{
			lblErrorMsg.setVisible(true);
			lblErrorMsg.setText("");
			lblErrorMsg.setText(error_msg);
			return false;
		}
		else
		{
			return true; 
	    }
	}
}
