package com.salesforce;

public class TimeZoneUtility {
    static String extractTimeZone(String s) {
        return s.substring(s.indexOf("/") + 1).replaceAll("/", "-");
    }
}
