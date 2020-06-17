package Contollers;

import boundary.ViewAnalyticDataBoundary;
import boundary.generalDashBoardBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.InputRating;
import entity.MarketingCampaignTemplate;
import entity.Rating;
import javafx.application.Platform;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class AnalyticDataCreator extends BasicController {

   // private generalDashBoardBoundary myBoundary; /**     * The boundary controlled by this controller     */
    private ViewAnalyticDataBoundary myBoundary;

    //private ViewAnalyticDataBoundary viewAnalyticDataBoundary;???????

    /*public AnalyticDataCreator(generalDashBoardBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }*/
    public AnalyticDataCreator(ViewAnalyticDataBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }


    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_CUSTOMER_X_PURCHASE_TABLE:
                    ArrayList<Rating> resultList = new ArrayList<>();
                    resultList.addAll(this.changeResultToInputRating(result));
                    for (int i=0; i<resultList.size();i++) {
                        setRatingTableInDB(resultList,i);
                    }
                    break;
                case INSERT_RATING:
                    break;
                default:
                    break;
            }
        });
    }

  /*
    public void getRatingTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_RATING_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }
    */
    private void setRatingTableInDB(ArrayList<Rating> resultList, int i) {
        ArrayList<Object> varArray = new ArrayList<>();
            varArray.add(resultList.get(i).getCustomerID());
            varArray.add(resultList.get(i).getRating());
            varArray.add(resultList.get(i).getRating());

        SqlAction sqlAction = new SqlAction(SqlQueryType.INSERT_RATING, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    public void getCustomerXPurchaseTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_CUSTOMER_X_PURCHASE_TABLE);
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

                String hourString = new String("");
                //קאונטר לסוגי הדלק לראות איזה יש בהזמנות של הלקוח
                HashMap<String, Integer> fuelTypeCounter = new HashMap<String, Integer>();
                fuelTypeCounter.put("Gasoline95",0);        fuelTypeCounter.put("Diesel",0);
                fuelTypeCounter.put("HomeHeatingFuel",0);  fuelTypeCounter.put("ScooterFuel",0);
                int j=i+1;
                if ( j == resultList.size()) {
                    fuelTypeCounter.put(my.getFuelType(), 1);
                    hourString = my.getPurchaseHour();
                }
                for (; j < resultList.size() ; j++ )//נעבור על כל שאר ההזמנות של הלקוחות ממנו והלאה
                {
                    System.out.println("inside for");
                    InputRating other = resultList.get(j);
                    if (other.getCustomerID()==my.getCustomerID()) //אם זה אותו לקוח - ניקח פרטים על הרכישות שלו
                    {
                        //נוסיף לקאונטר של אותו סוג דלק
                        fuelTypeCounter.put(other.getFuelType(),fuelTypeCounter.get((other.getFuelType()))+1);
                        //נכניס את כל השעות לסטרינג ובסוף נמצא ממוצע
                        hourString = hourString + other.getPurchaseHour();
                        //נסמן שטיפלנו בו
                        other.setFlag(true);
                    }
                }//סוף המעבר על כל הלקוחות והרכישות ממנו והלאה במערך
                //ממוצע של התאריכים וחישוב דירוג עבור הממוצע הזה
                System.out.println("after for");
                purchaseHourRating =calculateTimeRating(calculateAverageOfTime(hourString));

                //לבדוק לאיזה דלק יש הכי הרבה בקאונטר ולשמור דירוג לפי סוג הדלק
                purchaseTypeRating =find_max(fuelTypeCounter);
                //הכנסה לרשימה של דירוגים
                ratingList.add(new Rating(customerTypeRating+purchaseHourRating+purchaseTypeRating ,  my.getCustomerID(), my.getCustomerType()));
            }
        }
        return ratingList;
    }



//מחשב ממוצע מתוך סטרינג עם רשימה של שעות
    private static String calculateAverageOfTime(String timeInHHmmss) {
        String[] split = timeInHHmmss.split(" ");
        long seconds = 0;
        for (String timestr : split) {
            String[] hhmmss = timestr.split(":");
            seconds += Integer.valueOf(hhmmss[0]) * 60 * 60;
            seconds += Integer.valueOf(hhmmss[1]) * 60;
            seconds += Integer.valueOf(hhmmss[2]);
        }
        seconds /= split.length;
        long hh = seconds / 60 / 60;
        long mm = (seconds / 60) % 60;
        long ss = seconds % 60;
        return String.format("%02d:%02d:%02d", hh,mm,ss);
    }
//מחשב דירוג של זמנים לפי השעה הממוצעת
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
}
