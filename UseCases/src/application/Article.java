package application;

public class Article {
	
	private String name;
	private int articleID;
	private int price;
	private int quantity;
	
	public Article(String name, int articleID, int price, int quantity) {
		this.name = name;
		this.articleID = articleID;
		this.price = price;
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getArticleID() {
		return articleID;
	}
	public void setArticleID(int articleID) {
		this.articleID = articleID;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
