package entity;

import java.sql.Time;

public class MarketingCampaignTemplate {
    private String templateID;
    private String templateName;
    private String fuelType;
    private float discountPercentages;
    private String day;
    //private String marketingAdForTemplate;
    private Time beginHour;
    private Time endHour;

    public MarketingCampaignTemplate(){}

    public MarketingCampaignTemplate(String templateID, String templateName, String fuelType, String discountPercentages, String day /*,String marketingAdForTemplate*/, Time beginHour, Time endHour) {
        this.templateID = templateID;
        this.templateName = templateName;
        this.fuelType = fuelType;
        this.discountPercentages = Float.parseFloat(discountPercentages);
        this.day = day;
       // this.marketingAdForTemplate = marketingAdForTemplate;
        this.beginHour = beginHour;
        this.endHour = endHour;
    }

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

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public float getDiscountPercentages() {
        return discountPercentages;
    }

    public void setDiscountPercentages(float discountPercentages) {
        this.discountPercentages = discountPercentages;
    }
/*
    public String getMarketingAdForTemplate() {
        return marketingAdForTemplate;
    }

    public void setMarketingAdForTemplate(String marketingAdForTemplate) {
        this.marketingAdForTemplate = marketingAdForTemplate;
    }
*/
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
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
