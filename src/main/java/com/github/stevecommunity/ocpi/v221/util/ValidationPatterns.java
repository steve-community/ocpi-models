package com.github.stevecommunity.ocpi.v221.util;

public final class ValidationPatterns {

    public static final String TIME_HHMM = "([0-1][0-9]|2[0-3]):[0-5][0-9]";
    public static final String DATE_YYYY_MM_DD = "([12][0-9]{3})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";
    public static final String LATITUDE = "-?[0-9]{1,2}\\.[0-9]{5,7}";
    public static final String LONGITUDE = "-?[0-9]{1,3}\\.[0-9]{5,7}";

    private ValidationPatterns() {
    }
}
