package client.logic;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

public class NewPurchaseFuelForHomeHeatingController {
    /*Logic Variables*/
    private static NewPurchaseFuelForHomeHeatingController Instance = null;

    /*Logic Methods*/

    /**
     * NewPurchaseFuelForHomeHeatingLogic Instance getter using SingleTone DesignPatterns
     * @return Instance of logic class
     */
    public static NewPurchaseFuelForHomeHeatingController getInstance() {
        if (Instance == null)
            Instance = new NewPurchaseFuelForHomeHeatingController();
        return Instance;
    }

    public boolean validatePage(Object guiObj){
        if(guiObj instanceof JFXTextField){
            JFXTextField jfxTextField = (JFXTextField)guiObj;
            if(jfxTextField.getText().isEmpty())
                return false;
            return true;
        }
        if(guiObj instanceof JFXTextArea){
            JFXTextArea jfxTextArea = (JFXTextArea)guiObj;
            if(jfxTextArea.getText().isEmpty())
                return false;
            return true;
        }
        return false;
    }

}
