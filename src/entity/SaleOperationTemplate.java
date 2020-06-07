package entity;

import java.sql.Time;

public class SaleOperationTemplate {
    private String templateID;
    private String templateName;
    private FuelTypes fuelType;
    private float DiscountPercentages;
    private String MarketingAdForTemplate;
    private Day day;
    private Time beginHour;
    private Time endHour;

    public String getTemplateID() {
        return templateID;
    }

    public void setTemplateID(String templateID) {
        this.templateID = templateID;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public FuelTypes getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelTypes fuelType) {
        this.fuelType = fuelType;
    }

    public float getDiscountPercentages() {
        return DiscountPercentages;
    }

    public void setDiscountPercentages(float discountPercentages) {
        DiscountPercentages = discountPercentages;
    }

    public String getMarketingAdForTemplate() {
        return MarketingAdForTemplate;
    }

    public void setMarketingAdForTemplate(String marketingAdForTemplate) {
        MarketingAdForTemplate = marketingAdForTemplate;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Time getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(Time beginHour) {
        this.beginHour = beginHour;
    }

    public Time getEndHour() {
        return endHour;
    }

    public void setEndHour(Time endHour) {
        this.endHour = endHour;
    }



}
