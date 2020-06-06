package client;

import client.boundary.LoginToSystemController;
import client.boundary.OrderExecutionController;
import client.boundary.SettingDiscountRatesController;
import client.logic.CustomerRegistrationLogic;
import client.logic.LoginToSystemLogic;
import client.logic.OrderFromSupplierLogic;
import client.logic.SettingDiscountRatesLogic;
import common.entity.User;
import common.tools.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * get <Code> Message </code> from server and send it to the relevant
 * controller. Usually used for transfer data from db to client.
 *
 * @author daniel
 */
public class ClientMessages {

    /***
     * messageFromServer - The next method receives 1 Object and , divides all
     * actions into cases according to Operationtype and use the Object to perform
     * the operation and after send a msg to the client with the appropriate details
     */

    public static void messageFromServer(Message msg) throws SQLException {
        Message m = msg;
        ResultSet rs;



        switch (m.getOperationType()) {
            case getRequirementData:

               // EmployeesManagementGuiController.Instance.setDataTable(m.getObject())


                SettingDiscountRatesLogic.getInstance().setData(m.getReturnMsgType());
                break;
            case updateRequirement:
                //EmployeesManagementGuiController.Instance.afterUpdateJobTitleInDb(m.getObject());
                break;
            case getAllUsersTable:
                ArrayList<User> alu = (ArrayList<User>)m.getObject();
                LoginToSystemLogic.getInstance().setUsersArrayList(alu);
               // LoginToSystemController.getInstance().setUsersDetailsArrayList(m.getObject());
                break;

            default:
                break;

        }
    }
}// class