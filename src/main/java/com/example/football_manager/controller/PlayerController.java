package com.example.football_manager.controller;

import com.example.football_manager.dto.PlayerRequestDto;
import com.example.football_manager.dto.PlayerResponseDto;
import com.example.football_manager.entity.Player;
import com.example.football_manager.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<PlayerResponseDto>> getAllPlayers() {
        List<PlayerResponseDto> players = playerService.getAllPlayers().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponseDto> getPlayerById(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
        return ResponseEntity.ok(convertToResponseDto(player));
    }

    @PostMapping
    public ResponseEntity<PlayerResponseDto> createPlayer(@Valid @RequestBody PlayerRequestDto playerDto) {
        Player player = playerService.createPlayer(
                playerDto.getFirstName(),
                playerDto.getLastName(),
                playerDto.getAge(),
                playerDto.getMonthsOfExperience(),
                playerDto.getTeamId()
        );
        return ResponseEntity.ok(convertToResponseDto(player));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponseDto> updatePlayer(@PathVariable Long id, @Valid @RequestBody PlayerRequestDto playerDto) {
        Player player = playerService.updatePlayer(
                id,
                playerDto.getFirstName(),
                playerDto.getLastName(),
                playerDto.getAge(),
                playerDto.getMonthsOfExperience(),
                playerDto.getTeamId()
        );
        return ResponseEntity.ok(convertToResponseDto(player));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }

    private PlayerResponseDto convertToResponseDto(Player player) {
        return PlayerResponseDto.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .age(player.getAge())
                .monthsOfExperience(player.getMonthsOfExperience())
                .teamId(player.getTeam().getId())
                .build();
    }
}
