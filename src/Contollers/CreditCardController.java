package Contollers;

import boundary.CostumerManagmentTablePageBoundary;
import boundary.CreditCardWindowBoundery;
import common.assets.SqlResult;

public class CreditCardController extends BasicController{


    private CreditCardWindowBoundery myBoundary;

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public CreditCardController(CreditCardWindowBoundery myBoundary) {
        this.myBoundary = myBoundary;
    }












    @Override
    public void getResultFromClient(SqlResult result) {

    }
}
