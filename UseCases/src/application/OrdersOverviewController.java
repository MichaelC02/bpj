package application;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrdersOverviewController
{
	@FXML TableView<Order> ordersTable;
	
	OrdersOverviewController()
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
		
		ordersTable.setItems(orders);
		
		
		//((TableColumn)columns.get(1)).setCellValueFactory(new PropertyValueFactory("test"));
	}
}
