package com.example.asm01.controller;

import com.example.asm01.dto.EnrollmentDetail;
import com.example.asm01.model.StudentEnrollment;
import com.example.asm01.dto.response.ApiResponse;
import com.example.asm01.service.EnrollmentService;
import com.example.asm01.service.StudentEnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final StudentEnrollmentService studentEnrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService, StudentEnrollmentService studentEnrollmentService) {
        this.enrollmentService = enrollmentService;
        this.studentEnrollmentService = studentEnrollmentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EnrollmentDetail>>> findAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "fetched data successfully",
                        studentEnrollmentService.findAllStudentEnrollments()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentEnrollment>> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "fetched data successfully",
                            enrollmentService.findEnrollmentById(id)
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
    public ResponseEntity<ApiResponse<Void>> createStudentEnrollment(
            @RequestBody StudentEnrollment studentEnrollment
    ) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            studentEnrollmentService.enrollStudent(
                                studentEnrollment.getStudent().getId(),
                                studentEnrollment.getCourse().getId()
                            ),
                            null
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse<>(false, e.getMessage(), null)
            );
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentEnrollment>> update(
            @PathVariable Long id,
            @RequestBody StudentEnrollment studentEnrollment
    ) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "update enrollment successfully",
                            enrollmentService.updateEnrollment(id, studentEnrollment)
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
    public ResponseEntity<ApiResponse<StudentEnrollment>> delete(@PathVariable Long id) {
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
            (@RequestBody StudentEnrollment studentEnrollment)
    {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Enrollment successfully",
                            enrollmentService.enrollCourse(studentEnrollment)
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
