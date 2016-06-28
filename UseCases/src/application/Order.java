package application;

import java.util.ArrayList;
import java.util.Date;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Order
{
	private SimpleIntegerProperty orderId;
    private Date date;
    private SimpleStringProperty username;
    private SimpleStringProperty customerName;
    private SimpleStringProperty state;
    private ArrayList<Article> ArticleList;

    
	
	public ArrayList<Article> getArticleList() {
		return ArticleList;
	}

	public void setArticleList(ArrayList<Article> articleList) {
		ArticleList = articleList;
	}
	
	public void addArticleToList(Article newArticle){
		ArticleList.add(newArticle);
	}
	
	public void removeArticleFromList(int articlePos){
		ArticleList.remove(articlePos);
	}

	public Order(int _orderId, Date _date, String _username, String _customerName, String _state)
    {
    	this.orderId = new SimpleIntegerProperty(_orderId);
        this.date = _date;
        this.username = new SimpleStringProperty(_username);
        this.customerName = new SimpleStringProperty(_customerName);
        this.state = new SimpleStringProperty(_state);
    }

	public SimpleIntegerProperty getOrderId() {
		return orderId;
	}

	public void setOrderId(SimpleIntegerProperty orderId) {
		this.orderId = orderId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public SimpleStringProperty getUsername() {
		return username;
	}

	public void setUsername(SimpleStringProperty username) {
		this.username = username;
	}

	public SimpleStringProperty getCustomerName() {
		return customerName;
	}

	public void setCustomerName(SimpleStringProperty customerName) {
		this.customerName = customerName;
	}

	public SimpleStringProperty getState() {
		return state;
	}

	public void setState(SimpleStringProperty state) {
		this.state = state;
	}
    
    
    
       
 }
