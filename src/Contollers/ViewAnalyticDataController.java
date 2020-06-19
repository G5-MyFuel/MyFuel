package Contollers;

import boundary.ViewAnalyticDataBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.InputRating;
import entity.Rating;
import javafx.application.Platform;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  A department responsible for logical calculations and communicating with the client server and DB
 *  For page "ViewAnalyticDataBoundary"
 *
 *  * @author Hana Wiener
 * @see ViewAnalyticDataBoundary - the form's gui controller (boundary) class
 */
public class ViewAnalyticDataController extends BasicController {
    /**
     * General variables to save start and end time
     */
    private Time start;
    private Time end;
    /**
     *Array for keeping counters of ratings, for pie diagram
     */
    public int[] counters = new int[11];
    /**
     * The  by this controller
     */
    private ViewAnalyticDataBoundary myBoundary;

    /**
     * Constructor for boundary controlled - save its boundary
     *
     * @param myBoundary
     */
    public ViewAnalyticDataController(ViewAnalyticDataBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /**
     *  empty constructor for this page (uses on "one in a week" calculation of rankings)
     */
    public ViewAnalyticDataController() {
    }

    /**
     * This method will only be activated if the "Generate Analitic Data" button is pressed
     * The method calls for a query that will get customer and purchases data ,
     *  calculate their ratings, and save to DB
     *
     */
    public void startCalculate(){
        this.deletePreviosData();
        this.getCustomerXPurchaseTable();
    }

    /**
     *  this methode
     * @param result - The result recieved from the DB
     */
    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            try{
            switch (result.getActionType()) {
                case DELETE_ALL_RATINGS_ROWS:
                    System.out.println("switch->DELETE_ALL_RATINGS_ROWS");
                    break;
                case GET_CUSTOMER_X_PURCHASE_TABLE:
                    ArrayList<Rating> resultList1 = new ArrayList<>();
                    resultList1.addAll(this.changeResultToInputRating(result));
                    for (int i=0; i<resultList1.size();i++) {
                        setRatingTableInDB(resultList1,i);
                    }
                    break;
                case INSERT_RATING:
                    System.out.println("switch->Insert rating");
                    break;
                case GET_RATING_FOR_CUSTUMER_TYPE:
                    ArrayList<Rating> resultList2 = new ArrayList<>();
                    resultList2.addAll(this.changeResultToRating(result));
                    myBoundary.setRatingForCustomerTypeTable(resultList2);
                    break;
                case GET_RATING_FOR_TIME_RANGE:
                    ArrayList<Rating> resultList3 = new ArrayList<>();
                    resultList3.addAll(this.changeResultToRatingForTimeRange(result));
                    myBoundary.setRatingForTimeRangeTable(resultList3);
                    break;
                case GET_RATING_FOR_FUEL_TYPE:
                    ArrayList<Rating> resultList4 = new ArrayList<>();
                    resultList4.addAll(this.changeResultToRatingForFuelType(result));
                    myBoundary.setRatingForFuelTypeTable(resultList4);
                    break;
                default:
                    break;
            }
            }catch(NullPointerException npe){

            }
        });

    }
    public void deletePreviosData() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.DELETE_ALL_RATINGS_ROWS);
        super.sendSqlActionToClient(sqlAction);
    }

    public void getRatingTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_RATING_TABLE);
        super.sendSqlActionToClient(sqlAction);

    }

    public void getCustomerXPurchaseTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_CUSTOMER_X_PURCHASE_TABLE);
        super.sendSqlActionToClient(sqlAction);

    }

    private ArrayList<Rating> changeResultToRating(SqlResult result){
        ArrayList<Rating> resultList = new ArrayList<>();
        for(ArrayList<Object> a: result.getResultData()) {
            Rating cos = new Rating((Integer) a.get(1),(String) a.get(0),(String)a.get(2));
            resultList.add(cos);
        }
        return resultList;
    }

    private ArrayList<Rating> changeResultToRatingForTimeRange(SqlResult result) {
        ArrayList<Rating> resultList = new ArrayList<>();
        for(int t=0;t<11;t++)   counters[t]=0;
        for (ArrayList<Object> a : result.getResultData()) {
            if ((Time.valueOf((String) a.get(2)).after(start) && Time.valueOf((String) a.get(2)).before(end)) ||
                    Time.valueOf((String) a.get(2)).equals(start) && Time.valueOf((String) a.get(2)).equals(end)) {
                Rating cos = new Rating((Integer) a.get(0), (String) a.get(1), (String) a.get(2));
                if (resultList.isEmpty()) resultList.add(cos);

                int counter=0;
                for(int i = 0; i<resultList.size(); i++){
                     if (resultList.get(i).getCustomerID().equals(cos.getCustomerID())) {
                         counter++;
                     }
                }
                if (counter ==0 ) {
                    resultList.add(cos);
                    for(int k=1; k<11; k++)
                        if (cos.getRating()==k)
                            counters[k]++;
                }
            }
        }
         return resultList;
    }

    private ArrayList<Rating> changeResultToRatingForFuelType(SqlResult result) {
        ArrayList<Rating> resultList = new ArrayList<>();
        for(int t=0;t<11;t++)   counters[t]=0;
        for (int i=0;i < result.getResultData().size(); i++ ) {
            ArrayList<Object> a = result.getResultData().get(i);
            Rating cos = new Rating((Integer) a.get(0), (String) a.get(1), (String) a.get(2));
                int counter=0;
                for(int j = 0; j<resultList.size(); j++){
                    if (resultList.get(j).getCustomerID().equals(cos.getCustomerID())) {
                        counter++;
                    }
                }
                if (counter ==0 ) {
                    resultList.add(cos);
                    for(int k=1; k<11; k++)
                        if (cos.getRating()==k)
                            counters[k]++;
                    }
                }
        return resultList;
    }
        private void setRatingTableInDB(ArrayList<Rating> resultList, int i) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(resultList.get(i).getCustomerID());
        varArray.add(resultList.get(i).getRating());
        varArray.add(resultList.get(i).getCustomerType());

        SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_RATING, varArray);
        super.sendSqlActionToClient(sqlAction);
    }


    private ArrayList<Rating> changeResultToInputRating(SqlResult result){
        ArrayList<InputRating> resultList = new ArrayList<>();
        ArrayList<Rating> ratingList = new ArrayList<>();
        //bring all to array list from db
        //  customerID   |   customerType   |   purchaseID   |   FuelType   |   purchaseHour
        for(ArrayList<Object> a: result.getResultData()) {
            InputRating cos = new InputRating((String) a.get(0),(String)a.get(1), (String)a.get(2),(String)a.get(3), (String) a.get(4));
            resultList.add(cos);
        }
        int customerTypeRating; //0.2
        int purchaseHourRating; //0.4
        int purchaseTypeRating; //0.4
        //calculate rating for each customer:
        for (int i=0; i < resultList.size(); i++ ){
            InputRating my = resultList.get(i);
            if(my.isFlag() == false) {
                customerTypeRating = my.getCustomerType().equals("private") ? 1 : 2;

                ArrayList<Integer> hourRatingArray = new ArrayList<>();

                HashMap<String, Integer> fuelTypeCounter = new HashMap<String, Integer>();
                fuelTypeCounter.put("Gasoline95",0);        fuelTypeCounter.put("Diesel",0);
                fuelTypeCounter.put("HomeHeatingFuel",0);  fuelTypeCounter.put("ScooterFuel",0);
                int j=i;
                if ( j == resultList.size()) {
                    fuelTypeCounter.put(my.getFuelType(), 1);
                    hourRatingArray.add(calculateTimeRating(my.getPurchaseHour()));
                }
                for (; j < resultList.size() ; j++ )
                {
                    InputRating other = resultList.get(j);
                    if (other.getCustomerID()==my.getCustomerID())
                    {

                        fuelTypeCounter.put(other.getFuelType(),fuelTypeCounter.get((other.getFuelType()))+1);
                        hourRatingArray.add(calculateTimeRating(other.getPurchaseHour()));
                        other.setFlag(true);
                    }
                }
                purchaseHourRating = calculateAverage(hourRatingArray);

                purchaseTypeRating =find_max(fuelTypeCounter);
                ratingList.add(new Rating(customerTypeRating+purchaseHourRating+purchaseTypeRating ,
                        my.getCustomerID(), my.getCustomerType()));
            }
        }
        return ratingList;
    }



    private int calculateTimeRating(String avgHour){
        int purchaseHourRating;
        Time hour6 = java.sql.Time.valueOf("06:00:00");
        Time hour10 = java.sql.Time.valueOf("10:00:00");
        Time hour16 = java.sql.Time.valueOf("16:00:00");
        Time hour20 = java.sql.Time.valueOf("20:00:00");
        Time avg = java.sql.Time.valueOf(avgHour);

        if ((avg.after(hour6) && avg.before(hour10)) || avg.equals(hour6))   {
            purchaseHourRating=4;
        }else if ((avg.after(hour10) && avg.before(hour16)) || avg.equals(hour10)) {
            purchaseHourRating=2;
        }else if  ((avg.after(hour16) && avg.before(hour20)) || avg.equals(hour16)) {
            purchaseHourRating=3;
        }else purchaseHourRating=1;
        return purchaseHourRating;
    }
    private  int find_max(HashMap<String, Integer> fuelTypeCounter){
        int a, b, c, d ;
        a= fuelTypeCounter.get("Gasoline95");
        b=fuelTypeCounter.get("HomeHeatingFuel");
        c= fuelTypeCounter.get("Diesel");
        d=fuelTypeCounter.get("ScooterFuel");

        if(a > b && a > c && a > d) return 3;
        else if(b > a && b > c && c > d) return 2;
        else if(c > a && c > b && c > d) return 4;
        else return 1;
    }

    private int calculateAverage(List<Integer> marks) {
        if (marks == null || marks.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (Integer mark : marks) {
            sum += mark;
        }

        return sum / marks.size();
    }

    public void getRatingForCustomerTypeTable(String paramArray) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(paramArray);
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_RATING_FOR_CUSTUMER_TYPE, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    public void getRatingForTimeRangeTable(Time start, Time end) {
        this.start = start;
        this.end= end;
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_RATING_FOR_TIME_RANGE);
        super.sendSqlActionToClient(sqlAction);
    }

    public void getRatingForFuelTypeTable(String paramArray) {
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(paramArray);
        System.out.println(varArray.toString());
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_RATING_FOR_FUEL_TYPE, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

}