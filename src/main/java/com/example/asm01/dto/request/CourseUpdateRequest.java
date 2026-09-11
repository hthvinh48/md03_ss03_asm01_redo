package com.example.asm01.dto.request;

import com.example.asm01.model.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseUpdateRequest {
    private String title;
    private CourseStatus status;
    private Long instructorId;
}
