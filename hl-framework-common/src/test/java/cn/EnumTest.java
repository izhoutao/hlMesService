package cn;

import org.junit.Test;

import java.time.DayOfWeek;

public class EnumTest {

    @Test
    public void testDuration() {
//        System.out.println(DayOfWeek.MONDAY.toString().equalsIgnoreCase("MONDAY"));
        System.out.println(DayOfWeek.valueOf("MONDAY").equals(DayOfWeek.MONDAY));
    }
    @Test
    public void testDuration1() {
//        System.out.println(Long.parseLong("000A"));
        System.out.println(DayOfWeek.valueOf("MONDAY1"));
    }
}
