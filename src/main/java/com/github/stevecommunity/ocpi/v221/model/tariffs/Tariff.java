package com.github.stevecommunity.ocpi.v221.model.tariffs;

import com.github.stevecommunity.ocpi.v221.model.DisplayText;
import com.github.stevecommunity.ocpi.v221.model.Price;
import com.github.stevecommunity.ocpi.v221.model.locations.types.EnergyMix;
import com.github.stevecommunity.ocpi.v221.model.tariffs.types.TariffElement;
import com.github.stevecommunity.ocpi.v221.model.tariffs.types.TariffType;
import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Data
public class Tariff {
    @NotEmpty @Size(max = 2) String country_code;
    @NotEmpty @Size(max = 3) String party_id;
    @NotEmpty @Size(max = 36) String id;
    @NotEmpty @Size(max = 3) String currency;
    TariffType type;
    @Valid List<DisplayText> tariff_alt_text;
    @Size(max = 255) String tariff_alt_url;
    @Valid Price min_price;
    @Valid Price max_price;
    @NotEmpty @Valid List<TariffElement> elements;
    Instant start_date_time;
    Instant end_date_time;
    @Valid EnergyMix energy_mix;
    @NotNull Instant last_updated;
}
