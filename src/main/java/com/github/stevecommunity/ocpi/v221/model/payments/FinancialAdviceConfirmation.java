package com.github.stevecommunity.ocpi.v221.model.payments;

import com.github.stevecommunity.ocpi.v221.model.Price;
import com.github.stevecommunity.ocpi.v221.model.payments.types.CaptureStatusCode;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Data
public class FinancialAdviceConfirmation {
    @NotNull @Size(max = 36) String id;
    @NotNull @Size(max = 36) String authorization_reference;
    @NotNull Price total_costs;
    @NotNull @Size(max = 3) String currency;
    @NotEmpty List<String> eft_data;
    @NotNull CaptureStatusCode capture_status_code;
    @Size(max = 255) String capture_status_message;
    @NotNull Instant last_updated;
}
