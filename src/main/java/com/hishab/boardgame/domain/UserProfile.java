package com.hishab.boardgame.domain;

import com.hishab.boardgame.exeptionhandler.ExtendedRuntimeException;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserProfile {
    private String name;
    private int age;

    public UserProfile setAge(int age) {
        if (age <= 0) throw new ExtendedRuntimeException("Age should be greater than zero.");
        this.age = age;
        return this;
    }
}
