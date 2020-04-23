package controller;

import boundary.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginPageController {
    public LoginPageController() {
    }

    /*return flag:
     * 0: all fields are valid
     * 1: "login as" field is empty
     * 2: "userID field is empty
     * 3: "password field is empty
     * */
    public int checkIfThereAreEmptyFields(LoginPageBoundary lpb) {
        if (lpb.getUserIDTextField().getText().isEmpty()) return 1;
        else if (lpb.getPasswordField().getText().isEmpty()) return 2;
        else if (lpb.getLoginAsChoiceBox().getValue().isEmpty()) return 3;
        return 0;
    }

    //Checks whether the "User ID" consists only 9 digits
    public boolean checkUserIdValidation(LoginPageBoundary lpb) {
        String uidStr = lpb.getUserIDTextField().getText();
        if (!uidStr.matches("[0-9]+")) {
            System.out.println("User ID can only contain numbers");
            return false;
        } else if (uidStr.length() != 9) {
            System.out.println("User ID contain exactly 9 digits!");
            return false;
        }
        return true;
    }

    //פונקציה שבודקת אם מזהה המשתמש נמצא בDB
    public boolean checkIfUserExistInDB() {
        return true;
    }

    //פונקציה שבודקת אם הסיסמה תואמת למזהה המשתמש
    public boolean checkIfPasswordCorrect() {
        return true;
    }
}
