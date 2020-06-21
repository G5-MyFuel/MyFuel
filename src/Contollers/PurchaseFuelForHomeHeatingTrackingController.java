package Contollers;

import boundary.PurchaseFuelForHomeHeatingTrackingBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.HomeHeatingOrderTracking;
import entity.OrderDeliveryStatus;
import javafx.application.Platform;

import java.sql.Date;
import java.util.ArrayList;

public class PurchaseFuelForHomeHeatingTrackingController extends BasicController {
    private PurchaseFuelForHomeHeatingTrackingBoundary myBoundary;

    public PurchaseFuelForHomeHeatingTrackingController(PurchaseFuelForHomeHeatingTrackingBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            try {
                switch (result.getActionType()) {
                    case GET_CUSTOMER_PFH_TABLE:
                        ArrayList<HomeHeatingOrderTracking> arrayListOfCustomerOrders = new ArrayList<>();
                        arrayListOfCustomerOrders = fromResultSetToCustomers(result);
                        System.out.println(arrayListOfCustomerOrders);
                        myBoundary.setArrayListOfCustomerOrders(arrayListOfCustomerOrders);
                        break;
                }
            } catch (NullPointerException npe) {
            }
        });
    }

    public void PURCHASE_FUEL_FOR_HOME_HEATING_TRACKING_fromDB(ArrayList<Object> varArray) {
        SqlAction sqlAction2 = new SqlAction(SqlQueryType.GET_CUSTOMER_PFH_TABLE, varArray);
        super.sendSqlActionToClient(sqlAction2);
    }


//    private ArrayList<String[]> fromResultSetToCustomers(SqlResult result) {
//        ArrayList<String[]> res = new ArrayList<>();
//        String[] resin = new String[4];
//        for (ArrayList<Object> a : result.getResultData()) {
//            Date tempDate = (Date)a.get(0);
//            resin[0]= tempDate.toString();
//            for (int i = 1; i < 4; i++)
//                resin[i] = (String) a.get(i);
//            res.add(resin);
//        }
//        return res;
//    }

    private ArrayList<HomeHeatingOrderTracking> fromResultSetToCustomers(SqlResult result) {
        ArrayList<HomeHeatingOrderTracking> res = new ArrayList<>();
        HomeHeatingOrderTracking pfh;
        for (ArrayList<Object> a : result.getResultData()) {
            Date tempDate = (Date)a.get(0);
            pfh = new HomeHeatingOrderTracking(tempDate.toString(),(String)a.get(1),(String)a.get(2),(String)a.get(3));
            res.add(pfh);
        }
        return res;
    }

}
