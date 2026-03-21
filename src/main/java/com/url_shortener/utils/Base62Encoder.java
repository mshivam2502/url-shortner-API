package com.url_shortener.utils;

public class Base62Encoder {

    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String encode(Long number) {
        StringBuilder sb = new StringBuilder();

        while(number > 0) {
            int remainder = (int) (number % 62);
            sb.append(BASE62.charAt(remainder));
            number /= 62;
        }

        return sb.reverse().toString();

    }

}
