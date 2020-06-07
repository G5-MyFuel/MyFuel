package boundary;

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

public class pymentWindowControllerForRegistarBoundary implements Initializable {

    private CustomerRegistrationBoundary CRC;
    private static pymentWindowControllerForRegistarBoundary Instance = null;
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
    public static pymentWindowControllerForRegistarBoundary getInstance() {
        if (Instance == null)
            Instance = new pymentWindowControllerForRegistarBoundary();
        return Instance;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        payButton.setText("Add Card");
    }

    public void addCardOnClick(MouseEvent event) throws IOException {

    }


    public CreditCard getTempCreditCard() {
        return this.tempCreditCard;
    }
}
