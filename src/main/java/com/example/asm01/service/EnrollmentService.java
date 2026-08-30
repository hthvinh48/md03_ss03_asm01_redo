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
        return enrollmentRepository.getEnrollments();
    }

    public long findMaxId() {
        return findAll().stream()
                .mapToLong(Enrollment::getId)
                .max()
                .orElse(1);
    }

    public Enrollment insert(Enrollment enrollment) {
        enrollment.setId(findMaxId() + 1);
        enrollment.setStudentName(enrollment.getStudentName());
        enrollment.setCourseId(enrollment.getCourseId());
        return enrollmentRepository.insert(enrollment);
    }

    public Enrollment update(long id, Enrollment enrollment) {
        Enrollment currentEnrollment = enrollmentRepository.findById(id);

        if (currentEnrollment == null) {
            return null;
        }

        currentEnrollment.setStudentName(enrollment.getStudentName());
        currentEnrollment.setCourseId(enrollment.getCourseId());
        return currentEnrollment;
    }

    public Enrollment delete(long id) {
        Enrollment currentEnrollment = enrollmentRepository.findById(id);

        if (currentEnrollment == null) {
            return null;
        }

        enrollmentRepository.delete(currentEnrollment);
        return currentEnrollment;
    }
}
