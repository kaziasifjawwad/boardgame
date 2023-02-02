package com.hishab.boardgame.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CurrentStateResponse {
    private String name;
    private int score;
}
