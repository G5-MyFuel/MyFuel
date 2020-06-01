package client.logic;

public class RunSaleOperationLogic {

    private static RunSaleOperationLogic Instance = null;
    /**
     * SaleOperationTemplateLogic Instance getter using SingleTone DesignPatterns
     * @return Instance of logic class
     */
    public static RunSaleOperationLogic getInstance() {
        if (Instance == null)
            Instance = new RunSaleOperationLogic();
        return Instance;
    }
}
