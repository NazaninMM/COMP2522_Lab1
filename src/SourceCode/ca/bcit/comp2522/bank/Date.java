package ca.bcit.comp2522.bank;

/**
 * Represents a date with year, month, and day components.
 * Provides methods to get various formatted versions of the date.
 *
 * @author Leen and Veronica
 * @version 1.0
 */
public class Date
{
    private static final int MIN_YEAR = 1800;
    private static final int MAX_YEAR_1800S = 1899;
    private static final int CURRENT_YEAR = 2025;
    private static final int CENTURY_YEAR = 2000;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private static final int MIN_DAY = 1;

    // Months of the Year
    private static final int JAN = 1;
    private static final int FEB = 2;
    private static final int MAR = 3;
    private static final int APR = 4;
    private static final int MAY = 5;
    private static final int JUN = 6;
    private static final int JUL = 7;
    private static final int AUG = 8;
    private static final int SEP = 9;
    private static final int OCT = 10;
    private static final int NOV = 11;
    private static final int DEC = 12;

    // Century offsets
    private static final int ADJUSTMENT_1800S = 2;
    private static final int ADJUSTMENT_2000S = 6;
    private static final int ADJUSTMENT_LEAP_YEAR = 6;

    private static final int LEAP_YEAR_CYCLE = 4;
    private static final int CENTURY_CYCLE = 100;
    private static final int FOUR_CENTURY_CYCLE = 400;
    private static final int YEAR_WITHIN_CENTURY_DIVISOR = 100;
    private static final int TWELVE_YEAR_CYCLE = 12;

    // The month codes for jfmamjjasond is 144025036146
    private static final int[] MONTH_CODES = {0, 1, 4, 4, 0, 2, 5, 0, 3, 6, 1, 4, 6};

    private static final int DAYS_IN_MONTH_31 = 31;
    private static final int DAYS_IN_MONTH_30 = 30;
    private static final int DAYS_IN_WEEK = 7;
    private static final int FEB_DAYS_LEAP = 29;
    private static final int FEB_DAYS_COMMON = 28;

    private final int year;
    private final int month;
    private final int day;

    /**
     * Constructs a Date object with year, month and day.
     *
     * @param year the year
     * @param month the month
     * @param day the day
     * @throws IllegalArgumentException if invalid
     */
    public Date(final int year,
                final int month,
                final int day)
    {
        validateDate(year, month, day);

        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Returns the day
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns the month
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Returns the year
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the formatted date (YYYY-MM-DD)
     * @return the formatted date (YYYY-MM-DD)
     */
    public String getYYYYMMDD() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    /**
     * Returns the day of the week index
     * @return day of the week index (0=Sat, 1=Sun, 2=Mon, 3=Tue, 4=Wed, 5=Thu, 6=Fri)
     */
    public int getDayOfTheWeek() {
        int extraDay = 0;
        if (year >= CENTURY_YEAR && year <= CURRENT_YEAR)
        {
            extraDay += ADJUSTMENT_2000S;
        } else if (year >= MIN_YEAR && year <= MAX_YEAR_1800S)
        {
            extraDay += ADJUSTMENT_1800S;
        }
        final boolean janOrFeb = (month == JAN || month == FEB);
        if (janOrFeb && isLeapYear(year))
        {
            extraDay += ADJUSTMENT_LEAP_YEAR;
        }
        final int yearWithinCentury = year % YEAR_WITHIN_CENTURY_DIVISOR;
        final int dozenYear = yearWithinCentury / TWELVE_YEAR_CYCLE;
        final int remainder = yearWithinCentury - dozenYear * TWELVE_YEAR_CYCLE;
        final int foursInRemainder = remainder / LEAP_YEAR_CYCLE;
        final int sum = extraDay + day + dozenYear + remainder + foursInRemainder + MONTH_CODES[month];
        return sum % DAYS_IN_WEEK;
    }


    /**
     * Validates the date.
     * Date must be between the minimum year and the current year.
     * Date must have a valid month
     * Date must
     *
     * @param year the year
     * @param month the month
     * @param day the day
     */
    private void validateDate(final int year,
                              final int month,
                              final int day)
    {
        if(year < MIN_YEAR || year > CURRENT_YEAR)
        {
            throw new IllegalArgumentException("Year must be between " + MIN_YEAR + " and " + CURRENT_YEAR);
        }

        if(month < MIN_MONTH || month > MAX_MONTH)
        {
            throw new IllegalArgumentException("Month must be between " + MIN_MONTH + " and " + MAX_MONTH);
        }

        if (day < MIN_DAY || day > daysInMonth(year, month))
        {
            throw new IllegalArgumentException("Invalid day: " + day);
        }
    }

    /**
     * Finds the number of days in a given month during a given year.
     *
     * @param year the year
     * @param month the month
     * @return number of days in a month
     */
    private int daysInMonth(final int year, final int month) {
        switch (month) {
            case JAN:
            case MAR:
            case MAY:
            case JUL:
            case AUG:
            case OCT:
            case DEC:
                return DAYS_IN_MONTH_31;
            case APR:
            case JUN:
            case SEP:
            case NOV:
                return DAYS_IN_MONTH_30;
            case FEB:
                return isLeapYear(year) ? FEB_DAYS_LEAP : FEB_DAYS_COMMON;
            default:
                throw new IllegalArgumentException("Invalid year and month: (" + year + ", " + month + ")" );
        }
    }

    /**
     * Checks if a year is a leap year.
     *
     * @param year the year
     * @return true if leap year, false otherwise
     */
    private boolean isLeapYear(final int year) {
        return (year % LEAP_YEAR_CYCLE == 0 && year % CENTURY_CYCLE != 0) || (year % FOUR_CENTURY_CYCLE == 0);
    }
}
