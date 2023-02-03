package com.hishab.boardgame.constant;

public enum GameState {
    INITIAL_STATE("A player will start from this state. " +
            "He will be able to go to SCORE_STATE " +
            "if he get a 6 in his throw." +
            "However, if the get a 4 after his first 6," +
            "he will not be able to change his state to SCORE_STATE."),
    SCORE_STATE("A player will enter in this state after he successfully" +
            "pass the condition of INITIAL_STATE. A player will get a negative" +
            "score for hitting 4 and get an extra throw if he get a 6.");

    GameState(String description) {
    }
}
