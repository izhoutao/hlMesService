package com.haili.framework.utils;

import org.junit.Test;

public class BCryptUtilTest {

    @Test
    public void encode() {
        System.out.println(BCryptUtil.encode("111111"));
    }

    @Test
    public void matches() {
    }
}