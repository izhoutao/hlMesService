package cn;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class TimeTest {

    @Test
    public void testDuration() {
        String str = "2019-02-28 00:00:01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(str, formatter);
        str = "2019-03-01 00:00:01";
        LocalDateTime dateTime2 = LocalDateTime.parse(str, formatter);
        Duration duration = Duration.between(dateTime1, dateTime2);
        Period period = Period.between(dateTime1.toLocalDate(), dateTime2.toLocalDate());
        System.out.println(duration.toDays());
        System.out.println(period.toTotalMonths());
        System.out.println(period.getMonths());
    }

    @Test
    public void testDuration1() {
        String str = "2019-02-28 23:59:59";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(str, formatter);
        str = "2019-03-01 00:00:00";
        LocalDateTime dateTime2 = LocalDateTime.parse(str, formatter);
        LocalDateTime localDateTime1 = dateTime1.toLocalDate().atStartOfDay();
        LocalDateTime localDateTime2 = dateTime2.toLocalDate().atStartOfDay();
        Duration duration = Duration.between(localDateTime1, localDateTime2);
        Period period = Period.between(localDateTime1.toLocalDate(), localDateTime2.toLocalDate());

        System.out.println(localDateTime1.equals(localDateTime2));
        System.out.println(duration.toDays());
        System.out.println(period.toTotalMonths());
        System.out.println(period.getMonths());
    }

    @Test
    public void testDuration2() {
        String str = "2019-02-28 23:59:59";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(str, formatter);
        str = "2019-03-01 00:00:00";
        LocalDateTime dateTime2 = LocalDateTime.parse(str, formatter);
        LocalDateTime localDateTime1 = dateTime1.with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime localDateTime2 = dateTime2.with(TemporalAdjusters.firstDayOfMonth());
        Duration duration = Duration.between(localDateTime1, localDateTime2);
        Period period = Period.between(localDateTime1.toLocalDate(), localDateTime2.toLocalDate());
        System.out.println(duration.toDays());
        System.out.println(period.toTotalMonths());
        System.out.println(period.getMonths());
    }

    @Test
    public void testDuration3() {
        String str = "2019-12-28 23:59:59";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(str, formatter);
        LocalDateTime localDateTime1 = dateTime1.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println(localDateTime1.format(formatter));
    }

    @Test
    public void testDuration4() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str = "2019-12-22 23:59:59";
        LocalDateTime dateTime1 = LocalDateTime.parse(str, formatter);
        str = "2019-12-23 00:00:00";
        LocalDateTime dateTime2 = LocalDateTime.parse(str, formatter);
        dateTime1 = dateTime1.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).with(LocalTime.MIN);
        dateTime2 = dateTime2.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).with(LocalTime.MIN);
        System.out.println(dateTime1.equals(dateTime2));
    }

    @Test
    public void testDuration5() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str = "2019-02-28 23:59:59";
        LocalDateTime dateTime1 = LocalDateTime.parse(str, formatter);
        str = "2019-02-02 00:00:00";
        LocalDateTime dateTime2 = LocalDateTime.parse(str, formatter);
        dateTime1 = dateTime1.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
        dateTime2 = dateTime2.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);;
        System.out.println(dateTime1.equals(dateTime2));
    }
    @Test
    public void testDuration6() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str = "2019-02-28 23:59:59";
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("");
        dateTime.format(formatter1);
    }

}
