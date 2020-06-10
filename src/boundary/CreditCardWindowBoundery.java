package boundary;

import Contollers.CostumerManagementController;
import com.jfoenix.controls.JFXTextField;
import entity.CreditCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreditCardWindowBoundery implements Initializable {

    private CustomerRegistrationBoundary CRC;
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




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        payButton.setText("Add Card");
    }

    public void addCardOnClick(MouseEvent event) {

    }


    public CreditCard getTempCreditCard() {
        return this.tempCreditCard;
    }
}
