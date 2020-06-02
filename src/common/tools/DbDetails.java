package common.tools;

public class DbDetails {
    public String DB_HOST = "bpsdc8o22sikrlpvvxqm-mysql.services.clever-cloud.com";
    public String DB_SCHEME = "bpsdc8o22sikrlpvvxqm";
    public String DB_USERNAME = "uec30klrdxwlktiw";
    public String DB_PASSWORD = "tc3wfAIidXugUM7hr3nK";
    public String DB_PORT = "3306";
    public String Scheme = "bpsdc8o22sikrlpvvxqm";

    public DbDetails(String DB_HOST, String DB_SCHEME, String DB_USERNAME, String DB_PASSWORD, String DB_PORT) {
        this.DB_HOST = DB_HOST;
        this.DB_SCHEME = DB_SCHEME;
        this.DB_USERNAME = DB_USERNAME;
        this.DB_PASSWORD = DB_PASSWORD;
        this.DB_PORT = DB_PORT;
    }

    public String getDB_HOST() {
        return DB_HOST;
    }

    public void setDB_HOST(String DB_HOST) {
        this.DB_HOST = DB_HOST;
    }

    public String getDB_SCHEME() {
        return DB_SCHEME;
    }

    public void setDB_SCHEME(String DB_SCHEME) {
        this.DB_SCHEME = DB_SCHEME;
    }

    public String getDB_USERNAME() {
        return DB_USERNAME;
    }

    public void setDB_USERNAME(String DB_USERNAME) {
        this.DB_USERNAME = DB_USERNAME;
    }

    public String getDB_PASSWORD() {
        return DB_PASSWORD;
    }

    public void setDB_PASSWORD(String DB_PASSWORD) {
        this.DB_PASSWORD = DB_PASSWORD;
    }

    public String getDB_PORT() {
        return DB_PORT;
    }

    public void setDB_PORT(String DB_PORT) {
        this.DB_PORT = DB_PORT;
    }
}
