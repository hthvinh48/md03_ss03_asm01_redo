package com.example.asm01.repository;

import com.example.asm01.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {
    private final List<Course> courses = new ArrayList<>(List.of(
            new Course(1, "Java Fundamental - JDBC", "Closing", 1),
            new Course(2, "Java Spring Web - Restful API", "Opening", 2)
    ));

    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    public Optional<Course> findById(long id) {
        return courses.stream().filter(c -> c.getId() == id).findFirst();
    }

    public Course create(Course course) {
        courses.add(course);
        return course;
    }

    public Course update(long id, Course course) {
        Course existing = findById(id).orElseThrow(() ->
                new RuntimeException("Course with id: " + id + " not found")
        );

        existing.setTitle(course.getTitle());
        existing.setStatus(course.getStatus());
        existing.setInstructorId(course.getInstructorId());
        return existing;
    }

    public Course deleteById(long id) {
        Course existing = findById(id).orElseThrow(() ->
                new RuntimeException("Course with id: " + id + " not found")
        );

        courses.remove(existing);
        return existing;
    }
}
