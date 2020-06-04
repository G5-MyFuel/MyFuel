package client.boundary;

import client.logic.CustomerRegistrationLogic;
import com.jfoenix.controls.JFXTextField;
import common.entity.CreditCard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class pymentWindowControllerForRegistar implements Initializable {

    private CustomerRegistrationController CRC;
    private static pymentWindowControllerForRegistar Instance = null;
    private CreditCard tempCreditCard;
    @FXML
    private JFXTextField creditCardNumbertxt;

    @FXML
    private JFXTextField experationDatetxt;

    @FXML
    private JFXTextField CVVtxt;

    @FXML
    private ImageView addCardButton;

    @FXML
    public Button payButton;

    /**
     * CustomerRegistrationFXML1Logic Instance getter using SingleTone DesignPatterns
     * @return Instance of logic class
     */
    public static pymentWindowControllerForRegistar getInstance() {
        if (Instance == null)
            Instance = new pymentWindowControllerForRegistar();
        return Instance;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        payButton.setText("Add Card");
        CRC = CRC.getInstance();
    }

    public void addCardOnClick(MouseEvent event) throws IOException {
        tempCreditCard = new CreditCard(null,creditCardNumbertxt.getText(),experationDatetxt.getText(),CVVtxt.getText());
        CRC.setTempCreditCard(tempCreditCard);
        // get a handle to the stage
        Stage stage = (Stage) payButton.getScene().getWindow();
        stage.close();
    }
    public void init(CustomerRegistrationController CrController) {
        CRC = CrController;
    }

    public CreditCard getTempCreditCard() {
        return this.tempCreditCard;
    }
}
