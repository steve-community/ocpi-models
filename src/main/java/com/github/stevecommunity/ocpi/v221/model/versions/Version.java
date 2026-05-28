package com.github.stevecommunity.ocpi.v221.model.versions;

import com.github.stevecommunity.ocpi.v221.model.versions.types.VersionNumber;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class Version {
    @NotNull VersionNumber version;
    @NotNull @Size(max = 255) String url;
}
