package com.github.stevecommunity.ocpi.v221.model.commands;

import com.github.stevecommunity.ocpi.v221.model.DisplayText;
import com.github.stevecommunity.ocpi.v221.model.commands.types.CommandResponseType;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class CommandResponse {
    @NotNull CommandResponseType result;
    @NotNull Integer timeout;
    List<DisplayText> message;
}
