package client.logic;

public class CustomerRegistrationFXML1Logic {

    /*Logic Variables*/
    private static CustomerRegistrationFXML1Logic Instance = null;

    /*Logic Methods*/


    /**
     * CustomerRegistrationFXML1Logic Instance getter using SingleTone DesignPatterns
     * @return Instance of logic class
     */
    public static CustomerRegistrationFXML1Logic getInstance() {
        if (Instance == null)
            Instance = new CustomerRegistrationFXML1Logic();
        return Instance;
    }

//    public createCostumer(){
//
//    }



}
