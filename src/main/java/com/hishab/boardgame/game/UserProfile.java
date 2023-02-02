package com.hishab.boardgame.game;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserProfile {
    private String name;
    private int age;

    public UserProfile setAge(int age) {
        if (age < 0) throw new RuntimeException();
        this.age = age;
        return this;
    }
}
