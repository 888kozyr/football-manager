package com.example.football_manager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer monthsOfExperience;
    private Long teamId;
}
