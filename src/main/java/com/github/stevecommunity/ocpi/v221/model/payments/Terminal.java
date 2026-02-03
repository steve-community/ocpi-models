package com.github.stevecommunity.ocpi.v221.model.payments;

import lombok.Data;
import com.github.stevecommunity.ocpi.v221.model.locations.types.GeoLocation;
import com.github.stevecommunity.ocpi.v221.model.payments.types.InvoiceCreatorType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Data
public class Terminal {
    @NotNull @Size(max = 36) String terminal_id;
    @Size(max = 36) String customer_reference;
    @Size(max = 3) String party_id;
    @Size(max = 2) String country_code;
    @Size(max = 45) String address;
    @Size(max = 45) String city;
    @Size(max = 10) String postal_code;
    @Size(max = 20) String state;
    @Size(max = 3) String country;
    GeoLocation coordinates;
    @Size(max = 255) String invoice_base_url;
    InvoiceCreatorType invoice_creator;
    @NotEmpty List<@Size(max = 36) String> location_ids;
    @NotEmpty List<@Size(max = 36) String> evse_uids;
    @NotNull Instant last_updated;
}
