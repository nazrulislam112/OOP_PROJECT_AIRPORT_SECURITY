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
import java.io.IOException;

public class Login {

    @FXML
    private PasswordField passwordid;

    @FXML
    private TextField usernameid;

    private static final String USERS_FILE = "users.txt";  // File containing username and password

    @FXML
    void loginid(ActionEvent event) {
        String username = usernameid.getText();
        String password = passwordid.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Login Error", "Please enter both username and password.");
            return;
        }

        if (authenticateUser(username, password)) {
            loadDashboardScene();
        } else {
            showError("Login Failed", "Invalid username or password.");
        }
    }

    // Authenticate the user by checking the username and password from the file
    private boolean authenticateUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2 && credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            showError("File Error", "An error occurred while reading user data.");
        }
        return false;
    }

    // Load the dashboard scene if login is successful
    private void loadDashboardScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) usernameid.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showError("Scene Error", "An error occurred while loading the dashboard.");
        }
    }

    // Method to show error messages
    private void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
