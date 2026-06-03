package com.github.stevecommunity.ocpi.v221.model.tokens;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Token extends TokenPatch implements TokenNullChecks {
}
