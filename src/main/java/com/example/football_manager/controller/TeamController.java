package com.example.football_manager.controller;

import com.example.football_manager.dto.PlayerResponseDto;
import com.example.football_manager.dto.TeamRequestDto;
import com.example.football_manager.dto.TeamResponseDto;
import com.example.football_manager.entity.Team;
import com.example.football_manager.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamResponseDto>> getAllTeams() {
        List<TeamResponseDto> teams = teamService.getAllTeams().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDto> getTeamById(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);
        return ResponseEntity.ok(convertToResponseDto(team));
    }

    @PostMapping
    public ResponseEntity<TeamResponseDto> createTeam(@Valid @RequestBody TeamRequestDto teamDto) {
        Team team = teamService.createTeam(
                teamDto.getName(),
                teamDto.getBalance(),
                teamDto.getCommissionRate()
        );
        return ResponseEntity.ok(convertToResponseDto(team));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResponseDto> updateTeam(@PathVariable Long id, @Valid @RequestBody TeamRequestDto teamDto) {
        Team team = teamService.updateTeam(
                id,
                teamDto.getName(),
                teamDto.getBalance(),
                teamDto.getCommissionRate()
        );
        return ResponseEntity.ok(convertToResponseDto(team));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

    private TeamResponseDto convertToResponseDto(Team team) {
        return TeamResponseDto.builder()
                .id(team.getId())
                .name(team.getName())
                .balance(team.getBalance())
                .commissionRate(team.getCommissionRate())
                .players(team.getPlayers().stream()
                        .map(player -> PlayerResponseDto.builder()
                                .id(player.getId())
                                .firstName(player.getFirstName())
                                .lastName(player.getLastName())
                                .age(player.getAge())
                                .monthsOfExperience(player.getMonthsOfExperience())
                                .teamId(player.getTeam().getId())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
