package com.github.stevecommunity.ocpi.v221.model.commands;

import com.github.stevecommunity.ocpi.v221.model.DisplayText;
import com.github.stevecommunity.ocpi.v221.model.commands.types.CommandResponseType;
import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Accessors(chain = true)
@Data
public class CommandResponse {
    @NotNull CommandResponseType result;
    @NotNull Integer timeout;
    @Valid List<DisplayText> message;
}
