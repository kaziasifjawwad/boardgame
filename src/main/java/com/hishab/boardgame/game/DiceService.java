package com.hishab.boardgame.game;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

public class DiceService {
    RestTemplate restTemplate;

    public DiceService() {
        this.restTemplate = new RestTemplate();
    }

    public int nextFromApi() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Score> entity = new HttpEntity<Score>(headers);
        return Objects
                .requireNonNull(restTemplate.exchange
                        ("http://developer-test.hishab.io/api/v1/roll-dice",
                                HttpMethod.GET, entity, Score.class).getBody())
                .getScore();
    }

    public int nextDice() {
        return 1 + (int) (Math.random() * ((6 - 1) + 1));
    }
}
