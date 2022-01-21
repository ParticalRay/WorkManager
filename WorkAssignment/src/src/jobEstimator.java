
package src;
import java.util.ArrayList;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;


public class jobEstimator {
   
    private static final OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
    private static double[] y;
    private static double[][] x;
    private static ArrayList<Jobs> completedJobs = new ArrayList<>();
    private static double[] b;
    
    /**
     * Will assign the time estimate of the jobs incomplete and print the results
     * The results of the MLR will be displayed. 
     */
    public static void getResults(){
         populateData();
         regression.newSampleData(y, x);
         b = regression.estimateRegressionParameters();
         for(Jobs j: admin.getListOfAllJobs()){
             if(j.getTimeCompleted()==0.0){
                 double timeEstimate = calculateTime(j.getComponents()[0],j.getComponents()[1],j.getComponents()[2]);
                 j.setTimeEstimate(timeEstimate);
                 System.out.println("Job ID: " + j.getId() + " with time estimate of " + j.getTimeEstimate());
             }
         }
         printResults(b);
    }
    
    /**
     * Will populate the data into the matrices used for the MLR model. Y is the
     *      Time estimates, X is the components that equal the time. 
     */
    public static void populateData(){
        for(Jobs j: admin.getListOfAllJobs()){
            
            if(j.getTimeCompleted()!=0.0){
                completedJobs.add(j);
            }
        }
        
        y = new double[completedJobs.size()];
        x = new double[completedJobs.size()][];
        for(int i=0; i<completedJobs.size(); i++){
            y[i] = completedJobs.get(i).getTimeCompleted();
            x[i] = completedJobs.get(i).getComponents();
            
        }
    }
    
    /**
     * Will display the results of the MLR. Kept separate to increase visibility
     *      and understanding. 
     * @param b The resulting parameters of the MLR formula. Form of an array. 
     */
    private static void printResults(double[] b){
        double sigma = regression.estimateRegressionStandardError();
        
        double rSquared = regression.calculateAdjustedRSquared();
        double variance = regression.estimateRegressandVariance();
        System.out.println("Variance: " + variance);
        System.out.println("r-Squared: " + rSquared);
        System.out.println("Standard Error: " + sigma);
        System.out.printf("f(s, t) = %.2f + %.2fCategory + %.2fskillSet + %.2fequipUsed%n", b[0], b[1], b[2],b[3]);
        
    }

    /**
     * Will be used to calculate the time estimate by taking the components of
     *      the job and multiplying it by the parameters assigned by the model. 
     *      The result will be in minutes and is used to assign the time estimate
     *      to the job itself. 
     * @param category String that has been assigned a numerical value (1,2,3)
     * @param skillSet String that has been totaled together ("first","second") = 2
     * @param equipUsed Number of equipment used. 
     * @return minutes in double form. 
     */
    private static double calculateTime(double category, double skillSet, double equipUsed){
        return b[0] + b[1]*category + b[2]*skillSet + b[3]*equipUsed;
    }
        
}