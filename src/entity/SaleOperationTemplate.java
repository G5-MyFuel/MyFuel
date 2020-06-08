package entity;

import java.sql.Time;

public class SaleOperationTemplate {
    private String templateID;
    private String templateName;
    private FuelTypes fuelType;
    private float discountPercentages;

    public SaleOperationTemplate(String templateID, String templateName, FuelTypes fuelType, float discountPercentages, String marketingAdForTemplate, Day day, Time beginHour, Time endHour) {
        this.templateID = templateID;
        this.templateName = templateName;
        this.fuelType = fuelType;
        this.discountPercentages = discountPercentages;
        this.marketingAdForTemplate = marketingAdForTemplate;
        this.day = day;
        this.beginHour = beginHour;
        this.endHour = endHour;
    }

    private String marketingAdForTemplate;
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
        return discountPercentages;
    }

    public void setDiscountPercentages(float discountPercentages) {
        this.discountPercentages = discountPercentages;
    }

    public String getMarketingAdForTemplate() {
        return marketingAdForTemplate;
    }

    public void setMarketingAdForTemplate(String marketingAdForTemplate) {
        this.marketingAdForTemplate = marketingAdForTemplate;
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
