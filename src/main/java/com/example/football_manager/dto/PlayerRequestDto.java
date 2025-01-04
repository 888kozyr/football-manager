package com.example.football_manager.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PlayerRequestDto {

    @NotBlank(message = "Player first name cannot be blank")
    private String firstName;

    @NotBlank(message = "Player last name cannot be blank")
    private String lastName;

    @NotNull(message = "Player age is required")
    @Min(value = 1, message = "Player age must be greater than 0")
    private Integer age;

    @NotNull(message = "Months of experience are required")
    @Min(value = 0, message = "Months of experience must be non-negative")
    private Integer monthsOfExperience;

    @NotNull(message = "Team ID is required")
    private Long teamId;
}

