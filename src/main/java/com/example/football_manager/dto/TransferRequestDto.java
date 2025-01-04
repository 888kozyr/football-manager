package com.example.football_manager.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransferRequestDto {

    @NotNull(message = "Player ID is required")
    private Long playerId;

    @NotNull(message = "From Team ID is required")
    private Long fromTeamId;

    @NotNull(message = "To Team ID is required")
    private Long toTeamId;
}

