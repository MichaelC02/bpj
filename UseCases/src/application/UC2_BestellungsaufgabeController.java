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
	@FXML private Label lbcreator;
	@FXML private Label lbstate;
	@FXML private TextField txtarticle_id;
	@FXML private TextField txtquantity;
	@FXML private TextField txtarticle_name;
	@FXML private Label lblErrorMsg;
	@FXML private TableView<Article> table ;
	@FXML private TableView<Article> dbtable ;
	
	
	String article_name;
	String article_id;
	int art_id;
	String quantity;
	int quant;
	String error_msg;
	Boolean error = false;
	Article selarticle;
	
	@FXML
	public void clickaddorder() throws SQLException
	{
		
		ObservableList<Article> allarticles;
		allarticles = table.getItems();
		
		DBConnect.neworder(allarticles);
		
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
	public void initialize()
	{
		lbcreator.setText(CurrentUser.getUsername());
		lbstate.setText("In Erfassung");
		
		ObservableList<Article> articles = null;
		
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
		
		
		dbtable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    
			selarticle = dbtable.getSelectionModel().getSelectedItem();
			
			art_id = selarticle.getArticleID();
			article_id = Integer.toString(art_id);
			txtarticle_id.setText(article_id);
			
			txtarticle_name.setText(selarticle.getName());
		    
		});
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
