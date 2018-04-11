package frontend;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SalesDetails {

    private final StringProperty date;
    private final FloatProperty avgSales;


    public SalesDetails(String date, Float avgSales) {
        this.date = new SimpleStringProperty(date);
        this.avgSales = new SimpleFloatProperty(avgSales);
    }

    public String getDate() {
        return date.get();
    }

    public float getAvgSales() {
        return avgSales.get();
    }

    public void setDate(String value) {
        date.set(value);
    }

    public void setAvgSales(String value) {
        avgSales.set(Float.parseFloat(value));
    }

    public StringProperty dateProperty() {
        return date;
    }

    public FloatProperty setAvgSalesProperty() {
        return avgSales;
    }

}
