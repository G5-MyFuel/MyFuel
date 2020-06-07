package Contollers;

import client.ClientApp;
import common.assets.ReturnMsgType;
import server.MysqlConnection;

public class SettingDiscountRatesController {

    MysqlConnection mySqlConnector;

    /*Logic Variables*/
    private static SettingDiscountRatesController Instance = null;
    //private SettingDiscountRatesController settingDiscountRatesController = SettingDiscountRatesController.getInstance();

    /*Logic Methods*/

    public void getDiscountRatesTable(String query) {
        //(type of message to server, the message) = (requirement,query to get all employees table)
        //ClientApp.chatClient.handleMessageFromClientUI(new Message(OperationType.getRequirementData, (Object) query));
        System.out.println("Hey");
    }

    public static SettingDiscountRatesController getInstance() {
        if (Instance == null)
            Instance = new SettingDiscountRatesController();
        return Instance;
    }
    public void setData(Object object) {
        ReturnMsgType currentPrice = (ReturnMsgType) object;
        System.out.println(ReturnMsgType.values());
        //System.out.println(currentPrice);

    }

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