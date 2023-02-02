package com.hishab.boardgame.exeptionhandler;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ErrorMessage {
    private OffsetDateTime timestamp;
    private String message;
    private String path;


    public ErrorMessage(String message, String path) {
        this.timestamp = OffsetDateTime.now();
        this.message = message;
        this.path = path;
    }

}