package client.logic;

import common.entity.SaleOperationTemplate;

import java.util.ArrayList;

/**
 * @author hani
 */
public class SaleOperationTemplateLogic {
    private static SaleOperationTemplateLogic Instance = null;
    /**
     * SaleOperationTemplateLogic Instance getter using SingleTone DesignPatterns
     * @return Instance of logic class
     */
    public static SaleOperationTemplateLogic getInstance() {
        if (Instance == null)
            Instance = new SaleOperationTemplateLogic();
        return Instance;
    }
    
/*
    public void setTemplatesArrayList(ArrayList<SaleOperationTemplate> object) {
    }

    public Object getTemplatesArrayList() {
    }
    */
}
