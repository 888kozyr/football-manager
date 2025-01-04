package com.example.football_manager.service;

import com.example.football_manager.entity.Player;
import com.example.football_manager.entity.Team;
import com.example.football_manager.exception.CustomException;
import com.example.football_manager.repository.PlayerRepository;
import com.example.football_manager.repository.TeamRepository;
import com.example.football_manager.util.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new CustomException(String.format(ErrorMessages.PLAYER_NOT_FOUND, id)));
    }

    public Player createPlayer(String firstName, String lastName, int age, int monthsOfExperience, Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new CustomException(String.format(ErrorMessages.TEAM_NOT_FOUND, teamId)));

        Player player = Player.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .monthsOfExperience(monthsOfExperience)
                .team(team)
                .build();

        return playerRepository.save(player);
    }

    public Player updatePlayer(Long id, String firstName, String lastName, int age, int monthsOfExperience, Long teamId) {
        Player player = getPlayerById(id);
        Team team = null;
        if (teamId != null) {
            team = teamRepository.findById(teamId)
                    .orElseThrow(() -> new CustomException(String.format(ErrorMessages.TEAM_NOT_FOUND, teamId)));
        }

        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setAge(age);
        player.setMonthsOfExperience(monthsOfExperience);
        player.setTeam(team);

        return playerRepository.save(player);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}
