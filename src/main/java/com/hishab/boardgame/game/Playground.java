package com.hishab.boardgame.game;

import com.hishab.boardgame.exeptionhandler.ExtendedRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class Playground {
    private final DiceService diceService;
    private final Map<UserProfile, PlayerScore> mapOfPlayerToScore;
    private final List<UserProfile> listOfPlayer;
    private Boolean winState;
    private Integer totalScore;
    private int currentPlayerIndex;
    private int playerCounter;

    public Playground() {
        this.listOfPlayer = new ArrayList<>();
        this.mapOfPlayerToScore = new LinkedHashMap<>();
        this.diceService = new DiceService();
        playerCounter = 0;
        winState = true;
    }

    public void setTotalScore(int totalScore) {
        if (ObjectUtils.isEmpty(this.totalScore)) this.totalScore = totalScore;
        else throw new ExtendedRuntimeException("Total score can be defined once per game.");
    }

    public void addNewPlayer(UserProfile player) {
        if (this.playerCounter == 4) throw new
                ExtendedRuntimeException("Maximum number of player exceed. Only four people can play at a time.");
        mapOfPlayerToScore.put(player, new PlayerScore());
        listOfPlayer.add(player);
        playerCounter += 1;
    }


    public void play() {
        if (playerCounter < 2) throw new ExtendedRuntimeException("At least two player is required to play this game");
        while (winState) {
            int dice;
            var currentPlayer = this.listOfPlayer.get(this.currentPlayerIndex);
            var playerScore = mapOfPlayerToScore.get(currentPlayer);
            if (playerScore.getGameState().equals(GameState.ENTRY_STATE)) {
                dice = diceService.nextFromApi();
                if (dice == 6) {
                    log.info("{} GOT first move", currentPlayer.getName());
                    dice = diceService.nextFromApi();
                    if (dice != 4) {
                        playerScore.setGameState(GameState.SCORE_STATE);
                        playerScore.incrementScore(dice);
                        if (dice != 6) {
                            this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.listOfPlayer.size();
                        }

                    }

                } else {
                    this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.listOfPlayer.size();
                }
                log.info("{} HIT {} ----> CURRENT SCORE---> {}", currentPlayer.getName(), dice, playerScore.getScore());
            } else if (playerScore.getGameState().equals(GameState.SCORE_STATE)) {
                dice = diceService.nextFromApi();
                if (dice == 4) {
                    playerScore.incrementScore(-dice);
                    this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.listOfPlayer.size();
                } else if (dice == 6) {
                    playerScore.incrementScore(dice);
                } else {
                    playerScore.incrementScore(dice);
                    this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.listOfPlayer.size();
                }
                log.info("{} HIT {} ----> CURRENT SCORE---> {}", currentPlayer.getName(), dice, playerScore.getScore());
            }
            if (playerScore.getScore() >= this.totalScore) {
                log.info("WINNER -----------> {}", currentPlayer.getName());
                playerScore.setGameState(GameState.WINNER_STATE);
                winState = false;
            }
        }
    }


    public void playWithRestApi() {
        int dice;
        var currentPlayer = this.listOfPlayer.get(this.currentPlayerIndex);
        var playerScore = mapOfPlayerToScore.get(currentPlayer);
        if (playerScore.getGameState().equals(GameState.ENTRY_STATE)) {
            dice = diceService.nextFromApi();
            if (dice == 6) {
                log.info("{} GOT first move", currentPlayer.getName());
                dice = diceService.nextFromApi();
                if (dice != 4) {
                    playerScore.setGameState(GameState.SCORE_STATE);
                    playerScore.incrementScore(dice);
                    if (dice != 6) {
                        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.listOfPlayer.size();
                    }

                }

            } else {
                this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.listOfPlayer.size();
            }
            log.info("{} HIT {} ----> CURRENT SCORE---> {}", currentPlayer.getName(), dice, playerScore.getScore());
        } else if (playerScore.getGameState().equals(GameState.SCORE_STATE)) {
            dice = diceService.nextFromApi();
            if (dice == 4) {
                playerScore.incrementScore(-dice);
                this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.listOfPlayer.size();
            } else if (dice == 6) {
                playerScore.incrementScore(dice);
            } else {
                playerScore.incrementScore(dice);
                this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.listOfPlayer.size();
            }
            log.info("{} HIT {} ----> CURRENT SCORE---> {}", currentPlayer.getName(), dice, playerScore.getScore());
        }
        if (playerScore.getScore() >= this.totalScore) {
            log.info("WINNER -----------> {}", currentPlayer.getName());
            playerScore.setGameState(GameState.WINNER_STATE);
            winState = false;
        }
    }
}
