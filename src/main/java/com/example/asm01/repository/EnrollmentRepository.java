package com.example.asm01.repository;

import com.example.asm01.model.StudentEnrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EnrollmentRepository {
    private final List<StudentEnrollment> studentEnrollments = new ArrayList<>(List.of(
            new StudentEnrollment(1, "Eddie", 2),
            new StudentEnrollment(2, "Dylan", 1)
    ));

    public List<StudentEnrollment> findAll() {
        return new ArrayList<>(studentEnrollments);
    }

    public Optional<StudentEnrollment> findById(long id) {
        return studentEnrollments.stream().filter(e -> e.getId() == id).findFirst();
    }

    public StudentEnrollment create(StudentEnrollment studentEnrollment) {
        studentEnrollments.add(studentEnrollment);
        return studentEnrollment;
    }

    public StudentEnrollment update(long id, StudentEnrollment studentEnrollment) {
        StudentEnrollment currentStudentEnrollment = findById(id).orElseThrow(() ->
                new RuntimeException("Enrollment with id " + id + " not found.")
        );

        currentStudentEnrollment.setStudentName(studentEnrollment.getStudentName());
        currentStudentEnrollment.setCourseId(studentEnrollment.getCourseId());
        return currentStudentEnrollment;
    }

    public StudentEnrollment deleteById(long id) {
        StudentEnrollment currentStudentEnrollment = findById(id).orElseThrow(() ->
                new RuntimeException("Enrollment with id " + id + " not found.")
        );

        studentEnrollments.remove(currentStudentEnrollment);
        return currentStudentEnrollment;
    }
}
