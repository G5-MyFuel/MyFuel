package client.logic;

/**
 * @author itay
 * @see CustomerRegistrationLogic - the form's logic class
 */

public class CustomerRegistrationLogic {

    /*Logic Variables*/
    private static CustomerRegistrationLogic Instance = null;

    /*Logic Methods*/


    /**
     * CustomerRegistrationFXML1Logic Instance getter using SingleTone DesignPatterns
     * @return Instance of logic class
     */
    public static CustomerRegistrationLogic getInstance() {
        if (Instance == null)
            Instance = new CustomerRegistrationLogic();
        return Instance;
    }

    //~~~~~~~~~~~~~~~~~~~will get details from boundry and set costumer in database ~~~~~~~~~~~~~~~~~~~~~~~~
//    public createCostumer(){
//
//    }



}
