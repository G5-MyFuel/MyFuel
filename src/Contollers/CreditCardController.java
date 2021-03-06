package Contollers;

import boundary.CreditCardWindowBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.CreditCard;

import java.util.ArrayList;
/**
 * the class Credit Card Controller
 *
 * @author Itay Ziv
 * @see CreditCardController - the form's Controller class
 *
 */
public class CreditCardController extends BasicController {


    private CreditCardWindowBoundary myBoundary;

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public CreditCardController(CreditCardWindowBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /**
     * this method will update the credit card given in the database.
     * @param card
     */
    public void updateCostumerCreditCardInDb(CreditCard card) {
        ArrayList<Object> varArray = new ArrayList<>();
        if (card != null) {
            varArray.add(card.getCardNumber());
            varArray.add(card.getExperationDate());
            varArray.add(card.getCardSecurityNumber());
        } else {
            varArray.add("No Card Exists");
            varArray.add("No Card Exists");
            varArray.add("No Card Exists");
        }
        varArray.add(card.getCardOwner().getUserID());
        SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_COSTUMER_CREDIT_CARD, varArray);
        super.sendSqlActionToClient(sqlAction);
    }


    @Override
    public void getResultFromClient(SqlResult result) {

    }
}
