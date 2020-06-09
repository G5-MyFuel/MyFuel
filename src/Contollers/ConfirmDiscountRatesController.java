package Contollers;

import boundary.ConfirmDiscountRatesBoundary;
import common.assets.SqlResult;

public class ConfirmDiscountRatesController extends BasicController {

    /**
     * The boundary controlled by this controller
     */
    private final ConfirmDiscountRatesBoundary myBoundary;

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public ConfirmDiscountRatesController(ConfirmDiscountRatesBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /*Logic Methods*/

    @Override
    public void getResultFromClient(SqlResult result) {

    }

}