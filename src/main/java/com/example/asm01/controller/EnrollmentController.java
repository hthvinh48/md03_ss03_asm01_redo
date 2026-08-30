package com.example.asm01.controller;

import com.example.asm01.model.Enrollment;
import com.example.asm01.service.EnrollmentService;
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
    public ResponseEntity<List<Enrollment>> findAll() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(enrollments);
    }

    @PostMapping
    public ResponseEntity<Enrollment> save(@RequestBody Enrollment enrollment) {
        Enrollment result = enrollmentService.createEnrollment(enrollment);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> update(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        Enrollment result = enrollmentService.updateEnrollment(id, enrollment);

        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Enrollment> delete(@PathVariable Long id) {
        Enrollment result = enrollmentService.deleteEnrollmentById(id);

        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
