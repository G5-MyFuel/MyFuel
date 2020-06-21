package entity;

/**
 * @author Hana Wiener
 */
public class FastFuel {

    private String purchaseID;
    private String FuelType;
    private String StationNumber;
    private String companyName;
    private String PAZ;
    private String SONOL;
    private String YELLOW;

    public FastFuel(String purchaseID, String fuelType, String stationNumber, String companyName, String PAZ, String SONOL, String YELLOW) {
        this.purchaseID = purchaseID;
        FuelType = fuelType;
        StationNumber = stationNumber;
        this.companyName = companyName;
        this.PAZ = PAZ;
        this.SONOL = SONOL;
        this.YELLOW = YELLOW;
    }



}
