package com.example.football_manager.service;

import com.example.football_manager.entity.Team;
import com.example.football_manager.exception.CustomException;
import com.example.football_manager.repository.TeamRepository;
import com.example.football_manager.util.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new CustomException(String.format(ErrorMessages.TEAM_NOT_FOUND, id)));
    }

    public Team createTeam(String name, BigDecimal balance, BigDecimal commissionRate) {
        Team team = Team.builder()
                .name(name)
                .balance(balance)
                .commissionRate(commissionRate)
                .build();
        return teamRepository.save(team);
    }

    public Team updateTeam(Long id, String name, BigDecimal balance, BigDecimal commissionRate) {
        Team team = getTeamById(id);
        team.setName(name);
        team.setBalance(balance);
        team.setCommissionRate(commissionRate);
        return teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
