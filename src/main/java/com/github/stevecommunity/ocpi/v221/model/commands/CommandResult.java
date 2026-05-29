package com.github.stevecommunity.ocpi.v221.model.commands;

import com.github.stevecommunity.ocpi.v221.model.DisplayText;
import com.github.stevecommunity.ocpi.v221.model.commands.types.CommandResultType;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class CommandResult {
    @NotNull CommandResultType result;
    List<DisplayText> message;
}
