
package securityofficer;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import user.User;


public class SecurityOfficer extends User{
    
    public void performLogin(ActionEvent event) throws IOException{
            Parent employeeDashBoardRoot = FXMLLoader.load(getClass().getResource("SecurityOfficerDashboard.fxml"));
            Scene employeeScene = new Scene(employeeDashBoardRoot);
            Stage stg2 = (Stage)((Node)event.getSource()).getScene().getWindow();
            stg2.setScene(employeeScene);
            stg2.show();
    }
    
}
