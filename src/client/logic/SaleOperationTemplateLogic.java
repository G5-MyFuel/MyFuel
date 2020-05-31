package client.logic;

import common.entity.SaleOperation;

public class SaleOperationTemplateLogic {
    private static SaleOperationTemplateLogic Instance = null;
    public static SaleOperationTemplateLogic getInstance() {
        if(Instance == null)
            Instance = new SaleOperationTemplateLogic();
        return Instance;
    }
}
