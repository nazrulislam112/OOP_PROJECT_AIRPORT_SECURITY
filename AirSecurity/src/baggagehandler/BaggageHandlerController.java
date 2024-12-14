package baggagehandler;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import baggagehandler.Baggage;

import java.io.*;
import java.util.*;

public class BaggageHandlerController {
    @FXML
    private TextField baggageIdField, passengerNameField, weightField, searchField;
    @FXML
    private ChoiceBox<String> statusChoiceBox;
    @FXML
    private TextArea resultArea;

    private static final String DATABASE_FILE = "file/baggage.txt";

    @FXML
    public void initialize() {
        statusChoiceBox.getItems().addAll("Checked", "Not Checked");
    }

    @FXML
    public void addBaggage() {
        try {
            String baggageId = baggageIdField.getText();
            String passengerName = passengerNameField.getText();
            double weight = Double.parseDouble(weightField.getText());
            String status = statusChoiceBox.getValue();

            Baggage baggage = new Baggage(baggageId, passengerName, weight, status);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE, true))) {
                writer.write(baggage.toString());
                writer.newLine();
                showMessage("Baggage added successfully.");
            }

        } catch (Exception e) {
            showMessage("Error adding baggage: " + e.getMessage());
        }
    }

    @FXML
    public void searchBaggage() {
        String searchId = searchField.getText();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Baggage baggage = Baggage.fromString(line);
                if (baggage.getBaggageId().equals(searchId)) {
                    resultArea.setText(baggage.toString());
                    return;
                }
            }
            resultArea.setText("Baggage not found.");
        } catch (IOException e) {
            showMessage("Error searching baggage: " + e.getMessage());
        }
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }
}
