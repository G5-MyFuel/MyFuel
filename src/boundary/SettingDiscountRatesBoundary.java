package boundary;

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

/**
 * This department is responsible for controlling "SettingDiscountRatesFXML" page
 * Allows marketing manager to view current pricing model rates
 * And submit a request to set a new rate
 *
 * @author Nir Asulin
 * @see SettingDiscountRatesController - the form's logic class
 */
public class SettingDiscountRatesBoundary implements DataInitializable {

    /**
     * A parameters that represents who enters the page
     */
    String managerID;
    String managerCompany;
    String managerStation;

    /**
     * The supervisor boundary controller.
     */
    private final SettingDiscountRatesController myController = new SettingDiscountRatesController(this);

    /**
     * For proper validation
     */
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);

    /**
     * Gui variables:
     */
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

    /**
     * This method allows to save information sent when uploading the page (user id)
     * In addition initializes the variables, fields, and combo-boxes
     * What is initialized will appear when the screen is raised
     *
     * @param data - The data sent to the boundary
     */
    @Override
    public void initData(Object data) {

        this.managerID = (String) data;
        ArrayList<String> paramArray = new ArrayList<>();
        paramArray.add("Get Manager data");
        paramArray.add(managerID);
        myController.getDiscountRatesTable(paramArray); //start the process that will ask server to execute quarry and get the table details
        ChooseSubscriptionTypeCombo.setItems(SubscriptionType);
        ShowCurrentRateTXT.setVisible(false);
        ShowNewRateTXT.setVisible(false);
        btnSetNewRate.setVisible(false);
        RequestSentMessageLabel.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * This method will set the Manager Data when we will initialize the page.
     *
     * @param resultList
     */
    public void setManagerData(ArrayList<String> resultList) {

        managerCompany = resultList.get(0);
        managerStation = resultList.get(1);
    }

    /**
     * This method save manager selection Pricing model
     * And sends a query to get the current rate
     *
     * @param event
     */
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

    /**
     * This method will set the current rate.
     *
     * @param currentRate
     */
    public void setData(String currentRate) {
        ShowCurrentRateTXT.setText(currentRate + "%");
    }

    @FXML
    void handleClicks(ActionEvent event) {

    }

    /**
     * This method will Check the validation of the input
     * And Submit a rate approval request to administrator
     *
     * @param event
     */
    @FXML
    void handleSetNewRate(ActionEvent event) {

        RequestSentMessageLabel.setVisible(false);
        if (ShowNewRateTXT.getText().equals("")) {
            ErrorAlert.setTitle("Price rate ERROR");
            ErrorAlert.setHeaderText("Please insert between 0-100");
            ErrorAlert.showAndWait();
        } else {
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
    }

    @FXML
    void handleShowCurrentRate(ActionEvent event) {

    }

    @FXML
    void handleShowNewRate(ActionEvent event) {

    }
}