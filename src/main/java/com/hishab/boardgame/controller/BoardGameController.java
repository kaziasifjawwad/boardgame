package com.hishab.boardgame.controller;

import com.hishab.boardgame.game.Playground;
import com.hishab.boardgame.game.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardGameController implements BoardGameApi {
    private final Playground playground;

    @Override
    public ResponseEntity<Void> createNewPlayer(UserProfile registrationRequest) {
        playground.addNewPlayer(registrationRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> setTotalScore(int score) {
        playground.setTotalScore(score);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> startGame() {
        playground.play();
        return ResponseEntity.ok().build();
    }
}
