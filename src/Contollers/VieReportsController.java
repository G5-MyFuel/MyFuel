package Contollers;

import boundary.VieReportsBoundary;
import common.assets.SqlResult;

public class VieReportsController extends BasicController{

    /**
     * The boundary controlled by this controller
     */
    private final VieReportsBoundary myBoundary;

    /**
     * Instantiates a new Costumer Management controller.
     *
     * @param myBoundary the my boundary
     */
    public VieReportsController(VieReportsBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

    @Override
    public void getResultFromClient(SqlResult result) {

    }
}