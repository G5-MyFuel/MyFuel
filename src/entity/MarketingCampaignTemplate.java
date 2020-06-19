package entity;

import java.sql.Time;
/**
 * Marketing Campaign Template class
 *
 * @see MarketingCampaignTemplate - the form's entity class
 */
public class MarketingCampaignTemplate {

    private String templateID;
    private String templateName;
    private String fuelType;
    private float discountPercentages;
    private String day;
    private Time beginHour;
    private Time endHour;

    /**
     * empty constructor
     */
    public MarketingCampaignTemplate(){}

    /**
     * Constructor
     *
     * @param templateID
     * @param templateName
     * @param fuelType
     * @param discountPercentages
     * @param day
     * @param beginHour
     * @param endHour
     */
    public MarketingCampaignTemplate(String templateID, String templateName, String fuelType, String discountPercentages, String day /*,String marketingAdForTemplate*/, Time beginHour, Time endHour) {
        this.templateID = templateID;
        this.templateName = templateName;
        this.fuelType = fuelType;
        this.discountPercentages = Float.parseFloat(discountPercentages);
        this.day = day;
        this.beginHour = beginHour;
        this.endHour = endHour;
    }

    /**
     * get templateID method
     * @return templateID
     */
    public String getTemplateID() {
        return templateID;
    }

    /**
     * set templateID method
     * @param templateID
     */
    public void setTemplateID(String templateID) {
        this.templateID = templateID;
    }

    /**
     * get templateName method
     * @return templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * set templateName method
     * @param templateName
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * get fuelType method
     * @return fuelType
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * set fuelType method
     * @param fuelType
     */
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * get discountPercentages method
     * @return discountPercentages
     */
    public float getDiscountPercentages() {
        return discountPercentages;
    }

    /**
     * set discountPercentages method
     * @param discountPercentages
     */
    public void setDiscountPercentages(float discountPercentages) {
        this.discountPercentages = discountPercentages;
    }

    /**
     * get day method
     * @return day
     */
    public String getDay() {
        return day;
    }

    /**
     * set day method
     * @param day
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     *  get beginHour method
     * @return beginHour
     */
    public Time getBeginHour() {
        return beginHour;
    }

    /**
     * set beginHour method
     * @param beginHour
     */
    public void setBeginHour(Time beginHour) {
        this.beginHour = beginHour;
    }

    /**
     * get endHour method
     * @return endHour
     */
    public Time getEndHour() {
        return endHour;
    }

    /**
     * set endHour method
     * @param endHour
     */
    public void setEndHour(Time endHour) {
        this.endHour = endHour;
    }


}
