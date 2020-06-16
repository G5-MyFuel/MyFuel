package Contollers;

import boundary.generalDashBoardBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.InputRating;
import entity.Rating;
import javafx.application.Platform;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class AnalyticDataCreator extends BasicController {

    private generalDashBoardBoundary myBoundary; /**     * The boundary controlled by this controller     */

    public AnalyticDataCreator(generalDashBoardBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }
    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_CUSTOMER_X_PURCHASE_TABLE:
                    ArrayList<InputRating> resultList = new ArrayList<>();
                    resultList.addAll(this.changeResultToInputRating(result));
                //    myBoundary.setRatingTable(resultList);
                    break;

                default:
                    break;
            }
        });
    }

    public void getCustomerXPurchaseTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_CUSTOMER_X_PURCHASE_TABLE);
        super.sendSqlActionToClient(sqlAction);

    }

    private ArrayList<InputRating> changeResultToInputRating(SqlResult result){
        ArrayList<InputRating> resultList = new ArrayList<>();
        //bring all to array list from db
        //  customerID   |   customerType   |   purchaseID   |   FuelType   |   purchaseHour
        for(ArrayList<Object> a: result.getResultData()) {
            InputRating cos = new InputRating(Integer.parseInt((String) a.get(0)),(String)a.get(1), (String)a.get(2),(String)a.get(3), (String) a.get(4));
            resultList.add(cos);
        }
        int customerTypeRating; //0.2
        int purchaseHourRating; //0.4
        int purchaseTypeRating; //0.4
        //calculate rating for each customer:

        //while or for
        for (int i=0; i < resultList.size(); i++ ){ //לשים לב לגודל של המערך אם אין לי נל פוינטר אקספשיין
            InputRating my = resultList.get(i);
            if(my.isFlag() == false) { //עוד לא טיפלנו ולכן נטפל עכשיו
                customerTypeRating = my.getCustomerType().equals("private") ? 1 : 2;

                String hourString = new String();
                HashMap<String, Integer> fuelTypeCounter = new HashMap<String, Integer>();
                fuelTypeCounter.put("Gasoline 95",0);        fuelTypeCounter.put("Diesel",0);
                fuelTypeCounter.put("Home Heating Fuel",0);  fuelTypeCounter.put("Scooter fuel",0);

                for (int j=i+1; j < resultList.size() ; j++ )
                {
                    InputRating other = resultList.get(j);

                    if (other.getCustomerID()==my.getCustomerID())
                    {
                        //נוסיף לקאונטר של אותו סוג דלק
                        //fuelTypeCounter.put()
                        fuelTypeCounter.get("x");
                        //נכניס את כל השעות למערך ובסוף נמצא ממוצע
                        hourString += other.getPurchaseHour();
                    }

                }


            }


        }


        return resultList;
    }



}
