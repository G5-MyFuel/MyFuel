package Contollers;

import boundary.ManagerSupplyConfirmationBoundary;
import boundary.OrderExecutionBoundary;

/**
 * @author Adi Lampert
 * @see ManagerSupplyConfirmationController - the form's logic class
 */
public class ManagerSupplyConfirmationController {

    private ManagerSupplyConfirmationBoundary myBoundary;
    public ManagerSupplyConfirmationController(ManagerSupplyConfirmationBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }


}
