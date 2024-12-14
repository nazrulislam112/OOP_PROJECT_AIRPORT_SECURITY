
package securityofficer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mainpackage.LoginSceneController;
import mainpackage.WasaMainClass;


public class SecurityOfficerDashboardController implements Initializable {

    @FXML    private Button dashboardButton;
    @FXML    private Button addEmployeeButton;
    @FXML    private Button viewEmployeeButton;
    @FXML    private Button addEventButton;
    @FXML    private Button viewFeedbackButton;
    @FXML    private Button addGalleryButton;
    @FXML    private Button viewGalleyButton;
    @FXML    private Button addUserButton;
    @FXML    private Button searchUserButton; 
    @FXML    private Button searchPassengerButton;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label mobileLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label dobLabel;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dashboard("sun");
    }    

    @FXML
    private void customerLogOut(ActionEvent event) throws IOException {
         WasaMainClass wmc = new WasaMainClass();
        wmc.logOutScene(event);
    }
    
        private void loadUI(String ui) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
            borderPane.setCenter(root);
        } catch (IOException ex) {
//            Logger.getLogger(EmployeeDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void adminDashboardButtonOnClick(MouseEvent event) throws IOException {
            Parent r = FXMLLoader.load(getClass().getResource("SecurityOfficerDashboard.fxml"));
            Scene loginScene = new Scene(r);
            Stage stg2 = (Stage)((Node)event.getSource()).getScene().getWindow();
            stg2.setScene(loginScene);
            stg2.show();
    }
    
    
    public void dashboard(String u){
        //use BufferedReader OR Scanner
        File file = new File("file/users.txt");
        Scanner sc; String str=null;
        String s = "";
        
        try {
            sc = new Scanner(file);
            
            while(sc.hasNextLine()){
                
                str=sc.nextLine();
                             
                List<String> data = Arrays.asList(str.split(","));
                if(data.get(1).equals(u)  && data.get(7).equals("Security Officer")){
                    idLabel.setText(data.get(0));
                    nameLabel.setText(data.get(3));
                    userNameLabel.setText(data.get(1));
                    emailLabel.setText(data.get(4));
                    mobileLabel.setText(data.get(5));
                    addressLabel.setText(data.get(6));
                    dobLabel.setText(data.get(8));
                }
            }
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
    }

    @FXML
    private void addEmployeeButtonOnClick(MouseEvent event) {
        loadUI("AddEmployee");
        
    }

    @FXML
    private void viewEmployeeListButtonOnClick(MouseEvent event) {
        loadUI("ViewEmployee");
    }

    @FXML
    private void addEventButtonOnClick(MouseEvent event) {
        loadUI("AddEvent");
    }

    @FXML
    private void viewFeedbackButtonOnCLick(MouseEvent event) {
        loadUI("ViewFeedback");
    }

    @FXML
    private void addGalleryButtonOnClick(MouseEvent event) {
        loadUI("AddGallery");
    }

    @FXML
    private void viewGalleryButtonOnCLick(MouseEvent event) {
        loadUI("ViewGallery");
    }

    @FXML
    private void addUserButtonOnClick(MouseEvent event) {
        loadUI("AddUser");
    }
    @FXML
    private void searchPassengerButtonOnCLick(MouseEvent event) {
        loadUI("ViewPassenger");
    }

//    @FXML
//    private void searchUserButtonOnCLick(MouseEvent event) {
//        loadUI("ViewPassenger");
//    }
    
}
