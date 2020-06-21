package boundary;

import Contollers.FormValidation;
import Contollers.SettingDiscountRatesController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SettingDiscountRatesBoundary implements DataInitializable {

    String managerID;
    String managerCompany;
    String managerStation;
    /**
     * The supervisor boundary controller.
     */
    private final SettingDiscountRatesController myController = new SettingDiscountRatesController(this);
    private FormValidation formValidation;
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private JFXComboBox<String> ChooseSubscriptionTypeCombo;

    @FXML
    private JFXTextField ShowCurrentRateTXT;

    @FXML
    private Button btnSetNewRate;

    @FXML
    private JFXTextField ShowNewRateTXT;

    @FXML
    private Label RequestSentMessageLabel;

    private final ObservableList<String> SubscriptionType = FXCollections.observableArrayList("Regular monthly subscription (single)",
            "Full monthly subscription", "Regular monthly subscription (multiple)");

    @Override
    public void initData(Object data) {

        this.managerID = (String) data;
        //managerID = "109268386";
        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Get Manager data");
        paramArray.add(managerID);
        myController.getDiscountRatesTable(paramArray); //start the process that will ask server to execute quarry and get the table details
        this.formValidation = new FormValidation();
        ChooseSubscriptionTypeCombo.setItems(SubscriptionType);
        ShowCurrentRateTXT.setVisible(false);
        ShowNewRateTXT.setVisible(false);
        btnSetNewRate.setVisible(false);
        RequestSentMessageLabel.setVisible(false);

        /*  set all fields validators */
        formValidation();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //managerID = "109268386";
        /*ArrayList<String> paramArray = new ArrayList<>();
        System.out.println(managerID);
        paramArray.add("Get Manager data");
        paramArray.add(managerID);
        myController.getDiscountRatesTable(paramArray); //start the process that will ask server to execute quarry and get the table details
        this.formValidation = new FormValidation();
        ChooseSubscriptionTypeCombo.setItems(SubscriptionType);
        ShowCurrentRateTXT.setVisible(false);
        ShowNewRateTXT.setVisible(false);
        btnSetNewRate.setVisible(false);
        RequestSentMessageLabel.setVisible(false);*/

        /*  set all fields validators */
        //formValidation();
    }

    private void formValidation() {

        /*  New price validation */

        //formValidation.isContainsOnlyNumbers(ShowNewRateTXT, "New price");
        //formValidation.numberPositiveValidation(ShowNewRateTXT, "New price");
        //formValidation.maxLengthValidation(ShowNewRateTXT, "New price", 3);
        /*formValidation.isEmptyFieldValidation(ShowNewRateTXT, "New price");
        formValidation.maxFloatSizeValidation(ShowNewRateTXT, "New price", 100);
        formValidation.minFloatSizeValidation(ShowNewRateTXT, "New price", 0);*/
    }

    public void setManagerData(ArrayList<String> resultList) {

        managerCompany = resultList.get(0);
        managerStation = resultList.get(1);
    }

    @FXML
    void handleChoseSubscriptionType(ActionEvent event) {

        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add(ChooseSubscriptionTypeCombo.getValue());
        paramArray.add(managerCompany);
        myController.getDiscountRatesTable(paramArray); //start the process that will ask server to execute quarry and get the table details
        ShowNewRateTXT.clear();
        ShowCurrentRateTXT.setVisible(true);
        ShowCurrentRateTXT.setEditable(false);
        ShowNewRateTXT.setVisible(true);
        btnSetNewRate.setVisible(true);
        RequestSentMessageLabel.setVisible(false);
    }

    public void setData(String currentRate) {
        ShowCurrentRateTXT.setText(currentRate + "%");
    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void handleSetNewRate(ActionEvent event) {

        RequestSentMessageLabel.setVisible(false);
        if (ShowNewRateTXT.getText().equals("")) {
            ErrorAlert.setTitle("Price rate ERROR");
            ErrorAlert.setHeaderText("Please insert between 0-100");
            ErrorAlert.showAndWait();
        }
        else{
            Float NewRate = Float.parseFloat(ShowNewRateTXT.getText());
            ArrayList<String> paramArray = new ArrayList<>();
            paramArray.add("Insert NewRate");
            paramArray.add(ShowNewRateTXT.getText());
            paramArray.add(ChooseSubscriptionTypeCombo.getValue());
            paramArray.add(managerCompany);
            myController.getDiscountRatesTable(paramArray); //start the process that will ask server to execute quarry and get the table details
            ShowNewRateTXT.clear();
            RequestSentMessageLabel.setVisible(true);
        }
        //Float NewRate = Float.parseFloat(ShowNewRateTXT.getText());
        /*if(ShowNewRateTXT.getText().equals(""))
            btnSetNewRate.setVisible(false);
        else
            btnSetNewRate.setVisible(true);*/
        /*if ((NewRate < 0 || NewRate > 100)|| (ShowNewRateTXT.getText().equals("")) ) {
            ErrorAlert.setTitle("Price rate ERROR");
            ErrorAlert.setHeaderText("Please insert between 0-100");
            ErrorAlert.showAndWait();
        } else {
            ArrayList<String> paramArray = new ArrayList<>();
            paramArray.add("Insert NewRate");
            paramArray.add(ShowNewRateTXT.getText());
            paramArray.add(ChooseSubscriptionTypeCombo.getValue());
            paramArray.add(managerCompany);
            myController.getDiscountRatesTable(paramArray); //start the process that will ask server to execute quarry and get the table details
            ShowNewRateTXT.clear();
            RequestSentMessageLabel.setVisible(true);
        }*/
    }

    @FXML
    void handleShowCurrentRate(ActionEvent event) {

    }

    @FXML
    void handleShowNewRate(ActionEvent event) {

    }
}