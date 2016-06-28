package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CurrentUser {
private SimpleIntegerProperty userid; 
private SimpleStringProperty user_name;

private CurrentUser(int usrid, String usr_name)
{
	this.userid = new SimpleIntegerProperty(usrid);
	this.user_name = new SimpleStringProperty(usr_name);
}

public SimpleIntegerProperty getUserid() {
	return userid;
}

public void setUserid(SimpleIntegerProperty userid) {
	this.userid = userid;
}

public void setUser_name(SimpleStringProperty user_name) {
	this.user_name = user_name;
}

public SimpleStringProperty getUser_name() {
	return user_name;
}


}
