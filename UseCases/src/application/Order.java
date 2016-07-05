package application;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.ObservableList;

public class Order
{
	private Integer orderId;
    private Date date;
    private String username;
    private String customerName;
    private int userID;
    private int customerID;
    private String state;
    private ObservableList<Article> ArticleList;

    
	public Order(int _orderId, Date _date, String _username, String _customerName, int _userID, int _customerID, String _state)
    {
    	this.orderId = new Integer(_orderId);// SimpleIntegerProperty(_orderId);
        this.date = _date;
        this.username = new String(_username);
        this.customerName = new String(_customerName);
        this.userID=_userID;
        this.customerID=_customerID;
        this.state = new String(_state);
    }
	
	
	public ObservableList<Article> getArticleList() {
		return ArticleList;
	}

	public void setArticleList(ObservableList<Article> articleList) {
		ArticleList = articleList;
	}
	
	public void addArticleToList(Article newArticle){
		ArticleList.add(newArticle);
	}
	
	public void removeArticleFromList(int articlePos){
		ArticleList.remove(articlePos);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getPrettyDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		return sdf.format(date);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
		try {
			this.username = DBConnect.getUserName(userID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public int getCustomerID() {
		return customerID;
	}


	public void setCustomerID(int customerID) {
		this.customerID = customerID;
		try {
			this.customerName = DBConnect.getCustomerName(customerID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
    
       
 }
