package controller;

import boundary.*;
import com.sun.jndi.toolkit.url.UrlUtil;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
//itay commit test

public class loginPageController extends Application {
    private BorderPane root;
    private LoginPageBoundary loginPageBoundary;
    private TextField userIDTextField;
    private PasswordField passwordField;
    private Button loginBtn;
    private ChoiceBox<String> loginAsChoiceBox;
    private ObservableList<String> userTypes = FXCollections.observableArrayList("Client", "Worker");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginPageBoundary.class.getResource("loginPageInterface.fxml"));
            root = loader.load();
            loginPageBoundary = loader.getController();
            //
            setAllControllers();
            loginBtnOperations();
            //
            Scene scene = new Scene(root, 1100, 700);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setAllControllers() {
        userIDTextField = loginPageBoundary.getUserIDTextField();
        passwordField = loginPageBoundary.getPasswordField();
        loginBtn = loginPageBoundary.getLoginBtn();
        loginAsChoiceBox = loginPageBoundary.getLoginAsChoiceBox();
        loginAsChoiceBox.setItems(userTypes);
    }

    public void loginBtnOperations() {
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("LoginBtn Clicked");
                System.out.println("UserId field is: " + userIDTextField.getText());
                System.out.println("The password is:" + passwordField.getText());

                //
                //לממש פונקציה שבודקת אם אחת השדות Null
                if (!checkUserIdValidation()) {
                    //יוצג alertWindow שיגיד שהרכב שם המשתמש שגוי
                } else if (!checkIfUserExistInDB()) {
                    //יוצג חלון שיגיד שהמשתמש לא קיים במערכת
                } else if (!checkIfPasswordCorrect()) {
                    //יוצג חלון שהסיסמה לא תקינה
                }
            }
        });
    }

    //פונקציה שבודקת את תקינות הקלט של שדה מזהה משתמש (9 ספרות)
    public boolean checkUserIdValidation() {
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
