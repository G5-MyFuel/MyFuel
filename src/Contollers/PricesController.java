package Contollers;

import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;

public class PricesController extends BasicController{




    @Override
    public void getResultFromClient(SqlResult result) {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_PURCHASE_FUEL_AMOUNT_OF_USER);
        super.sendSqlActionToClient(sqlAction);
    }
}
