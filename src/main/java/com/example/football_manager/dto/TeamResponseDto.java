package com.example.football_manager.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class TeamResponseDto {

    private Long id;
    private String name;
    private BigDecimal balance;
    private BigDecimal commissionRate;
    private List<PlayerResponseDto> players;
}
