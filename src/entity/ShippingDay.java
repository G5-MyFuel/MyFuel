package entity;

public class ShippingDay {
    private String date;
    private Integer t1;//t_07_09
    private Integer t2;//t_09_11
    private Integer t3;//t_11_13
    private Integer t4;//t_13_15
    private Integer t5;//t_15_17
    private Integer t6;//t_17_19

    public ShippingDay(String date, Integer t_07_09, Integer t_09_11, Integer t_11_13, Integer t_13_15, Integer t_15_17, Integer t_17_19) {
        this.date = date;
        this.t1 = t_07_09;
        this.t2 = t_09_11;
        this.t3 = t_11_13;
        this.t4 = t_13_15;
        this.t5 = t_15_17;
        this.t6 = t_17_19;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ShippingDay{" +
                "date=" + date +
                ", t_07_09=" + t1 +
                ", t_09_11=" + t2 +
                ", t_11_13=" + t3 +
                ", t_13_15=" + t4 +
                ", t_15_17=" + t5 +
                ", t_17_19=" + t6 +
                '}' + "\n";
    }

//    public void fromStringToDate(String strDate) throws ParseException {
//        this.date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
//    }


    public Integer getT1() {
        return t1;
    }

    public void setT1(Integer t1) {
        this.t1 = t1;
    }

    public Integer getT2() {
        return t2;
    }

    public void setT2(Integer t2) {
        this.t2 = t2;
    }

    public Integer getT3() {
        return t3;
    }

    public void setT3(Integer t3) {
        this.t3 = t3;
    }

    public Integer getT4() {
        return t4;
    }

    public void setT4(Integer t4) {
        this.t4 = t4;
    }

    public Integer getT5() {
        return t5;
    }

    public void setT5(Integer t5) {
        this.t5 = t5;
    }

    public Integer getT6() {
        return t6;
    }

    public void setT6(Integer t6) {
        this.t6 = t6;
    }
}
