package client.logic;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

public class NewPurchaseFuelForHomeHeatingLogic {
    /*Logic Variables*/
    private static NewPurchaseFuelForHomeHeatingLogic Instance = null;

    /*Logic Methods*/

    /**
     * NewPurchaseFuelForHomeHeatingLogic Instance getter using SingleTone DesignPatterns
     * @return Instance of logic class
     */
    public static NewPurchaseFuelForHomeHeatingLogic getInstance() {
        if (Instance == null)
            Instance = new NewPurchaseFuelForHomeHeatingLogic();
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
