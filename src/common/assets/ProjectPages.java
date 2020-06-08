package common.assets;

/**
 * Enum that represents all our project boundaries
 * @author Itay Ziv
 *
 */
public enum ProjectPages {

    SERVER_START_PAGE("/server/serverGUI.fxml"),
    CLIENT_CONNECT_TO_SERVER_PAGE("/boundary/ClientConnectionToServerFXML.fxml"),
    CONFIRM_DISCOUNT_RATE_PAGE("/boundary/ConfirmDiscountRatesFXML.fxml"),
    COSTUMER_MANAGEMENT_TABLE_PAGE("/boundary/CostumerManagementTablePage.fxml"),
    COSTUMER_REGISTRATION_PAGE("/boundary/CustomerRegistrationFXML2.fxml"),
    FAST_FUEL_PAGE("/boundary/CustomerRegistrationFXML2.fxml"),


    //hani pages:
    SALE_OPERATION_TEMPLATE_PAGE("/boundary/SaleOperationTemplate.fxml"),

    //Nir pages:
    SETTING_DISCOUNT_RATES_PAGE("/boundary/SettingDiscountRatesFXML.fxml");


    private String path;

    private ProjectPages(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
