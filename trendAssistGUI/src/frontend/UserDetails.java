package frontend;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserDetails {
    private final StringProperty user;
    private final StringProperty pass;
    private final StringProperty pos;

    public UserDetails(String user, String pass, String pos){
        this.user = new SimpleStringProperty(user);
        this.pass = new SimpleStringProperty(pass);
        this.pos = new SimpleStringProperty(pos);
    }

    public String getUser() {
        return user.get();
    }

    public String getPass() {
        return pass.get();
    }

    public String getPos() {
        return pos.get();
    }

    public void setUser(String value){
        user.set(value);
    }

    public void setPass(String value){
        pass.set(value);
    }
    public void setPos(String value){
        pos.set(value);
    }

    public StringProperty userProperty() {
        return user;
    }

    public StringProperty passProperty() {
        return pass;
    }

    public StringProperty posProperty() {
        return pos;
    }
}
