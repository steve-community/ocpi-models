package com.github.stevecommunity.ocpi.v221.model.tariffs.types;

import lombok.Data;

import jakarta.validation.constraints.Pattern;
import java.util.List;

import static com.github.stevecommunity.ocpi.v221.util.ValidationPatterns.DATE_YYYY_MM_DD;
import static com.github.stevecommunity.ocpi.v221.util.ValidationPatterns.TIME_HHMM;

@Data
public class TariffRestriction {
    @Pattern(regexp = TIME_HHMM) String start_time;
    @Pattern(regexp = TIME_HHMM) String end_time;
    @Pattern(regexp = DATE_YYYY_MM_DD) String start_date;
    @Pattern(regexp = DATE_YYYY_MM_DD) String end_date;
    Double min_kwh;
    Double max_kwh;
    Double min_current;
    Double max_current;
    Double min_power;
    Double max_power;
    Integer min_duration;
    Integer max_duration;
    List<DayOfWeek> day_of_week;
    ReservationRestrictionType reservation;
}
