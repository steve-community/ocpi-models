package com.github.stevecommunity.ocpi.v221.model.versions;

import com.github.stevecommunity.ocpi.v221.model.versions.types.Endpoint;
import com.github.stevecommunity.ocpi.v221.model.versions.types.VersionNumber;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Accessors(chain = true)
@Data
public class VersionDetails {
    @NotNull VersionNumber version;
    @NotEmpty @Valid List<Endpoint> endpoints;
}
