package com.example.asm01.dto;

import com.example.asm01.model.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnrollmentDetail {
    long id;
    String studentName;
    Course course;
}
