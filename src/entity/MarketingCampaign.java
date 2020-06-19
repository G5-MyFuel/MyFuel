package entity;


import java.sql.Date; // pay attention that i import the SQL class of date
/**
 * @see MarketingCampaign - the form's entity class
 */
public class MarketingCampaign {
    private String CampaignID;
    private String templateName;
    private Date beginDate;
    private Date endDate;

    public MarketingCampaign(String marketingCampaignID, String templateID , Date beginDate, Date endDate) {
        this.CampaignID = marketingCampaignID;
        this.templateName = templateID;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public MarketingCampaign() {
    }

    public String getCampaignID() {
        return CampaignID;
    }

    public String getTemplateName() {
        return templateName;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public MarketingCampaign getSaleOperation () {return this;}
    public void setCampaignID(String campaignID) {
        this.CampaignID = campaignID;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }


}
