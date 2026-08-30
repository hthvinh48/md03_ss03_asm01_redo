package com.example.asm01.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Enrollment {
    private long id;
    private String studentName;
    private long courseId;

    public Enrollment(String studentName, long courseId) {
        this.studentName = studentName;
        this.courseId = courseId;
    }
}
