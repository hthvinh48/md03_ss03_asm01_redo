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

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public long findMaxId() {
        List<Course> courses = this.findAll();
        return courses.stream().mapToLong(Course::getId).max().orElse(0);
    }

    public Course insert(Course course) {
        course.setId(findMaxId() + 1);
        return courseRepository.insert(course);
    }

    public Course update(long id, Course course) {
        return courseRepository.update(id, course);
    }

    public Course delete(long id) {
        return courseRepository.delete(id);
    }
}
