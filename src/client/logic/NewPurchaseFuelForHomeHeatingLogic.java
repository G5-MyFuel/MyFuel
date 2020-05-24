package client.logic;

import org.apache.commons.validator.routines.EmailValidator;

public class NewPurchaseFuelForHomeHeatingLogic {
    private static NewPurchaseFuelForHomeHeatingLogic Instance = null;

    public static NewPurchaseFuelForHomeHeatingLogic getInstance() {
        if (Instance == null)
            Instance = new NewPurchaseFuelForHomeHeatingLogic();
        return Instance;
    }

    public boolean isValidFuelQty(double fuelAmount){
        if(fuelAmount<=0){
            System.out.println("The amount of fuel must be greater than 0");
            return false;
        }
        return true;
    }

    public boolean isValidEmail(String email) {
        // create the EmailValidator instance
        EmailValidator validator = EmailValidator.getInstance();
        // check for valid email addresses using isValid method
        return validator.isValid(email);
    }


}
