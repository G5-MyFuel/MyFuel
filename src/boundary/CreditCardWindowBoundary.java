package boundary;

import Contollers.CreditCardController;
import Contollers.FormValidation;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entity.CreditCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The Class Credit Card Window Boundary.
 *
 * @author Itay Ziv
 * @see CreditCardWindowBoundary - the form's Boundary class
 */

public class CreditCardWindowBoundary implements DataInitializable {

    private CreditCard tempCreditCard = new CreditCard();
    private Alert ErrorAlert = new Alert(Alert.AlertType.ERROR);
    private FormValidation formValidation;
    private Stage primStage;

    /**
     * The supervisor boundary controller.
     */
    private CreditCardController myController = new CreditCardController(this);
    private CustomerRegistrationBoundary registrationBoundary;
    private CostumerManagmentTablePageBoundary managmentBoundary;
    private String experationDate;

    @FXML
    private JFXTextField creditCardNumbertxt;

    @FXML
    private JFXComboBox<String> YearCombo;

    @FXML
    private JFXComboBox<String> MonthCombo;

    @FXML
    private JFXTextField CVVtxt;

    @FXML
    private Button addCardBtn;

    /**
     * initialize the observable items for the card expiration dates.
     */
    private ObservableList<String> Years = FXCollections.observableArrayList("21", "22", "23", "24", "25", "26", "27", "28", "29", "30");
    private ObservableList<String> Month = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

    /**
     * initialize function that star when boundary start.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CVVtxt.clear();
        formValidation = new FormValidation();
        //credit card number check
        formValidation.isOnlyNumbers(creditCardNumbertxt, "Card number");
        formValidation.ExactlyInLengthValidation(creditCardNumbertxt, "Card number", 16);
        //cvv
        formValidation.ExactlyInLengthValidation(CVVtxt, "CVV", 3);
        formValidation.isOnlyNumbers(CVVtxt, "CVV");


    }

    /**
     * validation for card fields.
     * @return boolean
     */
    private boolean validateCardFields() {
        if (formValidation.isOnlyNumbers() && formValidation.isExactlyInLength())
            return true;
        else
            return false;
    }

    /**
     * this method adds the credit card to db if all
     * inputs are valid.
     * @param event
     */
    @FXML
    public void addCardOnClick(MouseEvent event) {
        Stage primStage = (Stage) addCardBtn.getScene().getWindow();
        /*validate card and insert it to db
         * */
        //Update process
        if (formValidation.isEmptyField()) { //validate empty fields
            ErrorAlert.setTitle("Credit Card Fields Error");
            ErrorAlert.setHeaderText("Please insert all Credit Card Information.");
            ErrorAlert.showAndWait();
        } else if (!validateCardFields()) { //validate if fields are correct
            ErrorAlert.setTitle("Fields Error");
            ErrorAlert.setHeaderText("Please make sure all fields are correct.");
            ErrorAlert.showAndWait();
        } else {
            experationDate = "20" + YearCombo.getValue() + "-" + MonthCombo.getValue();
            tempCreditCard.setCardNumber(creditCardNumbertxt.getText());
            tempCreditCard.setExperationDate(experationDate);
            tempCreditCard.setCardSecurityNumber(CVVtxt.getText());
            if (registrationBoundary != null) {
                registrationBoundary.setTempCreditCard(tempCreditCard);
                registrationBoundary.setCardClickFlag(true);
                primStage.close();
            } else {
                tempCreditCard.setCardOwner(managmentBoundary.getCos());
                myController.updateCostumerCreditCardInDb(tempCreditCard);
                managmentBoundary.refreshTable();
                primStage.close();
            }
        }

    }


    public CreditCard getTempCreditCard() {
        return this.tempCreditCard;
    }

    /**
     * initData this will start in the initialize of the boundary.
     * sends parameters from anther pages
     * @param data - The data sent to the boundary
     */
    @Override
    public void initData(Object data) {
        YearCombo.setItems(Years);
        MonthCombo.setItems(Month);
        primStage = (Stage) creditCardNumbertxt.getScene().getWindow();
        primStage.setTitle("Credit Card Window");

        if (data instanceof CostumerManagmentTablePageBoundary) {
            managmentBoundary = (CostumerManagmentTablePageBoundary) data;
            if (managmentBoundary.getCos().getCostumerCreditCard().getCardNumber().equals("No Card Exists")) {
                creditCardNumbertxt.setText("Add new CreditCard");
            } else {
                creditCardNumbertxt.setText(managmentBoundary.getCos().getCostumerCreditCard().getCardNumber());
                experationDate = managmentBoundary.getCos().getCostumerCreditCard().getExperationDate();
                CVVtxt.setText(managmentBoundary.getCos().getCostumerCreditCard().getCardSecurityNumber());
            }
        }

        if (data instanceof CustomerRegistrationBoundary) {
            registrationBoundary = (CustomerRegistrationBoundary) data;
        }

        primStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            /**
             * this function handle the request to close the page.
             * simply ask user if he is sure.
             * @param e
             */
            @Override
            public void handle(WindowEvent e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getButtonTypes().remove(ButtonType.OK);
                alert.getButtonTypes().add(ButtonType.CANCEL);
                alert.getButtonTypes().add(ButtonType.YES);
                alert.setTitle("Quit application");
                alert.setContentText(String.format("Are you sure you want to quit?\nBy clicking YES all new inserted data will be lost"));
                Optional<ButtonType> res = alert.showAndWait();
                if (res.get().equals(ButtonType.CANCEL))
                    e.consume();
                else
                    primStage.close();
            }
        });


    }


}
