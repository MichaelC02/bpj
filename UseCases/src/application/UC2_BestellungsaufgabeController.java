package application;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UC2_BestellungsaufgabeController
{
	@FXML private TextField txtorderid;
	@FXML private TextField txtcreator;
	@FXML private TextField txtarticle_id;
	@FXML private TextField txtquantity;
	@FXML private TextField txtarticle_name;
	@FXML private Label lblErrorMsg;
	@FXML private TableView<Article> table ;
	  //    private final ObservableList<Article> data = table.getItems();
	//@FXML private TableView table = new TableView();
//	private final ObservableList<Article> data = FXCollections.observableArrayList( 
		 					//	new Article( "test", 10, 0, 50 ) ) ;
	
	//@FXML TableColumn<Article, String> articleidCol;
	
	
	String article_name;
	String article_id;
	int art_id;
	String quantity;
	int quant;
	String error_msg;
	Boolean error = false;
	
	@FXML
	public void clickaddorder() throws SQLException
	{
		
		
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
				
				
				Article article = new Article(article_name, art_id, 0, quant);
				
		        
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
		//table.get().clearSelection();
		return;
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

		
		if(error == false)
		{
			
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
