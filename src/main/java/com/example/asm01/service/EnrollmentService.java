package com.example.asm01.service;

import com.example.asm01.dto.EnrollCourseRequest;
import com.example.asm01.dto.EnrollmentDetail;
import com.example.asm01.model.Course;
import com.example.asm01.model.CourseStatus;
import com.example.asm01.model.StudentEnrollment;
import com.example.asm01.repository.CourseRepository;
import com.example.asm01.repository.StudentEnrollmentRepository;
import com.example.asm01.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public EnrollmentService(
            StudentEnrollmentRepository studentEnrollmentRepository,
            CourseRepository courseRepository,
            InstructorRepository instructorRepository
    ) {
        this.studentEnrollmentRepository = studentEnrollmentRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    public List<StudentEnrollment> findAllEnrollments() {
        return studentEnrollmentRepository.findAll();
    }

    public StudentEnrollment findEnrollmentById(Long id) {
        return studentEnrollmentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Enrollment with id " + id + " not found.")
        );
    }

    public StudentEnrollment createEnrollment(StudentEnrollment studentEnrollment) {
        return studentEnrollmentRepository.save(studentEnrollment);
    }

    public EnrollmentDetail enrollCourse(StudentEnrollment enrollment) {
        Course existing = courseRepository.findById(enrollment.getCourse().getId()).orElseThrow(() ->
                new RuntimeException("course not found.")
        );

        if (CourseStatus.INACTIVE.equals(existing.getStatus())) {
            throw new RuntimeException("Cannot enroll in an inactive course");
        }

        instructorRepository.findById(existing.getInstructor().getId()).orElseThrow(() ->
                new RuntimeException("instructor not found.")
        );

        StudentEnrollment saveStudentEnrollment = studentEnrollmentRepository.save(enrollment);

        return new EnrollmentDetail(
                saveStudentEnrollment.getId(),
                saveStudentEnrollment.getStudent().getName(),
                existing
        );
    }

    public StudentEnrollment updateEnrollment(Long id, StudentEnrollment studentEnrollment) {
        StudentEnrollment existing = studentEnrollmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Enrollment with id " + id + " not found.")
        );

        existing.setCourse(studentEnrollment.getCourse());
        existing.setStudent(studentEnrollment.getStudent());
        return studentEnrollmentRepository.save(existing);
    }

    public StudentEnrollment deleteEnrollmentById(long id) {
        StudentEnrollment existing = studentEnrollmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Enrollment with id " + id + " not found.")
        );

        studentEnrollmentRepository.delete(existing);
        return existing;
    }
}
