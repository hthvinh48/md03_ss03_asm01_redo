package com.example.asm01.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Instructor {
    private long id;
    private String name;
    private String email;

    public Instructor(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
