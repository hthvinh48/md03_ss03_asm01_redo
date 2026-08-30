package com.example.asm01.repository;

import com.example.asm01.model.Course;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
public class CourseRepository {
    private final List<Course> courses = new ArrayList<>(List.of(
            new Course(1, "Java Fundamental - JDBC", "Closing", 1),
            new Course(2, "Java Spring Web - Restful API", "Opening", 2)
    ));

    public Course findById(long id) {
        return courses.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public Course insert(Course course) {
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

    public Course delete(long id) {
        Course currentCourse = findById(id);

        if (currentCourse == null) {
            return null;
        }

        courses.remove(currentCourse);
        return currentCourse;
    }
}
