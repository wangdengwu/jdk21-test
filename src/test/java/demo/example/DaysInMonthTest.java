package demo.example;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static java.util.Calendar.MONTH;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DaysInMonthTest {

    @Test
    public void testDaysInMonth_31Days() {
        DaysInMonth daysInMonth = new DaysInMonth();
        Calendar calendar = Calendar.getInstance();
        calendar.set(MONTH, Calendar.JANUARY); // 1月 has 31 days
        assertEquals(31, daysInMonth.daysInMonthNewSwitch(calendar));

        calendar.set(MONTH, Calendar.MARCH); // 3月 has 31 days
        assertEquals(31, daysInMonth.daysInMonthOldSwitch(calendar));

        calendar.set(MONTH, Calendar.DECEMBER); // 12月 has 31 days
        assertEquals(31, daysInMonth.daysInMonthNewSwitch(calendar));
    }

    @Test
    public void testDaysInMonth_30Days() {
        DaysInMonth daysInMonth = new DaysInMonth();
        Calendar calendar = Calendar.getInstance();
        calendar.set(MONTH, Calendar.APRIL); // 4月 has 30 days
        assertEquals(30, daysInMonth.daysInMonthNewSwitch(calendar));

        calendar.set(MONTH, Calendar.JUNE); // 6月 has 30 days
        assertEquals(30, daysInMonth.daysInMonthOldSwitch(calendar));

        calendar.set(MONTH, Calendar.NOVEMBER); // 11月 has 30 days
        assertEquals(30, daysInMonth.daysInMonthNewSwitch(calendar));
    }

    @Test
    public void testDaysInMonth_February_LeapYear() {
        DaysInMonth daysInMonth = new DaysInMonth();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.FEBRUARY, 1); // 2020 is a leap year, 2月 has 29 days
        assertEquals(29, daysInMonth.daysInMonthOldSwitch(calendar));
        assertEquals(29, daysInMonth.daysInMonthNewSwitch(calendar));
    }

    @Test
    public void testDaysInMonth_February_NonLeapYear() {
        DaysInMonth daysInMonth = new DaysInMonth();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.FEBRUARY, 1); // 2019 is not a leap year, 2月 has 28 days
        assertEquals(28, daysInMonth.daysInMonthOldSwitch(calendar));
        assertEquals(28, daysInMonth.daysInMonthNewSwitch(calendar));
    }
}
