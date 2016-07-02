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
	@FXML private TableView<Article> table = new TableView<Article>();
	//@FXML private TableView table = new TableView();
	private final ObservableList<Article> data = FXCollections.observableArrayList( ) ;
	
	String article_name;
	String article_id;
	int art_id;
	String quantity;
	int quant;
	
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
		
		//Article[] article_list = new Article[50];
		boolean lv_valid;
		
		lv_valid = validate();
		
		if(lv_valid == true)
		{
			quant = Integer.parseInt(quantity);
			art_id = Integer.parseInt(article_id);
			//table.getColumns().addAll(article_id, article_name, quantity);
			//data.add( new Article(article_name, art_id, 0, quant) );
			
			
			Article article = new Article(article_name, art_id, 0, quant);
		    article.setArticleID(art_id);        
			        
			
			
			/*TableColumn articleidCol = new TableColumn("Artikelnummer");
	        articleidCol.setCellValueFactory(new PropertyValueFactory<Article,Integer>("article_id"));
	        
	        table.getColumns().setAll(articleidCol);*/
	        
	      //  table.setItems(data);
	        table.getItems().add(article);
		}
		
		
		
		return;
	}
	
	
	public boolean validate( )
	{
		
		
		if( article_id == null )
		{
			lblErrorMsg.setText("Geben Sie eine Artikelnummer ein!");
			return false;
		}
		
		else if( article_name == null )
		{
			lblErrorMsg.setText("Geben Sie einen Artikelnamen ein!");
			return false;
		}
		
		else if( quantity == null )
		{
			lblErrorMsg.setText("Geben Sie eine Artikelmenge ein!");
			return false;
		}
		else
		{
			return true; 
	    }
	}
}
