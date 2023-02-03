package com.hishab.boardgame.domain;

import com.hishab.boardgame.constant.GameState;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PlayerScore {
    private GameState gameState;
    private int score;

    public PlayerScore() {
        this.gameState = GameState.INITIAL_STATE;
        this.score = 0;
    }

    public void incrementScore(int score) {
        this.score += score;
    }

    public void decrementScore(int score) {
        this.score -= score;
    }
}
