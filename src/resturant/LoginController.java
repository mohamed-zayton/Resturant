/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class LoginController implements Initializable {
        @FXML
        TextField username;
        @FXML
         PasswordField password;
        
        public void login(Event event){
                if (username.getText().trim().matches("[aA]dmin") && password.getText().equals("123")) {
                        try {
                             Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.setTitle("Menu");
                             stage.setWidth(1000);
                             stage.setHeight(1000);
                                Rectangle2D rectangle2D = Screen.getPrimary().getVisualBounds();
                                stage.setX((rectangle2D.getWidth() - stage.getWidth()) /5);
                                stage.setY((rectangle2D.getWidth() - stage.getWidth()) /5);

                    } catch (Exception e) {
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Check Username or Password");
                }
        }
   
        public void exist(Event event){
            Platform.exit();
        }
      @Override
      public void initialize(URL url, ResourceBundle rb) {
          // TODO
      }    
    
}
