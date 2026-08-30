package com.example.asm01.repository;

import com.example.asm01.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {
    private final List<Course> courses = new ArrayList<>(List.of(
            new Course(1, "Java Fundamental - JDBC", "Closing", 1),
            new Course(2, "Java Spring Web - Restful API", "Opening", 2)
    ));

    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    public Course findById(long id) {
        return courses.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public Course create(Course course) {
        courses.add(course);
        return course;
    }

    public Course update(long id, Course course) {
        Course currentCourse = findById(id);

        if (currentCourse == null) {
            return null;
        }

        currentCourse.setTitle(course.getTitle());
        currentCourse.setStatus(course.getStatus());
        currentCourse.setInstructorId(course.getInstructorId());
        return currentCourse;
    }

    public Course deleteById(long id) {
        Course currentCourse = findById(id);

        if (currentCourse == null) {
            return null;
        }

        courses.remove(currentCourse);
        return currentCourse;
    }
}
