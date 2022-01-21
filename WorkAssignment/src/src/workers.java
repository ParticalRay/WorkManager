
package src;

import java.util.ArrayList;
import java.util.List;

/**
 * The Workers class will be used to load data and assign an object to it
 */
public class workers {
    private int workerID;
    private String firstName;
    private String lastName;
    private String fullName; 
    private List<Jobs> listOfAssignedJobs = new ArrayList();
    private String skillSet;
    

    /**
     * Constructor for worker class 
     * @param workerID int variable. Only assigned at load of database
     * @param firstName String first name of the worker.
     * @param lastName String last name of the worker.
     * @param skillSet String skill set, separated by comma(,).
     */
    public workers(int workerID, String firstName, String lastName, String skillSet) {
        this.workerID = workerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skillSet = skillSet;
        this.fullName =  firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName ;
    }

    
    public String getFullName(){
        return this.fullName;
    }
    
    public void setWorkerID(int workerID) {
        this.workerID = workerID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setListOfAssignedJobs(List<Jobs> listOfAssignedJobs) {
        this.listOfAssignedJobs = listOfAssignedJobs;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }

    public int getWorkerID() {
        return workerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Jobs> getListOfAssignedJobs() {
        return listOfAssignedJobs;
    }

    public String getSkillSet() {
        return skillSet;
    }
    
    public int getNumOfJobs(){
        return this.listOfAssignedJobs.size();
    }
    
}
