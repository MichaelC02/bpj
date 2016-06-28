package application;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class OrdersOverviewController
{
	@FXML TableView<Order> ordersTable;
	
	public OrdersOverviewController()
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
		
		//((TableColumn)columns.get(1)).setCellValueFactory(new PropertyValueFactory("test"));
	}
}