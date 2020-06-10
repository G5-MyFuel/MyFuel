package boundary;

import Contollers.CostumerManagementController;
import Contollers.CreditCardController;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entity.Costumer;
import entity.CreditCard;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreditCardWindowBoundery implements DataInitializable {

    private CreditCard tempCreditCard;
    private Costumer cos;

    /**
     * The supervisor boundary controller.
     */
    private CreditCardController myController = new CreditCardController(this);


    @FXML
    private Pane CreditCardWindow;

    @FXML
    private JFXTextField creditCardNumbertxt;

    @FXML
    private JFXTextField experationDatetxt;

    @FXML
    private JFXTextField CVVtxt;

    @FXML
    private Button addCardBtn;

    @FXML
    private JFXDatePicker experationDatePicker;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


    @FXML
    public void addCardOnClick(MouseEvent event) {
        Stage primStage = (Stage) addCardBtn.getScene().getWindow();
        /*validate card and insert it to db
         * */




        primStage.close();

    }


    public CreditCard getTempCreditCard() {
        return this.tempCreditCard;
    }

    @Override
    public void initData(Object data) {
        Stage primStage = (Stage) creditCardNumbertxt.getScene().getWindow();
        primStage.setTitle("Credit Card Window");
        primStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
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

        cos = (Costumer) data;
        if (cos.getCostumerCreditCard().getCardNumber().equals("No Card Exists")) {
            creditCardNumbertxt.setText("Add new CreditCard");
        } else {
            creditCardNumbertxt.setText(cos.getCostumerCreditCard().getCardNumber());
            experationDatetxt.setText(cos.getCostumerCreditCard().getExperationDate());
            CVVtxt.setText(cos.getCostumerCreditCard().getCardSecurityNumber());
        }

    }


}
