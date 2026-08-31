package com.example.asm01.controller;

import com.example.asm01.dto.EnrollCourseRequest;
import com.example.asm01.dto.EnrollmentDetail;
import com.example.asm01.model.Enrollment;
import com.example.asm01.response.ApiResponse;
import com.example.asm01.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> findAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "fetched data successfully",
                        enrollmentService.getAllEnrollments()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> findById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "fetched data successfully",
                            enrollmentService.getEnrollmentById(id)
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    )
            );
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> save(@RequestBody Enrollment enrollment) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "create new enrollment successfully",
                        enrollmentService.createEnrollment(enrollment)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> update(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "update enrollment successfully",
                            enrollmentService.updateEnrollment(id, enrollment)
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    )
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "delete enrollment successfully",
                            enrollmentService.deleteEnrollmentById(id)
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    )
            );
        }
    }

    @PostMapping("/enroll-course")
    public ResponseEntity<ApiResponse<EnrollmentDetail>> enrollCourse
            (@RequestBody EnrollCourseRequest enrollCourseRequest)
    {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Enrollment successfully",
                            enrollmentService.enrollCourse(enrollCourseRequest)
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    )
            );
        }
    }
}
