package com.example.asm01.controller;

import com.example.asm01.model.Course;
import com.example.asm01.response.ApiResponse;
import com.example.asm01.service.CourseService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse<List<Course>>> findAll() {
        List<Course> courses = courseService.findAllCourses();

        ApiResponse<List<Course>> apiResponse = new ApiResponse<>(
                true, "fetched courses successfully", courses
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true, "fetched course successfully", courseService.findCourseById(id)
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(
                    false, e.getMessage(), null
            ));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> insert(@RequestBody Course course) {
        Course newCourse = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(true, "course created", newCourse)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> update(@PathVariable Long id, @RequestBody Course course) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true, "updated course successfully", courseService.updateCourse(id, course)
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(
                    false, e.getMessage(), null
            ));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true, "deleted course successfully", courseService.deleteCourseById(id)
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(
                    false, e.getMessage(), null
            ));
        }
    }
}
