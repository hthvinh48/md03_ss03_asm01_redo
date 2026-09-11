package com.example.asm01.service;

import com.example.asm01.dto.EnrollmentDetail;
import com.example.asm01.model.Course;
import com.example.asm01.model.Student;
import com.example.asm01.model.StudentEnrollment;
import com.example.asm01.repository.CourseRepository;
import com.example.asm01.repository.StudentEnrollmentRepository;
import com.example.asm01.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentEnrollmentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentEnrollmentRepository studentEnrollmentRepository;

    public StudentEnrollmentService(
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            StudentEnrollmentRepository studentEnrollmentRepository
    ) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentEnrollmentRepository = studentEnrollmentRepository;
    }

    public List<EnrollmentDetail> findAllStudentEnrollments() {
        List<StudentEnrollment> studentEnrollments = studentEnrollmentRepository.findAll();
        List<EnrollmentDetail> enrollmentDetails = new ArrayList<>();

        studentEnrollments.forEach(studentEnrollment -> {
            EnrollmentDetail enrollmentDetail = new EnrollmentDetail();
            enrollmentDetail.setId(studentEnrollment.getId());
            enrollmentDetail.setStudentName(studentEnrollment.getStudent().getName());
            enrollmentDetail.setCourseName(studentEnrollment.getCourse().getTitle());
            enrollmentDetails.add(enrollmentDetail);
        });

        return enrollmentDetails;
    }

    public String enrollStudent(Long enrollmentId, Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new RuntimeException("Student with id: " + studentId + " not found")
        );

        Course course = courseRepository.findById(enrollmentId).orElseThrow(
                () -> new RuntimeException("Course with id: " + enrollmentId + " not found")
        );

        StudentEnrollment studentEnrollment = new StudentEnrollment();
        studentEnrollment.setStudent(student);
        studentEnrollment.setCourse(course);
        studentEnrollmentRepository.save(studentEnrollment);
        return "Enrolled course successfully";
    }
}
