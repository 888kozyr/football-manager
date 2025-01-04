package com.example.football_manager.service;

import com.example.football_manager.dto.TransferRequestDto;
import com.example.football_manager.entity.Player;
import com.example.football_manager.entity.Team;
import com.example.football_manager.exception.CustomException;
import com.example.football_manager.repository.PlayerRepository;
import com.example.football_manager.repository.TeamRepository;
import com.example.football_manager.util.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Transactional
@RequiredArgsConstructor
public class TransferService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public void transferPlayer(TransferRequestDto transferRequestDto) {
        Team fromTeam = getTeamById(transferRequestDto.getFromTeamId());
        Team toTeam = getTeamById(transferRequestDto.getToTeamId());
        Player player = getPlayerById(transferRequestDto.getPlayerId());

        validatePlayerTeam(player, fromTeam.getId());
        validatePlayerAge(player);

        BigDecimal transferPrice = calculateTransferPrice(player);
        BigDecimal commission = calculateCommission(transferPrice, fromTeam);
        BigDecimal totalPrice = transferPrice.add(commission);

        if (toTeam.getBalance().compareTo(totalPrice) < 0) {
            throw new CustomException(ErrorMessages.NOT_ENOUGH_FUNDS);
        }

        toTeam.setBalance(toTeam.getBalance().subtract(totalPrice));
        fromTeam.setBalance(fromTeam.getBalance().add(totalPrice));
        player.setTeam(toTeam);

        playerRepository.save(player);
        teamRepository.save(fromTeam);
        teamRepository.save(toTeam);
    }

    private BigDecimal calculateTransferPrice(Player player) {
        BigDecimal experiencePart = BigDecimal.valueOf(player.getMonthsOfExperience() * 100000.0);
        BigDecimal agePart = BigDecimal.valueOf(player.getAge());
        return experiencePart.divide(agePart, 2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateCommission(BigDecimal transferPrice, Team team) {
        BigDecimal commissionRate = team.getCommissionRate() != null ? team.getCommissionRate() : BigDecimal.ZERO;
        return transferPrice.multiply(commissionRate).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

    private Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new CustomException(String.format(ErrorMessages.TEAM_NOT_FOUND, teamId)));
    }

    private Player getPlayerById(Long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new CustomException(String.format(ErrorMessages.PLAYER_NOT_FOUND, playerId)));
    }

    private void validatePlayerTeam(Player player, Long expectedTeamId) {
        if (!player.getTeam().getId().equals(expectedTeamId)) {
            throw new CustomException(String.format(ErrorMessages.PLAYER_DOES_NOT_BELONG_TO_TEAM, expectedTeamId));
        }
    }

    private void validatePlayerAge(Player player) {
        if (player.getAge() <= 0) {
            throw new CustomException(ErrorMessages.INVALID_PLAYER_AGE);
        }
    }
}
