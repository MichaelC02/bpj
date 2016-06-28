package application;

import java.sql.SQLException;
import java.util.Date;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class OrdersOverviewController
{
	@FXML TableView<Order> ordersTable;
	
	@FXML
	public void initialize()
	{
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
		
		//ordersTable.setEditable(true);
		//ordersTable.setItems(orders);
		//ordersTable.getItems().add(new Order(3, new Date(), "user1", "kunde2", "Offen"));
		ordersTable.getItems().addAll(orders);
		
		
		//((TableColumn)columns.get(1)).setCellValueFactory(new PropertyValueFactory("test"));
	}	
}