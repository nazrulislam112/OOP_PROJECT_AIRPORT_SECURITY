/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage;

import baggagehandler.BaggageHandler;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import securityofficer.SecurityOfficer;
import user.User;

/**
 *
 * @author User
 */
public class LoginSceneController implements Initializable {
    
 
    @FXML    private TextField userNameTextField;
    @FXML    private PasswordField passwordFieldTextField;
    @FXML    private Button signInButtonField;
    @FXML    private Hyperlink forgetPasswordHyperlink;
    @FXML    private ComboBox<String> userTypeComboBox;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        userTypeComboBox.getItems().add("System Admin");
//        userTypeComboBox.getItems().add("Accounts");
//        userTypeComboBox.getItems().addAll("Employee","Customer","Service","Maintenance","Manager","Security officer","HR manager","Worker Supervisor");
          userTypeComboBox.getItems().addAll("Security officer","Baggage Handler");
    }

    private String readUserData(String value,int index) {
        //use BufferedReader OR Scanner
        File file = new File("file/users.txt");
        Scanner sc; String str=null;
        String s = "";
        
        try {
            sc = new Scanner(file);
            
            while(sc.hasNextLine()){
                
                str=sc.nextLine();
                             
                List<String> data = Arrays.asList(str.split(","));
                
                if(value.equals(data.get(index))){
                    
                    return data.get(index);
                }   




            }
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return null;
    }
   
    @FXML
    private void login(ActionEvent event) throws IOException {
        WasaMainClass wmc = new WasaMainClass();
        User u = new User();
        SecurityOfficer so = new SecurityOfficer();
        BaggageHandler bg = new BaggageHandler();
        


        String username = readUserData(userNameTextField.getText().toString(),1);
        String password = readUserData(passwordFieldTextField.getText().toString(),2);
        String usertype = readUserData(userTypeComboBox.getValue().toString(),7);

   
        if(userNameTextField.getText().toString().equals(username) && passwordFieldTextField.getText().toString().equals(password) && userTypeComboBox.getValue().toString().equals("Security officer")){
            so.performLogin(event); 
        }

        else if(userNameTextField.getText().toString().equals(username) && passwordFieldTextField.getText().toString().equals(password) && userTypeComboBox.getValue().toString().equals("Baggage Handler")){
            bg.performLogin(event); 
        }

        else{
            wmc.LoginWarning();
        }

    }

    
}
