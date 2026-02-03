package com.github.stevecommunity.ocpi.v221.model.commands;

import lombok.Data;
import com.github.stevecommunity.ocpi.v221.model.DisplayText;
import com.github.stevecommunity.ocpi.v221.model.commands.types.CommandResultType;

@Data
public class CommandResult {
    CommandResultType result;
    DisplayText message;
}
