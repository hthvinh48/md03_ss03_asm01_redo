package com.example.asm01.service;

import com.example.asm01.model.Enrollment;
import com.example.asm01.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(long id) {
        return enrollmentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Enrollment with id " + id + " not found.")
        );
    }

    public long findMaxId() {
        return getAllEnrollments().stream()
                .mapToLong(Enrollment::getId)
                .max()
                .orElse(0);
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        enrollment.setId(findMaxId() + 1);
        return enrollmentRepository.create(enrollment);
    }

    public Enrollment updateEnrollment(long id, Enrollment enrollment) {
        return enrollmentRepository.update(id, enrollment);
    }

    public Enrollment deleteEnrollmentById(long id) {
        return enrollmentRepository.deleteById(id);
    }
}
