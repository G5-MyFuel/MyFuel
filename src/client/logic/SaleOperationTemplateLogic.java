package client.logic;

/**
 * @author hani
 */
public class SaleOperationTemplateLogic {
    private static SaleOperationTemplateLogic Instance = null;
    /**
     * SaleOperationTemplateLogic Instance getter using SingleTone DesignPatterns
     * @return Instance of logic class
     */
    public SaleOperationTemplateLogic getInstance() {
        if (Instance == null)
            Instance = new SaleOperationTemplateLogic();
        return Instance;
    }

}
