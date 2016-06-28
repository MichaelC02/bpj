package application;

import java.awt.TextField;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class UC2_BestellungsaufgabeController
{
	
	@FXML private TextField txtorderid;
	@FXML private TextField txtcreator;
	@FXML private TextField txtarticle_id;
	@FXML private TextField txtquantity;
	@FXML private TextField txtarticle_name;
	@FXML private Label lblErrorMsg;
	@FXML private TableView<Article> table = new TableView<Article>();
	
	
	
	@FXML
	public void clickaddorder() throws SQLException
	{
		
		
		return;
	}
	
	@FXML
	public void clickaddarticle() throws SQLException
	{
		Article article;
		Article[] article_list = new Article[50];
		boolean lv_valid;
		
		lv_valid = validate();
		
		if(lv_valid == true)
		{
			
		}
		
		return;
	}
	
	
	public boolean validate( )
	{
		String article_id;
		article_id = txtarticle_id.getText();
		String article_name;
		article_name = txtarticle_name.getText();
		String quantity;
		quantity = txtquantity.getText();
		
		if( article_id == null )
		{
			lblErrorMsg.setText("Geben Sie eine Artikelnummer ein!");
			return false;
		}
		
		if( article_name == null )
		{
			lblErrorMsg.setText("Geben Sie einen Artikelnamen ein!");
			return false;
		}
		
		if( quantity == null )
		{
			lblErrorMsg.setText("Geben Sie eine Artikelmenge ein!");
			return false;
		}
		
		return true; 
	}
}
