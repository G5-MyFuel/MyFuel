package entity;


import java.sql.Date; // pay attention that i import the SQL class of date

public class SaleOperation {
    private String saleOperationID;
    private String templateName;
    private Date beginDate;
    private Date endDate;

    public SaleOperation(String saleOperationID, String templateID ,Date beginDate, Date endDate) {
        this.saleOperationID = saleOperationID;
        this.templateName = templateID;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public SaleOperation() {
    }

    public String getSaleOperationID() {
        return saleOperationID;
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

    public SaleOperation getSaleOperation () {return this;}
    public void setSaleOperationID(String saleOperationID) {
        this.saleOperationID = saleOperationID;
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
