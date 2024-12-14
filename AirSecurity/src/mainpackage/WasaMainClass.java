package mainpackage;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class WasaMainClass extends Application {
    
    private double x,y;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        
        Scene scene = new Scene(root);
        
        root.setOnMousePressed(new EventHandler<MouseEvent> (){
            @Override
            public void handle(MouseEvent event){
                x = event.getSceneX();
                y = event.getSceneY();
            }
        });
        
        root.setOnMouseDragged(new EventHandler<MouseEvent> (){
            @Override
            public void handle(MouseEvent event){
                //stage.setX(event.getSceneX() - x);
                //stage.setY(event.getSceneY() - y);
                
            }
        });
        
        stage.setScene(scene);
        stage.setTitle("Airport Security");
        stage.show();
    }
    
     public void logOutScene(ActionEvent event) throws IOException{
//        Parent pane = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
//        stg.getScene().setRoot(pane);
            Parent r = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
            Scene loginScene = new Scene(r);
            Stage stg2 = (Stage)((Node)event.getSource()).getScene().getWindow();
            stg2.setScene(loginScene);
            stg2.show();
    }
     
     public  void LoginWarning() throws IOException{
        Parent ro = FXMLLoader.load(getClass().getResource("WrongLoginWarning.fxml"));
        Scene warningScene = new Scene(ro);
        Stage loginWarningStage = new Stage();
        loginWarningStage.setScene(warningScene);
        loginWarningStage.setTitle("Warning!");
        loginWarningStage.show();
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
