package client.boundary;

import client.logic.CustomerRegistrationLogic;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class pymentWindowController implements Initializable {


    private static pymentWindowController Instance = null;
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
    public static pymentWindowController getInstance() {
        if (Instance == null)
            Instance = new pymentWindowController();
        return Instance;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        payButton.setVisible(false);
    }

    public void addCard(MouseEvent event){

    }

}