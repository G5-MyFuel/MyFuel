package Contollers;

import boundary.CreditCardWindowBoundary;
import common.assets.SqlAction;
import common.assets.SqlQueryType;
import common.assets.SqlResult;
import entity.CreditCard;

import java.util.ArrayList;

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
        varArray.add(card.getCardOwner().getID());
        SqlAction sqlAction = new SqlAction(SqlQueryType.UPDATE_COSTUMER_CREDIT_CARD, varArray);
        super.sendSqlActionToClient(sqlAction);
    }


    @Override
    public void getResultFromClient(SqlResult result) {

    }
}
