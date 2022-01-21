
package Controllers;

import alpha.launcher;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.Jobs;
import src.admin;
import src.workers;

/**
 * FXML Controller class
 *
 * @author Jonathon Makepeace
 */
public class CreateJobsController implements Initializable {
    private final String list[] = {"8:00:00", "10:00:00", "12:00:00","14:00:00","16:00:00"};
    Stage stage;
    Parent parent;
    @FXML
    private TextField custNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField equipUsedField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField skillNeededField;
    @FXML
    private DatePicker dateWantedField;
    @FXML
    private ChoiceBox<String> timeFrameField;
    @FXML
    private ChoiceBox<workers> workerNameField;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelJob;
    @FXML
    private Button resetButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        timeFrameField.setItems(FXCollections.observableArrayList(list));
        workerNameField.setItems(admin.getListOfAllWorkers());
    }    

    /**
     * Save everything in the form and load new job into database.
     * @param event Button Clicked
     */
    @FXML
    private void saveJob(ActionEvent event) throws IOException {
        //System.out.println(workerNameField.getSelectionModel().getSelectedItem().getWorkerID());
        //custNameField.getText();
        int workerID = workerNameField.getSelectionModel().getSelectedItem().getWorkerID();
        String custName = custNameField.getText();
        int equipUsed = Integer.parseInt(equipUsedField.getText());//add check later
        String category = categoryField.getText();
        String skillSet = skillNeededField.getText();
        String scheduledDateTime = dateWantedField.getValue() + " "
                + timeFrameField.getSelectionModel().getSelectedItem();
        
        String address = addressField.getText();
        Jobs jb = new Jobs(workerID,custName,equipUsed,category,skillSet,scheduledDateTime,address);
        
        launcher.sendJobConnection(jb);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        parent = FXMLLoader.load(getClass().getResource("/views/mainS.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * Cancel job creation and return to previous form.
     * @param event Button Clicked
     */
    @FXML
    private void cancelJobButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        parent = FXMLLoader.load(getClass().getResource("/views/mainS.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * Reset everything in the form to the default. In this instance
     *  It will return all values to initial values. 
     * @param event Button Clicked
     */
    @FXML
    private void resetForm(ActionEvent event) {
        custNameField.clear();
        addressField.clear();
        equipUsedField.clear();
        categoryField.clear();
        skillNeededField.clear();
        dateWantedField.getEditor().clear();
        timeFrameField.setValue(null);
        workerNameField.setValue(null);
        
    }
    
}
