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

    @Test
    public void testDuration2() {
        /*Float a = 1.1f;
        Float b = 2f;
        System.out.println(NumberUtil.compare(a,b));
        System.out.println("asdasd"+(char)('A' + 1));*/
        String a = null;
        Integer b =1;
        System.out.println(b.equals(a));




    }
}
