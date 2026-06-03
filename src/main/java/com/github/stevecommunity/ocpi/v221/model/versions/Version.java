package com.github.stevecommunity.ocpi.v221.model.versions;

import com.github.stevecommunity.ocpi.v221.model.versions.types.VersionNumber;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Accessors(chain = true)
@Data
public class Version {
    @NotNull VersionNumber version;
    @NotEmpty @Size(max = 255) String url;
}
