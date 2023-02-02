package com.hishab.boardgame.game;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PlayerScore {
    private GameState gameState;
    private int score;

    public PlayerScore() {
        this.gameState = GameState.ENTRY_STATE;
        this.score = 0;
    }

    public void incrementScore(int score) {
        this.score += score;
    }
}
