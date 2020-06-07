package Contollers;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

/**
 * The Class TimeManager.
 *
 * @author Itay Ziv
 */
public class TimeManager {

    /**
     * Gets the current date.
     *
     * @return the current date
     */
    public static Date getCurrentDate() {
        return new Date(Calendar.getInstance().getTime().getTime());
    }

    /**
     * Get the time difference in days between start date and end date
     * If it is negative it means the end date is before the start date.
     *
     * @param startDate - the start date
     * @param endDate   - the end date
     * @return the days between
     */
    public static long getDaysBetween(Date startDate, Date endDate) {

        long daysBetween;
        daysBetween = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate());
        /* Because we want difference included the first date */
        daysBetween += 1;

        return daysBetween;
    }

    /**
     * Gets the max date.
     *
     * @param first  the first
     * @param second the second
     * @return the max date
     */
    public static Date getMaxDate(Date first, Date second) {
        long dayDiffrence = getDaysBetween(first, second);
        if (dayDiffrence > 0) {
            return second;
        } else {
            return first;
        }
    }

    /**
     * Adds the days.
     *
     * @param date the date
     * @param days the days
     * @return the date
     */
    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }

    /**
     * Subtract days.
     *
     * @param date the date
     * @param days the days
     * @return the date
     */
    public static Date subtractDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -days);
        return new Date(c.getTimeInMillis());
    }
}
