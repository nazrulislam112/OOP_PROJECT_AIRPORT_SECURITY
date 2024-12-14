package baggagehandler;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewBaggageController {

    @FXML
    private TableView<Baggage> baggageTable;

    @FXML
    private TableColumn<Baggage, String> colBaggageId;
    @FXML
    private TableColumn<Baggage, String> colPassengerName;
    @FXML
    private TableColumn<Baggage, String> colWeight;
    @FXML
    private TableColumn<Baggage, String> colStatus;

    // Initialize the TableView with static data
    public void initialize() {
        // Set cell value factories for each column
        colBaggageId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBaggageId()));
        colPassengerName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassengerName()));
        colWeight.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWeight()));
        colStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

        // Create an ObservableList of Baggage objects
        ObservableList<Baggage> baggageData = FXCollections.observableArrayList(
                new Baggage("B001", "John Doe", "23 kg", "Checked In"),
                new Baggage("B002", "Jane Smith", "18 kg", "Checked In"),
                new Baggage("B003", "Alice Brown", "15 kg", "Checked In")
        );

        // Set the items in the TableView
        baggageTable.setItems(baggageData);
    }

    // Baggage class to represent each row of baggage data
    public static class Baggage {
        private final String baggageId;
        private final String passengerName;
        private final String weight;
        private final String status;

        public Baggage(String baggageId, String passengerName, String weight, String status) {
            this.baggageId = baggageId;
            this.passengerName = passengerName;
            this.weight = weight;
            this.status = status;
        }

        public String getBaggageId() {
            return baggageId;
        }

        public String getPassengerName() {
            return passengerName;
        }

        public String getWeight() {
            return weight;
        }

        public String getStatus() {
            return status;
        }
    }
}
