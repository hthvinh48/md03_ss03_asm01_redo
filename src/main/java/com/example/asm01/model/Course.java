package com.example.asm01.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course {
    private long id;
    private String title;
    private String status;
    private long instructorId;

    public Course(String title, String status, long instructorId) {
        this.title = title;
        this.status = status;
        this.instructorId = instructorId;
    }
}
