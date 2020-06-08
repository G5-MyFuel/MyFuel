package Contollers;

import boundary.CostumerManagmentTablePageBoundary;
import boundary.SettingDiscountRatesBoundary;
import client.ClientApp;
import common.assets.ReturnMsgType;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.Costumer;
import entity.CreditCard;
import entity.Vehicle;
import javafx.application.Platform;
import server.MysqlConnection;

import java.util.ArrayList;

public class SettingDiscountRatesController extends BasicController {

    /**
     * The boundary controlled by this controller
     */
    private SettingDiscountRatesBoundary myBoundary;

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public SettingDiscountRatesController(SettingDiscountRatesBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /*Logic Methods*/

    public void getDiscountRatesTable() {

        SqlAction sqlAction = new SqlAction(SqlQueryType.GET_ALL_DiscountRates_TABLE);
        super.sendSqlActionToClient(sqlAction);
        //(type of message to server, the message) = (requirement,query to get all employees table)
        //ClientApp.chatClient.handleMessageFromClientUI(new Message(OperationType.getRequirementData, (Object) query));
        System.out.println("Hey");
    }

    @Override
    public void getResultFromClient(SqlResult result) {
        Platform.runLater(() -> {
            switch (result.getActionType()) {
                case GET_ALL_DiscountRates_TABLE:
                    //ArrayList<Costumer> resultList = new ArrayList<>();
                    float currentRate;
                    currentRate = this.changeResultToFloat(result);
                    myBoundary.setData(currentRate);
                    break;

                default:
                    break;
            }
        });

    }

    /**
     * This method create array list of costumers from the data base result.
     *
     * @param result the result
     * @return Array list of costumers
     */
    private float changeResultToFloat(SqlResult result) {
        float currentRate = 0;
        for (ArrayList<Object> a : result.getResultData()) {
            currentRate = (float) a.get(0);
            /*Costumer cos = new Costumer((Integer) a.get(0), (String)a.get(1),(String)a.get(2),
                    (String)a.get(3),(String)a.get(4),(String)a.get(5),null,(boolean)a.get(9),null,(String)a.get(12));
            CreditCard card = new CreditCard(cos,(String)a.get(6),(String)a.get(7),(String)a.get(8));
            Vehicle vehicle = new Vehicle(cos.getID().toString(),(String)a.get(10),(String)a.get(11)); //here i need to find a way to get all vehicles and not just 1 of them.
            cos.setCostumerCreditCard(card);
            cos.addCostumerVehicle(vehicle);
            resultList.add(cos);*/
        }
        return currentRate;
    }

    /*public static SettingDiscountRatesController getInstance() {
        if (Instance == null)
            Instance = new SettingDiscountRatesController();
        return Instance;
    }*/

}

/*EchoServer:

 handleMessageFromClient:

 System.out.println(rs.getString(1));
 System.out.println(m.getObject().toString());
 System.out.println(m.getReturnMsgType().toString());
 System.out.println(m.getOperationType().toString());
 //sendToClient(new Message(OperationType.getRequirementData, QueryToArrayList.ResultSetToArrayList(rs)), client);
 sendToClient(new Message(OperationType.getRequirementData, QueryToArrayList.RsultSetToFloat(rs)), client);

 */


/*ClientMessages:

messageFromServer:

case getRequirementData:
               // EmployeesManagementGuiController.Instance.setDataTable(m.getObject())
                //OrderExecutionController.Instance.setOrderFuelFromSupplierTableView(m.getObject());
                SettingDiscountRatesLogic.getInstance().setData(m.getReturnMsgType());
                break;
 */


/*QueryToArrayList:

public static Float RsultSetToFloat(ResultSet rs) throws SQLException {
        System.out.println(rs.getString(1));
        Float num = rs.getFloat(1);
        return num;
    }
 */