package com.innovate.modules.util;

public class RandomUtils {
    private static byte[] lock = new byte[0];

    // 位数，默认是8位
    private final static long w = 100000000;

    public static String getRandomNums() {
        long r = 0;
        synchronized (lock) {
            r = (long) ((Math.random() + 1) * w);
        }

        return System.currentTimeMillis() + String.valueOf(r).substring(1);
    }
}
