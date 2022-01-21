
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import static java.sql.DriverManager.getConnection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jonathon Makepeace
 */
public class LoginFormController implements Initializable {

    Stage stage;
    Parent parent;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button confirmButton;
    @FXML
    private Button exitButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * Verify the user with the username and password given. Connects to the 
     *  database with the given information. 
     * @param user String username
     * @param pass String password
     * @return 
     */
    public Boolean verifyUser(String user, String pass){
            Connection conn = null;
    try{
        
        String url = "jdbc:sqlserver://workassignment.database.windows.net:1433;database=workers;user=" + user
                + "@workassignment;password=" + pass
                + ";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        
        conn = getConnection(url);
            conn.getMetaData();
       
        return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Issues found in connections");
            return false;
        }
    }
    
    /**
     * ConfirmUser is used when the button is pressed and sends the strings
     *  in the username and password fields to the database to verify. 
     *  
     * @param event Button pressed
     * @throws IOException Window transition. Needs exception to be thrown or
     *      try/catch set. 
     */
    @FXML
    private void confirmUser(ActionEvent event) throws IOException {
        String user = usernameField.getText();
        String pass = passField.getText();
        if(verifyUser(user, pass)){
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            parent = FXMLLoader.load(getClass().getResource("/Views/mainS.fxml"));
            stage.setScene(new Scene(parent));
            stage.show();
        }
    }

    /**
     * Exit the application
     * @param event Button Pressed
     */
    @FXML
    private void exitApplication(ActionEvent event) {
        System.exit(0);
    }
    
}
