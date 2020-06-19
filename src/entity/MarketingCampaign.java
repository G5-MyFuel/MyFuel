package entity;

import java.sql.Date;

/**
 * Marketing Campaign class
 * @see MarketingCampaign - the form's entity class
 * @author Hana Wiener
 */
public class MarketingCampaign {
    private String CampaignID;
    private String templateName;
    private Date beginDate;
    private Date endDate;

    /**
     * Constructor
     *
     * @param marketingCampaignID
     * @param templateID
     * @param beginDate
     * @param endDate
     */
    public MarketingCampaign(String marketingCampaignID, String templateID , Date beginDate, Date endDate) {
        this.CampaignID = marketingCampaignID;
        this.templateName = templateID;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    /**
     * empty Constructor
     */
    public MarketingCampaign() {
    }

    /**
     * get CampaignID method
     * @return CampaignID
     */
    public String getCampaignID() {
        return CampaignID;
    }

    /**
     * get templateName method
     * @return templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * get beginDate method
     * @return beginDate
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * get endDate method
     * @return endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    public MarketingCampaign getMarketingCampaign() {return this;}

    /**
     *  set CampaignID method
     *
     * @param campaignID
     */
    public void setCampaignID(String campaignID) {
        this.CampaignID = campaignID;
    }

    /**
     *  set beginDate method
     * @param beginDate
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     *  set endDate method
     * @param endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     *  set templateName method
     * @param templateName
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }


}
