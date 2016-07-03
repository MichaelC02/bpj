package application;

public class customer {
	private int customerId;
	private String customerName;
	
	public customer(int cust_id, String cust_name)
	{
		customerId = cust_id;
		customerName = cust_name;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomer_id(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
}
