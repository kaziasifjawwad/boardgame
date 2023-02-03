package com.hishab.boardgame;


import com.hishab.boardgame.domain.UserProfile;
import com.hishab.boardgame.mapper.ScoreMapper;
import com.hishab.boardgame.service.DiceService;
import com.hishab.boardgame.service.PlaygroundService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BoardGameApplicationTests {
    @InjectMocks
    PlaygroundService playgroundService;
    @Mock
    DiceService diceService;

    @BeforeEach
    void configure() {
        playgroundService.setDiceService(diceService);
        playgroundService.setListOfPlayer(new ArrayList<>());
        playgroundService.setMapOfPlayerToScore(new LinkedHashMap<>());
        playgroundService.setScoreMapper(new ScoreMapper());
        playgroundService.setPlayerCounter(0);
        playgroundService.setWinState(true);
        playgroundService.setPlayBackSpeed(Long.parseLong("0"));
    }

    @Test
    void playerWithTwoTeamMember() {
        playgroundService.addNewPlayer(new UserProfile().setName("Jon").setAge(28));
        playgroundService.addNewPlayer(new UserProfile().setName("Bob").setAge(28));
        playgroundService.setTotalScore(20);
        when(diceService.nextFromApi())
                .thenReturn(
                        3, 6, 4, 4, 3, 4,
                        1, 5, 1, 2, 3, 5, 6, 5, 4, 4
                        , 3, 3, 2, 1, 2, 6,
                        3, 4, 4, 4, 6, 6
                );
        playgroundService.play();
        Assertions.assertEquals(playgroundService.getWinnerProfile().getName(), "Bob");
    }

    @Test
    void gameWithThreeTeamMember() {
        playgroundService.addNewPlayer(new UserProfile().setName("Jim").setAge(28));
        playgroundService.addNewPlayer(new UserProfile().setName("Tom").setAge(28));
        playgroundService.addNewPlayer(new UserProfile().setName("Ben").setAge(28));
        playgroundService.setTotalScore(20);
        when(diceService.nextFromApi())
                .thenReturn(
                        3, 6, 4, 4, 3, 4, 1,
                        5, 1, 2, 3, 5, 6, 5, 4, 4
                        , 3, 3, 2, 1, 2, 6, 3, 4, 4, 4, 6, 6
                );
        playgroundService.play();
        Assertions.assertEquals(playgroundService.getWinnerProfile().getName(), "Ben");
    }

    @Test
    void gameWithFourTeamMember() {
        playgroundService.addNewPlayer(new UserProfile().setName("Job").setAge(28));
        playgroundService.addNewPlayer(new UserProfile().setName("Tom").setAge(28));
        playgroundService.addNewPlayer(new UserProfile().setName("Ben").setAge(28));
        playgroundService.addNewPlayer(new UserProfile().setName("Mike").setAge(28));
        playgroundService.setTotalScore(20);
        when(diceService.nextFromApi())
                .thenReturn(
                        3, 6, 4, 4, 3, 4, 1,
                        5, 1, 2, 3, 5, 6, 5, 4, 4
                        , 3, 3, 2, 1, 2, 6, 3, 4, 4, 4, 6, 6
                );
        playgroundService.play();
        Assertions.assertEquals(playgroundService.getWinnerProfile().getName(), "Mike");
    }
}
