package com.example.asm01.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstructorUpdateRequest {
    private String name;
    private String email;
    private List<Long> courseIds;
}
