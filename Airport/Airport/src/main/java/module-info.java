module org.example.airport {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.airport to javafx.fxml;
    exports org.example.airport;

    opens Mirza to javafx.fxml;
    exports Mirza;
}