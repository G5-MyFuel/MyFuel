package common.entity;


import java.sql.Date; // pay attention that i import the SQL class of date

public class SaleOperation {
    private String SaleOperationID;
    private Date BeginDate;
    private Date EndDate;
    private String TemplateID;


    public String getSaleOperationID() {
        return SaleOperationID;
    }

    public String getTemplateID() {
        return TemplateID;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public Date getBeginDate() {
        return BeginDate;
    }

}
