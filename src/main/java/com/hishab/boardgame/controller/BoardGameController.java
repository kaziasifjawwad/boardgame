package com.hishab.boardgame.controller;

import com.hishab.boardgame.domain.CurrentStateResponse;
import com.hishab.boardgame.domain.UserProfile;
import com.hishab.boardgame.service.PlaygroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardGameController implements BoardGameApi {
    private final PlaygroundService playgroundService;

    @Override
    public ResponseEntity<Void> createNewPlayer(UserProfile registrationRequest) {
        playgroundService.addNewPlayer(registrationRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> setTotalScore(int score) {
        playgroundService.setTotalScore(score);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CurrentStateResponse>> getCurrentStateScore() {
        return ResponseEntity.ok(this.playgroundService.getCurrentState());
    }

    @Override
    public ResponseEntity<Void> startGame() {
        playgroundService.play();
        return ResponseEntity.ok().build();
    }
}
