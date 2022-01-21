
package src;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jonathon Makepeace
 */
public class admin {
    private static ObservableList<Jobs> listOfAllJobs = FXCollections.observableArrayList();
    private static ObservableList<workers> listOfAllWorkers = FXCollections.observableArrayList();


    public static ObservableList<Jobs> getListOfAllJobs() {
        return listOfAllJobs;
    }

    public static ObservableList<workers> getListOfAllWorkers() {
        return listOfAllWorkers;
    }



    public static void setListOfAllJobs(ObservableList<Jobs> listOfAllJobs) {
        admin.listOfAllJobs = listOfAllJobs;
    }

    public static void setListOfAllWorkers(ObservableList<workers> listOfAllWorkers) {
        admin.listOfAllWorkers = listOfAllWorkers;
    }

    
    
    
    public static void addJob(Jobs jb){
        listOfAllJobs.add(jb);
    }
    
    public static void removeJob(Jobs jb){
        listOfAllJobs.remove(jb);
    }
    
    public static void addWorker(workers wkr){
        listOfAllWorkers.add(wkr);
    }
    
    public static void removeWorker(workers wkr){
        listOfAllWorkers.remove(wkr);
    }
    
    /**
     * Find the worker object using its ID in the observableList
     * @param id int id of the workers object
     * @return the workers with the id given
     */
    public static workers getWorker(int id){
        for(workers i: listOfAllWorkers){
            if(i.getWorkerID()==id){
                return i;
            }
        }
        return null;
    }
}

