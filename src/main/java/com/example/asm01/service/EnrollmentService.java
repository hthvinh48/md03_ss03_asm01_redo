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

    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    public long findMaxId() {
        return findAll().stream()
                .mapToLong(Enrollment::getId)
                .max()
                .orElse(0);
    }

    public Enrollment insert(Enrollment enrollment) {
        enrollment.setId(findMaxId() + 1);
        return enrollmentRepository.insert(enrollment);
    }

    public Enrollment update(long id, Enrollment enrollment) {
        return enrollmentRepository.update(id, enrollment);
    }

    public Enrollment delete(long id) {
        return enrollmentRepository.delete(id);
    }
}
