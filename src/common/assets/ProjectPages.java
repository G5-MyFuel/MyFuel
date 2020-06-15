package common.assets;

/**
 * Enum that represents all our project boundaries
 *
 * @author Itay Ziv
 */
public enum ProjectPages {

    SERVER_START_PAGE("/server/serverGUI.fxml"),
    CLIENT_CONNECT_TO_SERVER_PAGE("/boundary/ClientConnectionToServerFXML.fxml"),
    CONFIRM_DISCOUNT_RATE_PAGE("/boundary/ConfirmDiscountRatesFXML.fxml"),
    COSTUMER_MANAGEMENT_TABLE_PAGE("/boundary/CostumerManagementTablePage.fxml"),
    COSTUMER_REGISTRATION_PAGE("/boundary/CustomerRegistrationFXML2.fxml"),
    CREDIT_CARD_DIALOG_PAGE("/boundary/CreditCardDialogWindow.fxml"),
    GENERAL_DASH_BOARD("/boundary/generalDashBoard.fxml"),


    //hani pages:
    SALE_OPERATION_TEMPLATE_PAGE("/boundary/MarketingCampaignTemplate.fxml"),
    RUN_SALE_OPERATION_PAGE("/boundary/MarketingCampaignFXML.fxml"),
    VIEW_ANALITIC_DATA("/boundary/ViewAnalyticDataFXML.fxml"),
    //Nir pages:
    SETTING_DISCOUNT_RATES_PAGE("/boundary/SettingDiscountRatesFXML.fxml"),
    CONFIRM_DISCOUNT_RATES_PAGE("/boundary/ConfirmDiscountRatesFXML.fxml"),
    GENERATING_REPORTS_STATION_MANAGER_PAGE("/boundary/GeneratingReportsStationManagerFXML.fxml"),
    GENERATING_REPORTS_MARKETING_MANAGER_PAGE("/boundary/GeneratingReportsMarketingManagerFXML.fxml"),

    //Daniel pages:
    //PURCHASE_FUEL_FOR_HOME_HEATING,TRACKING_FUEL_FOR_HOME_HEATING,
    FAST_FUEL_PAGE("/boundary/CustomerRegistrationFXML2.fxml"),
    PURCHASE_FUEL_FOR_HOME_HEATING("/boundary/NewPurchaseFuelForHomeHeatingFXML.fxml"),
    LOGIN_PAGE("/boundary/LoginToSystemFXML.fxml");

    private String path;

    private ProjectPages(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}