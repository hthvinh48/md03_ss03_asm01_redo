package com.example.asm01.repository;

import com.example.asm01.model.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InstructorRepository {
    private final List<Instructor> instructors = new ArrayList<>(List.of(
            new Instructor(1, "Cristiano Ronaldo", "cr7@gmail.com"),
            new Instructor(2, "David Beckham", "david@gmail.com")
    ));

    public List<Instructor> findAll() {
        return new ArrayList<>(instructors);
    }

    public Instructor findById(long id) {
        return instructors.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    public Instructor insert(Instructor instructor) {
        instructors.add(instructor);
        return instructor;
    }

    public Instructor update(long id, Instructor instructor) {
        Instructor currentInstructor = findById(id);

        if (currentInstructor == null) {
            return null;
        }

        currentInstructor.setName(instructor.getName());
        currentInstructor.setEmail(instructor.getEmail());
        return currentInstructor;
    }

    public Instructor delete(long id) {
        Instructor currentInstructor = findById(id);

        if (currentInstructor == null) {
            return null;
        }

        instructors.remove(currentInstructor);
        return currentInstructor;
    }
}
