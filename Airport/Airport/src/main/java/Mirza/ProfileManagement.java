package Mirza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProfileManagement {

    @FXML
    private TextField emailid;

    @FXML
    private TextField nameid;

    @FXML
    private PasswordField passid;

    private static final String PROFILE_FILE = "user_profile.txt";

    // Initialize the controller and load the user profile data
    @FXML
    public void initialize() {
        loadProfileData();
    }

    // Method to update the user profile
    @FXML
    void updateid(ActionEvent event) {
        String name = nameid.getText();
        String email = emailid.getText();
        String password = passid.getText();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showError("Validation Error", "All fields must be filled out.");
            return;
        }

        // Save the updated profile data
        saveProfileData(name, email, password);

        showInfo("Update Successful", "Your profile has been updated.");
    }

    // Utility method to load profile data from a file
    private void loadProfileData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PROFILE_FILE))) {
            String name = reader.readLine();
            String email = reader.readLine();
            String password = reader.readLine();

            if (name != null && email != null && password != null) {
                nameid.setText(name);
                emailid.setText(email);
                passid.setText(password);
            }
        } catch (IOException e) {
            showError("Error", "An error occurred while loading the profile data.");
        }
    }

    // Utility method to save profile data to a file
    private void saveProfileData(String name, String email, String password) {
        try (FileWriter writer = new FileWriter(PROFILE_FILE)) {
            writer.write(name + "\n");
            writer.write(email + "\n");
            writer.write(password + "\n");
        } catch (IOException e) {
            showError("Error", "An error occurred while saving the profile data.");
        }
    }

    // Method to switch to the Dashboard scene
    @FXML
    void switchToDashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) nameid.getScene().getWindow();
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
