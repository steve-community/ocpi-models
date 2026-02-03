package com.github.stevecommunity.ocpi.v221.model.versions;

import com.github.stevecommunity.ocpi.v221.model.versions.types.Endpoint;
import com.github.stevecommunity.ocpi.v221.model.versions.types.VersionNumber;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class VersionDetails {
    @NotNull VersionNumber version;
    @NotEmpty List<Endpoint> endpoints;
}
