package client.logic;

public class SettingDiscountRatesLogic {

    /*Logic Variables*/
    private static SettingDiscountRatesLogic Instance = null;

    /*Logic Methods*/

    public static SettingDiscountRatesLogic getInstance() {

        if (Instance == null)
            Instance = new SettingDiscountRatesLogic();
        return Instance;
    }


}
