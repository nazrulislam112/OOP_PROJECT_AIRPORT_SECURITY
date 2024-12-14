package securityofficer;

import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewPassengerController {
    @FXML
    private TextField boardingPassIDField;
    @FXML
    private Label passengerDetailsLabel;
    @FXML
    private ComboBox<String> clearanceStatusComboBox;
    @FXML
    private Button updateStatusButton;

    @FXML
    public void initialize() {
        clearanceStatusComboBox.getItems().addAll("Cleared", "Not Cleared");
    }

    // Search for a passenger by ID
    @FXML
    private void searchPassenger() {
        String boardingPassID = boardingPassIDField.getText();
        try {
            List<Passenger> passengers = FileHandler.readPassengers(); // Can throw IOException
            for (Passenger passenger : passengers) {
                if (passenger.getBoardingPassID().equals(boardingPassID)) {
                    passengerDetailsLabel.setText("Name: " + passenger.getName() +
                            ", Flight: " + passenger.getFlightNumber() +
                            ", Time: " + passenger.getBoardingTime() +
                            ", Status: " + passenger.getClearanceStatus());
                    return;
                }
            }
            passengerDetailsLabel.setText("Passenger not found.");
        } catch (IOException e) {
            // Display error if file cannot be read
            passengerDetailsLabel.setText("Error reading passenger data: " + e.getMessage());
        }
    }

    // Update clearance status
    @FXML
    private void updateClearanceStatus() {
        String boardingPassID = boardingPassIDField.getText();
        String status = clearanceStatusComboBox.getValue();
        if (status == null || boardingPassID.isEmpty()) {
            passengerDetailsLabel.setText("Please fill all fields.");
            return;
        }
        try {
            FileHandler.updateClearanceStatus(boardingPassID, status); // Can throw IOException
            passengerDetailsLabel.setText("Clearance status updated successfully.");
        } catch (IOException e) {
            // Display error if file cannot be written
            passengerDetailsLabel.setText("Error updating clearance status: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            passengerDetailsLabel.setText(e.getMessage());
        }
    }
}
