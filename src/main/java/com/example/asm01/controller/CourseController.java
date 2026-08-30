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
        List<Course> courses = courseService.getAllCourses();

        ApiResponse<List<Course>> apiResponse = new ApiResponse<>(
                true, "fetched courses successfully", courses
        );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> findById(@PathVariable long id) {
        Course course = courseService.getCourseById(id);

        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(false, "course not found", null)
            );
        }

        return ResponseEntity.ok(new ApiResponse<>(true, "course found", course));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> insert(@RequestBody Course course) {
        Course newCourse = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(true, "course created", newCourse)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> update(@PathVariable long id, @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(id, course);

        if (updatedCourse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(false, "course not found", null)
            );
        }

        return ResponseEntity.ok(new ApiResponse<>(true, "course updated", updatedCourse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> delete(@PathVariable long id) {
        Course course = courseService.deleteCourseById(id);

        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(false, "course not found", null)
            );
        }

        return ResponseEntity.ok(new ApiResponse<>(true, "course deleted", course));
    }
}
