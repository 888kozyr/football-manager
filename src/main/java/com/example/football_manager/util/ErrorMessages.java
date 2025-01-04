package com.example.football_manager.util;

public class ErrorMessages {

    public static final String TEAM_NOT_FOUND = "Team with id %d not found";
    public static final String PLAYER_NOT_FOUND = "Player with id %d not found";
    public static final String PLAYER_DOES_NOT_BELONG_TO_TEAM = "Player does not belong to team with id %d";
    public static final String NOT_ENOUGH_FUNDS = "Not enough funds on buyer team's balance";
    public static final String INVALID_PLAYER_AGE = "Player age cannot be zero or negative";

    private ErrorMessages() {
    }
}