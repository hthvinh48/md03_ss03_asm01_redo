package com.example.asm01.service;

import com.example.asm01.dto.InstructorDetail;
import com.example.asm01.model.Course;
import com.example.asm01.model.Enrollment;
import com.example.asm01.model.Instructor;
import com.example.asm01.repository.CourseRepository;
import com.example.asm01.repository.EnrollmentRepository;
import com.example.asm01.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public InstructorService(
            InstructorRepository instructorRepository,
            CourseRepository courseRepository,
            EnrollmentRepository enrollmentRepository
    ) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor getInstructorById(long id) {
        return instructorRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Instructor with id " + id + " not found")
        );
    }

    public List<InstructorDetail> getInstructorDetail() {
        List<Instructor> instructors = instructorRepository.findAll();
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        List<Course> courses = courseRepository.findAll();

        return instructors.stream().map(
                instructor -> {
                    List<Course> validCourse = courses.stream()
                            .filter(course ->
                                    Objects.equals(
                                            course.getInstructorId(),
                                            instructor.getId()
                                    )
                            )
                            .filter(course -> "ACTIVE".equals(course.getStatus()))
                            .filter(course -> enrollments.stream().anyMatch(enrollment ->
                                    Objects.equals(
                                            course.getId(),
                                            enrollment.getCourseId()
                                        )
                                    )
                            ).toList();

                    return new InstructorDetail(
                            instructor.getId(),
                            instructor.getName(),
                            instructor.getEmail(),
                            validCourse
                    );
                }
        ).toList();
    }

    public long findMaxId() {
        List<Instructor> instructors = this.getAllInstructors();
        return instructors.stream().mapToLong(Instructor::getId).max().orElse(0);
    }

    public Instructor createInstructor(Instructor instructor) {
        instructor.setId(findMaxId() + 1);
        return instructorRepository.create(instructor);
    }

    public Instructor updateInstructor(long id, Instructor instructor) {
        return instructorRepository.update(id, instructor);
    }

    public Instructor deleteInstructorById(long id) {
        return instructorRepository.deleteById(id);
    }
}
