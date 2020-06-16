package Contollers;

import boundary.*;
import common.assets.SqlResult;
import entity.Rating;
import javafx.application.Platform;

import java.util.ArrayList;

public class GeneralDashBoardController {
    private generalDashBoardBoundary myBoundary; /**     * The boundary controlled by this controller     */


    public GeneralDashBoardController(generalDashBoardBoundary myBoundary) {
        this.myBoundary = myBoundary;
    }

}
