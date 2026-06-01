package com.github.stevecommunity.ocpi.v221.model.tokens.types;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

@Data
public class LocationReferences {
    @NotEmpty @Size(max = 36) String location_id;
    List<@Size(max = 36) String> evse_uids;
}
