package server;

import Contollers.TimeManager;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 */
public class TimeWatcher implements Runnable {


    /** The sql connection. */
    private MysqlConnection sqlConnection;

    /** The last day watch. */
    private Date lastDayWatch;

    @Override
    public void run() {
        /* Initialize all the private objects */
        sqlConnection = new MysqlConnection();
        MysqlConnection.initSqlArray();
        lastDayWatch = TimeManager.getCurrentDate();


        /* Start work horse */
        for(;;)
        {
            sleepUntilNextDay();
        }
    }

    /**
     * Sleep until next day.
     */
    private void sleepUntilNextDay() {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long milliesUntilMidnight = (c.getTimeInMillis()-System.currentTimeMillis());
        System.out.println("Time Watcher - Going to sleep " + milliesUntilMidnight + " millisecond until midnight");

        try
        {
            Thread.sleep(milliesUntilMidnight);
            /* Sleep was successful */
            lastDayWatch = TimeManager.getCurrentDate();
            System.out.println("Time Watcher - I just woke up, starting to check time deviations");
        }
        catch (Exception e)
        {
            if (lastDayWatch.compareTo(TimeManager.getCurrentDate()) == 0)
            {
                /* Exception was caught and the date did not change */
                sleepUntilNextDay();
            }
            else
            {
                lastDayWatch = TimeManager.getCurrentDate();
                System.out.println("Time Watcher - I just woke up, starting to check time deviations");
            }
        }

    }






}
