package alpha;

import java.sql.Connection;
import static java.sql.DriverManager.getConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import src.*;

/**
 *
 * @author Jonathon Makepeace
 */
public class launcher extends Application {
    
    Stage stage;
    Parent parent;
    
    /**
     * Will get the connection information and load it into the admin lists.
     */
    public static void getInfoConnection(){
        Connection conn = null;
    try{
        
        String url = "jdbc:sqlserver://workassignment.database.windows.net:1433;database=workers;user=partical@workassignment;password={admin@123};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        
        conn = getConnection(url);
            
            
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select workerID,firstName,lastName,skillSet from workers;");
        
        while(rs.next()){
            int workerID = rs.getInt(1);
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String skillSet = rs.getString("skillSet");
            
            workers wrk = new workers(workerID, firstName, lastName, skillSet);
            admin.addWorker(wrk);
        }    
            
        stmt = conn.createStatement();
        rs = stmt.executeQuery("select * from jobs");
        
        while(rs.next()){
            int jobID = rs.getInt(1);
            int workerID = rs.getInt(2);
            String custName = rs.getString("custName");
            Double timeComplete = rs.getDouble("timeComplete");
            int equipUsed = rs.getInt(4);
            String category = rs.getString("category");
            String skillSet = rs.getString("skillSet");
            String scheduledDateTime = rs.getString("scheduledDateTime");
            Boolean completionStatus = rs.getBoolean("completionStatus");
            String address = rs.getString("address");
            
            Jobs jb = new Jobs(jobID,workerID,custName,timeComplete,
            equipUsed, category, skillSet,scheduledDateTime,completionStatus, address);
            workers wk = admin.getWorker(workerID);
            wk.getListOfAssignedJobs().add(jb);
            admin.addJob(jb);
        }
        
    }   catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    
    /**
     * Will take the job object, break it down to the sql components and create
     *      a new job in the database. 
     * @param jb Job object
     */
    public static void sendJobConnection(Jobs jb){
            Connection conn = null;
    try{
        
        String url = "jdbc:sqlserver://workassignment.database.windows.net:1433;database=workers;user=partical@workassignment;password={admin@123};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    
        conn = getConnection(url);
            
        
        
        PreparedStatement stmt;
        String insert = "INSERT INTO jobs (workerID, custName,equipUsed,"
                + "category, skillSet, scheduledDateTime,completionStatus, address"
                + ",timeComplete)"
                + " values(?,?,?,?,?,?,?,?,?)";
        stmt = conn.prepareStatement(insert);
        stmt.setInt(1, jb.getWorkerID());
        stmt.setString(2, jb.getCustName());
        stmt.setInt(3, jb.getEquipUsed());
        stmt.setString(4, jb.getCategory());
        stmt.setString(5, jb.getSkillSet());
        stmt.setString(6, jb.getScheduledDateTime());
        stmt.setBoolean(7, jb.getCompletionStatus());
        stmt.setString(8, jb.getAddress());
        if(jb.getTimeCompleted()==null){
            stmt.setDouble(9, 0.0);
        }else{
            stmt.setDouble(9, jb.getTimeCompleted());
        }
        stmt.execute();
    }   
        catch(Exception ex){
                ex.printStackTrace();
                }
    }
    
    /**
     * Will erase the job based on its job id
     * @param id int id
     */
    public static void eraseJobConnection(int id){
            Connection conn = null;
    try{
        
        String url = "jdbc:sqlserver://workassignment.database.windows.net:1433;database=workers;user=partical@workassignment;password={admin@123};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    
        conn = getConnection(url);
            
        
        
        PreparedStatement stmt;
        String insert = "DELETE FROM jobs where jobID=?";
        stmt = conn.prepareStatement(insert);
        stmt.setInt(1, id);
        stmt.execute();
    }   
        catch(Exception ex){
                ex.printStackTrace();
                }
    }
    
    /**
     * Start the JavaFX application
     * @param primaryStage the window that will be loaded
     * @throws Exception window change can cause exception and needs to be 
     *      thrown or in try/catch. 
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        Parent root = FXMLLoader.load(getClass().getResource("/Views/loginForm.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Work Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }

    
    
}
