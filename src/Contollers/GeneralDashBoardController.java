package Contollers;

import boundary.*;

public class GeneralDashBoardController {
    private generalDashBoardBoundary myBoundary; /**     * The boundary controlled by this controller     */

    /*Logic Variables*/
    private static ViewAnalyticDataController Instance = null;

    public GeneralDashBoardController(generalDashBoardBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }


}
