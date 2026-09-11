package com.example.asm01.service;

import com.example.asm01.dto.request.InstructorCreateRequest;
import com.example.asm01.dto.InstructorDetail;
import com.example.asm01.model.Course;
import com.example.asm01.model.CourseStatus;
import com.example.asm01.model.StudentEnrollment;
import com.example.asm01.model.Instructor;
import com.example.asm01.repository.CourseRepository;
import com.example.asm01.repository.StudentEnrollmentRepository;
import com.example.asm01.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final StudentEnrollmentRepository studentEnrollmentRepository;

    public InstructorService(
            InstructorRepository instructorRepository,
            CourseRepository courseRepository,
            StudentEnrollmentRepository studentEnrollmentRepository
    ) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
        this.studentEnrollmentRepository = studentEnrollmentRepository;
    }

    public List<Instructor> findAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor findInstructorById(Long id) {
        return instructorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Instructor with id " + id + " not found")
        );
    }

    public List<InstructorDetail> getInstructorDetail() {
        List<Instructor> instructors = instructorRepository.findAll();
        List<StudentEnrollment> studentEnrollments = studentEnrollmentRepository.findAll();
        List<Course> courses = courseRepository.findAll();

        return instructors.stream().map(
                instructor -> {
                    List<Course> validCourse = courses.stream()
                            .filter(course ->
                                    Objects.equals(
                                            course.getInstructor().getId(),
                                            instructor.getId()
                                    )
                            )
                            .filter(course -> CourseStatus.ACTIVE.equals(course.getStatus()))
                            .filter(course -> studentEnrollments.stream().anyMatch(studentEnrollment ->
                                    Objects.equals(
                                            course.getId(),
                                            studentEnrollment.getCourse().getId()
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

    public Instructor createInstructor(InstructorCreateRequest req) {
        Instructor instructor = new Instructor();
        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());
        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructor(Long id, Instructor instructor) {
        Instructor existingInstructor = instructorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Instructor with id " + id + " not found")
        );
        existingInstructor.setName(instructor.getName());
        existingInstructor.setEmail(instructor.getEmail());
        return instructorRepository.save(existingInstructor);
    }

    public Instructor deleteInstructorById(Long id) {
        Instructor existingInstructor = instructorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Instructor with id " + id + " not found")
        );
        instructorRepository.delete(existingInstructor);
        return existingInstructor;
    }
}
