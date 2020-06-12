package common.assets;

public enum UserType {
    CUSTOMER,
    DALKAN,
    COMPANY_MANAGER,
    MARKETING_DEPARTMENT_WORKER,
    MARKETING_MANAGER,
    MARKETING_REPRESENTATIVE,
    STATION_MANAGER,
    SUPPLIER;

    public static UserType asUserType(String str) {
        for (UserType me : UserType.values()) {
            if (me.name().equalsIgnoreCase(str))
                return me;
        }
        return null;
    }
}


