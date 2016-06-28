package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CurrentUser {
private final SimpleIntegerProperty userid; 
private final SimpleStringProperty user_name;

private CurrentUser(int usrid, String usr_name)
{
	this.userid = new SimpleIntegerProperty(usrid);
	this.user_name = new SimpleStringProperty(usr_name);
}

public SimpleIntegerProperty getUserid() {
	return userid;
}

public SimpleStringProperty getUser_name() {
	return user_name;
}


}
