package com.hishab.boardgame.controller;

import com.hishab.boardgame.domain.CurrentStateResponse;
import com.hishab.boardgame.domain.UserProfile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(value = "board-game", tags = "board-game")
@RequestMapping(path = "board-game")
public interface BoardGameApi {

    @ApiOperation(
            value = "Create new player",
            nickname = "createNewPlayer",
            notes = "This API is used to create new player.",
            tags = {
                    "board-game",
            })
    @PostMapping
    ResponseEntity<Void> createNewPlayer(@RequestBody UserProfile registrationRequest);

    @ApiOperation(
            value = "Set total score",
            nickname = "setTotalScore",
            notes = "This API is used to define total score. This is the second step of this game." +
                    "Score can be anything greater than 0.",
            tags = {
                    "board-game",
            })
    @PostMapping("totalscore")
    ResponseEntity<Void> setTotalScore(@RequestParam int score);


    @ApiOperation(
            value = "Start the game.",
            nickname = "startGame",
            notes = "This API is used to start the game.",
            tags = {
                    "board-game",
            })
    @PostMapping("start")
    ResponseEntity<Void> startGame();


    @ApiOperation(
            value = "Get all player's score",
            nickname = "getCurrentStateScore",
            notes = "This API will fetch all the score of current state of the game",
            tags = {
                    "board-game",
            })
    @PostMapping("get-score")
    ResponseEntity<List<CurrentStateResponse>> getCurrentStateScore();


}
