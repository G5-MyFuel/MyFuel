package Contollers;

import boundary.CostumerManagmentTablePageBoundary;
import boundary.SaleOperationTemplateBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.Day;
import entity.FuelTypes;
import entity.SaleOperationTemplate;
import javafx.application.Platform;
import server.MysqlConnection;

import java.sql.Time;
import java.util.ArrayList;

/**
 * @author Hana Wiener
 */

public class SaleOperationTemplateController extends BasicController  {
    /**
     * The boundary controlled by this controller
     */
    private SaleOperationTemplateBoundary myBoundary;

    /**
     * Instantiates a new Template Management controller.
     *
     * @param myBoundary the my boundary
     */
    public SaleOperationTemplateController(SaleOperationTemplateBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }


    public void getCostumerTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_COSTUMER_TABLE);
        super.sendSqlActionToClient(sqlAction);
    }


    public void getTemplatesTable() {
        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_TEMPLATES_TABLE);
        super.sendSqlActionToClient(sqlAction);

    }


    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_TEMPLATES_TABLE:
                    ArrayList<SaleOperationTemplate> resultList = new ArrayList<>();
                    resultList.addAll(this.changeResultToTemplate(result));
                    myBoundary.setTemplateTable(resultList);
                    break;

                default:
                    break;
            }
        });
    }
    /**
     * This method create array list of templates from the data base result.
     *
     * @param result the result
     * @return Array list of costumers
     */
    private ArrayList<SaleOperationTemplate> changeResultToTemplate(SqlResult result){
        ArrayList<SaleOperationTemplate> resultList = new ArrayList<>();
        for(ArrayList<Object> a: result.getResultData()) {
            SaleOperationTemplate cos = new SaleOperationTemplate((String) a.get(0),(String)a.get(1),(String) a.get(2),(String) a.get(3),(String) a.get(4),
                    null,null);
            cos.setBeginHour(Time.valueOf((String) a.get(5)));
            cos.setEndHour(Time.valueOf((String) a.get(6)));
            resultList.add(cos);
        }

        return resultList;
    }
}