package Contollers;

import boundary.GeneratingReportsMarketingManagerBoundary;
import common.assets.SqlResult;

public class GeneratingReportsMarketingManagerController extends BasicController {

    /**
     * The boundary controlled by this controller
     */
    private final GeneratingReportsMarketingManagerBoundary myBoundary;

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public GeneratingReportsMarketingManagerController(GeneratingReportsMarketingManagerBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    /*Logic Methods*/

    @Override
    public void getResultFromClient(SqlResult result) {

    }

}