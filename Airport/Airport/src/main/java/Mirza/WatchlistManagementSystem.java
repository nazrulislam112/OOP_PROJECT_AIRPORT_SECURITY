package Mirza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WatchlistManagementSystem {

    @FXML
    private TableColumn<?, ?> dobcol;

    @FXML
    private DatePicker dobid;

    @FXML
    private TableColumn<?, ?> matchcol;

    @FXML
    private TableColumn<?, ?> namecol;

    @FXML
    private TextField nameid;

    @FXML
    private TableColumn<?, ?> notescol;

    @FXML
    private TextField searchid;

    @FXML
    private TableView<?> tableid;

    private List<String> watchlistData = new ArrayList<>();

    // File paths
    private static final String FILE_PATH = "watchlist_data.txt";

    // Initializes the watchlist from the file when the app starts
    @FXML
    public void initialize() {
        loadWatchlistData();
    }

    // Logout button functionality
    @FXML
    void logoutid(ActionEvent event) {
        // Switch to the login scene or close the application
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) tableid.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showError("Error", "Could not switch to the login scene.");
        }
    }

    // Notify button functionality
    @FXML
    void notifyid(ActionEvent event) {
        // Simple notification alert (can be enhanced)
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText("New notification");
        alert.setContentText("This is a test notification.");
        alert.showAndWait();
    }

    // Report button functionality
    @FXML
    void reportid(ActionEvent event) {
        // Generate a report based on the watchlist data
        StringBuilder reportContent = new StringBuilder();
        for (String item : watchlistData) {
            reportContent.append(item).append("\n");
        }

        try (FileWriter writer = new FileWriter("watchlist_report.txt")) {
            writer.write(reportContent.toString());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Report Generated");
            alert.setHeaderText("Watchlist report generated successfully.");
            alert.showAndWait();
        } catch (IOException e) {
            showError("Error", "Failed to generate the report.");
        }
    }

    // Save watchlist data to file
    private void saveWatchlistData() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (String item : watchlistData) {
                writer.write(item + "\n");
            }
        } catch (IOException e) {
            showError("Error", "Failed to save data to file.");
        }
    }

    // Load watchlist data from file
    private void loadWatchlistData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                watchlistData.add(line);
            }
        } catch (IOException e) {
            showError("Error", "Failed to load data from file.");
        }
    }

    // Show error alert
    private void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Add a new entry to the watchlist
    @FXML
    void addWatchlistEntry(ActionEvent event) {
        String name = nameid.getText();
        String dob = dobid.getValue() != null ? dobid.getValue().toString() : "";
        String notes = searchid.getText();

        if (name.isEmpty() || dob.isEmpty() || notes.isEmpty()) {
            showError("Input Error", "Please fill out all fields.");
            return;
        }

        String newEntry = "Name: " + name + ", DOB: " + dob + ", Notes: " + notes;
        watchlistData.add(newEntry);
        saveWatchlistData(); // Save to file
        refreshTable();
    }

    // Refresh the TableView (you can add a TableModel class to improve this part)
    private void refreshTable() {
        // Clear and repopulate the table (this should be done with a TableModel)
        tableid.getItems().clear();
        for (String item : watchlistData) {
            // Add rows to the table (adjust based on your data structure)
            // You would need to properly map the item to the table columns
        }
    }
}
