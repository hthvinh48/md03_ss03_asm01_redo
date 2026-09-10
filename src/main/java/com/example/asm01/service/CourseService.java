package com.example.asm01.service;

import com.example.asm01.model.Course;
import com.example.asm01.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Course with id " + id + " not found!")
        );
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course course) {
        Course existingCourse = courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Course with id " + id + " not found!")
        );

        existingCourse.setTitle(course.getTitle());
        existingCourse.setStatus(course.getStatus());
        existingCourse.setInstructor(course.getInstructor());

        return courseRepository.save(existingCourse);
    }

    public Course deleteCourseById(Long id) {
        Course existingCourse = courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Course with id " + id + " not found!")
        );

        courseRepository.delete(existingCourse);
        return existingCourse;
    }
}
