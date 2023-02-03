package com.hishab.boardgame.service;

import com.hishab.boardgame.constant.GameState;
import com.hishab.boardgame.domain.CurrentStateResponse;
import com.hishab.boardgame.domain.PlayerScore;
import com.hishab.boardgame.domain.UserProfile;
import com.hishab.boardgame.exeptionhandler.ExtendedRuntimeException;
import com.hishab.boardgame.mapper.ScoreMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@Getter
@Setter
public class PlaygroundService {
    private DiceService diceService;
    private Map<UserProfile, PlayerScore> mapOfPlayerToScore;
    private List<UserProfile> listOfPlayer;
    private Boolean winState;
    private Integer totalScore;
    private int currentPlayerIndex;
    private int playerCounter;
    private ScoreMapper scoreMapper;
    private UserProfile winnerProfile;

    public PlaygroundService() {
        this.listOfPlayer = new ArrayList<>();
        this.mapOfPlayerToScore = new LinkedHashMap<>();
        this.diceService = new DiceService();
        this.scoreMapper = new ScoreMapper();
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

    public List<CurrentStateResponse> getCurrentState() {
        return this.scoreMapper.map(this.mapOfPlayerToScore);
    }

    private void giveDiceToNextPlayer() {
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.listOfPlayer.size();
    }

    public void play() {
        if (playerCounter < 2) throw new ExtendedRuntimeException("At least two player is required to play this game");
        while (winState) {
            int dice;
            var currentPlayer = this.listOfPlayer.get(this.currentPlayerIndex);
            var currentPlayerScore = mapOfPlayerToScore.get(currentPlayer);
            var gameStateOfCurrentPlayer = currentPlayerScore.getGameState();
            switch (gameStateOfCurrentPlayer) {
                case INITIAL_STATE:
                    dice = diceService.nextFromApi();
                    switch (dice) {
                        case 6:
                            log.info("{} hit 6 and got his first move for accumulating score\n", currentPlayer.getName());
                            dice = diceService.nextFromApi();
                            switch (dice) {
                                case 4:
                                    giveDiceToNextPlayer();
                                    break;
                                case 6:
                                    currentPlayerScore.setGameState(GameState.SCORE_STATE);
                                    currentPlayerScore.incrementScore(dice);
                                    break;
                                default:
                                    currentPlayerScore.setGameState(GameState.SCORE_STATE);
                                    currentPlayerScore.incrementScore(dice);
                                    giveDiceToNextPlayer();
                            }
                            break;
                        default:
                            giveDiceToNextPlayer();
                    }
                    log.info("Player name: {}, Total Score: {}, Current Value of Dice: {}", currentPlayer.getName(),
                            currentPlayerScore.getScore(), dice);
                    break;

                case SCORE_STATE:
                    dice = diceService.nextFromApi();
                    switch (dice) {
                        case 4:
                            currentPlayerScore.decrementScore(dice);
                            giveDiceToNextPlayer();
                            break;
                        case 6:
                            currentPlayerScore.incrementScore(dice);
                            break;
                        default:
                            currentPlayerScore.incrementScore(dice);
                            giveDiceToNextPlayer();
                    }
                    log.info("Player name: {}, Total Score: {}, Current Value of Dice: {}", currentPlayer.getName(),
                            currentPlayerScore.getScore(), dice);
            }
            if (currentPlayerScore.getScore() >= this.totalScore) {
                this.winnerProfile = currentPlayer;
                winState = false;
            }
        }
    }
}
