package client.logic;

import server.serverControllers.MySqlConnection;

public class ViewAnalyticDataController {

    MySqlConnection mySqlConnector;

    /*Logic Variables*/
    private static ViewAnalyticDataController Instance = null;

    public static ViewAnalyticDataController getInstance() {

        if (Instance == null)
            Instance = new ViewAnalyticDataController();
        return Instance;
    }
}