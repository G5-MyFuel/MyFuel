package client.logic;

import org.apache.commons.validator.routines.EmailValidator;

public class NewPurchaseFuelForHomeHeatingLogic {
    private static NewPurchaseFuelForHomeHeatingLogic Instance = null;

    public static NewPurchaseFuelForHomeHeatingLogic getInstance() {
        if (Instance == null)
            Instance = new NewPurchaseFuelForHomeHeatingLogic();
        return Instance;
    }


}
