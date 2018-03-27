package frontend;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserDetails {
    private final StringProperty user;
    private final StringProperty pass;

    public UserDetails(String user, String pass){
        this.user = new SimpleStringProperty(user);
        this.pass = new SimpleStringProperty(pass);
    }

    public String getUser() {
        return user.get();
    }

    public String getPass() {
        return pass.get();
    }

    public void setUser(String value){
        user.set(value);
    }

    public void setPass(String value){
        pass.set(value);
    }

    public StringProperty userProperty() {
        return user;
    }

    public StringProperty passProperty() {
        return pass;
    }
}
