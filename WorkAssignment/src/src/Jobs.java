package src;

/**
 * The jobs class will be used to create objects that contain job information. 
 *  
 * @author Jonathon Makepeace
 */
public class Jobs {
    private int id;
    private int workerID;
    private String custName;
    private Double timeCompleted;
    private int equipUsed;
    private String category;
    private String skillSet;
    private String scheduledDateTime;
    private Boolean completionStatus; 
    private String timeframe; //timeframes can be changed
    private String address;
    private double timeEstimate;
    private double[] components = new double[3];
    
    /**
     * The first job constructor. Used to create the job object and perform a 
     *  split to assign the timeframe wanted by the customer. There are three
     *  constructors because some values are not needed yet or are assigned later
     *  in the application. Used for final upload of information to DB. 
     * @param id job id
     * @param workerID worker id
     * @param custName customer name
     * @param timeCompleted time duration till completion in minutes
     * @param equipUsed equipment used, currently from 1 to 12
     * @param category category of job, either install, trouble call, or business
     * @param skillSet skill set needed for job
     * @param scheduledDateTime scheduled date and time of arrival to job
     * @param completionStatus completion status
     * @param address address of the job
     */
    public Jobs(int id, int workerID, String custName, Double timeCompleted,
            int equipUsed, String category, String skillSet,
            String scheduledDateTime, Boolean completionStatus, String address) {
        this.timeEstimate = 0.0;
        this.id = id;
        this.workerID = workerID;
        this.custName = custName;
        this.timeCompleted = timeCompleted;
        this.equipUsed = equipUsed;
        this.category = category;
        this.skillSet = skillSet;
        this.scheduledDateTime = scheduledDateTime;
        this.completionStatus = completionStatus;
        this.address = address;
        this.timeframe = scheduledDateTime.split(" ")[1];
        setComponents();
    }

    /**
     * The second job constructor. 
     *  Used to create the job object and perform a 
     *  split to assign the timeframe wanted by the customer. There are three
     *  constructors because some values are not needed yet or are assigned later
     *  in the application. 
     * @param workerID worker id
     * @param custName customer name
     * @param equipUsed equipment used, currently from 1 to 12
     * @param category category of job, either install, trouble call, or business
     * @param skillSet skill set needed for job
     * @param timeCompleted time duration till completion in minutes
     * @param scheduledDateTime scheduled date and time of arrival to job
     * @param address address of the job
     */
    public Jobs(int workerID, String custName, int equipUsed, String category, String skillSet, String scheduledDateTime, String address,
            double timeCompleted) {
        this.timeEstimate = 0.0;
        this.workerID = workerID;
        this.custName = custName;
        this.equipUsed = equipUsed;
        this.category = category;
        this.skillSet = skillSet;
        this.scheduledDateTime = scheduledDateTime;
        this.address = address;
        this.completionStatus = false;
        this.timeframe = scheduledDateTime.split(" ")[1];
        this.timeCompleted = timeCompleted;
        setComponents();
    }
    
    /**
     * The third job constructor. 
     *  Used to create the job object and perform a 
     *  split to assign the timeframe wanted by the customer. There are three
     *  constructors because some values are not needed yet or are assigned later
     *  in the application. Used in case a job is not completed yet. 
     * @param workerID worker id
     * @param custName customer name
     * @param equipUsed equipment used, currently from 1 to 12
     * @param category category of job, either install, trouble call, or business
     * @param skillSet skill set needed for job
     * @param scheduledDateTime scheduled date and time of arrival to job
     * @param address address of the job
     */
    public Jobs(int workerID, String custName, int equipUsed, String category, String skillSet, String scheduledDateTime, String address) {
        this.timeEstimate = 0.0;
        this.workerID = workerID;
        this.custName = custName;
        this.equipUsed = equipUsed;
        this.category = category;
        this.skillSet = skillSet;
        this.scheduledDateTime = scheduledDateTime;
        this.address = address;
        this.completionStatus = false;
        this.timeframe = scheduledDateTime.split(" ")[1];
        setComponents();
    }
    
    
    
    
    /**
     * Convert job to a string format to view all information
     * @return String of all attributes of the job
     */
    @Override
    public String toString() {
        return "Jobs{" + "id=" + id + ", workerID=" + workerID + ", custName=" 
                + custName + ", timeCompleted=" + timeCompleted + ", equipUsed=" 
                + equipUsed + ", category=" + category + ", skillSet=" + skillSet 
                + '}' + ", scheduledDateTime=" + scheduledDateTime + '}';
    }

    /**
     * Set the components needed for the Machine learning algorithm
     *  Multiple Linear Regression. The components are assigned to the job to 
     *  later be used for the final time estimate. The results are compared and
     *  evaluated in the jobEstimator class. 
     *  
     */
    public void setComponents(){
        String[] allCat = new String[]{"Install","Trouble Call","Business"};
        switch (category) {
        case "Install":
            components[0] = 1;
            break;
        case "Trouble Call":
            components[0] = 2;
            break;
        default:
            components[0] = 3;
            break;
        }
        components[1] = skillSet.split(" ").length;
        components[2] = equipUsed;
    }
 
    public void setTimeEstimate(double time){
        this.timeEstimate = time;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setWorkerID(int workerID) {
        this.workerID = workerID;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public void setTimeCompleted(Double timeCompleted) {
        this.timeCompleted = timeCompleted;
    }

    public void setEquipUsed(int equipUsed) {
        this.equipUsed = equipUsed;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }

    public void setScheduledDateTime(String scheduledDateTime) {
        this.scheduledDateTime = scheduledDateTime;
    }

    public void setCompletionStatus(Boolean completionStatus) {
        this.completionStatus = completionStatus;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }
    
    public void setAddress(String address){
        this.address = address;
    }

    public double[] getComponents(){
        return components;
    }
    
    public double getTimeEstimate(){
        return timeEstimate;
    }
    
    public int getId() {
        return id;
    }

    public int getWorkerID() {
        return workerID;
    }

    public String getCustName() {
        return custName;
    }

    public Double getTimeCompleted() {
        return timeCompleted;
    }

    public int getEquipUsed() {
        return equipUsed;
    }

    public String getCategory() {
        return category;
    }

    public String getSkillSet() {
        return skillSet;
    }

    public String getScheduledDateTime() {
        return scheduledDateTime;
    }

    public Boolean getCompletionStatus() {
        return completionStatus;
    }

    public String getTimeframe() {
        return timeframe;
    }
    
    public String getAddress(){
        return this.address;
    }
    

    
}
