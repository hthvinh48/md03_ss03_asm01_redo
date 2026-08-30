package com.example.asm01.repository;

import com.example.asm01.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>(List.of(
            new Enrollment(1, "Eddie", 2),
            new Enrollment(2, "Dylan", 1)
    ));

    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollments);
    }

    public Enrollment findById(long id) {
        return enrollments.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public Enrollment create(Enrollment enrollment) {
        enrollments.add(enrollment);
        return enrollment;
    }

    public Enrollment update(long id, Enrollment enrollment) {
        Enrollment currentEnrollment = findById(id);

        if (currentEnrollment == null) {
            return null;
        }

        currentEnrollment.setStudentName(enrollment.getStudentName());
        currentEnrollment.setCourseId(enrollment.getCourseId());
        return currentEnrollment;
    }

    public Enrollment deleteById(long id) {
        Enrollment currentEnrollment = findById(id);

        if (currentEnrollment == null) {
            return null;
        }

        enrollments.remove(currentEnrollment);
        return currentEnrollment;
    }
}
