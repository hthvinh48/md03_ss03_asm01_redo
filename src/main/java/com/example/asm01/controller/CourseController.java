package com.example.asm01.controller;

import com.example.asm01.model.Course;
import com.example.asm01.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> findAll() {
        List<Course> courses = courseService.findAll();
        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<Course> insert(@RequestBody Course course) {
        Course newCourse = courseService.insert(course);
        return ResponseEntity.ok(newCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable long id, @RequestBody Course course) {
        Course updatedCourse = courseService.update(id, course);

        if (updatedCourse == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> delete(@PathVariable long id) {
        Course course = courseService.delete(id);

        if (course == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(course);
    }
}
