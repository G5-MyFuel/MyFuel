package Contollers;

import boundary.fuelManagmentBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.GasStation;
import javafx.application.Platform;

import java.util.ArrayList;

public class fuelManagmentController extends BasicController {


    private fuelManagmentBoundary myBoundary;

    public fuelManagmentController(fuelManagmentBoundary myBoundary){
        this.myBoundary = myBoundary;
    }
    public void getAllManagerStations(String managerID){
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(managerID);
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_MANAGER_STATIONS, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    public void updateFuelLimit(Double amount , Integer stationNumber){
        ArrayList<Object> varArray = new ArrayList<>();
        varArray.add(amount);
        varArray.add(stationNumber);
        SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_FUEL_LIMIT_STOCK, varArray);
        super.sendSqlActionToClient(sqlAction);
    }

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_MANAGER_STATIONS:
                    myBoundary.setGasStations(this.changeResultToGasStation(result));
                    break;
                case UPDATE_FUEL_LIMIT_STOCK:
                    myBoundary.stockLimitHasBeenUpdate();
                default:
                    break;
            }
        });

    }

    private ArrayList<GasStation> changeResultToGasStation(SqlResult result){
        ArrayList<GasStation> resultList = new ArrayList<>();
        for (ArrayList<Object> a : result.getResultData()) {
            GasStation station = new GasStation((Integer)a.get(0),(String)a.get(1),(String)a.get(2),(String)a.get(3),(String)a.get(4),(String)a.get(5),(String)a.get(6),(Double) a.get(7));
            resultList.add(station);
        }
        return resultList;
    }
}
