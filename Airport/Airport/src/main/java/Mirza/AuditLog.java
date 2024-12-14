package Mirza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuditLog {

    @FXML
    private TableColumn<AuditLogEntry, String> actioncol;

    @FXML
    private TableColumn<AuditLogEntry, String> detailscol;

    @FXML
    private TableView<AuditLogEntry> tablecol;

    @FXML
    private TableColumn<AuditLogEntry, String> timecol;

    @FXML
    private TableColumn<AuditLogEntry, String> usercol;

    private ObservableList<AuditLogEntry> auditLogList = FXCollections.observableArrayList();
    private static final String LOG_FILE = "audit_log.txt";

    // Initialize the controller and load audit log data
    @FXML
    public void initialize() {
        loadAuditLogData();
        populateTableView();
    }

    // Handle clearing the audit log (i.e., remove all entries)
    @FXML
    void clearid(ActionEvent event) {
        auditLogList.clear();  // Clear the list of audit logs
        saveAuditLogData();  // Save the empty list to the file
        populateTableView();  // Update the TableView with the cleared data
        showInfo("Clear Successful", "Audit logs have been cleared.");
    }

    // Utility method to load audit log data from a file
    private void loadAuditLogData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    AuditLogEntry entry = new AuditLogEntry(data[0], data[1], data[2], data[3]);
                    auditLogList.add(entry);
                }
            }
        } catch (IOException e) {
            showError("Error", "An error occurred while loading the audit log data.");
        }
    }

    // Utility method to save the audit log data to a file
    private void saveAuditLogData() {
        try (FileWriter writer = new FileWriter(LOG_FILE)) {
            for (AuditLogEntry entry : auditLogList) {
                writer.write(entry.getUser() + "," + entry.getTime() + "," + entry.getAction() + "," + entry.getDetails() + "\n");
            }
        } catch (IOException e) {
            showError("Error", "An error occurred while saving the audit log data.");
        }
    }

    // Utility method to populate the TableView with audit log data
    private void populateTableView() {
        tablecol.setItems(auditLogList);
    }

    // Method to switch to another scene (e.g., back to the Dashboard)
    @FXML
    void switchToDashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) tablecol.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showError("Error", "An error occurred while switching scenes.");
        }
    }

    // Method to show an error message
    private void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to show an informational message
    private void showInfo(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Information");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

