package Contollers;

import boundary.MarketingCampaignTemplateBoundary;
import boundary.ViewAnalyticDataBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.InputRating;
import entity.MarketingCampaign;
import entity.MarketingCampaignTemplate;
import entity.Rating;
import javafx.application.Platform;
import server.MysqlConnection;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ViewAnalyticDataController extends BasicController {
    private Time start;
    private Time end;
    private ViewAnalyticDataBoundary myBoundary; /**     * The boundary controlled by this controller     */
    public ViewAnalyticDataController(ViewAnalyticDataBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }
    public int[] monim = new int[11];

    public ViewAnalyticDataController() {
    }

    public void startCalculate(){
        this.deletePreviosData();
        this.getCustomerXPurchaseTable();
    }

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case DELETE_ALL_RATINGS_ROWS:
                    break;
                case GET_CUSTOMER_X_PURCHASE_TABLE:
                    ArrayList<Rating> resultList1 = new ArrayList<>();
                    resultList1.addAll(this.changeResultToInputRating(result));
                    for (int i=0; i<resultList1.size();i++) {
                        setRatingTableInDB(resultList1,i);
                    }
                    break;
                case INSERT_RATING:
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
                for(int t=0;t<4;t++)   monim[t]=0;
                if (counter ==0 ) {//הכנסה לרשימה שתוצג בסוף
                    resultList.add(cos);
                    for(int k=1; k<11; k++)
                        if (cos.getRating()==k)
                            monim[k]++;
                }
            }
        }
         return resultList;
    }

    private ArrayList<Rating> changeResultToRatingForFuelType(SqlResult result) {
        ArrayList<Rating> resultList = new ArrayList<>();
        for (int i=0;i < result.getResultData().size(); i++ ) {
            ArrayList<Object> a = result.getResultData().get(i);
            Rating cos = new Rating((Integer) a.get(0), (String) a.get(1), (String) a.get(2));
                int counter=0;
                for(int j = 0; j<resultList.size(); j++){
                    if (resultList.get(j).getCustomerID().equals(cos.getCustomerID())) {
                        counter++;
                    }
                }
                for(int t=0;t<4;t++)   monim[t]=0;
                if (counter ==0 ) {//הכנסה לרשימה שתוצג בסוף
                    resultList.add(cos);
                    for(int k=1; k<11; k++)
                        if (cos.getRating()==k)
                            monim[k]++;
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
        for (int i=0; i < resultList.size(); i++ ){ //לשים לב לגודל של המערך אם אין לי נל פוינטר אקספשיין // עבור כל הלקוחות ברשימה שהבאנו
            InputRating my = resultList.get(i); //נביא אותו מהרשימה
            if(my.isFlag() == false) { //עוד לא טיפלנו ולכן נטפל עכשיו
                customerTypeRating = my.getCustomerType().equals("private") ? 1 : 2; //דירוג שקשור לסוג לקוח

                ArrayList<Integer> hourRatingArray = new ArrayList<>();
                //קאונטר לסוגי הדלק לראות איזה יש בהזמנות של הלקוח
                HashMap<String, Integer> fuelTypeCounter = new HashMap<String, Integer>();
                fuelTypeCounter.put("Gasoline95",0);        fuelTypeCounter.put("Diesel",0);
                fuelTypeCounter.put("HomeHeatingFuel",0);  fuelTypeCounter.put("ScooterFuel",0);
                int j=i;
                if ( j == resultList.size()) {//אם אחרון
                    fuelTypeCounter.put(my.getFuelType(), 1);
                    hourRatingArray.add(calculateTimeRating(my.getPurchaseHour()));
                }
                for (; j < resultList.size() ; j++ )//נעבור על כל שאר ההזמנות של הלקוחות ממנו והלאה
                {
                    InputRating other = resultList.get(j);
                    if (other.getCustomerID()==my.getCustomerID()) //אם זה אותו לקוח - ניקח פרטים על הרכישות שלו
                    {
                        //נוסיף לקאונטר של אותו סוג דלק
                        fuelTypeCounter.put(other.getFuelType(),fuelTypeCounter.get((other.getFuelType()))+1);
                        //נמצא דירוג של השעה הנוכחית -ואחכ נעשה ממוצע
                        hourRatingArray.add(calculateTimeRating(other.getPurchaseHour()));
                        //נסמן שטיפלנו בו
                        other.setFlag(true);
                    }
                }//סוף המעבר על כל הלקוחות והרכישות ממנו והלאה במערך
                //ממוצע של הדירוגים עבור השעות והצבה
                purchaseHourRating = calculateAverage(hourRatingArray);

                //לבדוק לאיזה דלק יש הכי הרבה בקאונטר ולשמור דירוג לפי סוג הדלק
                purchaseTypeRating =find_max(fuelTypeCounter);
                //הכנסה לרשימה של דירוגים
                ratingList.add(new Rating(customerTypeRating+purchaseHourRating+purchaseTypeRating ,
                        my.getCustomerID(), my.getCustomerType()));
            }
        }
        return ratingList;
    }



    //מחשב דירוג של זמנים
    private int calculateTimeRating(String avgHour){
        int purchaseHourRating;
        Time hour6 = java.sql.Time.valueOf("06:00:00");
        Time hour10 = java.sql.Time.valueOf("10:00:00");
        Time hour16 = java.sql.Time.valueOf("16:00:00");
        Time hour20 = java.sql.Time.valueOf("20:00:00");
        Time avg = java.sql.Time.valueOf(avgHour);

        if ((avg.after(hour6) && avg.before(hour10)) || avg.equals(hour6))   {//מ6 כולל עד 10 לא כולל
            purchaseHourRating=4;
        }else if ((avg.after(hour10) && avg.before(hour16)) || avg.equals(hour10)) {//מ10 כולל עד 15 לא כולל
            purchaseHourRating=2;
        }else if  ((avg.after(hour16) && avg.before(hour20)) || avg.equals(hour16)) {//מ15 כולל עד 20 לא כולל
            purchaseHourRating=3;
        }else purchaseHourRating=1;
        return purchaseHourRating;
    }
    //מוצא מקסימום מבין 4 ובהתאם מחזיר את הדירוגים המתאימים
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

    //חישוב ממוצע של מערך:
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
        System.out.println(varArray.toString());//todo: להוריד את זה
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_RATING_FOR_FUEL_TYPE, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

}