package Mirza;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Document {

    private final StringProperty title;
    private final StringProperty number;
    private final StringProperty description;
    private final StringProperty date;
    private final StringProperty status;

    public Document(String title, String number, String description, String date, String status) {
        this.title = new SimpleStringProperty(title);
        this.number = new SimpleStringProperty(number);
        this.description = new SimpleStringProperty(description);
        this.date = new SimpleStringProperty(date);
        this.status = new SimpleStringProperty(status);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getNumber() {
        return number.get();
    }

    public StringProperty numberProperty() {
        return number;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    @Override
    public String toString() {
        return "Title: " + title.get() + ", Number: " + number.get() + ", Date: " + date.get() + ", Status: " + status.get() + ", Description: " + description.get();
    }
}
