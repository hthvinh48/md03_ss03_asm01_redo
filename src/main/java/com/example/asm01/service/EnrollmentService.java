package com.example.asm01.service;

import com.example.asm01.dto.EnrollCourseRequest;
import com.example.asm01.dto.EnrollmentDetail;
import com.example.asm01.model.Course;
import com.example.asm01.model.StudentEnrollment;
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

    public List<StudentEnrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public StudentEnrollment getEnrollmentById(long id) {
        return enrollmentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Enrollment with id " + id + " not found.")
        );
    }

    public long findMaxId() {
        return getAllEnrollments().stream()
                .mapToLong(StudentEnrollment::getId)
                .max()
                .orElse(0);
    }

    public StudentEnrollment createEnrollment(StudentEnrollment studentEnrollment) {
        studentEnrollment.setId(findMaxId() + 1);
        return enrollmentRepository.create(studentEnrollment);
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

        StudentEnrollment saveStudentEnrollment = enrollmentRepository.create(
                new StudentEnrollment(findMaxId() + 1, enrollment.getStudentName(), enrollment.getCourseId())
        );

        return new EnrollmentDetail(saveStudentEnrollment.getId(), saveStudentEnrollment.getStudentName(), existing);
    }

    public StudentEnrollment updateEnrollment(long id, StudentEnrollment studentEnrollment) {
        return enrollmentRepository.update(id, studentEnrollment);
    }

    public StudentEnrollment deleteEnrollmentById(long id) {
        return enrollmentRepository.deleteById(id);
    }
}
