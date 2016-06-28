package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Order {
	
        private final SimpleIntegerProperty orderid;
        private final SimpleStringProperty state;
        private final SimpleIntegerProperty userid;
        private final SimpleStringProperty user_name;
        private final SimpleStringProperty customer;

        private Order(int ordid, String stat, int usrid, String usr_name, String cust) {
            this.orderid = new SimpleIntegerProperty(ordid);
            this.state = new SimpleStringProperty(stat);
            this.userid = new SimpleIntegerProperty(usrid);
            this.user_name = new SimpleStringProperty(usr_name);
            this.customer = new SimpleStringProperty(cust);
        }

        public int getuserid() {
            return userid.get();
        }
        public void setuserid(int usrid) {
            userid.set(usrid);
        }
       
        public int getoderid() {
            return orderid.get();
        }
        public void setorderid(int ordid) {
            orderid.set(ordid);
        }
        
               
       public String getstate() {
            return state.get();
        }
        public void setstate(String stat) {
            state.set(stat);
        }
       
        public String getcustomer() {
            return state.get();
        }
        public void setcustomer(String cust) {
            customer.set(cust);
        }
        
        public String getuser_name() {
            return user_name.get();
        }
        public void setuser_name(String usr_name) {
            user_name.set(usr_name);
        }
 }
