
package Controllers;

import alpha.launcher;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import src.Jobs;
import src.admin;
import src.workers;
import src.jobEstimator;

/**
 * FXML Controller class
 *
 * @author Jonathon Makepeace
 */
public class MainSController implements Initializable {

    Stage stage;
    Parent parent;
    @FXML
    private TableView<workers> workerTable;
    @FXML
    private TableView<Jobs> jobTable;
    @FXML
    private Button createModyifyButton;
    @FXML
    private Button getDetailsButton;
    @FXML
    private Button eraseJobButton;
    @FXML
    private TableColumn<workers, String> workerNameCol;
    @FXML
    private TableColumn<workers, Integer> workerIDCol;
    @FXML
    private TableColumn<workers, Integer> numOfJobsCol;
    @FXML
    private TableColumn<Jobs, String> addressCol;
    @FXML
    private TableColumn<Jobs, Integer> jobIDCol;
    @FXML
    private TableColumn<Jobs, Integer> jobWorkerID;
    @FXML
    private TableColumn<Jobs, String> timeFrameCol;
   
    @FXML
    private TableColumn<Jobs, String> categoryCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO Load information into jobs and workers
        admin.getListOfAllJobs().clear();
        admin.getListOfAllWorkers().clear();
        launcher.getInfoConnection();
        workerTable.setItems(admin.getListOfAllWorkers());
        workerNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        workerIDCol.setCellValueFactory(new PropertyValueFactory<>("workerID"));
        numOfJobsCol.setCellValueFactory(new PropertyValueFactory<>("numOfJobs"));
        
        jobTable.setItems(admin.getListOfAllJobs());
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        jobIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        jobWorkerID.setCellValueFactory(new PropertyValueFactory<>("workerID"));
        timeFrameCol.setCellValueFactory(new PropertyValueFactory<>("timeframe"));
    }    


    /**
     * Create or modify job object with information. Saving will load into 
     * database. If a job object is selected it will modify, otherwise, will
     * create a new job object.
     * 
     * Information will need to be loaded into the new form for adjustments.
     * @param event Button clicked
     */
    @FXML
    private void createOrModifyJob(ActionEvent event) throws IOException {
        if(jobTable.getSelectionModel().getSelectedItem()!=null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/modifyJobs.fxml"));
            loader.load();
            
            
            Jobs jb = jobTable.getSelectionModel().getSelectedItem();
            
            ModifyJobsController mjc = loader.getController();
            mjc.setDefaultJob(jb);
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            parent = loader.getRoot();
            stage.setScene(new Scene(parent));
            stage.show();
        }else{
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            parent = FXMLLoader.load(getClass().getResource("/views/createJobs.fxml"));
            stage.setScene(new Scene(parent));
            stage.show();
        }
    }

    /**
     * Will print the results of the MLR model and set the estimates for the 
     *  jobs.
     * @param event 
     */
    @FXML
    private void getDetails(ActionEvent event) {
        jobEstimator.getResults();
        
    }

    /**
     * Will erase the job that has been selected. 
     * @param event 
     */
    @FXML
    private void eraseJob(ActionEvent event) {
        Jobs jb = jobTable.getSelectionModel().getSelectedItem();
        admin.removeJob(jb);
        launcher.eraseJobConnection(jb.getId());
        
    }
    
}
