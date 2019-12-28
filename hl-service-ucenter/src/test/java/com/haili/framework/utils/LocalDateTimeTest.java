package com.haili.framework.utils;

import org.junit.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTest {

    @Test
    public void localDateTimeTest(){
        String str = "yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(str);
        LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
        String formattedDateTime = formatter.format(now);
        System.out.println(formattedDateTime);
    }
}