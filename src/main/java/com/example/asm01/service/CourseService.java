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

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(long id) {
        return courseRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Course with id " + id + " not found!")
        );
    }

    public long findMaxId() {
        List<Course> courses = this.getAllCourses();
        return courses.stream().mapToLong(Course::getId).max().orElse(0);
    }

    public Course createCourse(Course course) {
        course.setId(findMaxId() + 1);
        return courseRepository.create(course);
    }

    public Course updateCourse(long id, Course course) {
        return courseRepository.update(id, course);
    }

    public Course deleteCourseById(long id) {
        return courseRepository.deleteById(id);
    }
}
