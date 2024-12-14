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

public class LegalDocumentationManagement {

    @FXML
    private TableColumn<Document, String> datecol;

    @FXML
    private DatePicker dobid;

    @FXML
    private TextField docdesid;

    @FXML
    private TableColumn<Document, String> docnumcol;

    @FXML
    private TextField docnumid;

    @FXML
    private TextField doctitleid;

    @FXML
    private TableColumn<Document, String> statuscol;

    @FXML
    private TextField searchid;

    @FXML
    private TableView<Document> tableid;

    @FXML
    private TableColumn<Document, String> titlecol;

    private List<Document> documentsList = new ArrayList<>();
    private static final String FILE_PATH = "legal_documents.txt";

    // Initialize the controller by loading existing data
    @FXML
    public void initialize() {
        loadDocumentsData();

        // Setup TableView columns
        titlecol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        docnumcol.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        datecol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        statuscol.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        refreshTable(); // Populate the TableView
    }

    // Handle adding a new document
    @FXML
    void addnewid(ActionEvent event) {
        String title = doctitleid.getText();
        String number = docnumid.getText();
        String description = docdesid.getText();
        String date = dobid.getValue() != null ? dobid.getValue().toString() : "";
        String status = "Draft";  // Default status

        if (title.isEmpty() || number.isEmpty() || description.isEmpty() || date.isEmpty()) {
            showError("Input Error", "All fields must be filled out.");
            return;
        }

        Document newDocument = new Document(title, number, description, date, status);
        documentsList.add(newDocument);
        saveDocumentsData(); // Save to file after adding
        refreshTable(); // Refresh the table to show updated list
    }

    // Handle deleting a document
    @FXML
    void deleteid(ActionEvent event) {
        String docTitle = doctitleid.getText();
        if (docTitle.isEmpty()) {
            showError("Delete Error", "Please specify the document title to delete.");
            return;
        }

        boolean deleted = documentsList.removeIf(document -> document.getTitle().equals(docTitle));
        if (deleted) {
            saveDocumentsData();  // Save to file after deleting
            refreshTable();  // Refresh the table after deletion
        } else {
            showError("Delete Error", "Document not found.");
        }
    }

    // Handle exporting the document list
    @FXML
    void exportid(ActionEvent event) {
        try (FileWriter writer = new FileWriter("exported_documents.txt")) {
            for (Document doc : documentsList) {
                writer.write(doc.toString() + "\n");
            }
            showInfo("Export Successful", "Documents have been exported.");
        } catch (IOException e) {
            showError("Export Error", "An error occurred while exporting documents.");
        }
    }

    // Handle saving the document list to a file
    @FXML
    void saveid(ActionEvent event) {
        saveDocumentsData();
        showInfo("Save Successful", "Documents have been saved.");
    }

    // Handle searching the documents by title or number
    @FXML
    void searchbutton(ActionEvent event) {
        String searchText = searchid.getText().toLowerCase();
        List<Document> searchResults = new ArrayList<>();
        for (Document document : documentsList) {
            if (document.toString().toLowerCase().contains(searchText)) {
                searchResults.add(document);
            }
        }
        updateTableView(searchResults);
    }

    // Method to save documents to a file
    private void saveDocumentsData() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Document document : documentsList) {
                writer.write(document.toString() + "\n");
            }
        } catch (IOException e) {
            showError("Save Error", "An error occurred while saving the documents.");
        }
    }

    // Method to load documents from a file
    private void loadDocumentsData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] docData = line.split(", ");
                String title = docData[0].split(": ")[1];
                String number = docData[1].split(": ")[1];
                String date = docData[2].split(": ")[1];
                String status = docData[3].split(": ")[1];
                String description = docData[4].split(": ")[1];

                Document document = new Document(title, number, description, date, status);
                documentsList.add(document);
            }
        } catch (IOException e) {
            showError("Load Error", "An error occurred while loading the documents.");
        }
    }

    // Method to refresh the TableView with the updated list of documents
    private void refreshTable() {
        updateTableView(documentsList);
    }

    // Helper method to update the TableView with a list of documents
    private void updateTableView(List<Document> documents) {
        tableid.getItems().clear();
        tableid.getItems().addAll(documents);
    }

    // Method to show error messages
    private void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to show informational messages
    private void showInfo(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Information");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to switch scenes (example: to login screen)
    @FXML
    void logoutid(ActionEvent event) {
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
}
