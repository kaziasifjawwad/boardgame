package com.hishab.boardgame.service;

import com.hishab.boardgame.domain.Score;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

public class DiceService {
    RestTemplate restTemplate;

    public DiceService() {
        this.restTemplate = new RestTemplate();
    }

    public int nextFromApi() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Score> entity = new HttpEntity<>(headers);
        return Objects
                .requireNonNull(restTemplate.exchange
                        ("http://developer-test.hishab.io/api/v1/roll-dice",
                                HttpMethod.GET, entity, Score.class).getBody())
                .getScore();
    }
}
