package com.example.football_manager.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TeamRequestDto {

    @NotBlank(message = "Team name cannot be blank")
    private String name;

    @NotNull(message = "Team balance is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be non-negative")
    private BigDecimal balance;

    @NotNull(message = "Commission rate is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Commission rate must be non-negative")
    @DecimalMax(value = "10.0", inclusive = true, message = "Commission rate must not exceed 10%")
    private BigDecimal commissionRate;
}
