package com.github.stevecommunity.ocpi.v221.model.tokens.types;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

@Accessors(chain = true)
@Data
public class LocationReferences {
    @NotEmpty @Size(max = 36) String location_id;
    List<@Size(max = 36) String> evse_uids;
}
