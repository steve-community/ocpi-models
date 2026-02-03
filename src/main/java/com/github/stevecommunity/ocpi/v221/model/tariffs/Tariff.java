package com.github.stevecommunity.ocpi.v221.model.tariffs;

import com.github.stevecommunity.ocpi.v221.model.DisplayText;
import com.github.stevecommunity.ocpi.v221.model.Price;
import com.github.stevecommunity.ocpi.v221.model.locations.types.EnergyMix;
import com.github.stevecommunity.ocpi.v221.model.payments.adjustments.PaymentsModule;
import com.github.stevecommunity.ocpi.v221.model.tariffs.types.TariffElement;
import com.github.stevecommunity.ocpi.v221.model.tariffs.types.TariffType;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Data
public class Tariff {
    @NotEmpty(groups = PaymentsModule.class) List<DisplayText> tariff_alt_text;
    @NotNull(groups = PaymentsModule.class) Price max_price;

    @NotNull @Size(max = 2) String country_code;
    @NotNull @Size(max = 3) String party_id;
    @NotNull @Size(max = 36) String id;
    @NotNull @Size(max = 3) String currency;
    TariffType type;
    @Size(max = 255) String tariff_alt_url;
    Price min_price;
    @NotEmpty List<TariffElement> elements;
    Instant start_date_time;
    Instant end_date_time;
    EnergyMix energy_mix;
    @NotNull Instant last_updated;
}
