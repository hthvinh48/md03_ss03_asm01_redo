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
        List<Enrollment> enrollments = enrollmentService.findAll();
        return ResponseEntity.ok(enrollments);
    }

    @PostMapping
    public ResponseEntity<Enrollment> save(@RequestBody Enrollment enrollment) {
        Enrollment result = enrollmentService.insert(enrollment);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> update(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        Enrollment result = enrollmentService.update(id, enrollment);

        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Enrollment> delete(@PathVariable Long id) {
        Enrollment result = enrollmentService.delete(id);

        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
