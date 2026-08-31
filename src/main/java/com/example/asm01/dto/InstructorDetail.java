package com.example.asm01.dto;

import com.example.asm01.model.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InstructorDetail {
    private Long id;
    private String name;
    private String email;
    private List<Course> course;
}
