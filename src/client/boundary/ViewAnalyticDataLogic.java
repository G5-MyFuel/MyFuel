package client.boundary;

import server.serverControllers.MySqlConnection;

public class ViewAnalyticDataLogic {

    MySqlConnection mySqlConnector;

    /*Logic Variables*/
    private static ViewAnalyticDataLogic Instance = null;

    public static ViewAnalyticDataLogic getInstance() {

        if (Instance == null)
            Instance = new ViewAnalyticDataLogic();
        return Instance;
    }
}