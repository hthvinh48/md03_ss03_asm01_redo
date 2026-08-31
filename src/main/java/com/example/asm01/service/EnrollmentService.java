package com.example.asm01.service;

import com.example.asm01.dto.EnrollCourseRequest;
import com.example.asm01.dto.EnrollmentDetail;
import com.example.asm01.model.Course;
import com.example.asm01.model.Enrollment;
import com.example.asm01.repository.CourseRepository;
import com.example.asm01.repository.EnrollmentRepository;
import com.example.asm01.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            CourseRepository courseRepository,
            InstructorRepository instructorRepository
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
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

    public EnrollmentDetail enrollCourse(EnrollCourseRequest enrollment) {
        Course existing = courseRepository.findById(enrollment.getCourseId()).orElseThrow(() ->
                new RuntimeException("course not found.")
        );

        if (!"ACTIVE".equals(existing.getStatus())) {
            throw new RuntimeException("Cannot enroll in an inactive course");
        }

        instructorRepository.findById(existing.getInstructorId()).orElseThrow(() ->
                new RuntimeException("instructor not found.")
        );

        Enrollment saveEnrollment = enrollmentRepository.create(
                new Enrollment(findMaxId() + 1, enrollment.getStudentName(), enrollment.getCourseId())
        );

        return new EnrollmentDetail(saveEnrollment.getId(), saveEnrollment.getStudentName(), existing);
    }

    public Enrollment updateEnrollment(long id, Enrollment enrollment) {
        return enrollmentRepository.update(id, enrollment);
    }

    public Enrollment deleteEnrollmentById(long id) {
        return enrollmentRepository.deleteById(id);
    }
}
